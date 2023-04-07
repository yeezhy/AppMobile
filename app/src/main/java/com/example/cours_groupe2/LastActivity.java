package com.example.cours_groupe2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.cours_groupe2.DAO.ScoreBaseHelper;
import com.example.cours_groupe2.DAO.ScoreDao;
import com.example.cours_groupe2.model.entities.Score;

import java.util.List;


public class LastActivity extends AppCompatActivity {

    private ScoreDao scoreDao;
    TextView textViewPremier;
    TextView textViewDeuxième;
    TextView textViewTroisième;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);
        scoreDao = new ScoreDao(new ScoreBaseHelper(this,"BDD",1));
        textViewPremier = findViewById(R.id.textViewPremier);
        textViewDeuxième = findViewById(R.id.textViewDeuxième);
        textViewTroisième = findViewById(R.id.textViewTroisième);
        //Score monScore = scoreDao.lastOrNull();
        List<Score> items = (List<Score>) scoreDao.lastOrNull();
        Score score1;
        Score score2;
        Score score3;
        switch ( items.size())
        {
            case 3 :
                score1 = items.get(0);
                score2 = items.get(1);
                score3 = items.get(2);
                textViewPremier.setText(score1.getNom()+ "  :  "+ score1.getScore());
                textViewDeuxième.setText(score2.getNom()+ "  :  "+ score2.getScore());
                textViewTroisième.setText(score3.getNom()+ "  :  "+ score3.getScore());
                break;
            case 2:
                score1 = items.get(0);
                score2 = items.get(1);
                textViewPremier.setText(score1.getNom()+ "  :  "+ score1.getScore());
                textViewDeuxième.setText(score2.getNom()+ "  :  "+ score2.getScore());
                break;
            case 1:
                score1 = items.get(0);
                textViewPremier.setText(score1.getNom()+ "  :  "+ score1.getScore());
                break;
            default:
                break;

        }


    }
}