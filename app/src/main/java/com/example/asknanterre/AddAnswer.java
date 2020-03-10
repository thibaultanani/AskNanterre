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

import java.util.HashMap;
import java.util.Map;

public class AddAnswer extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Menu itemToHide;
    private DatabaseReference mDatabase;
    private static final String TAG = "AddAnswer";
    TextView question;
    String coursId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addansweropen);

        Bundle b = getIntent().getExtras();
        question = (TextView) findViewById(R.id.question);
        question.setText(b.getString("name"));
        coursId= b.getString("idcours");
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

        DatabaseReference ref;
        ref=FirebaseDatabase.getInstance().getReference().child("question").child(questionId);
        Map<String,Object> questionMap = new HashMap<String,Object>();
        questionMap.put("repondu", true);
        ref.updateChildren(questionMap);

        Toast.makeText(this, "La réponse: \"" + name.getText() + "\" a été ajoutée", Toast.LENGTH_LONG).show();

        name.setText("");
        Bundle b2= new Bundle();
        b2.putString("key",coursId);
        Intent intent = new Intent(this, DisplayQuestionProf.class);
        intent.putExtras(b2);
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

    public void annuler(View v) {
        finish();
    }
}
