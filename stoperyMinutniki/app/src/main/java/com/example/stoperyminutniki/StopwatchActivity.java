package com.example.stoperyminutniki;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class StopwatchActivity extends AppCompatActivity {

    private TextView time1, time2;
    private Button start1, stop1, reset1;
    private Button start2, stop2, reset2;

    private boolean running1 = false;
    private boolean running2 = false;
    private int milliseconds1 = 0;
    private int milliseconds2 = 0;

    private Handler handler = new Handler();
    private Runnable runnable1, runnable2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        time1 = findViewById(R.id.stopwatch1_time);
        time2 = findViewById(R.id.stopwatch2_time);

        start1 = findViewById(R.id.stopwatch1_start);
        stop1 = findViewById(R.id.stopwatch1_stop);
        reset1 = findViewById(R.id.stopwatch1_reset);

        start2 = findViewById(R.id.stopwatch2_start);
        stop2 = findViewById(R.id.stopwatch2_stop);
        reset2 = findViewById(R.id.stopwatch2_reset);

        runnable1 = new Runnable() {
            @Override
            public void run() {
                if (running1) {
                    milliseconds1 += 10;
                    updateTime(time1, milliseconds1);
                }
                handler.postDelayed(this, 10);
            }
        };

        runnable2 = new Runnable() {
            @Override
            public void run() {
                if (running2) {
                    milliseconds2 += 10;
                    updateTime(time2, milliseconds2);
                }
                handler.postDelayed(this, 10);
            }
        };

        start1.setOnClickListener(v -> {
            if (!running1) {
                running1 = true;
                handler.post(runnable1);
            }
        });

        stop1.setOnClickListener(v -> running1 = false);

        reset1.setOnClickListener(v -> {
            running1 = false;
            milliseconds1 = 0;
            time1.setText("00:00.00");
        });

        start2.setOnClickListener(v -> {
            if (!running2) {
                running2 = true;
                handler.post(runnable2);
            }
        });

        stop2.setOnClickListener(v -> running2 = false);

        reset2.setOnClickListener(v -> {
            running2 = false;
            milliseconds2 = 0;
            time2.setText("00:00.00");
        });
    }

    private void updateTime(TextView view, int millis) {
        int seconds = (millis / 100) % 60;
        int minutes = (millis / 6000);
        int centis = millis % 100;
        view.setText(String.format("%02d:%02d.%02d", minutes, seconds, centis));
    }
}
