package com.example.gads_top20;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class GadsLauncherActivity extends AppCompatActivity {
    /**
     * The number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gads_launcher);
        Handler hideHandler = new Handler();
        hideHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(GadsLauncherActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        },AUTO_HIDE_DELAY_MILLIS);
    }

}
