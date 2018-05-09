package com.example.sproj.sproj.m_UI;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sproj.sproj.DisplayImagesActivity;
import com.example.sproj.sproj.R;
import com.example.sproj.sproj.m_Model.ImageUploadInfo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

/**
 * Created by mahnoor on 10/03/2018.
 */

public class MyImagesDataAdapter extends RecyclerView.Adapter<MyImagesDataAdapter.ViewHolder> {
    Context context;
    List<ImageUploadInfo> MainImageUploadInfoList;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    ImageUploadInfo uploadInfo;
    String audiourl;
    public MyImagesDataAdapter(Context context, List<ImageUploadInfo> TempList) {

        this.MainImageUploadInfoList = TempList;

        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_display_images, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        uploadInfo = MainImageUploadInfoList.get(position);

        audiourl = uploadInfo.audioURL;
        holder.cv.setTag(uploadInfo.audioURL);
//        String file[] = UploadInfo.audURL.split("\\.");
//        try{
//            File outputDir = context.getCacheDir();
//            localFile = File.createTempFile(file[0], file[1],outputDir);
//            audRef.child(uploadInfo.audioURL).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                @Override
//                public void onSuccess(Uri uri) {
//                    audURI = uri;
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {

//                }
//            });
//            audRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//
//                }
//            });

//        }
//        catch(IOException e){
//
//        }
//        holder.imageNameTextView.setText(UploadInfo.getImageName());
//        Toast.makeText(context, "Getting picture",
//                Toast.LENGTH_SHORT).show();
        //Loading image from Glide library.
        Glide.with(context).load(uploadInfo.imgURL).into(holder.imageView);
    }

    @Override
    public int getItemCount() {

        return MainImageUploadInfoList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView imageView;

//        public TextView imageNameTextView;
        File outputDir = context.getCacheDir();
        protected  CardView cv;
//        final MediaPlayer MP = MediaPlayer.create(context, audURI);
        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.org_image);
            cv = (CardView) itemView.findViewById(R.id.cardView);
            cv.setOnClickListener(this);
//            imageNameTextView = (TextView) itemView.findViewById(R.id.ImageNameTextView);
        }
        @Override
        public void onClick(View view){
//            MP.start();
            try {
                MediaPlayer player = new MediaPlayer();
                player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                player.setDataSource((String) view.getTag());
                player.prepare();
                player.start();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
}
