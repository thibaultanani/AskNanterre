package com.example.asknanterre;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class AddAnswer2 extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Menu itemToHide;
    private DatabaseReference mDatabase;
    private static final String TAG = "AddAnswer";
    Spinner spinner;
    EditText edit;
    EditText editText;
    TextView textView;
    TextView question;
    String text;
    DatabaseReference ref;
    String coursId;
    ListView myListView;
    ArrayList<String> list;
    String[] c1;
    String[] c2;
    boolean[] c3;
    String currentVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addanswerclose);

        //spinner = (Spinner) findViewById(R.id.spinner1);
        //text = spinner.getSelectedItem().toString();
        myListView = (ListView) findViewById(R.id.myListView);
        myListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        Bundle b = getIntent().getExtras();
        final String questionId;
        questionId = b.getString("key");

        question = (TextView) findViewById(R.id.question);
        question.setText(b.getString("name"));

        editText = (EditText) findViewById(R.id.lname);
        textView = (TextView) findViewById(R.id.ltext);
        coursId= b.getString("idcours");

        textView.setVisibility(View.GONE);
        editText.setVisibility(View.GONE);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Log.v("Exemple", database.toString());

        ref=FirebaseDatabase.getInstance().getReference().child("qcm");

        final List<String> list_ = new ArrayList<String>();
        /*final List<String> spinnerArray =  new ArrayList<String>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);*/
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String product = ds.getKey();
                    if(ds.getValue(QCM.class).questionId.equals(questionId)) {
                        //spinnerArray.add(ds.getValue(QCM.class).rep);
                        list_.add(ds.getValue(QCM.class).rep);
                        Log.d("HEY", product);
                    }
                    Log.d("TAG", product);
                }
                //spinnerArray.add("Réponse personalisée");
                list_.add(getString(R.string.reponse_personalisee));
                //adapter.notifyDataSetChanged();
                c1 = new String[list_.size()];
                c2 = new String[list_.size()];
                c3 = new boolean[list_.size()];

                for(int i=0; i<list_.size(); i++) {
                    c1[i] = list_.get(i);
                    c2[i] = "" + i;
                    c3[i] = false;
                    Log.d("Je suis ici", "lalalalalaal");
                }

                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(AddAnswer2.this, android.R.layout.simple_list_item_1, c1);
                ArrayList<String> list1 = new ArrayList(Arrays.asList(c1));
                ArrayList<String> list2 = new ArrayList(Arrays.asList(c2));
                ArrayList<Boolean> list3 = new ArrayList(Arrays.asList(c3));

                CustomAdapterAddAnswer2 adapt = new CustomAdapterAddAnswer2(list1, list2, list3,  AddAnswer2.this);
                myListView.setAdapter(adapt);
                adapt.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        ref.addListenerForSingleValueEvent(eventListener);

        //spinner.setAdapter(adapter);

        /*spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                if(position!=(spinnerArray.size()-1)) {
                    textView.setVisibility(View.GONE);
                    editText.setVisibility(View.GONE);
                }
                else {
                    textView.setVisibility(View.VISIBLE);
                    editText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });*/
    }

    public String getCheckBoxValue() {
        CheckBox cb;
        TextView txt;
        for (int x = 0; x<myListView.getChildCount();x++){
            cb = (CheckBox)myListView.getChildAt(x).findViewById(R.id.list_view_item_checkbox);
            txt = (TextView) myListView.getChildAt(x).findViewById(R.id.list_view_item_text);
            if(cb.isChecked()){
                currentVal = txt.getText().toString();
                return txt.getText().toString();
            }
        }
        return "";
    }

    public boolean allUnchecked() {
        int cpt = 0;
        CheckBox cb;
        for (int x = 0; x<myListView.getChildCount();x++){
            cb = (CheckBox)myListView.getChildAt(x).findViewById(R.id.list_view_item_checkbox);
            if(cb.isChecked()) {
                cpt++;
            }
        }
        if(cpt>0) {
            return false;
        }
        else {
            return true;
        }
    }

    public void textVisibility(int position, boolean checked) {
        if((position == (myListView.getAdapter().getCount()-1))&& checked) {
            textView.setVisibility(View.VISIBLE);
            editText.setVisibility(View.VISIBLE);
        }
        else {
            textView.setVisibility(View.GONE);
            editText.setVisibility(View.GONE);
        }
    }

    public void valider(View v) {

        EditText name = (EditText) findViewById(R.id.lname);
        TextView text = (TextView) findViewById(R.id.ltext);
        String s;

        Bundle b = getIntent().getExtras();
        String questionId;
        questionId = b.getString("key");

        if (allUnchecked()) {
            Toasty.error(AddAnswer2.this, getString(R.string.Il_faut_au_moins_choisir_une_reponse), Toast.LENGTH_LONG).show();
        }
        else {
            Answer a;
            if (editText.getVisibility() != View.VISIBLE) {
                a = new Answer(getCheckBoxValue(), questionId);
                s = getCheckBoxValue();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                Log.v("Exemple", database.toString());

                database.getReference("answer").push().setValue(a);

                DatabaseReference ref;
                ref = FirebaseDatabase.getInstance().getReference().child("question").child(questionId);
                Map<String, Object> questionMap = new HashMap<String, Object>();
                questionMap.put("repondu", true);
                ref.updateChildren(questionMap);

                Toasty.success(this, getString(R.string.la_reponse) + s + getString(R.string.a_ete_ajoutee), Toast.LENGTH_LONG).show();

                name.setText("");

                Bundle b2 = new Bundle();
                b2.putString("key", coursId);
                Log.v("valeur du cours Id", coursId);
                Intent intent = new Intent(this, DisplayQuestionProf.class);
                intent.putExtras(b2);
                startActivity(intent);
            } else {
                if (name.getText().toString().isEmpty()) {
                    Toasty.error(this, getString(R.string.Le_nom_de_la_reponse), Toast.LENGTH_LONG).show();
                }
                else {
                    a = new Answer(name.getText().toString(), questionId);
                    s = name.getText().toString();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    Log.v("Exemple", database.toString());

                    database.getReference("answer").push().setValue(a);

                    DatabaseReference ref;
                    ref = FirebaseDatabase.getInstance().getReference().child("question").child(questionId);
                    Map<String, Object> questionMap = new HashMap<String, Object>();
                    questionMap.put("repondu", true);
                    ref.updateChildren(questionMap);

                    Toasty.success(this, getString(R.string.la_reponse) + s + getString(R.string.a_ete_ajoutee), Toast.LENGTH_LONG).show();

                    name.setText("");

                    Bundle b2 = new Bundle();
                    b2.putString("key", coursId);
                    Log.v("valeur du cours Id", coursId);
                    Intent intent = new Intent(this, DisplayQuestionProf.class);
                    intent.putExtras(b2);
                    startActivity(intent);
                }
            }

        }
    }

    public void goToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToProfUIActivity(){
        Intent intent = new Intent(this, ProfessorUI.class);
        startActivity(intent);
    }

    public void annuler(View v) {
        finish();
    }
}
