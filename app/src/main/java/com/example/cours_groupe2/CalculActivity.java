package com.example.cours_groupe2;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cours_groupe2.DAO.ScoreBaseHelper;
import com.example.cours_groupe2.DAO.ScoreDao;

public class CalculActivity extends AppCompatActivity {
    private TextView textViewRep;
    private TextView textViewCal;
    private MenuItem score;
    private Integer nbrScore=0;
    private MenuItem vie;
    private Integer nbrVie=3;

    private Button boutonVerif;
    private Button boutonClear;
    private Button bouton1;
    private Button bouton2;
    private Button bouton3;
    private Button bouton4;
    private Button bouton5;
    private Button bouton6;
    private Button bouton7;
    private Button bouton8;
    private Button bouton9;
    private Button bouton0;

    private Integer premierTerme = 0;
    private Integer deuxiemeTerme = 0;
    private TypeOperationEnum typeOperation;
    private Integer resultat = 0;
    private Integer calculerResultat = 0;

    private ScoreDao calculDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);

        calculDao= new ScoreDao(new ScoreBaseHelper(this,"BDD",1));

        textViewCal = findViewById(R.id.textViewCal);
        textViewRep = findViewById(R.id.textViewRep);

        bouton0 = findViewById(R.id.buttonZero);
        bouton0.setOnClickListener(view -> {
            ajouterChiffre(0);
        });
        bouton1 = findViewById(R.id.button_1);
        bouton1.setOnClickListener(view -> {
            ajouterChiffre(1);
        });
        bouton2 = findViewById(R.id.button_2);
        bouton2.setOnClickListener(view -> {
            ajouterChiffre(2);
        });
        bouton3 = findViewById(R.id.button_3);
        bouton3.setOnClickListener(view -> {
            ajouterChiffre(3);
        });
        bouton4 = findViewById(R.id.button_4);
        bouton4.setOnClickListener(view -> {
            ajouterChiffre(4);
        });
        bouton5 = findViewById(R.id.button_5);
        bouton5.setOnClickListener(view -> {
            ajouterChiffre(5);
        });
        bouton6 = findViewById(R.id.button_6);
        bouton6.setOnClickListener(view -> {
            ajouterChiffre(6);
        });
        bouton7 = findViewById(R.id.button_7);
        bouton7.setOnClickListener(view -> {
            ajouterChiffre(7);
        });
        bouton8 = findViewById(R.id.button_8);
        bouton8.setOnClickListener(view -> {
            ajouterChiffre(8);
        });
        bouton9 = findViewById(R.id.button_9);
        bouton9.setOnClickListener(view -> {
            ajouterChiffre(9);
        });

        boutonVerif = findViewById(R.id.button_verif);
        boutonVerif.setOnClickListener(view -> {
            verifieLeCalcul();
        });
        boutonClear = findViewById(R.id.button_clear);
        boutonClear.setOnClickListener(view -> {
            calculerResultat= calculerResultat/10;
            majTextRep();
        });

        genererCalcul();
        majTextView();
        calculerResultat=0;
        majTextRep();
    }

    private void genererCalcul() {
        Integer typeOpera;
        Random random = new Random();
        premierTerme = random.nextInt(10) + 1;
        deuxiemeTerme = random.nextInt(10) + 1;
        typeOpera = random.nextInt(4);

        switch (typeOpera) {
            case 0:
                typeOperation = TypeOperationEnum.ADD;
                resultat = premierTerme+deuxiemeTerme;
                break;
            case 1:
                typeOperation = TypeOperationEnum.SUBSTRACT;
                resultat = premierTerme-deuxiemeTerme;
                break;
            case 2:
                typeOperation = TypeOperationEnum.MULTIPLY;
                resultat = premierTerme*deuxiemeTerme;
                break;
            case 3:
                typeOperation = TypeOperationEnum.DIVIDE;
                resultat = premierTerme;
                premierTerme = resultat*deuxiemeTerme;
                break;
        }
    }

    private void ajouterChiffre(Integer chiffre) {
        if(calculerResultat<=999){
            calculerResultat=10*calculerResultat+chiffre;
            majTextRep();
        }else{
            Toast.makeText(this,getString(R.string.ERROR_NUMBER_TOO_HIGH), Toast.LENGTH_LONG).show();
        }
    }

    private void majTextView(){
        textViewCal.setText(premierTerme + typeOperation.getSymbole() + deuxiemeTerme);
    }

    private void majTextRep(){
        textViewRep.setText(""+calculerResultat);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar,menu);
        score = menu.findItem(R.id.toolbar_score);
        this.score.setTitle("Score : "+nbrScore);
        vie = menu.findItem(R.id.toolbar_vie);
        this.vie.setTitle("Vies : "+nbrVie);
        return super.onCreateOptionsMenu(menu);
    }

    private void verifieLeCalcul(){
        if(calculerResultat==resultat){
            nbrScore++;
            this.score.setTitle("Score : "+nbrScore);
        }else{
            nbrVie--;
            this.vie.setTitle("Vies : "+nbrVie);
        }

        if(nbrVie<=0){
            Intent intent = new Intent(CalculActivity.this, ResulstatActivity.class);
            intent.putExtra("SCORE", nbrScore);
            startActivity(intent);
        }

        genererCalcul();
        majTextView();
        calculerResultat=0;
        majTextRep();
    }


}