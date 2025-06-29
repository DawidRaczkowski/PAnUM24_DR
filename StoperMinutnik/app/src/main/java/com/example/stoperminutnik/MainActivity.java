package com.example.stoperminutnik;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button buttonStoper, buttonMinutnik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStoper = findViewById(R.id.buttonStoper);
        buttonMinutnik = findViewById(R.id.buttonMinutnik);

        buttonStoper.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, StoperActivity.class);
            startActivity(intent);
        });

        buttonMinutnik.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MinutnikActivity.class);
            startActivity(intent);
        });
    }
}
