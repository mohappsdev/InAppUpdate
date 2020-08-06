package mohapps.iaudemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.android.play.core.install.model.AppUpdateType;


import androidx.appcompat.app.AppCompatActivity;
import mohapps.inappupdate.helper.InAppUpdateHelper;
import mohapps.iaudemo.R;
import mohapps.iaudemo.config.Config;

import static mohapps.inappupdate.helper.Constants.IN_APP_UPDATE;


public class MainActivity extends AppCompatActivity {

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
        inAppUpdateHelper.handleInAppUpdate(this, AppUpdateType.FLEXIBLE, false, findViewById(R.id.button_in_app_update));

        //TODO: customize button_in_app_update (visibility = GONE) and place it in desired place in your layout

        findViewById(R.id.button_in_app_update).setOnClickListener(v ->
                inAppUpdateHelper.handleInAppUpdate(this, AppUpdateType.FLEXIBLE, true, findViewById(R.id.button_in_app_update)));
    }
}