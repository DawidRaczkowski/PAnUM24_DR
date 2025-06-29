package com.example.stoperyminutniki;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TimerActivity extends AppCompatActivity {

    private TextView timerText1, timerText2;
    private EditText inputMinutes1, inputMinutes2;
    private Button startBtn1, stopBtn1, resetBtn1;
    private Button startBtn2, stopBtn2, resetBtn2;

    private CountDownTimer countDownTimer1, countDownTimer2;
    private long timeLeft1 = 0, timeLeft2 = 0;
    private boolean running1 = false, running2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        timerText1 = findViewById(R.id.timer1_time);
        timerText2 = findViewById(R.id.timer2_time);
        inputMinutes1 = findViewById(R.id.input1);
        inputMinutes2 = findViewById(R.id.input2);

        startBtn1 = findViewById(R.id.timer1_start);
        stopBtn1 = findViewById(R.id.timer1_stop);
        resetBtn1 = findViewById(R.id.timer1_reset);

        startBtn2 = findViewById(R.id.timer2_start);
        stopBtn2 = findViewById(R.id.timer2_stop);
        resetBtn2 = findViewById(R.id.timer2_reset);

        startBtn1.setOnClickListener(v -> {
            if (!running1) {
                String input = inputMinutes1.getText().toString();
                if (!input.isEmpty()) {
                    timeLeft1 = Long.parseLong(input) * 1000;
                    startTimer1();
                }
            }
        });

        stopBtn1.setOnClickListener(v -> {
            if (countDownTimer1 != null) {
                countDownTimer1.cancel();
                running1 = false;
            }
        });

        resetBtn1.setOnClickListener(v -> {
            timeLeft1 = 0;
            updateTimerDisplay(timerText1, timeLeft1);
        });

        startBtn2.setOnClickListener(v -> {
            if (!running2) {
                String input = inputMinutes2.getText().toString();
                if (!input.isEmpty()) {
                    timeLeft2 = Long.parseLong(input) * 1000;
                    startTimer2();
                }
            }
        });

        stopBtn2.setOnClickListener(v -> {
            if (countDownTimer2 != null) {
                countDownTimer2.cancel();
                running2 = false;
            }
        });

        resetBtn2.setOnClickListener(v -> {
            timeLeft2 = 0;
            updateTimerDisplay(timerText2, timeLeft2);
        });
    }

    private void startTimer1() {
        running1 = true;
        countDownTimer1 = new CountDownTimer(timeLeft1, 10) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft1 = millisUntilFinished;
                updateTimerDisplay(timerText1, millisUntilFinished);
            }

            @Override
            public void onFinish() {
                running1 = false;
            }
        }.start();
    }

    private void startTimer2() {
        running2 = true;
        countDownTimer2 = new CountDownTimer(timeLeft2, 10) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft2 = millisUntilFinished;
                updateTimerDisplay(timerText2, millisUntilFinished);
            }

            @Override
            public void onFinish() {
                running2 = false;
            }
        }.start();
    }

    private void updateTimerDisplay(TextView view, long millis) {
        int seconds = (int) (millis / 1000);
        int minutes = seconds / 60;
        seconds = seconds % 60;
        int centis = (int) (millis % 1000) / 10;
        view.setText(String.format("%02d:%02d.%02d", minutes, seconds, centis));
    }
}
