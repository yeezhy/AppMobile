package com.example.cours_groupe2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button boutonCalcul;

    private Button boutonMeilleurScore;
    private Button boutonAPropos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boutonCalcul = findViewById(R.id.boutonCalcul);
        boutonCalcul.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,CalculActivity.class);
            startActivity(intent);
        });

        boutonMeilleurScore= findViewById(R.id.boutonMeilleurScore);
        boutonMeilleurScore.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,LastActivity.class);
            startActivity(intent);
        });
        boutonAPropos = findViewById(R.id.boutonAbout);
        boutonAPropos.setOnClickListener(view ->{
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        });
    }
}