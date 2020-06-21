package mohapps.myproject.activity;

import android.os.Bundle;

import com.google.android.play.core.install.model.AppUpdateType;

import mohapps.myproject.R;
import mohapps.myproject.helper.Config;
import mohapps.myproject.helper.InAppUpdateHelper;

public class ForceUpdateActivity extends BaseActivity {


    InAppUpdateHelper inAppUpdateHelper = new InAppUpdateHelper(Config.getCacheUtilConfig());

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
