package com.example.speakpedia;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainGame extends AppCompatActivity {

    private static final String KEY_CORRECT_WORD = "correct_word";

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
                verifyAnswer();
            }
        });

        if (savedInstanceState != null) {
            correctWord = savedInstanceState.getString(KEY_CORRECT_WORD);
        } else {
            startGame(); // Initialize a new game if no saved instance state.
        }
    }

    private void startGame() {
        correctWord = getJumbledWord();
        jumbledWordTextView.setText(correctWord);
        answerEditText.setText("");
    }

    private void verifyAnswer() {
        String userAnswer = answerEditText.getText().toString().trim();
        String originalWord = getOriginalWord(correctWord);

        Log.d("VerifyAnswer", "User Answer: " + userAnswer);
        Log.d("VerifyAnswer", "Original Word: " + originalWord);

        if (!userAnswer.isEmpty() && userAnswer.equalsIgnoreCase(originalWord)) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            startGame(); // Start a new game after a correct answer.
        } else {
            Toast.makeText(this, "Incorrect. Try again!", Toast.LENGTH_SHORT).show();
        }
    }

    private String unJumbleWord(String jumbledWord) {
        for (String word : words) {
            if (jumbledWord.equalsIgnoreCase(jumbleWord(word))) {
                return word;
            }
        }
        return ""; // Return an empty string if the original word is not found.
    }

    private String getOriginalWord(String jumbledWord) {
        for (String word : words) {
            if (jumbledWord.equalsIgnoreCase(jumbleWord(word))) {
                return word;
            }
        }
        return ""; // Return an empty string if the original word is not found.
    }

    private String getJumbledWord() {
        Random random = new Random();
        String word = words[random.nextInt(words.length)];
        correctWord = word; // Store the original word for later comparison.
        return jumbleWord(word);
    }

    private String jumbleWord(String word) {
        char[] chars = word.toCharArray();
        Random random = new Random(word.hashCode()); // Use word's hashcode as a seed for randomness.
        for (int i = chars.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char temp = chars[index];
            chars[index] = chars[i];
            chars[i] = temp;
        }
        return new String(chars);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_CORRECT_WORD, correctWord);
    }
}