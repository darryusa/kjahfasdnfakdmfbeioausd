package com.onesol.pointofsale;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class PinDialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setTitle("PIN");
//        getActionBar().hide();
        setContentView(R.layout.activity_pin_dialog);
    }
}
