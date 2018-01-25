package com.example.sproj.sproj;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private StorageReference mStorageRef;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

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

        try {
            final File localFile = File.createTempFile("images", "jpg");
            mStorageRef.child("bg2.jpg").getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
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
            e.printStackTrace();
        }

    }

}
