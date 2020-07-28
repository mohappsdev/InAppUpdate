package mohapps.iaudemo.activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.play.core.install.model.AppUpdateType;


import mohapps.inappupdate.helper.InAppUpdateHelper;
import mohapps.iaudemo.R;
import mohapps.iaudemo.config.Config;

public class ForceUpdateActivity extends BaseActivity {


    InAppUpdateHelper inAppUpdateHelper = null;

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
        if(inAppUpdateHelper==null){
            inAppUpdateHelper = new InAppUpdateHelper(Config.getForceUpdateStrategyConfig(), new Intent(this, ForceUpdateActivity.class));
        }
        inAppUpdateHelper.handleInAppUpdate(this, AppUpdateType.IMMEDIATE, false);
    }
}
