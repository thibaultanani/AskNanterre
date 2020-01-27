package com.example.asknanterre;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddQuestion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addquestion);
    }

    public void valider(View v) {

        EditText name = (EditText) findViewById(R.id.lname);

        Question q = new Question(name.getText().toString());
        q.save();

        Toast.makeText(this, "la Question: " + name.getText() + " a été ajoutée", Toast.LENGTH_LONG).show();

        name.setText("");
    }
}