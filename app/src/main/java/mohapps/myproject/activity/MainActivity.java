package mohapps.myproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.play.core.install.model.AppUpdateType;

import mohapps.myproject.R;
import mohapps.myproject.helper.Config;
import mohapps.myproject.helper.DataLoader;
import mohapps.myproject.helper.InAppUpdateHelper;

import static mohapps.myproject.helper.Constants.IN_APP_UPDATE;

public class MainActivity extends BaseActivity{

    InAppUpdateHelper inAppUpdateHelper = new InAppUpdateHelper(Config.getCacheUtilConfig());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

   

    @Override
    protected void onResume() {
        super.onResume();
        inAppUpdateHelper.handleInAppUpdate(this, AppUpdateType.FLEXIBLE, false);

        //TODO: layout main is your root layout's id, customize button_in_app_update and place it in desired place in your layout
        inAppUpdateHelper.loadInAppUpdate(this, findViewById(R.id.layout_main), findViewById(R.id.button_in_app_update));
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
                DataLoader.deleteAppUpdateInfo();
                inAppUpdateHelper.loadInAppUpdate(this, findViewById(R.id.layout_main), findViewById(R.id.button_in_app_update));
            }

        }
    }
}
