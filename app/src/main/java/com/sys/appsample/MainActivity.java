package com.sys.appsample;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ProgressBar progbar = (ProgressBar) findViewById(R.id.progressBar);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<100;i++){
                    try{
                        Thread.sleep(10);
                        progbar.setProgress(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Intent intent= new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }).start();
    }
}
