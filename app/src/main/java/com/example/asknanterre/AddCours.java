package com.example.asknanterre;

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

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Log.v("Exemple", database.toString());

        database.getReference("cours").push().setValue(c);


        Toast.makeText(this, "le cours: " + name.getText() + " a été ajoutée", Toast.LENGTH_LONG).show();

        name.setText("");
    }

    public void annuler(View v) {
        finish();
    }
}
