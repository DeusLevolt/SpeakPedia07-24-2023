package com.example.speakpedia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.game_jumbled_words);

        TextView startgame = findViewById(R.id.start_button_game);
        startgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainGameActivity();
            }
        });
    }

    private void startMainGameActivity() {
        Intent intent = new Intent(this, MainGame.class);
        startActivity(intent);
    }
}