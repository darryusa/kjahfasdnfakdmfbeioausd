package com.onesol.pointofsale;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class employeeActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        Button addButton = (Button) findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                employeePopUp employeeInfo = new employeePopUp(employeeActivity.this);
                employeeInfo.show();
                Toast.makeText( getApplicationContext(),"Add Employee", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private Context getContext()
    {
        return getApplicationContext();
    }
}
