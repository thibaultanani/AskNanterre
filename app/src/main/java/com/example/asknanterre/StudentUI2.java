package com.example.asknanterre;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class StudentUI2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentui2);
    }

    public void addQuestion(View v){
        Intent intent = new Intent(this, AddQuestion.class);
        startActivity(intent);
    }

    public void addQuestionQCM(View v){
        Intent intent = new Intent(this, AddQCM.class);
        startActivity(intent);
    }
}
