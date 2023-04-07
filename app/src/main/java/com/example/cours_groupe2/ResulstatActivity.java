package com.example.cours_groupe2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;
import com.example.cours_groupe2.model.entities.Score;

public class ResulstatActivity extends AppCompatActivity {


    private String nomJoueur;
    private Integer score;

    private Button boutonSave;

    private TextView textviewScore;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resulstat);

        boutonSave = findViewById(R.id.button_save);
        textviewScore = findViewById(R.id.textViewScore);
        Intent intent =getIntent();
        String afficheScore = intent.getStringExtra("SCORE");
        textviewScore.setText("Score : "+afficheScore);
        this.score = Integer.parseInt(afficheScore);

        boutonSave.setOnClickListener(view ->{
            nomJoueur = textviewScore.getHint().toString();
            ajouterBDD();
            Intent intention = new Intent(ResulstatActivity.this, MainActivity.class);
            startActivity(intention);
        });
    }

    private boolean ajouterBDD(){
        Score monScore =new Score();
        monScore.setScore(this.score);
        monScore.setNom(this.nomJoueur);
        //ScoreDao.create(monScore);

        return true;
    }
}