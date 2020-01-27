package com.example.asknanterre;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<String> list1 = new ArrayList<String>();
    private ArrayList<String> list2 = new ArrayList<String>();
    private ArrayList<String> list3 = new ArrayList<String>();
    private Context context;



    public CustomAdapter(ArrayList<String> list1, ArrayList<String> list2, ArrayList<String> list3,Context context) {
        this.list1 = list1;
        this.list2 = list2;
        this.list3=list3;
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
            view = inflater.inflate(R.layout.item, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.textView_name);
        String formatedText="Nombre d'upvote: " + list3.get(position);
        TextView listItemTextUpvote= (TextView) view.findViewById(R.id.textView_upvote);
        listItemTextUpvote.setText(formatedText);
        listItemText.setText(list1.get(position));

        //Handle buttons and add onClickListeners
        Button deleteBtn = (Button)view.findViewById(R.id.del);
        Button likeBtn= (Button ) view.findViewById(R.id.like);
        Button answBtn = (Button) view.findViewById(R.id.answ) ;
       // Button showAnswBtn = (Button) view.findViewById(R.id.showansw);
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

        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question question = Question.findById(Question.class, Integer.parseInt(list2.get(position)));
                question.upvote= question.upvote+1;
                question.save();

            }
        });

        /*answBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Answer r = Answer.findById(Answer.class, Integer.parseInt(list1.get(position)));

                r.save();
            }
        });*/

        /*addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                notifyDataSetChanged();
            }
        });*/


        return view;
    }




}