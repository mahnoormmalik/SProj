package com.example.sproj.sproj;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sproj.sproj.m_Model.Students;
import com.example.sproj.sproj.m_UI.MyStudentDataAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.content.ContentValues.TAG;

public class StudentListFragment extends Fragment {

    DatabaseReference db;
    ArrayList<Students> students;
    ArrayList<String> keys;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    //private OnFragmentInteractionListener mListener;

    public StudentListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = FirebaseDatabase.getInstance().getReference().child("students");
        //fetchData();
        View rootView = inflater.inflate(R.layout.fragment_student_list, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.request_list_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        mRecyclerView.addItemDecoration(new RecycleMarginDecoration(getActivity()));
        //mAdapter = new RequestListDataAdapter(students, keys, getContext());
        mAdapter = new MyStudentDataAdapter(fetchData());
        mRecyclerView.setAdapter(mAdapter);
        return rootView;
    }

    //Getting data from database
    public ArrayList<Students> fetchData() {
        students = new ArrayList<Students>();
        ValueEventListener studentsListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    Students student = child.getValue(Students.class);
                    student.id = child.getKey();
                    students.add(student);
                }
                mAdapter.notifyDataSetChanged();
                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        db.addValueEventListener(studentsListener);
        return students;
    }
}