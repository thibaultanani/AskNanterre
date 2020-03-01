package com.example.asknanterre;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustomAdapterQuiz extends BaseAdapter implements ListAdapter {

    private ArrayList<String> list1 = new ArrayList<String>();
    private ArrayList<String> list2 = new ArrayList<String>();
    private Context context;
    private boolean checkExists = false;
    private Quiz tmp;
    private String tmpKey;


    public CustomAdapterQuiz(ArrayList<String> list1, ArrayList<String> list2, Context context) {
        this.list1 = list1;
        this.list2 = list2;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list1.size();
    }

    @Override
    public Object getItem(int pos) {
        return list1.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_quiz, null);
        }


        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.textView_namequiz);
        listItemText.setText(list1.get(position));

        listItemText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference postRef = FirebaseDatabase.getInstance().getReference().child("quiz");


                postRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterable<DataSnapshot> answerChildren = dataSnapshot.getChildren();

                        for (DataSnapshot answer: answerChildren) {
                            Quiz q = answer.getValue(Quiz.class);      //make a model User with necessary fields
                            Log.v("questId", q.questionId);
                            if(q.questionId.equals(list2.get(position))){
                                checkExists = true;
                                tmp = q;
                                tmpKey = answer.getKey();
                            }
                        }
                        Intent intent;
                        intent = new Intent(context,DisplayAnswerQuiz.class);
                        Bundle b = new Bundle();
                        b.putString("key", list2.get(position)); //Your id
                        b.putString("name", list1.get(position));
                        intent.putExtras(b); //Put your id to your next Intent
                        context.startActivity(intent);


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });


        return view;
    }
}
