package com.example.biotechgeneral;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ForumAdapter extends RecyclerView.Adapter {

     List<Forum> forumList;
     Context context;

     public ForumAdapter(List<Forum> forumList) {
        this.forumList = forumList;
    }
    public ForumAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forum_layout,parent,false);
        ViewHolderclass viewHolderclass = new ViewHolderclass(view);
        return viewHolderclass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderclass viewHolderclass = (ViewHolderclass) holder;

         Forum forum = forumList.get(position);
        ViewHolderclass.name.setText(forum.getName());
        ViewHolderclass.description.setText(forum.getDescription());
        ViewHolderclass.type.setText(forum.getType());

    }

    @Override
    public int getItemCount() {
        return forumList.size();
    }
    public static class ViewHolderclass extends RecyclerView.ViewHolder{

        static TextView name;
       static  TextView description;
        static TextView type;



        public  ViewHolderclass(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.Fname);
            description = itemView.findViewById(R.id.Fdescription);
            type = itemView.findViewById(R.id.Ftype);

        }

    }


}

