package com.example.asknanterre;

import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;

public class DisplayAnswer extends AppCompatActivity {
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
    TextView question;
    TextView reponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayanswer);

        b = getIntent().getExtras();

        question = (TextView) findViewById(R.id.question);
        reponse = (TextView) findViewById(R.id.answer);

        question.setText(b.getString("name"));
        reponse.setText(b.getString("nameAnswer"));


    }
}
