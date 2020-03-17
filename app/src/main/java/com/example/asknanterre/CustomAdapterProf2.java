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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustomAdapterProf2 extends BaseAdapter implements ListAdapter {

    private ArrayList<String> list1 = new ArrayList<String>();
    private ArrayList<String> list2 = new ArrayList<String>();
    private ArrayList<String> list3 = new ArrayList<String>();
    private ArrayList<String> list4 = new ArrayList<String>();
    private ArrayList<String> list5 = new ArrayList<String>();
    private ArrayList<String> list6 = new ArrayList<String>();
    private ArrayList<String> list7 = new ArrayList<String>();
    private ArrayList<String> list8 = new ArrayList<String>();
    private ArrayList<String> list9 = new ArrayList<String>();
    private Context context;
    private boolean checkExists = false;
    private Answer tmp;
    private String tmpKey;
    private String coursId;


    public CustomAdapterProf2(ArrayList<String> list1, ArrayList<String> list2, ArrayList<String> list3, ArrayList<String> list4, ArrayList<String> list5, ArrayList<String> list6, ArrayList<String> list7, ArrayList<String> list8, ArrayList<String> list9, String coursId, Context context) {
        this.list1 = list1;
        this.list2 = list2;
        this.list3 = list3;
        this.list4 = list4;
        this.list5 = list5;
        this.list6 = list6;
        this.list7 = list7;
        this.list8 = list8;
        this.list9 = list9;
        this.context = context;
        this.coursId=coursId;
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
            view = inflater.inflate(R.layout.item_prof2, null);
        }


        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.textView_nom2);
        String formatedText="" + list3.get(position);
        TextView listItemTextUpvote= (TextView) view.findViewById(R.id.textView_upvote2);
        String formatedText2="" + list4.get(position);
        TextView listItemTextDownvote= (TextView) view.findViewById(R.id.textView_downvote2);
        listItemTextUpvote.setText(formatedText);
        listItemTextDownvote.setText(formatedText2);
        listItemText.setText(list1.get(position));
        TextView listItemTextTitre= (TextView) view.findViewById(R.id.textView_titre);
        String formatedText3="" + list6.get(position);
        listItemTextTitre.setText(formatedText3);
        TextView listItemTextDate= (TextView) view.findViewById(R.id.textView_date);
        String formatedText4="" + list7.get(position);
        listItemTextDate.setText(formatedText4);

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.mylinearLayoutx);
        if(!Boolean.parseBoolean(list8.get(position))) {
            linearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorBlue2));
        }
        else {
            linearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorAccentDark));
        }

        ImageView imageview = (ImageView) view.findViewById(R.id.lock);
        if(Integer.parseInt(list9.get(position))==1) {
            imageview.setImageResource(R.drawable.ic_up_w);
        }
        else if(Integer.parseInt(list9.get(position))==-1) {
            imageview.setImageResource(R.drawable.ic_down_w);
        }
        else {
            imageview.setImageResource(android.R.color.transparent);
        }

        ImageView imageview2 = (ImageView) view.findViewById(R.id.lock2);
        if(Integer.parseInt(list5.get(position))!=1) {
            imageview2.setImageResource(R.drawable.ic_lock);
        }
        else {
            imageview2.setImageResource(R.drawable.ic_unlock);
        }
        /*
        //Handle buttons and add onClickListeners
        Button deleteBtn = (Button)view.findViewById(R.id.del);
        Button validateBtn= (Button ) view.findViewById(R.id.val);
        //Button addBtn = (Button)view.findViewById(R.id.add_btn);

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                Question question = Question.findById(Question.class, Integer.parseInt(list2.get(position)));
                question.delete();
                list1.remove(position); //or some other task
                notifyDataSetChanged();
            }
        });

        validateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question question = Question.findById(Question.class, Integer.parseInt(list2.get(position)));
                question.valide = true;
                question.save();
            }
        });*/

        listItemText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*DatabaseReference postRef = FirebaseDatabase.getInstance().getReference().child("reponse").child(list2.get(position));
                Log.v("postRef", list2.get(position));
                postRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(!dataSnapshot.exists()) {
                            Intent intent = new Intent(context, AddAnswer.class);
                            Bundle b = new Bundle();
                            b.putString("key", list2.get(position)); //Your id
                            b.putString("name", list1.get(position));
                            intent.putExtras(b); //Put your id to your next Intent
                            context.startActivity(intent);
                        }
                        else {
                            Intent intent = new Intent(context, AddAnswer.class);
                            Bundle b = new Bundle();
                            b.putString("key", list2.get(position)); //Your id
                            b.putString("name", list1.get(position));
                            b.putString("keyAnswer", dataSnapshot.getKey());
                            b.putString("nameAnswer", dataSnapshot.getValue(Answer.class).nom);
                            intent.putExtras(b); //Put your id to your next Intent
                            context.startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });*/
                DatabaseReference postRef = FirebaseDatabase.getInstance().getReference().child("answer");
                Log.v("postRef", list2.get(position));
                postRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterable<DataSnapshot> answerChildren = dataSnapshot.getChildren();

                        for (DataSnapshot answer: answerChildren) {
                            Answer a = answer.getValue(Answer.class);      //make a model User with necessary fields
                            Log.v("questId", a.questionId);
                            if(a.questionId.equals(list2.get(position))){
                                checkExists = true;
                                tmp = a;
                                tmpKey = answer.getKey();
                            }
                        }
                        if(!checkExists) {
                            Intent intent;

                            if(Integer.parseInt(list5.get(position))==1) {
                                intent = new Intent(context, AddAnswer.class);
                            }
                            else {
                                intent = new Intent(context, AddAnswer2.class);
                            }
                            Bundle b = new Bundle();
                            b.putString("key", list2.get(position)); //Your id
                            b.putString("name", list1.get(position));
                            b.putString("idcours",coursId);
                            intent.putExtras(b); //Put your id to your next Intent
                            context.startActivity(intent);
                        }
                        else {
                            Intent intent = new Intent(context, ModifyAnswer.class);
                            Bundle b = new Bundle();
                            b.putString("key", list2.get(position)); //Your id
                            b.putString("name", list1.get(position));
                            b.putString("keyAnswer", tmpKey);
                            b.putString("nameAnswer", tmp.nom);
                            intent.putExtras(b); //Put your id to your next Intent
                            context.startActivity(intent);
                        }
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
