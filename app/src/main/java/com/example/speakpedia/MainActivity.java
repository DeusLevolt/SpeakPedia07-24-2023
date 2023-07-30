package com.example.speakpedia;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Gravity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity {

    private TextView displayText;
    private SpeechRecognizer speechRecognizer;
    TextToSpeech textToSpeech;
    private static final int REQUEST_PERMISSION_CODE = 1;
    private static final int SPEECH_REQUEST_CODE = 2;
    private DrawerLayout drawerLayout;
    private MediaPlayer mediaPlayer;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton burgerButton = findViewById(R.id.burger);


        //for the textview
        displayText = findViewById(R.id.display_text);

        setupRetrofit();
        drawerLayout = findViewById(R.id.drawer_layout);
        mediaPlayer = MediaPlayer.create(this, R.raw.click_pebbles);

        //reference to all buttons
        Button aboutus = findViewById(R.id.about_us);
        Button game = findViewById(R.id.game_button);
        ImageButton imageButtondel = findViewById(R.id.imageButtondel);
        ImageButton imageButtonspeak = findViewById(R.id.imageButtonspeak);
        TextView imageButtonclearall = findViewById(R.id.imageButtonclearall);
        ImageView imageButtonspeech = findViewById(R.id.imageButtonspeech);
        TextView q = findViewById(R.id.q);
        TextView w = findViewById(R.id.w);
        TextView e = findViewById(R.id.e);
        TextView r = findViewById(R.id.r);
        TextView t = findViewById(R.id.t);
        TextView y = findViewById(R.id.y);
        TextView u = findViewById(R.id.u);
        TextView i = findViewById(R.id.i);
        TextView o = findViewById(R.id.o);
        TextView p = findViewById(R.id.p);
        TextView a = findViewById(R.id.a);
        TextView s = findViewById(R.id.s);
        TextView d = findViewById(R.id.d);
        TextView f = findViewById(R.id.f);
        TextView g = findViewById(R.id.g);
        TextView h = findViewById(R.id.h);
        TextView j = findViewById(R.id.j);
        TextView k = findViewById(R.id.k);
        TextView l = findViewById(R.id.l);
        TextView z = findViewById(R.id.z);
        TextView x = findViewById(R.id.x);
        TextView c = findViewById(R.id.c);
        TextView v = findViewById(R.id.v);
        TextView b = findViewById(R.id.b);
        TextView n = findViewById(R.id.n);
        TextView m = findViewById(R.id.m);
        View space = findViewById(R.id.space_button);


        //set onclick listener for each buttons

        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);

            }
        });

        burgerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                } else {
                    drawerLayout.openDrawer(Gravity.RIGHT);
                }
            }
        });

        q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                String currentText = displayText.getText().toString();
                displayText.setText(currentText+"Q");
            }
        });

        w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                String currentText = displayText.getText().toString();
                displayText.setText(currentText+"W");
            }
        });

        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                String currentText = displayText.getText().toString();
                displayText.setText(currentText+"E");
            }
        });

        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                String currentText = displayText.getText().toString();
                displayText.setText(currentText+"R");
            }
        });

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                String currentText = displayText.getText().toString();
                displayText.setText(currentText+"T");
            }
        });

        y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                String currentText = displayText.getText().toString();
                displayText.setText(currentText+"Y");
            }
        });

        u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                String currentText = displayText.getText().toString();
                displayText.setText(currentText+"U");
            }
        });

        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                String currentText = displayText.getText().toString();
                displayText.setText(currentText+"I");
            }
        });

        o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                String currentText = displayText.getText().toString();
                displayText.setText(currentText+"O");
            }
        });

        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                String currentText = displayText.getText().toString();
                displayText.setText(currentText+"P");
            }
        });

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                String currentText = displayText.getText().toString();
                displayText.setText(currentText+"A");
            }
        });

        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                String currentText = displayText.getText().toString();
                displayText.setText(currentText+"S");
            }
        });

        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                String currentText = displayText.getText().toString();
                displayText.setText(currentText+"D");
            }
        });

        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                String currentText = displayText.getText().toString();
                displayText.setText(currentText+"F");
            }
        });

        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                String currentText = displayText.getText().toString();
                displayText.setText(currentText+"G");
            }
        });

        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                String currentText = displayText.getText().toString();
                displayText.setText(currentText+"H");
            }
        });

        j.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                String currentText = displayText.getText().toString();
                displayText.setText(currentText+"J");
            }
        });

        k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                String currentText = displayText.getText().toString();
                displayText.setText(currentText+"K");
            }
        });

        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                String currentText = displayText.getText().toString();
                displayText.setText(currentText+"L");
            }
        });

        z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                String currentText = displayText.getText().toString();
                displayText.setText(currentText+"Z");
            }
        });

        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                String currentText = displayText.getText().toString();
                displayText.setText(currentText+"X");
            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                String currentText = displayText.getText().toString();
                displayText.setText(currentText+"C");
            }
        });

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                String currentText = displayText.getText().toString();
                displayText.setText(currentText+"V");
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                String currentText = displayText.getText().toString();
                displayText.setText(currentText+"B");
            }
        });

        n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                String currentText = displayText.getText().toString();
                displayText.setText(currentText+"N");
            }
        });

        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                String currentText = displayText.getText().toString();
                displayText.setText(currentText+"M");
            }
        });

        space.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                String currentText = displayText.getText().toString();
                displayText.setText(currentText+" ");
            }
        });

        imageButtondel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                String currentText = displayText.getText().toString();
                //check if  the text is not empty
                if (!currentText.isEmpty()) {
                    //this will remove the last character from the text
                    String updateText = currentText.substring(0, currentText.length() - 1);
                    displayText.setText(updateText);
                }
            }
        });
        imageButtonclearall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
                displayText.setText("");
                textView.setText("");
            }
        });

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int language = textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }

        });
        imageButtonspeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = displayText.getText().toString();
                int speech = textToSpeech.speak(s, TextToSpeech.QUEUE_FLUSH, null);

                String userInput = displayText.getText().toString();
                searchWord(userInput);


            }
        });


        imageButtonspeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkPermissionAndStartSpeechToText();

                displayText.setText("");
                textView.setText("");
            }

        });
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
    }

    private void playSound() {
        // Check if MediaPlayer is playing, stop and reset it
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
        }

        // Start playing the sound
        mediaPlayer.start();
    }


    private void checkPermissionAndStartSpeechToText() {
        //check if the record audio permission is okay
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            //request the permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_PERMISSION_CODE);
        } else {
            //is permission is already granted, start the stt
            startSpeechToText();
        }
    }

    private void startSpeechToText() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        //start the stt act and results
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            //retrieve the stt result
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (result != null && !result.isEmpty()) {
                //update the textview with the recognized text
                displayText.setText(result.get(0));
            }
        }
    }

    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //permission granted, start stt
                startSpeechToText();
            } else {
                //permission denied
                Toast.makeText(this, "Permission is denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //release the speechRecodnizer resources
        if (speechRecognizer != null) {
            speechRecognizer.destroy();
        }
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }


    TextView textView;
    private DictionaryService dictionaryService;


    private void setupRetrofit() {

        textView = findViewById(R.id.textView2);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public okhttp3.Response intercept(Chain chain) throws IOException {
                                Request.Builder requestBuilder = chain.request().newBuilder();
                                requestBuilder.header("Content-Type", "application/json");
                                return chain.proceed(requestBuilder.build());
                            }
                        })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.dictionaryapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        dictionaryService = retrofit.create(DictionaryService.class);
    }

    private void searchWord(String word) {
        Call<WordResponse[]> call = dictionaryService.getWordDefinition(word, "fd5d3406-f23d-42b4-acfd-42a0eb637af9");
        call.enqueue(new Callback<WordResponse[]>() {
            @Override
            public void onResponse(Call<WordResponse[]> call, Response<WordResponse[]> response) {
                if (response.isSuccessful()) {
                    WordResponse[] wordResponses = response.body();
                    if (wordResponses != null && wordResponses.length > 0) {
                        WordResponse firstEntry = wordResponses[0];
                        String definition = parseDefinitionFromResponse(firstEntry);
                        runOnUiThread(() -> textView.setText(definition));
                    } else {
                        runOnUiThread(() -> textView.setText("No definition found."));
                    }
                } else {
                    runOnUiThread(() -> textView.setText("Unable to fetch definition. Please try again."));
                }
            }

            @Override
            public void onFailure(Call<WordResponse[]> call, Throwable t) {
                runOnUiThread(() -> textView.setText("Unable to fetch definition. Please try again."));
            }
        });
    }

    private String parseDefinitionFromResponse(WordResponse responseBody) {
        if(responseBody.getHwi().getPhonetics() == null){
            return "No definition found.";
        }
        if(responseBody.getDefinitions() == null){
            return "No definition found.";
        }
        return "Phonetics: " + responseBody.getHwi().getPhonetics()[0].getMw().toString() + "\n\nDefinition: " + responseBody.getShortDef()[0] + "\n\n";
    }

    private void showExitConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User clicked "Yes" - close the app
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User clicked "No" - dismiss the dialog
                dialog.dismiss();
            }
        });
        builder.show();
    }
    @Override
    public void onBackPressed() {
        // Show the Exit confirmation dialog
        showExitConfirmationDialog();
    }
}

