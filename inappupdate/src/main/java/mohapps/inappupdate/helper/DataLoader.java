package mohapps.inappupdate.helper;

import com.google.gson.Gson;
import com.haohaohu.cachemanage.CacheUtil;

import mohapps.inappupdate.entity.AppUpdateInfo;


public class DataLoader{

public static AppUpdateInfo sideloadAppUpdateInfo() {
       return new Gson().fromJson(CacheUtil.get(Constants.APP_UPDATE_INFO_CACHE_KEY, true), AppUpdateInfo.class);
    }

    public static void saveAppUpdateInfo(int updateAvailability, int availableVersionCode, boolean isUpdateTypeAllowedIMMEDIATE, boolean isUpdateTypeAllowedFLEXIBLE, String packageName, int installStatus) {

        AppUpdateInfo appUpdateInfo= new AppUpdateInfo();
        appUpdateInfo.setAvailableVersionCode(availableVersionCode);
        appUpdateInfo.setPackageName(packageName);
        appUpdateInfo.setUpdateAvailability(updateAvailability);
        appUpdateInfo.setUpdateTypeAllowedFLEXIBLE(isUpdateTypeAllowedFLEXIBLE);
        appUpdateInfo.setUpdateTypeAllowedIMMEDIATE(isUpdateTypeAllowedIMMEDIATE);
        appUpdateInfo.setInstallStatus(installStatus);
        CacheUtil.put(Constants.APP_UPDATE_INFO_CACHE_KEY, new Gson().toJson(appUpdateInfo),true);
    }
    public static void deleteAppUpdateInfo() {
        try {
            CacheUtil.clear(Constants.APP_UPDATE_INFO_CACHE_KEY);
        } catch (Exception e) {e.printStackTrace();
        }
    }

}