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
    }

    public void addUser(View v){
        Intent intent = new Intent(this, AddUser.class);
        startActivity(intent);
    }

    public void connection(View v){
        Intent intent = new Intent(this, ConnectionActivity.class);
        startActivity(intent);
    }
}
