package com.onesol.pointofsale;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class employeePopUp extends Dialog implements View.OnClickListener
{
    public Activity c;
    public Dialog d;
    public Button save, cancel;

    public EditText firstName;
    public EditText lastName;
    public EditText phoneNum;
    public EditText address;
    public EditText ssn;
    public EditText pin;
    public RadioButton role;




    public employeePopUp(Activity a)
    {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_pop_up);
        setTitle("Employee Info");
        save = (Button) findViewById(R.id.saveButton);
        cancel = (Button) findViewById(R.id.cancelButton);
        save.setOnClickListener(this);
        cancel.setOnClickListener(this);
        setCanceledOnTouchOutside(false);
        //numberTextWatcher(phoneNum);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveButton:
                  //insertEmployee( );
                  c.finish();
                break;
            case R.id.cancelButton:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }


    private void insertEmployee(String firstName, String lastName,String address, String phoneNumber, String SSN, String PIN,String role)
    {
        ContentValues values = new ContentValues();
        values.put(DbHandler.KEY_FIRSTNAME, firstName);
        values.put(DbHandler.KEY_LASTNAME, lastName);
        values.put(DbHandler.KEY_ADDRESS, address);
        values.put(DbHandler.KEY_PHONENUMBER, phoneNumber);
        values.put(DbHandler.KEY_SSN, SSN);
        values.put(DbHandler.KEY_PIN, PIN);
//        values.put(DbHandler.KEY_DATECREATED, dateCreated);


//        Uri employeeUri = getContentResolver().insert(DataProvider.CONTENT_URI, values);
        //Log.d("HomeScreen", "InsertedNote" + employeeUri.getLastPathSegment());
    }

//    private void numberTextWatcher(EditText phoneNum)
//    {
//
//    }


    //public Context getContext()
//    {
//        return getApplicationContext();
//    }

}
