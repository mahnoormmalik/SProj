package com.example.sproj.sproj;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {

    private StorageReference mStorageRef;
    ImageView image;
    FirebaseAuth mAuth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        final MediaPlayer waterMP = MediaPlayer.create(this, R.raw.paani);

        ImageButton waterSound = (ImageButton) this.findViewById(R.id.image1);

        waterSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                waterMP.start();
            }
        });

        mStorageRef = FirebaseStorage.getInstance().getReference();
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
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


    }
    private void signInAnonymously(){

    }

}
