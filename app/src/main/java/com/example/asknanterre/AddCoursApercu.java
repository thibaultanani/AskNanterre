package com.example.asknanterre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

import es.dmoral.toasty.Toasty;

public class AddCoursApercu extends AppCompatActivity {
    Bundle b;
    TextView nom;
    TextView date;
    TextView titre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cours_apercu);
        b=getIntent().getExtras();
        nom= findViewById(R.id.name_cours);
        date=findViewById(R.id.date_cours);
        titre=findViewById(R.id.titre_cours);

        nom.setText(b.getString("nom"));
        titre.setText(b.getString("titre"));
        date.setText(b.getString("date"));


    }

    public void valider_cours(View v) {

        TextView name =  findViewById(R.id.name_cours);
        TextView titre= findViewById(R.id.titre_cours);


        Normalizer n = new Normalizer();
        Cours c = new Cours(n.normalizeNom(name.getText().toString()));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        c.date =formatter.format(date);
        c.titre = n.normalizeTitre(titre.getText().toString());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Log.v("Exemple", database.toString());

        database.getReference("cours").push().setValue(c);

        Toasty.success(this, getString(R.string.le_cours) + name.getText() + getString(R.string.a_ete_ajoute), Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this,  ProfessorUI2.class);
        startActivity(intent);
    }
}
