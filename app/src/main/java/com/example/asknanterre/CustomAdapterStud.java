package com.example.asknanterre;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class CustomAdapterStud extends BaseAdapter implements ListAdapter {

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
    DatabaseReference ref;
    int upvote;
    private boolean checkExists = false;
    private Answer tmp;
    private String tmpKey;
    int textlength=0;



    public CustomAdapterStud(ArrayList<String> list1, ArrayList<String> list2, ArrayList<String> list3,ArrayList<String> list4,ArrayList<String> list5,ArrayList<String> list6,ArrayList<String> list7,ArrayList<String> list8,ArrayList<String> list9,Context context) {
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
            view = inflater.inflate(R.layout.item_stud, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.textView_nom2);
        String formatedText=" " + list3.get(position);
        TextView listItemTextUpvote= (TextView) view.findViewById(R.id.textView_upvote2);
        //ImageView listItemTextRepondu= (ImageView) view.findViewById(R.id.lock);
        String formatedText3="" + list5.get(position);
        TextView listItemTextDownvote= (TextView) view.findViewById(R.id.textView_downvote2);
        listItemTextUpvote.setText(formatedText);
        listItemTextDownvote.setText(formatedText3);
        TextView listItemTextTitre= (TextView) view.findViewById(R.id.textView_titre);
        String formatedText4="" + list7.get(position);
        listItemTextTitre.setText(formatedText4);
        TextView listItemTextDate= (TextView) view.findViewById(R.id.textView_date);
        String formatedText5="" + list8.get(position);
        listItemTextDate.setText(formatedText5);

        /*if ( list4.get(position).equals("true"))
            {
                 String formatedText2=" Répondu";
                 //listItemTextRepondu.setText(formatedText2);
                listItemTextRepondu.setVisibility(View.VISIBLE);
            }
        else
            {
                 String formatedText2=" Non Répondu";
                //listItemTextRepondu.setText(formatedText2);
                listItemTextRepondu.setVisibility(View.INVISIBLE);
            }*/

        //TextView listItemTextupvoteProf= (TextView) view.findViewById(R.id.textView_upvoteProf);


        /*if (list6.get(position).equals("0"))
        {
            //String formatedText4=" Pas de vote";
            //listItemTextupvoteProf.setText(formatedText4);
            myLinearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorLightBlue));
        }
         if (list6.get(position).equals("1"))
        {
            //String formatedText4=" Upvoté par le prof";
            //listItemTextupvoteProf.setText(formatedText4);
            myLinearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorAccentDark));
        }

         if ( list6.get(position).equals("-1"))
        {
            //String formatedText4=" Downvoté par le prof";
            //listItemTextupvoteProf.setText(formatedText4);
            myLinearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorRed));
        }*/

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.mylinearLayoutx);
        if(!Boolean.parseBoolean(list4.get(position))) {
            linearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorBlue2));
        }
        else {
            linearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorAccentDark));
        }

        listItemText.setText(list1.get(position));

        ImageView imageview = (ImageView) view.findViewById(R.id.lock);
        if(Integer.parseInt(list6.get(position))==1) {
            imageview.setImageResource(R.drawable.ic_up_w);
        }
        else if(Integer.parseInt(list6.get(position))==-1) {
            imageview.setImageResource(R.drawable.ic_down_w);
        }
        else {
            imageview.setImageResource(android.R.color.transparent);
        }

        ImageView imageview2 = (ImageView) view.findViewById(R.id.lock2);
        if(Integer.parseInt(list9.get(position))!=1) {
            imageview2.setImageResource(R.drawable.ic_lock);
        }
        else {
            imageview2.setImageResource(R.drawable.ic_unlock);
        }
        //Handle buttons and add onClickListeners

        //Button addBtn = (Button)view.findViewById(R.id.add_btn);

       ImageButton likeBtn= (ImageButton ) view.findViewById(R.id.like);

        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Question question = Question.findById(Question.class, Integer.parseInt(list2.get(position)));
                question.upvote= question.upvote+1;

                question.save();*/
                ref = FirebaseDatabase.getInstance().getReference().child("question").child(list2.get(position));
                final String key = ref.getKey();
                Log.d("key originel", key);
                final Map<String,Object> questionMap = new HashMap<String,Object>();
                ValueEventListener valueEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds : dataSnapshot.getChildren()) {
                            Log.d("key recu", ds.getKey());
                            if(ds.getKey().equals("upvote")) {
                                questionMap.put("upvote", ds.getValue(Integer.class) + 1);
                                ref.updateChildren(questionMap);
                                if (context instanceof DisplayQuestionStud) {
                                    ((DisplayQuestionStud)context).updateList();
                                }
                            }
                            Log.d("TAG", "tour de boucle");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                };
                ref.addListenerForSingleValueEvent(valueEventListener);


            }
        });


        ImageButton dislikeBtn= (ImageButton ) view.findViewById(R.id.dislike);

        dislikeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Question question = Question.findById(Question.class, Integer.parseInt(list2.get(position)));
                question.downvote= question.downvote+1;

                question.save();
                //Modifier list3
                notifyDataSetChanged();*/
                ref = FirebaseDatabase.getInstance().getReference().child("question").child(list2.get(position));
                final String key = ref.getKey();
                Log.d("key originel", key);
                final Map<String,Object> questionMap = new HashMap<String,Object>();
                ValueEventListener valueEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds : dataSnapshot.getChildren()) {
                            Log.d("key recu", ds.getKey());
                            if(ds.getKey().equals("downvote")) {
                                questionMap.put("downvote", ds.getValue(Integer.class) + 1);
                                ref.updateChildren(questionMap);
                                if (context instanceof DisplayQuestionStud) {
                                    ((DisplayQuestionStud)context).updateList();
                                }
                            }
                            Log.d("TAG", "tour de boucle");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                };
                ref.addListenerForSingleValueEvent(valueEventListener);


            }
        });

        listItemText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                        if(checkExists) {
                            checkExists = false;
                            Intent intent = new Intent(context, DisplayAnswer.class);
                            Bundle b = new Bundle();
                            b.putString("key", list2.get(position)); //Your id
                            b.putString("name", list1.get(position));
                            b.putString("keyAnswer", tmpKey);
                            b.putString("nameAnswer", tmp.nom);
                            intent.putExtras(b); //Put your id to your next Intent
                            context.startActivity(intent);
                        }
                        else {
                            Toasty.info(context, context.getString(R.string.le_professeur_na_pas_repondu), Toast.LENGTH_SHORT).show();
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
