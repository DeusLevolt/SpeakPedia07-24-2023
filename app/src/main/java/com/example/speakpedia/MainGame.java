package com.example.speakpedia;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainGame extends AppCompatActivity {

    private TextView jumbledWordTextView;
    private EditText answerEditText;
    private Button submitButton;

    private String correctWord;

    private static final String[] words = {"apple", "banana", "orange", "grape", "mango"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_game);

        jumbledWordTextView = findViewById(R.id.jumbledWordTextView);
        answerEditText = findViewById(R.id.answerEditText);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });

        startGame();
    }

    private void startGame() {
        correctWord = getJumbledWord();
        jumbledWordTextView.setText(correctWord);
        answerEditText.setText("");
    }

    private void checkAnswer() {
        String userAnswer = answerEditText.getText().toString().trim();
        if (userAnswer.equalsIgnoreCase(correctWord)) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Incorrect. Try again!", Toast.LENGTH_SHORT).show();
        }
        startGame(); // Start a new round
    }

    private String getJumbledWord() {
        Random random = new Random();
        String word = words[random.nextInt(words.length)];
        return shuffleWord(word);
    }

    private String shuffleWord(String word) {
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int randomIndex = (int) (Math.random() * chars.length);
            char temp = chars[i];
            chars[i] = chars[randomIndex];
            chars[randomIndex] = temp;
        }
        return new String(chars);
    }
}
