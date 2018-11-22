package com.example.android.fa3el5eer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import java.util.prefs.Preferences;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        final SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);

        final boolean login = preferences.getBoolean("login",false);

        preferences.edit().apply();

        RelativeLayout relativeLayout = findViewById(R.id.relSplash);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animation_splash);
        relativeLayout.setAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {



//                Intent intent = new Intent(SplashActivity.this,AboutAppActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);

                if (login){
                    Intent intent = new Intent(SplashActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent i = new Intent(SplashActivity.this,AboutAppActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        },3000);

    }
}
