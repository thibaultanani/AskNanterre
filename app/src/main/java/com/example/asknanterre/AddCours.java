package com.example.asknanterre;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddCours extends AppCompatActivity {

    Bundle b;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcours);
    }

    public void valider_cours(View v) {

        EditText name = (EditText) findViewById(R.id.lname);

        Normalizer n = new Normalizer();
        Cours c = new Cours(n.normalizeNom(name.getText().toString()));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        c.date = formatter.format(date);
        c.titre = n.normalizeTitre(name.getText().toString());

        Bundle b=new Bundle();
        b.putString("nom",c.nom);
        b.putString("titre",c.titre);
        b.putString("date",c.date);
        Intent intent = new Intent(context, AddCoursApercu.class);
        intent.putExtras(b); //Put your id to your next Intent
        context.startActivity(intent);



    }

    public void annuler(View v) {
        finish();
    }
}
