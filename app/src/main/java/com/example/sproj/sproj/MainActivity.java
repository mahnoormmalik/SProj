package com.example.sproj.sproj;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.estimote.cloud_plugin.common.EstimoteCloudCredentials;
import com.estimote.internal_plugins_api.cloud.CloudCredentials;
import com.estimote.internal_plugins_api.cloud.proximity.ProximityAttachment;
import com.estimote.mustard.rx_goodness.rx_requirements_wizard.Requirement;
import com.estimote.mustard.rx_goodness.rx_requirements_wizard.RequirementsWizardFactory;
import com.estimote.proximity_sdk.proximity.ProximityObserver;
import com.estimote.proximity_sdk.proximity.ProximityObserverBuilder;
import com.estimote.proximity_sdk.proximity.ProximityZone;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {

    private StorageReference mStorageRef;
    ImageView image;
    FirebaseAuth mAuth;
    FirebaseUser user;

    private ProximityObserver proximityObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

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

//        ProximityZone zone1 = proximityObserver.zoneBuilder()
//                .forAttachmentKeyAndValue("floor", "1st")
//                .inCustomRange(1)
//                .withOnEnterAction(new Function1<ProximityAttachment, Unit>() {
//                    @Override
//                    public Unit invoke(ProximityAttachment attachment) {
//                        Toast.makeText(MainActivity.this, "bluetooth beacon detected",
//                                Toast.LENGTH_SHORT).show();
//                        Log.d("app", "Welcome to the 1st floor");
//                        return null;
//                    }
//                })
//                .withOnExitAction(new Function1<ProximityAttachment, Unit>() {
//                    @Override
//                    public Unit invoke(ProximityAttachment attachment) {
//                        Log.d("app", "Bye bye, come visit us again on the 1st floor");
//                        Toast.makeText(MainActivity.this, "bluetooth beacon left",
//                                Toast.LENGTH_SHORT).show();
//                        return null;
//                    }
//                })
//                .withOnChangeAction(new Function1<List<? extends ProximityAttachment>, Unit>() {
//                    @Override
//                    public Unit invoke(List<? extends ProximityAttachment> proximityAttachments) {
//                            /* Do something here */
//                        return null;
//                    }
//                })
//                .create();
//        ProximityObserver.Handler observationHandler =
//                proximityObserver
//                        .addProximityZone(zone1)
//                        .start();
//        this.proximityObserver.addProximityZone(zone1);

        // Mint beacon
        ProximityZone mint = proximityObserver.zoneBuilder()
                .forAttachmentKeyAndValue("area", "mint")
                .inCustomRange(1)
                .withOnEnterAction(new Function1<ProximityAttachment, Unit>() {
                    @Override
                    public Unit invoke(ProximityAttachment attachment) {
                        Toast.makeText(MainActivity.this, "Welcome to mint area",
                                Toast.LENGTH_SHORT).show();
                        Log.d("app", "Welcome to mint area");
                        return null;
                    }
                })
                .withOnExitAction(new Function1<ProximityAttachment, Unit>() {
                    @Override
                    public Unit invoke(ProximityAttachment attachment) {
                        Log.d("app", "Bye bye, come visit us again on the mint area");
                        Toast.makeText(MainActivity.this, "Bye bye from mint area",
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
                        .addProximityZone(mint)
                        .start();
        // Ice beacon
        ProximityZone ice = proximityObserver.zoneBuilder()
                .forAttachmentKeyAndValue("area", "ice")
                .inCustomRange(2)
                .withOnEnterAction(new Function1<ProximityAttachment, Unit>() {
                    @Override
                    public Unit invoke(ProximityAttachment attachment) {
                        Toast.makeText(MainActivity.this, "Welcome to ice area",
                                Toast.LENGTH_SHORT).show();
                        Log.d("app", "Welcome to ice area");
                        return null;
                    }
                })
                .withOnExitAction(new Function1<ProximityAttachment, Unit>() {
                    @Override
                    public Unit invoke(ProximityAttachment attachment) {
                        Log.d("app", "Bye bye, come visit us again on the ice area");
                        Toast.makeText(MainActivity.this, "Bye bye from ice area",
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
        ProximityObserver.Handler observationHandler2 =
                proximityObserver
                        .addProximityZone(ice)
                        .start();
        // Blueberry beacon
        ProximityZone blueberry = proximityObserver.zoneBuilder()
                .forAttachmentKeyAndValue("area", "blueberry")
                .inCustomRange(2)
                .withOnEnterAction(new Function1<ProximityAttachment, Unit>() {
                    @Override
                    public Unit invoke(ProximityAttachment attachment) {
                        Toast.makeText(MainActivity.this, "Welcome to blueberry area",
                                Toast.LENGTH_SHORT).show();
                        Log.d("app", "Welcome to blueberry area");
                        return null;
                    }
                })
                .withOnExitAction(new Function1<ProximityAttachment, Unit>() {
                    @Override
                    public Unit invoke(ProximityAttachment attachment) {
                        Log.d("app", "Bye bye, come visit us again on the blueberry area");
                        Toast.makeText(MainActivity.this, "Bye bye from blueberry area",
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
        ProximityObserver.Handler observationHandler3 =
                proximityObserver
                        .addProximityZone(blueberry)
                        .start();

        RequirementsWizardFactory
                .createEstimoteRequirementsWizard()
                .fulfillRequirements(MainActivity.this,
                        // onRequirementsFulfilled
                        new Function0<Unit>() {
                            @Override public Unit invoke() {
                                Log.d("app", "requirements fulfilled");
                                proximityObserver.start();
                                return null;
                            }
                        },
                        // onRequirementsMissing
                        new Function1<List<? extends Requirement>, Unit>() {
                            @Override public Unit invoke(List<? extends Requirement> requirements) {
                                Log.e("app", "requirements missing: " + requirements);
                                return null;
                            }
                        },
                        // onError
                        new Function1<Throwable, Unit>() {
                            @Override public Unit invoke(Throwable throwable) {
                                Log.e("app", "requirements error: " + throwable);
                                return null;
                            }
                        });
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        mStorageRef = FirebaseStorage.getInstance().getReference();

        TextView myTextView = (TextView) findViewById(R.id.textView);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "Fonts/TitilliumWeb-Black.ttf");
        image = (ImageView)findViewById(R.id.imagedb);
        myTextView.setTypeface(typeface);
        // Successfully downloaded data to local file
        // ...
        // Load the image using Glide
//        Glide.with(this /* context */)
//                .using(new FirebaseImageLoader())
//                .load(mStorageRef)
//                .into(image);

        // Sound code here
        final MediaPlayer waterMP = MediaPlayer.create(this, R.raw.paani);

        ImageButton waterSound = (ImageButton) this.findViewById(R.id.image1);

        waterSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                waterMP.start();
            }
        });

        if (user != null) {
            user.reload();
            Toast.makeText(MainActivity.this, "user signed in.",
                    Toast.LENGTH_SHORT).show();
        } else {
            mAuth.signInAnonymously()
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
//                                Log.d(TAG, "signInAnonymously:success");
                                user = mAuth.getCurrentUser();
                                try {
                                    final File localFile = File.createTempFile("images", "jpg");
                                    mStorageRef.child("bg2.jpg").getFile(localFile)
                                            .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                                @Override
                                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                                    Toast.makeText(MainActivity.this, "In storage on success.",
                                                            Toast.LENGTH_SHORT).show();
                                                    image.setImageDrawable(Drawable.createFromPath(localFile.getPath()));

                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception exception) {
                                            // Handle failed download
                                            // ...
                                        }
                                    });
                                } catch (IOException e) {
                                    Toast.makeText(MainActivity.this, "In storage catch.",
                                            Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }
                            } else {
                                // If sign in fails, display a message to the user.
//                                Log.w(TAG, "signInAnonymously:failure", task.getException());
                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
        }
        try {
            final File localFile = File.createTempFile("images", "png");
            mStorageRef.child("inst2.png").getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bmp = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            image.setImageBitmap(bmp);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle failed download
                    // ...
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Intent intent = new Intent(this,StudentListFragment.class);
//        startActivity(intent);


    }
    private void signInAnonymously(){

    }

}
