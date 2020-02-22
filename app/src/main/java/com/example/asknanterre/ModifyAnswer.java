package com.example.asknanterre;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ModifyAnswer extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Menu itemToHide;
    private DatabaseReference mDatabase;
    private static final String TAG = "AddAnswer";
    DatabaseReference ref;
    EditText name;
    Bundle b;
    String questionId;
    String AnswerId;
    String nameAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifyansweropen);

        b = getIntent().getExtras();
        name = (EditText) findViewById(R.id.lname);
        name.setText(b.getString("nameAnswer"));
    }

    public void modifier(View v) {

        EditText name = (EditText) findViewById(R.id.lname);

        Bundle b = getIntent().getExtras();
        questionId = b.getString("key");
        AnswerId = b.getString("keyAnswer");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Log.v("Exemple", database.toString());

        ref=FirebaseDatabase.getInstance().getReference().child("answer").child(AnswerId);
        Map<String,Object> answerMap = new HashMap<String,Object>();
        answerMap.put("nom", name.getText().toString());
        ref.updateChildren(answerMap);
        Toast.makeText(this, "la réponse: " + name.getText() + " a été mise à jour", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, DisplayQuestionProf.class);
        startActivity(intent);
        //name.setText("");
    }

    public void supprimer(View v) {
        EditText name = (EditText) findViewById(R.id.lname);

        Bundle b = getIntent().getExtras();
        questionId = b.getString("key");
        AnswerId = b.getString("keyAnswer");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Log.v("Exemple", database.toString());

        ref=FirebaseDatabase.getInstance().getReference().child("answer").child(AnswerId);
        ref.removeValue();
        Toast.makeText(this, "la réponse: " + name.getText() + " a été supprimée", Toast.LENGTH_LONG).show();
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
