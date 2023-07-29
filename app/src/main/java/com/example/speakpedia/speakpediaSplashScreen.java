package com.example.speakpedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

public class speakpediaSplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);
        overridePendingTransition(R.anim.rotate_in, R.anim.rotate_out);

        // Start the countdown timer for the first layout change
        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Nothing to do on each tick
            }

            @Override
            public void onFinish() {
                // Change the layout after 5 seconds
                setContentView(R.layout.welcome2);

                // Start the countdown timer for the second layout change
                new CountDownTimer(5000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        // Nothing to do on each tick
                    }

                    @Override
                    public void onFinish() {
                        // Change the layout after another 5 seconds
                        setContentView(R.layout.activity_speakpedia_splash_screen);
                        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);

                        // Start the countdown timer for launching MainActivity
                        new CountDownTimer(5000, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                // Nothing to do on each tick
                            }

                            @Override
                            public void onFinish() {
                                // Show the welcome_start.xml layout with the "Start" button
                                setContentView(R.layout.welcome_start);
                            }
                        }.start(); // Start the third countdown timer
                    }
                }.start(); // Start the second countdown timer
            }
        }.start(); // Start the first countdown timer
    }

    // This method will be called when the "Start" button is clicked
    public void onStartButtonClick(View view) {
        // Launch the MainActivity when the button is clicked
        Intent intent = new Intent(speakpediaSplashScreen.this, MainActivity.class);
        startActivity(intent);
        finish(); // Finish the Splash Screen activity
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}