package com.example.myapplication;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Button clk;
    VideoView videov;
    MediaController mediaC;
    MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clk = (Button) findViewById(R.id.button);
        videov = (VideoView) findViewById(R.id.videoView);
        mediaC = new MediaController(this);

        TextView textView = (TextView)findViewById(R.id.textView);

        List<String> data = readTextFileAsList(this, R.raw.sdfg);

        /*for(int i=0; i < data.size(); i++){

            textView.setText(textView.getText() + data.get(i) + " , ");
        }*/

        textView.setText(textView.getText() + data.get(2) + " , ");
    }

    public static List<String> readTextFileAsList(Context ctx, int resId) {

        InputStream inputStream = ctx.getResources().openRawResource(resId);

        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader bufferedreader = new BufferedReader(inputreader);
        String line;
        List<String> list = new ArrayList<String>();

        try {
            while (( line = bufferedreader.readLine()) != null) {
                if (isUrl(line)) {
                    list.add(line);
                }
            }
        }
        catch (IOException e) {
            return null;
        }
        return list;
    }


    public void video_play(View v) {

        String videopath = "android.resource://com.example.myapplication/"+R.raw.bubbles;
        List<String> data = readTextFileAsList(this, R.raw.sdfg);
        Uri uri = Uri.parse(data.get(1));
        String urlPpath = "http://1ttvauth.top/h/78/1/2/MlFDU0hXakRJRmk2Q1lkZnZyTDFDS093cktaV0kvRDB6djZxUXphNzFBSW9sYU9WVlR1TVo5WWFycVloOGZZSw.mp4";
        String newt = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
        Uri test = Uri.parse(newt);

        /*videov.setVideoURI(test);
        videov.setMediaController(mediaC);
        mediaC.setAnchorView(videov);
        videov.start();*/

        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(videopath);
        } catch (IllegalArgumentException e) {
            Toast.makeText(getApplicationContext(), "You might not set the URI correctly! Illegal Argument", Toast.LENGTH_LONG).show();
        } catch (SecurityException e) {
            Toast.makeText(getApplicationContext(), "You might not set the URI correctly! Security ", Toast.LENGTH_LONG).show();
        } catch (IllegalStateException e) {
            Toast.makeText(getApplicationContext(), "You might not set the URI correctly! Illegal Statement", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            mPlayer.prepareAsync();
            mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mPlayer.start();
                }
            });
        } catch (IllegalStateException e) {
            Toast.makeText(getApplicationContext(), "You might not set the URI correctly! Prepare Illegal State", Toast.LENGTH_LONG).show();
        }
       /* videov.setVideoURI(test);
        videov.setMediaController(new MediaController(this));
        videov.requestFocus();
        videov.start();*/

    }

    public static boolean isUrl(String url) {
        String trimmed = url.trim();
        return trimmed.length() > 0 && trimmed.charAt(0) != '#'
                && trimmed.charAt(0) != '<';
    }
}



