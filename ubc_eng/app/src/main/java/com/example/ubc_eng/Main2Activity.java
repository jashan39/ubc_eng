package com.example.ubc_eng;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.speech.tts.TextToSpeech;
import java.util.Locale;

public class Main2Activity extends AppCompatActivity {

    TextToSpeech t1;
    Button b1;
    TextView content;
    TextView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        b1 = (Button)findViewById(R.id.button4);
        header = (TextView) findViewById(R.id.textView2);
        content = (TextView) findViewById(R.id.textView3);
        content.setMovementMethod(ScrollingMovementMethod.getInstance());

        header.setPaintFlags(header.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);

        Intent intent = getIntent();
        final String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        content.setText(message);

        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t1.speak(message, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }

    @Override
    public void onBackPressed() {
        onPause();
        super.onBackPressed();
    }

    @Override
    public void onStop(){
        onPause();
        super.onStop();
    }

    public void onPause(){

        if(t1 !=null){
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }
}
