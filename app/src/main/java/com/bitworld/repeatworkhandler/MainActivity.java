package com.bitworld.repeatworkhandler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int TIME_INTERVAL = 1000;
    int mCount = 0;

    ProgressBar progressBar;
    TextView messageView;

    Handler mHandler = new Handler();

    Runnable updateRunnable = new Runnable() {
        @Override
        public void run() {
            if (mCount<20) {
                progressBar.setProgress(mCount * 5);
                messageView.setText("progress : " + mCount * 5);
                mCount++;
                mHandler.postDelayed(this, TIME_INTERVAL);
            } else {
                progressBar.setProgress(100);
                messageView.setText("progress done");
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        messageView = (TextView) findViewById(R.id.messageView);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCount = 0;
                mHandler.removeCallbacks(updateRunnable);
                mHandler.post(updateRunnable);
            }
        });

    }
}