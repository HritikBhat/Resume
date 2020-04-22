package com.hritik.resume;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String flag_var = "flashFlag";
    public static final boolean default_val = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                //default_val is used if there is no key then it will return default_val.
                Boolean imgSett = sharedpreferences.getBoolean(flag_var,default_val);
                if (imgSett==true){
                    Intent i = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(i);
                }
                else {
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putBoolean(flag_var, true);
                    editor.commit();
                    Intent i = new Intent(SplashScreen.this, InstructionMain.class);
                    startActivity(i);
                }

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
