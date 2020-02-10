package com.example.asknanterre;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapterStud extends BaseAdapter implements ListAdapter {

    private ArrayList<String> list1 = new ArrayList<String>();
    private ArrayList<String> list2 = new ArrayList<String>();
    private ArrayList<String> list3 = new ArrayList<String>();
    private ArrayList<String> list4 = new ArrayList<String>();
    private ArrayList<String> list5 = new ArrayList<String>();

    private Context context;



    public CustomAdapterStud(ArrayList<String> list1, ArrayList<String> list2, ArrayList<String> list3,ArrayList<String> list4,ArrayList<String> list5,Context context) {
        this.list1 = list1;
        this.list2 = list2;
        this.list3 = list3;
        this.list4 = list4;
        this.list5=list5;
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
        TextView listItemText = (TextView)view.findViewById(R.id.textView_name);
        String formatedText="Like: " + list3.get(position);
        TextView listItemTextUpvote= (TextView) view.findViewById(R.id.textView_upvote);
        String formatedText3="Dislike: " + list5.get(position);
        TextView listItemTextDownvote= (TextView) view.findViewById(R.id.textView_downvote);
        TextView listItemTextRepondu= (TextView) view.findViewById(R.id.textView_repondu);

        if ( list4.get(position).equals("true"))
            {
                 String formatedText2=" Répondu";
                 listItemTextRepondu.setText(formatedText2);
            }
        else
            {
                 String formatedText2=" Non Répondu";
                listItemTextRepondu.setText(formatedText2);
            }

        listItemTextUpvote.setText(formatedText);
        listItemTextDownvote.setText(formatedText3);
        listItemText.setText(list1.get(position));

        //Handle buttons and add onClickListeners

        //Button addBtn = (Button)view.findViewById(R.id.add_btn);

       Button likeBtn= (Button ) view.findViewById(R.id.like);

        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question question = Question.findById(Question.class, Integer.parseInt(list2.get(position)));
                question.upvote= question.upvote+1;

                question.save();
                //Modifier list3
                notifyDataSetChanged();


            }
        });


        Button dislikeBtn= (Button ) view.findViewById(R.id.dislike);

        dislikeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question question = Question.findById(Question.class, Integer.parseInt(list2.get(position)));
                question.downvote= question.downvote+1;

                question.save();
                //Modifier list3
                notifyDataSetChanged();


            }
        });

        return view;
    }
}
