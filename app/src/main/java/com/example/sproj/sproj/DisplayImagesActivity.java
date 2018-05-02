package com.example.sproj.sproj;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.estimote.proximity_sdk.proximity.EstimoteCloudCredentials;
import com.estimote.proximity_sdk.proximity.ProximityAttachment;
import com.estimote.proximity_sdk.proximity.ProximityObserver;
import com.estimote.proximity_sdk.proximity.ProximityObserverBuilder;
import com.estimote.proximity_sdk.proximity.ProximityZone;
import com.example.sproj.sproj.m_Model.ImageUploadInfo;
import com.example.sproj.sproj.m_UI.MyImagesDataAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

import static java.security.AccessController.getContext;

/**
 * Created by aimengaba on 09/03/2018.
 */

public class DisplayImagesActivity extends AppCompatActivity{
    // Creating DatabaseReference.
    DatabaseReference databaseReference;
    private ProximityObserver proximityObserver;

    // Creating RecyclerView.
    RecyclerView recyclerView;

    // Creating RecyclerView.Adapter.
    RecyclerView.Adapter adapter ;

    // Creating Progress dialog
    ProgressDialog progressDialog;

    // Creating List of ImageUploadInfo class.
    List<ImageUploadInfo> listClassroom = new ArrayList<>();
    List<ImageUploadInfo> listPlayGround = new ArrayList<>();
    List<ImageUploadInfo> listLivingSkills = new ArrayList<>();

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
        EstimoteCloudCredentials cloudCredentials = new EstimoteCloudCredentials("aimengaba-gmail-com-s-your-2ft","f8176d44e4b225178f02cc7f524a6f0f");

        //Proximity Observer
        proximityObserver =
                new ProximityObserverBuilder(getApplicationContext(), cloudCredentials)
                        .withOnErrorAction(new Function1<Throwable, Unit>() {
                            @Override
                            public Unit invoke(Throwable throwable) {
                                Log.e("app", "proximity observer error: " + throwable);
                                return null;
                            }
                        })
                        .withBalancedPowerMode()
                        .build();

        // Setting up Firebase image upload folder path in databaseReference.
        // The path is already defined in MainActivity.
        databaseReference = FirebaseDatabase.getInstance().getReference().child("students").child(studentID).child("pics").child("classroom");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    if((postSnapshot.getKey().equals("0"))) { //check if placeholder value is added to database

                    } else {
//                        Toast.makeText(DisplayImagesActivity.this, postSnapshot.getKey(),
//                                Toast.LENGTH_SHORT).show();
                        ImageUploadInfo imageUploadInfo = postSnapshot.getValue(ImageUploadInfo.class);

                        listClassroom.add(imageUploadInfo);
                    }

                }

                // Hiding the progress dialog.
//                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                // Hiding the progress dialog.
//                progressDialog.dismiss();

            }
        });
        ProximityZone classRoom = proximityObserver.zoneBuilder()
                .forAttachmentKeyAndValue("area", "classroom")
                .inCustomRange(1)
                .withOnEnterAction(new Function1<ProximityAttachment, Unit>() {
                    @Override
                    public Unit invoke(ProximityAttachment attachment) {
                        Toast.makeText(DisplayImagesActivity.this, "Welcome to blueberry desk",
                                Toast.LENGTH_SHORT).show();
                        Log.d("app", "Welcome to blueberry desk");
                        adapter = new MyImagesDataAdapter(getApplicationContext(), listClassroom);

                        recyclerView.setAdapter(adapter);
                        return null;
                    }
                })
                .withOnExitAction(new Function1<ProximityAttachment, Unit>() {
                    @Override
                    public Unit invoke(ProximityAttachment attachment) {
                        Log.d("app", "Bye bye, come visit us again on the blueberry desk");
                        Toast.makeText(DisplayImagesActivity.this, "Bye bye from blueberry desk",
                                Toast.LENGTH_SHORT).show();
                        return null;
                    }
                })
                .withOnChangeAction(new Function1<List<? extends ProximityAttachment>, Unit>() {
                    @Override
                    public Unit invoke(List<? extends ProximityAttachment> proximityAttachments) {
                            /* Do something here */
                        return null;
                    }
                })
                .create();
        ProximityObserver.Handler observationHandler1 =
                proximityObserver
                        .addProximityZone(classRoom)
                        .start();
        ProximityZone playground = proximityObserver.zoneBuilder()
                .forAttachmentKeyAndValue("area", "playground")
                .inCustomRange(1)
                .withOnEnterAction(new Function1<ProximityAttachment, Unit>() {
                    @Override
                    public Unit invoke(ProximityAttachment attachment) {
                        Toast.makeText(DisplayImagesActivity.this, "Welcome to Ice desk",
                                Toast.LENGTH_SHORT).show();
                        Log.d("app", "Welcome to blueberry desk");
                        adapter = new MyImagesDataAdapter(getApplicationContext(), listPlayGround);

                        recyclerView.setAdapter(adapter);
                        return null;
                    }
                })
                .withOnExitAction(new Function1<ProximityAttachment, Unit>() {
                    @Override
                    public Unit invoke(ProximityAttachment attachment) {
                        Log.d("app", "Bye bye, come visit us again on the blueberry desk");
                        Toast.makeText(DisplayImagesActivity.this, "Bye bye from Ice desk",
                                Toast.LENGTH_SHORT).show();
                        return null;
                    }
                })
                .withOnChangeAction(new Function1<List<? extends ProximityAttachment>, Unit>() {
                    @Override
                    public Unit invoke(List<? extends ProximityAttachment> proximityAttachments) {
                            /* Do something here */
                        return null;
                    }
                })
                .create();
        ProximityObserver.Handler observationHandler1playground =
                proximityObserver
                        .addProximityZone(playground)
                        .start();
        ProximityZone livingSkills = proximityObserver.zoneBuilder()
                .forAttachmentKeyAndValue("area", "livingskills")
                .inCustomRange(1)
                .withOnEnterAction(new Function1<ProximityAttachment, Unit>() {
                    @Override
                    public Unit invoke(ProximityAttachment attachment) {
                        Toast.makeText(DisplayImagesActivity.this, "Welcome to mint desk",
                                Toast.LENGTH_SHORT).show();
                        Log.d("app", "Welcome to blueberry desk");
                        adapter = new MyImagesDataAdapter(getApplicationContext(), listLivingSkills);

                        recyclerView.setAdapter(adapter);
                        return null;
                    }
                })
                .withOnExitAction(new Function1<ProximityAttachment, Unit>() {
                    @Override
                    public Unit invoke(ProximityAttachment attachment) {
                        Log.d("app", "Bye bye, come visit us again on the blueberry desk");
                        Toast.makeText(DisplayImagesActivity.this, "Bye bye from mint desk",
                                Toast.LENGTH_SHORT).show();
                        return null;
                    }
                })
                .withOnChangeAction(new Function1<List<? extends ProximityAttachment>, Unit>() {
                    @Override
                    public Unit invoke(List<? extends ProximityAttachment> proximityAttachments) {
                            /* Do something here */
                        return null;
                    }
                })
                .create();
        ProximityObserver.Handler observationHandler1liningskills =
                proximityObserver
                        .addProximityZone(livingSkills)
                        .start();

        // Adding Add Value Event Listener to databaseReference.


    }
}
