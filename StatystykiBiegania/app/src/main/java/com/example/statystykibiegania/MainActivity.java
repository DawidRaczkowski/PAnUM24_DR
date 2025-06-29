package com.example.statystykibiegania;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText inputTempo, inputDistance;
    Button buttonFromTempo;
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputTempo = findViewById(R.id.inputTempo);
        inputDistance = findViewById(R.id.inputDistance);
        buttonFromTempo = findViewById(R.id.buttonFromTempo);
        resultText = findViewById(R.id.resultText);

        buttonFromTempo.setOnClickListener(view -> {
            String tempoStr = inputTempo.getText().toString();
            String distanceStr = inputDistance.getText().toString();

            if (!tempoStr.isEmpty()) {
                try {
                    double tempoMinPerKm = Double.parseDouble(tempoStr);
                    double speed = 60.0 / tempoMinPerKm; // km/h

                    StringBuilder result = new StringBuilder();
                    result.append("Prędkość: ").append(round(speed)).append(" km/h\n");

                    result.append("Czas maratonu: ").append(formatTime(tempoMinPerKm * 42.195)).append("\n");
                    result.append("Czas półmaratonu: ").append(formatTime(tempoMinPerKm * 21.0975)).append("\n");

                    if (!distanceStr.isEmpty()) {
                        double distance = Double.parseDouble(distanceStr);
                        result.append("Czas na dystans ").append(distance).append(" km: ")
                                .append(formatTime(tempoMinPerKm * distance)).append("\n");
                    }

                    resultText.setText(result.toString());

                } catch (Exception e) {
                    resultText.setText("Błąd w danych.");
                }
            } else {
                resultText.setText("Wprowadź tempo.");
            }
        });
    }

    // Zamienia minuty (np. 85.5 min) na tekst HH:MM:SS
    private String formatTime(double totalMinutes) {
        int totalSeconds = (int) (totalMinutes * 60);
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;
        return hours + "h " + minutes + "m " + seconds + "s";
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
