package com.example.biotechgeneral;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ForumStudentAdapter extends RecyclerView.Adapter {
    List<ForumStudent>forumStudentList;
    Context context;

    public ForumStudentAdapter(List<ForumStudent> forumStudentList) {
        this.forumStudentList = forumStudentList;
    }

    public ForumStudentAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forum_student,parent,false);
        ViewHolderclass viewHolderclass = new ViewHolderclass(view);
        return viewHolderclass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolderclass viewHolderclass = (ViewHolderclass)holder;
        ForumStudent forumStudent = forumStudentList.get(position);
        ViewHolderclass.name.setText(forumStudent.getFName());
        ViewHolderclass.quetionNo.setText(forumStudent.getQuetionNo());
        ViewHolderclass.answer.setText(forumStudent.getAnswer());

    }

    @Override
    public int getItemCount() {
       return forumStudentList.size();
    }
    public static class ViewHolderclass extends RecyclerView.ViewHolder{
        static TextView name;
        static TextView quetionNo;
        static TextView answer;;

        public ViewHolderclass(@NonNull View itemView) {
            super(itemView);
            name  = itemView.findViewById(R.id.display_FName);
            quetionNo = itemView.findViewById(R.id.display_quetionNo);
            answer = itemView.findViewById(R.id.display_answer);
        }


    }

}
