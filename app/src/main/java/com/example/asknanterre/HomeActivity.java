package com.example.asknanterre;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void addQuestion(View v){
        Intent intent = new Intent(this, AddQuestion.class);
        startActivity(intent);
    }

    public void displayQuestion(View v){
        Intent intent = new Intent(this, DisplayQuestion.class);
        startActivity(intent);
    }

    public void addCours(View v) {
        Intent intent = new Intent(this, AddCours.class);
        startActivity(intent);
    }

    public void displayCours(View v) {
        Intent intent = new Intent(this, DisplayCours.class);
        startActivity(intent);
    }
}
