package com.example.ubc_eng;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.speech.tts.TextToSpeech;
import java.util.Locale;

public class Main2Activity extends AppCompatActivity {

    TextToSpeech t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        Toast.makeText(getApplicationContext(), message,Toast.LENGTH_SHORT).show();

        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                    Toast.makeText(getApplicationContext(), "DONE",Toast.LENGTH_SHORT).show();
                }
            }
        });

        t1.speak("DONE", TextToSpeech.QUEUE_FLUSH, null);

    }

    public void onPause(){

        if(t1 !=null){
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }
}
