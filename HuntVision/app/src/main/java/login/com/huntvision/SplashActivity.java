package login.com.huntvision;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.sql.SQLException;


public class SplashActivity extends DefaultActivity {
    private final int SPLASH_MILIS = 1000;
    protected ProgressBar progressBar;
    protected TextView textView;
    protected ImageButton imageButton;
    @Override
    protected void onCreate(Bundle icicle) {
           super.onCreate(icicle);
        setContentView(R.layout.activity_splash);

        this.progressBar = (ProgressBar) this.findViewById(R.id.activity_splash_progressBar);
        this.textView = (TextView) this.findViewById(R.id.activity_splash_textView);
        this.imageButton = (ImageButton) this.findViewById(R.id.activity_splash_image_button);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goHuntVision();
            }
        }, SPLASH_MILIS);




    }

    private void goHuntVision() {

        Intent mainIntent = null;

        if(getHelper().getUsuarioRuntimeDAO().queryForAll().isEmpty()) {

            mainIntent = new Intent(this, SincronizacaoActivity_.class);

        } else {

            mainIntent = new Intent(this, LoginActivity_.class);

        }

        SplashActivity.this.startActivity(mainIntent);

        SplashActivity.this.finish();

    }

}
