package com.example.asknanterre;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adduser);
    }

    public void valider(View v) {

        EditText name = (EditText) findViewById(R.id.name);
        EditText firstname = (EditText) findViewById(R.id.firstname);
        EditText email = (EditText) findViewById(R.id.email);

        User u = new User(name.getText().toString(), firstname.getText().toString(), email.getText().toString());
        u.save();

        Toast.makeText(this, "l'Utilisateur: " + name.getText() + " " +
                firstname.getText() + " " + email.getText()  +
                " a été ajoutée", Toast.LENGTH_LONG).show();

        name.setText("");
        firstname.setText("");
        email.setText("");
    }
}
