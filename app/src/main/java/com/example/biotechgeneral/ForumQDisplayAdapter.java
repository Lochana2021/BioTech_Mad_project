
package com.example.biotechgeneral;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ForumQDisplayAdapter extends RecyclerView.Adapter {

    List<ForumQuetions> forumQuetionsList;
    Context context;


    public ForumQDisplayAdapter(List<ForumQuetions> forumQuetionsList) {
        this.forumQuetionsList = forumQuetionsList;
    }

    public ForumQDisplayAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forum_quetion,parent,false);
        ViewHolderclass viewHolderclass = new ViewHolderclass(view);
        return viewHolderclass;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolderclass viewHolderclass = (ViewHolderclass)holder;
        ForumQuetions forumQuetions = forumQuetionsList.get(position);
        ViewHolderclass.name.setText(forumQuetions.getForumName());
        ViewHolderclass.quetionNo.setText(forumQuetions.getQuetionNo());
        ViewHolderclass.quetion.setText(forumQuetions.getQuetionNo());

    }

    @Override
    public int getItemCount() {
       return forumQuetionsList.size();
    }
    private static class ViewHolderclass extends  RecyclerView.ViewHolder{

        static TextView name;
        static TextView quetionNo;
        static TextView quetion;

        public ViewHolderclass(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.QFName);
            quetionNo = itemView.findViewById(R.id.QquetionNo);
            quetion = itemView.findViewById(R.id.Qquetion);


        }
    }
}

