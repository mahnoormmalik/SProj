package com.example.sproj.sproj;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.sproj.sproj.m_Model.ImageUploadInfo;
import com.example.sproj.sproj.m_UI.MyImagesDataAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by aimengaba on 09/03/2018.
 */

public class DisplayImagesActivity extends AppCompatActivity{
    // Creating DatabaseReference.
    DatabaseReference databaseReference;

    // Creating RecyclerView.
    RecyclerView recyclerView;

    // Creating RecyclerView.Adapter.
    RecyclerView.Adapter adapter ;

    // Creating Progress dialog
    ProgressDialog progressDialog;

    // Creating List of ImageUploadInfo class.
    List<ImageUploadInfo> list = new ArrayList<>();

    String studentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_images_list);
        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                studentID = null;
            } else {
                studentID = extras.getString("student");
            }
        }

        // Assign id to RecyclerView.
        recyclerView = (RecyclerView) findViewById(R.id.images_list_recycler_view);

        // Setting RecyclerView size true.
        recyclerView.setHasFixedSize(true);

        // Setting RecyclerView layout as LinearLayout.
        recyclerView.setLayoutManager(new LinearLayoutManager(DisplayImagesActivity.this));

        // Assign activity this to progress dialog.
//        progressDialog = new ProgressDialog(DisplayImagesActivity.this);
//
//        // Setting up message in Progress dialog.
//        progressDialog.setMessage("Loading Images From Firebase.");
//
//        // Showing progress dialog.
//        progressDialog.show();

        // Setting up Firebase image upload folder path in databaseReference.
        // The path is already defined in MainActivity.
        databaseReference = FirebaseDatabase.getInstance().getReference().child("students").child(studentID).child("pics").child("canteen");

        // Adding Add Value Event Listener to databaseReference.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    if((postSnapshot.getKey().equals("0"))) { //check if placeholder value is added to database

                    } else {
//                        Toast.makeText(DisplayImagesActivity.this, postSnapshot.getKey(),
//                                Toast.LENGTH_SHORT).show();
                        ImageUploadInfo imageUploadInfo = postSnapshot.getValue(ImageUploadInfo.class);

                        list.add(imageUploadInfo);
                    }

                }


                adapter = new MyImagesDataAdapter(getApplicationContext(), list);

                recyclerView.setAdapter(adapter);

                // Hiding the progress dialog.
//                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                // Hiding the progress dialog.
//                progressDialog.dismiss();

            }
        });

    }
}
