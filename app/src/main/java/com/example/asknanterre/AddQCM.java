package com.example.asknanterre;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddQCM extends AppCompatActivity {

    Spinner spinner;
    String text;
    EditText edit;
    LinearLayout ll;
    LinearLayout.LayoutParams lp;
    Bundle b;
    ArrayList list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addqcm);

        spinner = (Spinner) findViewById(R.id.spinner1);
        text = spinner.getSelectedItem().toString();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                ll = (LinearLayout)findViewById(R.id.mylinearlayout);
                lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                Log.d("Nombre actuel", spinner.getSelectedItem().toString() );

                ll.removeAllViewsInLayout();

                Log.d("Nombre actuel", spinner.getSelectedItem() + "" );
                for(int i=0; i<Integer.parseInt(spinner.getSelectedItem().toString()); i++){
                    edit = new EditText(AddQCM.this);
                    edit.setBackgroundResource(R.drawable.edittext_bg);
                    lp.setMargins(0, 0, 0, 20);
                    ll.addView(edit, lp);
                }

                Log.d("Nombre d'enfants", ll.getChildCount()+"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    public void valider(View v) {

        EditText name = (EditText) findViewById(R.id.lname);

        final Bundle b = getIntent().getExtras();
        final String coursId = b.getString("key");

        Normalizer n = new Normalizer();
        Question q = new Question(n.normalizeNom(name.getText().toString()));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        q.date = formatter.format(date);
        q.titre = n.normalizeTitre(name.getText().toString());
        q.type = 2;
        q.coursId = coursId;

        ArrayList<String> list= new ArrayList<>();
        final int childCount = ll.getChildCount();
        for (int i = 0; i < childCount; i++) {
            EditText v1 = (EditText) ll.getChildAt(i);
            list.add(v1.getText().toString());

        }
        Bundle b2= new Bundle();
        b2.putString("nom",q.nom);
        b2.putString("titre",q.titre);
        b2.putString("date",q.date);
        b2.putStringArrayList("rep",list);
        b2.putString("key",coursId);




        Intent intent = new Intent(this, AddQCMApercu.class);
        intent.putExtras(b2); //Put your id to your next Intent
        startActivity(intent);

    }

    public void annuler(View v) {
        finish();
    }
}
