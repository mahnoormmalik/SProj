package com.example.sproj.sproj.m_Model;

/**
 * Created by mahnoor on 10/03/2018.
 */

public class ImageUploadInfo {
    public String imgURL, audioURL;

    public ImageUploadInfo(){

    }

    public ImageUploadInfo(String Audurl, String Imgurl) {
        this.imgURL = Imgurl;
        this.audioURL = Audurl;
    }
}
