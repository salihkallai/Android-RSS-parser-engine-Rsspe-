package com.tomercon.myrssfeed.Rsspe;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tomercon.myrssfeed.R;

import java.util.Locale;

/**
 * Created by user on 11-Jun-16.
 */
public class Rsspe_description extends Activity {

    TextToSpeech t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rsspe_description);
        String title = getIntent().getExtras().get("titla").toString();
        final String description = getIntent().getExtras().get("descripta").toString();

        final String rbg = getIntent().getExtras().get("descripta_bgcolor").toString();
        //Toast.makeText(this, rbg, Toast.LENGTH_SHORT).show();
        int des_bg;
        try{
            des_bg= Color.parseColor(rbg);
        }
        catch(IllegalArgumentException e)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            des_bg= Color.parseColor("#F6F6F6");
        }

        LinearLayout bgv = (LinearLayout)findViewById(R.id.bgv);
        bgv.setBackgroundColor(des_bg);

        TextView t = (TextView)findViewById(R.id.t);
        TextView d = (TextView)findViewById(R.id.d);
        t.setText(title);
        d.setText(Html.fromHtml(description));
        nx("ooo");
    }

    public void nx(final String speechtext)
    {
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    Locale loc = new Locale("en_IN");
                    t1.setLanguage(loc);
                    t1.setPitch(5.0f);
                    t1.setSpeechRate(0.75f);
                    //t1.speak(speechtext, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
    }


    @Override
    public void onPause()
    {
        super.onPause();
        if(t1.isSpeaking())
        {
            t1.stop();
            //When you are done using the TextToSpeech instance, call the shutdown() method to release the native resources used by the TextToSpeech engine.
            t1.shutdown();
        }

    }
}
