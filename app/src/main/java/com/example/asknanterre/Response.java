package com.example.asknanterre;

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText

public class Response extends AppCompatActivity {

    @Override
    ResponseButton btn = (Button)findViewById(R.id.button)
            ResponseButton.setOnClickListener(new View.OnClickListener)
    {
        @Override
                public void onClick(View r)
        {
            startActivity(new Intent(getApplicationContext(), Response.class));
        }
    }
}