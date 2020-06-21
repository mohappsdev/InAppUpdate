package mohapps.myproject.helper

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.IntentSender.SendIntentException
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.view.View
import androidx.core.content.pm.PackageInfoCompat
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType.FLEXIBLE
import com.google.android.play.core.install.model.AppUpdateType.IMMEDIATE
import com.google.android.play.core.install.model.InstallStatus.*
import com.google.android.play.core.install.model.UpdateAvailability
import mohapps.myproject.activity.ForceUpdateActivity
import mohapps.myproject.helper.Constants.IN_APP_UPDATE
import kotlin.math.pow

class InAppUpdateHelper(private var forceUpdateStrategyConfig: ForceUpdateStrategyConfig?) {
    private var appUpdateManager: AppUpdateManager? = null

    fun handleInAppUpdate(context: Context, appUpdateType: Int, launchedByUser: Boolean) {

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
                            if (isForceUpdateNeeded(context, appUpdateInfo.availableVersionCode())) {
                                context.startActivity(Intent(context, ForceUpdateActivity::class.java))
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
                    && appUpdateInfo.availableVersionCode > getInstalledVersionCode(context)
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

    private fun getInstalledVersionCode(context: Context): Long {
        val pInfo: PackageInfo
        return try {
            pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            PackageInfoCompat.getLongVersionCode(pInfo)
        } catch (e: PackageManager.NameNotFoundException) {
            -1
        }
    }

    private fun isForceUpdateNeeded(context: Context, availableVersionCode: Int): Boolean {
        //we assume availableVersionCode is bigger than installedVersion based on where we call this
        //consider this when calling
        if (forceUpdateStrategyConfig == null || forceUpdateStrategyConfig!!.forceUpdateStrategyList.isEmpty()) {
            return false
        }
        for (forceUpdateStrategy in forceUpdateStrategyConfig!!.forceUpdateStrategyList) {
            if (forceUpdateStrategy == ForceUpdateStrategy.LAST_DIGIT) {
                if (forceUpdateStrategyConfig!!.endsWith in 0..availableVersionCode) {
                    val len = forceUpdateStrategyConfig!!.endsWith.toString().length
                    if (availableVersionCode % 10.0.pow(len).toLong() == forceUpdateStrategyConfig!!.endsWith) {
                        return true
                    }
                }
            } else if (forceUpdateStrategy == ForceUpdateStrategy.MAJOR_CHANGE) {
                if (availableVersionCode.toString().length > getInstalledVersionCode(context).toString().length) {
                    return true
                } else {
                    if (forceUpdateStrategyConfig!!.majorLength in 1..availableVersionCode.toString().length) {
                        if (getMajor(availableVersionCode, forceUpdateStrategyConfig!!.majorLength) > getMajor(getInstalledVersionCode(context).toInt(), forceUpdateStrategyConfig!!.majorLength)) {
                            return true
                        }
                    }
                }
            }
        }
        return false
    }
    private fun getMajor(versionCode: Int, majorLength: Int): Long{
        return versionCode / 10.0.pow(versionCode.toString().length - majorLength).toLong()
    }
}