package com.example.sproj.sproj.m_UI;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.example.sproj.sproj.DisplayImagesActivity;
import com.example.sproj.sproj.MainActivity;
import com.example.sproj.sproj.R;
import com.example.sproj.sproj.m_Model.Students;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mahnoor on 06/03/2018.
 */
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sproj.sproj.m_Model.Students;

import static java.security.AccessController.getContext;

public class MyStudentDataAdapter extends RecyclerView.Adapter<MyStudentDataAdapter.ViewHolder> {
    private ArrayList<Students> students;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mName, mPAge, mMAge;
        String mID;
        protected CardView cv;
        public ViewHolder(View itemView){
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cardView);
            mName = (TextView) itemView.findViewById(R.id.name);
            mPAge = (TextView) itemView.findViewById(R.id.p_age);
            mMAge = (TextView) itemView.findViewById(R.id.mental_age);
            mID = "";
            cv.setOnClickListener(this);
        }
        @Override
        public void onClick(View view){
//            processClick(mID);
            Toast.makeText(view.getContext(), "view clicked",
                               Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(view.getContext(), DisplayImagesActivity.class);
            intent.putExtra("student", (String) view.getTag());
            view.getContext().startActivity(intent);

        }
    }

    public MyStudentDataAdapter(ArrayList<Students> stds) {
        students = stds;
    }

    @Override
    public MyStudentDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_card_view, parent, false);
        return new ViewHolder(v);
    }
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyStudentDataAdapter.ViewHolder holder, int position) {
        Students student = (Students) students.get(position);
        holder.cv.setTag(student.id);
        holder.mName.setText(student.firstName+" "+student.lastName);
        holder.mPAge.setText(student.p_age);
        holder.mMAge.setText(student.m_age);
    }
    @Override
    public int getItemCount() {
        return students.size();
    }

}
