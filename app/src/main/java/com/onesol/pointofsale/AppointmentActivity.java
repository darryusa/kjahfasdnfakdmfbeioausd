package com.onesol.pointofsale;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
public class AppointmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        String times[] = new String[48];
//        String start = "7:00";
        String end = "9:00";
       // Minutes start = new Minutes(15);
        long start = 60 * 7 * 1000;
        for(int i = 0 ; i < times.length; i++)
        {
//            times[i] = (start + 15 * 1000 * 60)
        }
    }
}
