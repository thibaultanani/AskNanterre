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
import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;

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

        if (!Pattern.matches("[a-zA-ZÀ-ÿ0-9]+\\s?[ _\\-&a-zA-ZÀ-ÿ0-9]+$", name.getText().toString())) {
            Toasty.error(this, getString(R.string.Le_nom_du_cours), Toast.LENGTH_LONG).show();
        }
        else {
            Normalizer n = new Normalizer();
            Cours c = new Cours(n.normalizeNom(name.getText().toString()));
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            c.date = formatter.format(date);
            c.titre = n.normalizeTitre(name.getText().toString());
            c.visible = false;

            Bundle b = new Bundle();
            b.putString("nom", c.nom);
            b.putString("titre", c.titre);
            b.putString("date", c.date);
            b.putBoolean("visible", c.visible);
            Intent intent = new Intent(this, AddCoursApercu.class);
            intent.putExtras(b); //Put your id to your next Intent
            startActivity(intent);
        }


    }

    public void annuler(View v) {
        finish();
    }
}
