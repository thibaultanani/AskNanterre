package com.example.asknanterre;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StudentUI2 extends AppCompatActivity {

    Bundle b;
    String coursId;
    TextView cours;
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentui2);

        b = getIntent().getExtras();
        coursId = b.getString("key");
        cours = (TextView) findViewById(R.id.txtdashboard2);
        s = cours.getText().toString();
        cours.setText(s + " (" + b.getString("name") + ") ");
    }

    public void addQuestion(View v){
        Intent intent = new Intent(this, AddQuestion.class);
        Bundle b2 = new Bundle();
        b2.putString("key", coursId);
        intent.putExtras(b2);
        startActivity(intent);
    }

    public void addQuestionQCM(View v){
        Intent intent = new Intent(this, AddQCM.class);
        Bundle b2 = new Bundle();
        b2.putString("key", coursId);
        intent.putExtras(b2);
        startActivity(intent);
    }
}
