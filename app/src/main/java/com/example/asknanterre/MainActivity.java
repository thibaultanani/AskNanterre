package com.example.asknanterre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Question q = new Question ();
        q.save();
        Answer r = new Answer();
        r.save();
    }

    public void addQuestion(View v){
        Intent intent = new Intent(this, AddQuestion.class);
        startActivity(intent);
    }

    public void displayQuestion(View v){

        Intent intent = new Intent(this,DisplayQuestion.class);
        startActivity(intent);
    }



    //
}