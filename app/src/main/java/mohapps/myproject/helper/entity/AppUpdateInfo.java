package mohapps.myproject.helper.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppUpdateInfo {
    @SerializedName("updateAvailability")
    @Expose
    private int updateAvailability;
    @SerializedName("availableVersionCode")
    @Expose
    private int availableVersionCode;
    @SerializedName("isUpdateTypeAllowedIMMEDIATE")
    @Expose
    private boolean isUpdateTypeAllowedIMMEDIATE;
    @SerializedName("isUpdateTypeAllowedFLEXIBLE")
    @Expose
    private boolean isUpdateTypeAllowedFLEXIBLE;
    @SerializedName("packageName")
    @Expose
    private String packageName;
    @SerializedName("installStatus")
    @Expose
    private int installStatus;

    public int getUpdateAvailability() {
        return updateAvailability;
    }

    public void setUpdateAvailability(int updateAvailability) {
        this.updateAvailability = updateAvailability;
    }

    public int getAvailableVersionCode() {
        return availableVersionCode;
    }

    public void setAvailableVersionCode(int availableVersionCode) {
        this.availableVersionCode = availableVersionCode;
    }

    public boolean isUpdateTypeAllowedIMMEDIATE() {
        return isUpdateTypeAllowedIMMEDIATE;
    }

    public void setUpdateTypeAllowedIMMEDIATE(boolean updateTypeAllowedIMMEDIATE) {
        isUpdateTypeAllowedIMMEDIATE = updateTypeAllowedIMMEDIATE;
    }

    public boolean isUpdateTypeAllowedFLEXIBLE() {
        return isUpdateTypeAllowedFLEXIBLE;
    }

    public void setUpdateTypeAllowedFLEXIBLE(boolean updateTypeAllowedFLEXIBLE) {
        isUpdateTypeAllowedFLEXIBLE = updateTypeAllowedFLEXIBLE;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getInstallStatus() {
        return installStatus;
    }

    public void setInstallStatus(int installStatus) {
        this.installStatus = installStatus;
    }
}
