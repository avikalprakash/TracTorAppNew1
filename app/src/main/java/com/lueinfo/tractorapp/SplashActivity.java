package com.lueinfo.tractorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    ImageView mc9img,mgeowineimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mc9img = (ImageView) findViewById(R.id.img_second);
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.myanimation);
        mc9img.startAnimation(hyperspaceJumpAnimation);



        Thread splashthread = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                    runOnUiThread(new Runnable()
                    {
                        public void run() {
                            startActivity(new Intent(SplashActivity.this, SelectRestaurant.class));
                        }
                    });
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        splashthread.start();
    }
}
