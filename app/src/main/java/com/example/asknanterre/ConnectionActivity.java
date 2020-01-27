package com.example.asknanterre;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ConnectionActivity extends AppCompatActivity {

    EditText email;
    Button submit;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        submit = (Button)findViewById(R.id.button);
        email = (EditText)findViewById(R.id.email);

        submit.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Log.v("EditText", email.getText().toString());
                    }
                });
    }

}
