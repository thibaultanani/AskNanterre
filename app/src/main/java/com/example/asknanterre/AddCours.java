package com.example.asknanterre;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddCours extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcours);
    }

    public void valider_cours(View v) {

        EditText name = (EditText) findViewById(R.id.name_cours);

        Cours c = new Cours(name.getText().toString());
        c.save();

        Toast.makeText(this, "le cours: " + name.getText() + " a été ajoutée", Toast.LENGTH_LONG).show();

        name.setText("");
    }
}
