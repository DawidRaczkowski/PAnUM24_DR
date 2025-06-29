package com.example.konwerterliczb;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText inputNumber;
    Button convertButton;
    TextView resultText;

    EditText inputRoman;
    Button convertToArabicButton;
    TextView resultArabicText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputNumber = findViewById(R.id.inputNumber);
        convertButton = findViewById(R.id.convertButton);
        resultText = findViewById(R.id.resultText);

        inputRoman = findViewById(R.id.inputRoman);
        convertToArabicButton = findViewById(R.id.convertToArabicButton);
        resultArabicText = findViewById(R.id.resultArabicText);

        // Arabski ➝ Rzymski
        convertButton.setOnClickListener(view -> {
            String input = inputNumber.getText().toString();
            if (!input.isEmpty()) {
                try {
                    int number = Integer.parseInt(input);
                    String roman = convertToRoman(number);
                    resultText.setText("Wynik: " + roman);
                } catch (NumberFormatException e) {
                    resultText.setText("Niepoprawna liczba!");
                }
            } else {
                resultText.setText("Pole jest puste!");
            }
        });

        
        convertToArabicButton.setOnClickListener(view -> {
            String romanInput = inputRoman.getText().toString().toUpperCase().trim();
            if (!romanInput.isEmpty()) {
                try {
                    int arabic = convertToArabic(romanInput);
                    resultArabicText.setText("Wynik: " + arabic);
                } catch (Exception e) {
                    resultArabicText.setText("Błąd! Sprawdź zapis.");
                }
            } else {
                resultArabicText.setText("Pole jest puste!");
            }
        });
    }

    private String convertToRoman(int number) {
        if (number <= 0 || number > 3999) return "Zakres: 1 - 3999";

        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] values =     {1000, 900, 500, 400, 100, 90,  50,  40,  10,   9,   5,   4,   1};

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (number >= values[i]) {
                number -= values[i];
                result.append(romans[i]);
            }
        }

        return result.toString();
    }

    private int convertToArabic(String roman) {
        int result = 0;
        int prevValue = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            char c = roman.charAt(i);
            int value = romanCharToInt(c);

            if (value < prevValue) {
                result -= value;
            } else {
                result += value;
            }

            prevValue = value;
        }

        return result;
    }

    private int romanCharToInt(char c) {
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: throw new IllegalArgumentException("Nieprawidłowy znak: " + c);
        }
    }
}
