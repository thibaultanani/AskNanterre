package com.example.asknanterre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AnswerQuestion extends AppCompatActivity {
    /*
    @Override
    ResponseButton btn = (Button)findViewById(R.id.button)
            ResponseButton.setOnClickListener(new View.OnClickListener)
    {
        @Override
                public void onClick(View r)
        {
            startActivity(new Intent(getApplicationContext(), AnswerQuestion.class));
        }
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answerquestion);

    }


    /*public void answerQuestion2(View v) {

        EditText name = (EditText) findViewById((R.id.lname));

        Answer r = new Answer(name.getText().toString());
        r.save();

        Toast.makeText(this, "la reponse: " + name.getText() + " a été ajoutée", Toast.LENGTH_LONG).show();

        name.setText("");
    }*/
}