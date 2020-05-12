package mohapps.myproject.activity;

import android.os.Bundle;

import mohapps.myproject.R;

public class ForceUpdateActivity extends ParentForceUpdateActivity{



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

    }
}
