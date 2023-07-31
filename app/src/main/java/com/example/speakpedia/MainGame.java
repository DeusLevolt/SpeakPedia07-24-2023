package com.example.speakpedia;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainGame extends AppCompatActivity {

    private static final String KEY_CORRECT_WORD = "correct_word";

    private TextView jumbledWordTextView;
    private TextView hintTextView;

    private String correctWord;

    private static final String[] words = {"smell", "crowd", "gifts", "light", "water", "music", "paint", "book", "phone", "games"};
    private Map<String, String> wordHintMap;
    private Button[] letterButtons;
    private List<String> shownWords;
    private int currentWordIndex = 0; // To keep track of the current word index

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_game);

        shownWords = new ArrayList<>();
        jumbledWordTextView = findViewById(R.id.jumbledWordTextView);
        Button submitButton = findViewById(R.id.submitButton);
        ImageButton backButton = findViewById(R.id.back_button);
        hintTextView = findViewById(R.id.hintTextView);
        initializeWordHintMap();
        letterButtons = new Button[5];
        letterButtons[0] = findViewById(R.id.button1);
        letterButtons[1] = findViewById(R.id.button2);
        letterButtons[2] = findViewById(R.id.button3);
        letterButtons[3] = findViewById(R.id.button4);
        letterButtons[4] = findViewById(R.id.button5);
        ImageButton deleteButton = findViewById(R.id.imageButtondel);
        ImageButton shuffleButton = findViewById(R.id.shuffle_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteLastCharacter();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainGame.this, MainActivity.class);
                startActivity(intent);
            }
        });

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

        shuffleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reshuffleGame();
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
        jumbledWordTextView.setText(""); // Clear the TextView initially

        // Check if all words have been shown once
        if (shownWords != null && shownWords.size() == words.length) {
            // If all words have been shown once, reset the list
            shownWords = new ArrayList<>();
        }

        // Get the current word from the words array
        String word = words[currentWordIndex];

        // If the word has already been shown in this session, get the next word
        while (shownWords.contains(word)) {
            currentWordIndex = (currentWordIndex + 1) % words.length;
            word = words[currentWordIndex];
        }
        // Add the selected word to the list of shown words
        shownWords.add(word);
        // Set the current word index for the next iteration
        currentWordIndex = (currentWordIndex + 1) % words.length;
        // Set a fixed width for the buttons to prevent them from moving when hidden
        int buttonWidth = getResources().getDimensionPixelSize(R.dimen.button_fixed_width);
        for (Button button : letterButtons) {
            button.getLayoutParams().width = buttonWidth;
            button.requestLayout(); // Refresh the layout after setting the width
        }
        //set all letter buttons to visible
        for (Button button : letterButtons) {
            button.setVisibility(View.VISIBLE);
        }

        // Update the hint text based on the current jumbled word
        String hint = wordHintMap.get(getOriginalWord(correctWord));
        hintTextView.setText(hint);

        // Shuffle the letters of the jumbled word to ensure they are displayed in random order
        String shuffledWord = shuffleWord(correctWord);

        // Ensure the shuffled word has a length of at least 5 before accessing characters at index 4 or higher
        if (shuffledWord.length() >= 5) {
            // Update the buttons with the jumbled letters
            for (int i = 0; i < letterButtons.length; i++) {
                char letter = shuffledWord.charAt(i);
                letterButtons[i].setText(String.valueOf(letter));
                letterButtons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Button button = (Button) v;
                        String buttonText = button.getText().toString();
                        String currentText = jumbledWordTextView.getText().toString();
                        jumbledWordTextView.setText(currentText + buttonText);

                        //hide the button when the user click it
                        button.setVisibility(View.INVISIBLE);

                        //re-enable the delete button
                        ImageButton deleteButton = findViewById(R.id.imageButtondel);
                        deleteButton.setEnabled(true);
                    }
                });
            }
        }
    }

    private String getRandomUnshownWord() {
        List<String> unshownWords = new ArrayList<>();
        for (String word : words) {
            if (!shownWords.contains(word)) {
                unshownWords.add(word);
            }
        }
        if (unshownWords.isEmpty()) {
            // If all words have been shown, reset the list and start again
            shownWords = new ArrayList<>();
            return getRandomUnshownWord();
        } else {
            Random random = new Random();
            return unshownWords.get(random.nextInt(unshownWords.size()));
        }
    }


    private void verifyAnswer() {
        String userAnswer = jumbledWordTextView.getText().toString().trim();
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
        Random random = new Random(word.hashCode());

        // Shuffle the letters using Fisher-Yates algorithm
        for (int i = chars.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char temp = chars[index];
            chars[index] = chars[i];
            chars[i] = temp;
        }

        // Convert the char array back to a string
        return new String(chars);
    }

    private String shuffleWord(String word) {
        char[] chars = word.toCharArray();
        Random random = new Random(word.hashCode());
        for (int i = chars.length - 1; i >= 0; i--) {
            int index = random.nextInt(i + 1);
            char temp = chars[index];
            chars[index] = chars[i];
            chars[i] = temp;
        }
        return new String(chars);
    }

    private void initializeWordHintMap() {
        wordHintMap = new HashMap<>();
        // Add word-hint pairs to the map
        wordHintMap.put("smell", "A human human sensory to pick up scent.");
        wordHintMap.put("crowd", " A large number of people gathered together in a disorganized or unruly way.");
        wordHintMap.put("gifts", "Thing given willingly to someone without payment.");
        wordHintMap.put("light", "the natural agent that stimulates sight and makes things visible.");
        wordHintMap.put("water", "transparent, odorless, tasteless liquid compound.");
        wordHintMap.put("music", " vocal, instrumental, or mechanical sounds having rhythm, melody, or harmony.");
        wordHintMap.put("paint", "A liquid substance that is applied to various surfaces to create a protective or decorative coating.");
        wordHintMap.put("books", "Written or printed work consist of pages.");
        wordHintMap.put("phone", "refers to a communication device that allows people to speak with each other over long distances.");
        wordHintMap.put("games", "structured, interactive activities for enjoyment, involving entertainment, and skill development.");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_CORRECT_WORD, correctWord);
    }

    private void deleteLastCharacter() {
        String currentText = jumbledWordTextView.getText().toString();
        if (!currentText.isEmpty()) {
            // Remove the last character from the current text
            String deletedLetter = currentText.substring(currentText.length() - 1);
            currentText = currentText.substring(0, currentText.length() - 1);
            jumbledWordTextView.setText(currentText);

            // Bring back the button for the deleted letter
            showButtonForLetter(deletedLetter);

            // Disable the delete button if there are no letters left
            ImageButton deleteButton = findViewById(R.id.imageButtondel);
            if (currentText.isEmpty()) {
                deleteButton.setEnabled(false);
            }
        }
    }

    private void showButtonForLetter(String letter) {
        for (Button button : letterButtons) {
            if (button.getText().toString().equals(letter)) {
                button.setVisibility(View.VISIBLE);
                break;
            }
        }
    }

    private void reshuffleGame() {
        // Reshuffle the jumbled word and update the jumbledWordTextView
        correctWord = getJumbledWord();
        jumbledWordTextView.setText("");

        // Reshuffle the letters of the jumbled word to update the buttons
        String shuffledWord = shuffleWord(correctWord);
        for (int i = 0; i < letterButtons.length; i++) {
            char letter = shuffledWord.charAt(i);
            letterButtons[i].setText(String.valueOf(letter));
            letterButtons[i].setVisibility(View.VISIBLE);
        }

        // Reshuffle the hint based on the current jumbled word
        String hint = wordHintMap.get(getOriginalWord(correctWord));
        hintTextView.setText(hint);
    }
}