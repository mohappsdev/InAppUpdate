package mohapps.myproject.activity;

import android.os.Bundle;

import com.google.android.play.core.install.model.AppUpdateType;

import mohapps.myproject.helper.InAppUpdateHelper;


public class ParentForceUpdateActivity extends BaseActivity {

    InAppUpdateHelper inAppUpdateHelper =new InAppUpdateHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        inAppUpdateHelper.handleInAppUpdate(this, AppUpdateType.IMMEDIATE, false);
    }
}
