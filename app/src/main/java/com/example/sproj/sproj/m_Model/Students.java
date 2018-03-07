package com.example.sproj.sproj.m_Model;

/**
 * Created by aimengaba on 08/02/2018.
 */

import com.google.firebase.database.IgnoreExtraProperties;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Students {

    public String username, m_age, p_age, iq, firstName, lastName;

    public Students() {
        // Default constructor required for calls to DataSnapshot.getValue(BloodRequest.class)
    }

    public Students(String username, String m_age, String p_age, String iq, String firstName, String lastName) {
        this.username = username;
        this.m_age = m_age;
        this.p_age = p_age;
        this.iq = iq;
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
