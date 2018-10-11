package com.benjizaid.myapp;

import android.content.Intent;
import android.os.health.TimerStat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_TIME = 3000;
    private Timer timer;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView = (ImageView)findViewById(R.id.ivBarberSplash);
        timer = new Timer();
        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.mytransition);
        imageView.startAnimation(myanim);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                goToLogIn();
            }
        };

        timer.schedule(task, SPLASH_TIME);

    }

    private void goToLogIn()
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
