package com.example.asknanterre;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /* public void addUser(View v){
        Intent intent = new Intent(this, AddUser.class);
        startActivity(intent);
    }

    public void connection(View v){
        Intent intent = new Intent(this, ConnectionActivity.class);
        startActivity(intent);
    }*/

    /*public void addQuestion(View v){
        Intent intent = new Intent(this, AddQuestion.class);
        startActivity(intent);
    }

    public void displayQuestion(View v){
        Intent intent = new Intent(this, DisplayQuestion.class);
        startActivity(intent);
    }*/

    public void professorUI(View v){
        Intent intent = new Intent(this, ProfessorUI2.class);
        startActivity(intent);
    }

    public void studentUI(View v){
        Intent intent = new Intent(this, StudentUI3.class);
        startActivity(intent);
    }

}
