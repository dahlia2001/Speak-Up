package com.example.speakup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

public class speakingactivity extends AppCompatActivity {

    TextView textView;
    ImageButton Listen;
    ImageView imageView;
    SpeechRecognizer speechRecognizer;
    ArrayList<String> objects = new ArrayList<>();
    ArrayList<String> newObjects = new ArrayList<>();
    HashMap<String, Integer> map = new HashMap<>();
    HashMap<Integer, Integer> hm = new HashMap<>();
    Iterator iterator = hm.entrySet().iterator();
    String[] answer = new String[10];
    int index = 0;
    int flag = 0;
    int points = 0;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.speakingact);


        Listen = (ImageButton) findViewById(R.id.mbtn);
        textView = findViewById(R.id.textspeak);
        imageView = findViewById(R.id.imagespeak);
        objects.add("potato");
        objects.add("grapes");
        objects.add("watermelon");
        objects.add("carrot");
        objects.add("apple");
        objects.add("tomato");
        objects.add("banana");
        objects.add("mango");
        index = 0;
        map.put(objects.get(0), R.drawable.brown_potato_vegetable);
        map.put(objects.get(1), R.drawable.green_grape_fruit);
        map.put(objects.get(2), R.drawable.green_watermelon_fruit);
        map.put(objects.get(3), R.drawable.orange_carrot_vegetable);
        map.put(objects.get(4), R.drawable.red_apple_fruit);
        map.put(objects.get(5), R.drawable.red_tomato_fruit);
        map.put(objects.get(6), R.drawable.yellow_banana_fruit);
        map.put(objects.get(7), R.drawable.yellow_mango_fruit);
//        Collections.shuffle(objects);

        hm.put(0, map.get("potato"));
        hm.put(1, map.get("grapes"));
        hm.put(2, map.get("watermelon"));
        hm.put(3, map.get("carrot"));
        hm.put(4, map.get("apple"));
        hm.put(5, map.get("tomato"));
        hm.put(6, map.get("banana"));
        hm.put(7, map.get("mango"));

        iterator = hm.entrySet().iterator();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        }

        speechRecognizer = speechRecognizer.createSpeechRecognizer(this);
        final Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        Listen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    generateImages(index);

                    if (count == 0) {
                        Listen.setImageResource(R.drawable.ic_baseline_mic_24);

                        // Start Listening

                        speechRecognizer.startListening(speechRecognizerIntent);
                        Log.d("Output: ", "Hello");
                        count = 1;
                    }

                    else {
                        Listen.setImageResource(R.drawable.micoff);
                        speechRecognizer.stopListening();
                        count = 0;
                        checkAnswer();
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                    Log.i("Output", e.getMessage());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Log.i("Output", e.getMessage());
                }
            }
        });

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle results) {
                speechRecognizer.stopListening();
                Listen.setImageResource(R.drawable.micoff);
                textView.setVisibility(View.VISIBLE);
                ArrayList<String> data = results.getStringArrayList(speechRecognizer.RESULTS_RECOGNITION);
                textView.setText(data.get(0));
                checkAnswer();
            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });
    }

    private void generateImages(int index) throws ExecutionException, InterruptedException {
        if (iterator.hasNext()) {
            imageView.setImageResource(hm.get(index));
            textView.setText("Tap mic to speak");
            Log.i("OutputinsideOfif", String.valueOf(index));
        }
        Log.i("Output", String.valueOf(index));
    }

    private void checkAnswer () {
        String answer = textView.getText().toString();
        if (objects.get(index).contains(answer)) {
            textView.setText("Correct Answer!!!");
            flag = 1;
        }

        else {
            textView.setText("Incorrect. Please try again");
        }

        if (index < 7 && flag == 1) {
            index++;
            flag = 0;
            iterator.next();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT);
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT);
            }
        }
    }
}