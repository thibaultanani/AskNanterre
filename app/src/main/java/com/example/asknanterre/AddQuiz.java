package com.example.asknanterre;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddQuiz extends AppCompatActivity {

    Spinner spinner;
    Spinner spinner2;
    Spinner spinner3;
    String text;
    String text2;
    String text3;
    String bonnerep;
    EditText edit;
    LinearLayout ll;
    LinearLayout.LayoutParams lp;
    ArrayList<String> list;
    ArrayList<String> list2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addquiz);

        spinner = (Spinner) findViewById(R.id.spinner1);
        text = spinner.getSelectedItem().toString();

        final List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("1");
        spinnerArray.add("2");

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                ll = (LinearLayout)findViewById(R.id.mylinearlayout);
                lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                Log.d("Nombre actuel", spinner.getSelectedItem().toString() );

                ll.removeAllViewsInLayout();
                spinnerArray.clear();


                Log.d("Nombre actuel", spinner.getSelectedItem() + "" );
                for(int i=0; i<Integer.parseInt(spinner.getSelectedItem().toString()); i++){
                    edit = new EditText(AddQuiz.this);
                    edit.setBackgroundResource(R.drawable.edittext_bg);
                    lp.setMargins(0, 0, 0, 20);
                    ll.addView(edit, lp);
                    spinnerArray.add(""+(i+1));

                }

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                final Spinner sItems = (Spinner) findViewById(R.id.spinner2);
                sItems.setAdapter(adapter);

                Log.d("Nombre d'enfants", ll.getChildCount()+"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        spinner3 = (Spinner) findViewById(R.id.spinner3);

        final List<String> spinnerArray2 =  new ArrayList<String>();
        spinnerArray2.add("1");
        spinnerArray2.add("2");
        spinnerArray2.add("3");

        final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.spinner3);
        sItems.setAdapter(adapter2);
    }

    public void valider(View v) {

        EditText name = (EditText) findViewById(R.id.lname);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        text2 = spinner2.getSelectedItem().toString();
        text3 = spinner3.getSelectedItem().toString();

        final Bundle b = getIntent().getExtras();
        final String coursId = b.getString("key");

        Normalizer n = new Normalizer();
        QuestionProf q = new QuestionProf(n.normalizeNom(name.getText().toString()));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        q.date = formatter.format(date);
        q.titre = n.normalizeTitre(name.getText().toString());
        q.difficulte = Integer.parseInt(text3);
        q.coursId = coursId;

        ArrayList<String> list= new ArrayList<>();
        ArrayList<String> list2= new ArrayList<>();
        final int childCount = ll.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if(Integer.parseInt(text2)!=(i+1)) {
                EditText v1 = (EditText) ll.getChildAt(i);
                list.add(v1.getText().toString());
                list2.add(v1.getText().toString()+"(Réponse fausse)");
            }
            else {
                EditText v1 = (EditText) ll.getChildAt(i);
                list.add(v1.getText().toString());
                 bonnerep= v1.getText().toString();
                list2.add(v1.getText().toString()+"(Réponse bonne)");
            }

        }


        Bundle b2= new Bundle();
        b2.putString("nom",q.nom);
        b2.putString("titre",q.titre);
        b2.putString("date",q.date);
        b2.putStringArrayList("rep",list);
        b2.putStringArrayList("rep2",list2);
        b2.putString("key",coursId);
        b2.putString("bonnerep",bonnerep);
        b2.putInt("dif",Integer.parseInt(text3));


        Intent intent = new Intent(this, AddQuizApercu.class);
        intent.putExtras(b2); //Put your id to your next Intent
        startActivity(intent);
    }

    public void annuler(View v) {
        finish();
    }
}
