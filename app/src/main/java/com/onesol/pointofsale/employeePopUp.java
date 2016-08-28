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
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class employeePopUp extends Dialog implements View.OnClickListener
{
    public Activity c;
    public Dialog d;
    public Button save, cancel;

    private EditText firstName;
    private EditText lastName;
    private EditText phoneNum;
    private EditText address;
    private EditText ssn;
    private EditText pin;
    private RadioGroup role;
    private EditText driverLicense;
    private EditText dateOfBirth;
    private String roleString;



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
        init();




    }

    private void init() {
        save = (Button) findViewById(R.id.saveButton);
        cancel = (Button) findViewById(R.id.cancelButton);
        save.setOnClickListener(this);
        cancel.setOnClickListener(this);
        setCanceledOnTouchOutside(false);
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        address = (EditText) findViewById(R.id.address);
        phoneNum = (EditText) findViewById(R.id.phoneNum);
        ssn = (EditText) findViewById(R.id.ssn);
        pin = (EditText) findViewById(R.id.pin);
        driverLicense = (EditText) findViewById(R.id.driverlicense);
        dateOfBirth = (EditText) findViewById(R.id.dateOfBirth);
        role = (RadioGroup) findViewById(R.id.radioGroup);
        roleString = role.getCheckedRadioButtonId() == R.id.employeeRadioButton? "Employee" : "Manager";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveButton:
                insertEmployee(firstName.getText().toString(),
                        lastName.getText().toString(),
                        address.getText().toString(),
                        phoneNum.getText().toString(),
                        ssn.getText().toString(),
                        pin.getText().toString(),
                        roleString,
                        dateOfBirth.getText().toString(),
                        driverLicense.getText().toString()
                     );
                break;
            case R.id.cancelButton:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }


    private void insertEmployee(String firstName, String lastName,String address, String phoneNumber, String SSN,
                                String PIN,String role,String dateOfBirth,String driverLicense)
    {
        ContentValues values = new ContentValues();
        values.put(DbHandler.EMPLOYEE_KEY_FIRSTNAME, firstName);
        values.put(DbHandler.EMPLOYEE_KEY_LASTNAME, lastName);
        values.put(DbHandler.EMPLOYEE_KEY_ADDRESS, address);
        values.put(DbHandler.EMPLOYEE_KEY_PHONENUMBER, phoneNumber);
        values.put(DbHandler.EMPLOYEE_KEY_SSN, SSN);
        values.put(DbHandler.EMPLOYEE_KEY_PIN, PIN);
        values.put(DbHandler.EMPLOYEE_KEY_ROLE, role);
        values.put(DbHandler.EMPLOYEE_KEY_DATEOFBIRTH,dateOfBirth);
        values.put(DbHandler.EMPLOYEE_KEY_DRIVERLICENSE,driverLicense);

        Uri employeeUri = c.getContentResolver().insert(DataProvider.URI_EMPLOYEE, values);
        Log.d("HomeScreen", "InsertedNote" + employeeUri.getLastPathSegment());
    }

//    private void numberTextWatcher(EditText phoneNum)
//    {
//
//    }


//    public Context getContext()
//    {
//        return getApplicationContext();
//    }

}
