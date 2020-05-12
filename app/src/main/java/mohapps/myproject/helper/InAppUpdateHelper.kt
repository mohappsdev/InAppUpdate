package mohapps.myproject.helper

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.IntentSender.SendIntentException
import android.view.View
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType.FLEXIBLE
import com.google.android.play.core.install.model.AppUpdateType.IMMEDIATE
import com.google.android.play.core.install.model.InstallStatus.*
import com.google.android.play.core.install.model.UpdateAvailability
import mohapps.myproject.activity.ForceUpdateActivity
import mohapps.myproject.helper.Constants.IN_APP_UPDATE

class InAppUpdateHelper {
    private var appUpdateManager: AppUpdateManager? = null

    fun handleInAppUpdate(context: Context?, appUpdateType: Int, launchedByUser: Boolean) {

        if (appUpdateManager == null) {
            appUpdateManager = AppUpdateManagerFactory.create(context)
        }

        appUpdateManager?.appUpdateInfo?.addOnSuccessListener { appUpdateInfo: AppUpdateInfo ->

            DataLoader.saveAppUpdateInfo(appUpdateInfo.updateAvailability(),
                    appUpdateInfo.availableVersionCode(),
                    appUpdateInfo.isUpdateTypeAllowed(IMMEDIATE),
                    appUpdateInfo.isUpdateTypeAllowed(FLEXIBLE),
                    appUpdateInfo.packageName(),
                    appUpdateInfo.installStatus())

            when (appUpdateInfo.updateAvailability()) {

                UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS -> {
                    // If an in-app update is already running, resume the update.
                    try {
                        appUpdateManager?.startUpdateFlowForResult(appUpdateInfo, appUpdateType, context as Activity?, IN_APP_UPDATE)
                    } catch (e: SendIntentException) {
                        e.printStackTrace()
                    }
                }

                UpdateAvailability.UPDATE_AVAILABLE -> {
                    if (appUpdateInfo.isUpdateTypeAllowed(appUpdateType) && appUpdateInfo.installStatus() != DOWNLOADED && appUpdateInfo.installStatus() != DOWNLOADING) {
                        if (appUpdateType == IMMEDIATE || launchedByUser) {
                            appUpdateManager?.startUpdateFlowForResult(appUpdateInfo, appUpdateType, context as Activity?, IN_APP_UPDATE)
                        } else
                            if (UpdateHelper.isForceUpdateNeeded(UpdateHelper.getVersionFromVersionCode(appUpdateInfo.availableVersionCode()))) {
                                context?.startActivity(Intent(context, ForceUpdateActivity::class.java))
                            }
                    }
                }
                UpdateAvailability.UPDATE_NOT_AVAILABLE -> {
                }
                UpdateAvailability.UNKNOWN -> {
                }
            }

        }
    }

    fun loadInAppUpdate(context: Context, layout_main: View?, button_in_app_update: View?) {
        var appUpdateInfo: mohapps.myproject.helper.entity.AppUpdateInfo? = null
        try {
            appUpdateInfo = DataLoader.sideloadAppUpdateInfo()
            if (appUpdateInfo != null
                    && UpdateHelper.isPlayStoreVersionBigger(UpdateHelper.getVersionFromVersionCode(appUpdateInfo.availableVersionCode), UpdateHelper.getInstalledVersion(context))
                    && appUpdateInfo.installStatus != DOWNLOADED && appUpdateInfo.installStatus != DOWNLOADING && appUpdateInfo.installStatus != PENDING) {


                button_in_app_update?.visibility = View.VISIBLE
                button_in_app_update?.setOnClickListener {
                    handleInAppUpdate(context, FLEXIBLE, true)
                }
            } else {
                button_in_app_update?.visibility = View.GONE
            }
        } catch (ignore: Exception) {
            button_in_app_update?.visibility = View.GONE
        }
        if (appUpdateInfo?.installStatus == DOWNLOADED) {
            SnackbarMaker.makeInAppUpdateSnackBar(context, layout_main, true, appUpdateManager)
        }
    }
}