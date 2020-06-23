package mohapps.iaudemo.activity;

import android.os.Bundle;

import com.google.android.play.core.install.model.AppUpdateType;


import mohapps.inappupdate.helper.InAppUpdateHelper;
import mohapps.iaudemo.R;
import mohapps.iaudemo.config.Config;

public class ForceUpdateActivity extends BaseActivity {


    InAppUpdateHelper inAppUpdateHelper = new InAppUpdateHelper(Config.getForceUpdateStrategyConfig(), new ForceUpdateActivity());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_force_update);
        //TODO: You need to customize this layout
    }

    //blocking back button
    @Override
    public void onBackPressed() {}

    @Override
    protected void onResume() {
        super.onResume();
        inAppUpdateHelper.handleInAppUpdate(this, AppUpdateType.IMMEDIATE, false);
    }
}
