package com.example.asknanterre;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.CompoundButtonCompat;

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

public class DisplayAnswerQuiz extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private static final String TAG = "DisplayAnswerQuiz";
    Spinner spinner;
    EditText edit;
    EditText editText;
    TextView textView;
    TextView question;
    String text;
    ListView myListView;
    DatabaseReference ref;
    DatabaseReference ref2;
    private boolean correct;
    private Context context;
    int nbRep;
    String key3;
    ArrayList<String> list;
    String[] c1;
    String[] c2;
    boolean[] c3;
    String currentVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayanswerquiz);

        //spinner = (Spinner) findViewById(R.id.spinner1);
        //text = spinner.getSelectedItem().toString();
        myListView = (ListView) findViewById(R.id.myListView);
        myListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        Bundle b = getIntent().getExtras();
        final String questionId;
        questionId = b.getString("key");

        updateList();
    }

    public void updateList() {

        Bundle b = getIntent().getExtras();
        final String questionId;
        questionId = b.getString("key");

        question = (TextView) findViewById(R.id.question);
        question.setText(b.getString("name"));

        editText = (EditText) findViewById(R.id.lname);
        textView = (TextView) findViewById(R.id.ltext);

        textView.setVisibility(View.GONE);
        editText.setVisibility(View.GONE);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Log.v("Exemple", database.toString());

        ref=FirebaseDatabase.getInstance().getReference().child("quiz");

        final List<String> list_ = new ArrayList<String>();

        //final List<String> spinnerArray =  new ArrayList<String>();
        /*final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, R.layout.spinner_item,R.id.textdropdown, spinnerArray);*/
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String product = ds.getKey();
                    if(ds.getValue(Quiz.class).questionId.equals(questionId)) {
                        //spinnerArray.add(ds.getValue(Quiz.class).rep);
                        list_.add(ds.getValue(Quiz.class).rep);
                        //l.addView(ch);

                        Log.d("HEY", product);
                    }
                    Log.d("TAG", product);
                }
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

                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(DisplayAnswerQuiz.this, android.R.layout.simple_list_item_1, c1);
                ArrayList<String> list1 = new ArrayList(Arrays.asList(c1));
                ArrayList<String> list2 = new ArrayList(Arrays.asList(c2));
                ArrayList<Boolean> list3 = new ArrayList(Arrays.asList(c3));

                CustomAdapterAnswerQuiz adapt = new CustomAdapterAnswerQuiz(list1, list2, list3,  DisplayAnswerQuiz.this);
                myListView.setAdapter(adapt);
                adapt.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        ref.addListenerForSingleValueEvent(eventListener);

        //spinner.setAdapter(adapter);

    }

    public int getCheckBoxPos() {
        CheckBox cb;
        TextView txt;
        for (int x = 0; x<myListView.getChildCount();x++){
            cb = (CheckBox)myListView.getChildAt(x).findViewById(R.id.list_view_item_checkbox);
            txt = (TextView) myListView.getChildAt(x).findViewById(R.id.list_view_item_text);
            if(cb.isChecked()){
                currentVal = txt.getText().toString();
                return x;
            }
        }
        return -1;
    }

    public void valider(View v) {

       /* EditText name = (EditText) findViewById(R.id.lname);
        TextView text = (TextView) findViewById(R.id.ltext);
        String s;

        Bundle b = getIntent().getExtras();
        String questionId;
        questionId = b.getString("key");

        Answer a;
        if(editText.getVisibility() != View.VISIBLE) {
            a = new Answer(spinner.getSelectedItem().toString(), questionId);
            s = spinner.getSelectedItem().toString();
        }
        else {
            a = new Answer(name.getText().toString(), questionId);
            s = name.getText().toString();
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Log.v("Exemple", database.toString());

        database.getReference("answer").push().setValue(a);


        Toast.makeText(this, "la Réponse: " + s + " a été ajoutée", Toast.LENGTH_LONG).show();

        name.setText("");

        Intent intent = new Intent(this, DisplayQuestionProf.class);
        startActivity(intent);*/

        EditText name = (EditText) findViewById(R.id.lname);
        TextView text = (TextView) findViewById(R.id.ltext);
        String s;
        final int position;

        Bundle b = getIntent().getExtras();
        final String questionId;
        questionId = b.getString("key");
        Log.v("questID", questionId);

        //s = spinner.getSelectedItem().toString();
        //spinner = (Spinner) findViewById(R.id.spinner1);
        //position = spinner.getSelectedItemPosition();
        position = getCheckBoxPos();
        //Log.d("position1", ""+spinner.getSelectedItemPosition());
        Log.d("position2", ""+getCheckBoxPos());
        Log.v("Pos", ""+position);
        ref2 = FirebaseDatabase.getInstance().getReference().child("quiz");
        final String key2 = ref2.getKey();
        ValueEventListener valueEventListener2 = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    Log.d("key recu2", ds.getKey());
                    Quiz q = ds.getValue(Quiz.class);
                    if(q.questionId.equals(questionId)){
                        Log.d("key recu2.1", "tour de boucle" + i);
                        Log.d("key recu2.2", "bon id");
                        if(position == i) {
                            Log.d("ok et correct =", ""+q.correct);
                            if(q.correct) {
                                correct = true;
                                nbRep = q.nbRep;
                                key3 = ds.getKey();
                            }
                            else {
                                correct = false;
                                nbRep = q.nbRep;
                                key3 = ds.getKey();
                            }
                            i++;
                        }
                        else {
                            i++;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        ref2.addListenerForSingleValueEvent(valueEventListener2);

        ref = FirebaseDatabase.getInstance().getReference().child("questionProf").child(questionId);
        final String key = ref.getKey();
        Log.d("key originel", key);
        final Map<String,Object> questionProfMap = new HashMap<String,Object>();
        final Map<String,Object> quizMap = new HashMap<String,Object>();
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    Log.d("key recu", ds.getKey());
                    if(correct) {
                        if (ds.getKey().equals("ncorrects")) {
                            questionProfMap.put("ncorrects", ds.getValue(Integer.class) + 1);
                            ref.updateChildren(questionProfMap);
                            quizMap.put("nbRep", nbRep + 1);
                            ref2.child(key3).updateChildren(quizMap);
                            //Toast.makeText(DisplayAnswerQuiz.this, getString(R.string.la_reponse) + spinner.getSelectedItem().toString() + getString(R.string.est_correcte), Toast.LENGTH_LONG).show();
                            Toast.makeText(DisplayAnswerQuiz.this, getString(R.string.la_reponse)  + currentVal + getString(R.string.est_correcte), Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        if (ds.getKey().equals("nfalses")) {
                            questionProfMap.put("nfalses", ds.getValue(Integer.class) + 1);
                            ref.updateChildren(questionProfMap);
                            quizMap.put("nbRep", nbRep + 1);
                            ref2.child(key3).updateChildren(quizMap);
                            //Toast.makeText(DisplayAnswerQuiz.this, getString(R.string.la_reponse) + spinner.getSelectedItem().toString() + getString(R.string.est_fausse), Toast.LENGTH_LONG).show();
                            Toast.makeText(DisplayAnswerQuiz.this, getString(R.string.la_reponse)  + currentVal + getString(R.string.est_fausse), Toast.LENGTH_LONG).show();
                        }
                    }
                    Log.d("TAG", "tour de boucle");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        ref.addListenerForSingleValueEvent(valueEventListener);

        if (context instanceof DisplayQuiz) {
            ((DisplayQuiz)context).updateList();
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after 5 seconds
                finish();
            }
        }, 1000);
    }

    public void annuler(View v) {
        finish();
    }

    public void goToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToProfUIActivity(){
        Intent intent = new Intent(this, ProfessorUI.class);
        startActivity(intent);
    }

}
