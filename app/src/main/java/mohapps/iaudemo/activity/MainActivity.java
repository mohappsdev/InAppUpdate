package mohapps.iaudemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.android.play.core.install.model.AppUpdateType;


import mohapps.inappupdate.helper.DataLoader;
import mohapps.inappupdate.helper.InAppUpdateHelper;
import mohapps.iaudemo.R;
import mohapps.iaudemo.config.Config;

import static mohapps.inappupdate.helper.Constants.IN_APP_UPDATE;


public class MainActivity extends BaseActivity{

    InAppUpdateHelper inAppUpdateHelper = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

   

    @Override
    protected void onResume() {
        super.onResume();
        if(inAppUpdateHelper==null){
            inAppUpdateHelper = new InAppUpdateHelper(Config.getForceUpdateStrategyConfig(), new Intent(this, ForceUpdateActivity.class));
        }
        inAppUpdateHelper.handleInAppUpdate(this, AppUpdateType.FLEXIBLE, false);

        //TODO: customize button_in_app_update (visibility = GONE) and place it in desired place in your layout
        inAppUpdateHelper.loadInAppUpdate(this, findViewById(R.id.button_in_app_update));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IN_APP_UPDATE) {
            if (resultCode != Activity.RESULT_OK) {
                Log.i("Update flow failed!", String.valueOf(resultCode));
                // If the update is cancelled or fails,
                // you can request to start the update again.
            }
            if(resultCode== Activity.RESULT_OK){
                inAppUpdateHelper.loadInAppUpdate(this, findViewById(R.id.button_in_app_update));
                new Handler().postDelayed(DataLoader::deleteAppUpdateInfo, 1000);
            }

        }
    }
}
