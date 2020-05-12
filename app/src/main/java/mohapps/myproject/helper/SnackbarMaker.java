package mohapps.myproject.helper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateManager;

import mohapps.myproject.R;


public class SnackbarMaker {
    
    public static void makeInAppUpdateSnackBar(Context context, View view, boolean isSuccessful, AppUpdateManager appUpdateManager) {
        Snackbar snackbar = showSnackbar(context, view, Snackbar.LENGTH_INDEFINITE);
        TextView textView = snackbar.getView().findViewById(R.id.snackbar_text);
        ImageView imageView = snackbar.getView().findViewById(R.id.snackbar_image);
        Button button = snackbar.getView().findViewById(R.id.snackbar_action);

        if (isSuccessful) {
            textView.setText("INFO_UPDATE_SUCCESSFUL");
            imageView.setImageResource(R.drawable.approved);
        } else {
            textView.setText("INFO_UPDATE_FAILED");
            imageView.setImageResource(R.drawable.rejected);
        }
        button.setText("ACTION_COMPLETE_UPDATE");
        button.setOnClickListener(v -> appUpdateManager.completeUpdate());
        snackbar.show();
    }
    private static Snackbar showSnackbar(Context context, View view, int duration) { // Create the Snackbar
        Snackbar snackbar = Snackbar.make(view, "", duration);
        // margin from all the sides for snackbar
        int marginFromSides = 0;
        DisplayMetrics dm =new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        float height = dm.heightPixels;

        //inflate view
        View snackView = ((Activity)context).getLayoutInflater().inflate(R.layout.snackbar_layout_single_option, null);

        // background
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        // for rounded edges
        //snackbar.getView().setBackground(context.getResources().getDrawable(R.drawable.round_edges));


        Snackbar.SnackbarLayout snackBarView = (Snackbar.SnackbarLayout) snackbar.getView();
        snackBarView.setPadding(0,0,0,0);
        FrameLayout.LayoutParams parentParams = (FrameLayout.LayoutParams) snackBarView.getLayoutParams();
        parentParams.setMargins(marginFromSides, 0, marginFromSides, marginFromSides);
        parentParams.height = (int) height;
        parentParams.width = FrameLayout.LayoutParams.MATCH_PARENT;
        snackBarView.setLayoutParams(parentParams);

        snackBarView.addView(snackView, 0);
        snackbar.addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                super.onDismissed(transientBottomBar, event);
                ((Activity)context).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }
        });
        ((Activity)context).getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return snackbar;
    }

}
