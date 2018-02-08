package com.example.sproj.sproj.m_Model;

/**
 * Created by aimengaba on 08/02/2018.
 */

import com.google.firebase.database.IgnoreExtraProperties;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Students {

    public String username;
    public long age, iq;

    public Students() {
        // Default constructor required for calls to DataSnapshot.getValue(BloodRequest.class)
    }

    public Students(String username, long age, long iq) {
        this.username = username;
        this.age = age;
        this.iq = iq;
    }

}
