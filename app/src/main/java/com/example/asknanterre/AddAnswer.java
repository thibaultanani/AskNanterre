package com.example.asknanterre;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddAnswer extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Menu itemToHide;
    private DatabaseReference mDatabase;
    private static final String TAG = "AddAnswer";
    TextView question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addansweropen);

        Bundle b = getIntent().getExtras();
        question = (TextView) findViewById(R.id.question);
        question.setText(b.getString("name"));
    }

    public void valider(View v) {

        EditText name = (EditText) findViewById(R.id.lname);

        Bundle b = getIntent().getExtras();
        String questionId;
        questionId = b.getString("key");

        Answer a = new Answer(name.getText().toString(), questionId);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Log.v("Exemple", database.toString());

        database.getReference("answer").push().setValue(a);


        Toast.makeText(this, "la Réponse: " + name.getText() + " a été ajoutée", Toast.LENGTH_LONG).show();

        name.setText("");

        Intent intent = new Intent(this, DisplayQuestionProf.class);
        startActivity(intent);
    }

    public void goToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToProfUIActivity(){
        Intent intent = new Intent(this, ProfessorUI.class);
        startActivity(intent);
    }
}