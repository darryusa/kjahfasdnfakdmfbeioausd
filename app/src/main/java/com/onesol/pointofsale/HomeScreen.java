package com.onesol.pointofsale;


import android.app.SearchManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filterable;
import android.widget.GridLayout;

import android.widget.GridView;
import android.widget.SearchView;


import java.util.ArrayList;

import java.util.List;
import android.view.LayoutInflater;
import android.widget.Toast;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import static android.R.layout.simple_list_item_1;

public class HomeScreen extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        SearchView searchView = (SearchView) findViewById(R.id.searchView);
        Button employeeButton = (Button) findViewById(R.id.employeeButton);
        final ArrayList<String> textView = new ArrayList<String>();
        final GridView employeeGrid = (GridView) findViewById(R.id.gridView);
        final CustomGridAdapter gridAdapter;

        for (int i = 0; i < 100; i++) {
            textView.add("employee" + (i + 1));
        }
        gridAdapter = new CustomGridAdapter(HomeScreen.this, textView);
        employeeGrid.setAdapter(gridAdapter);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, simple_list_item_1, textView);

        employeeGrid.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                PinDialog pin = new PinDialog(HomeScreen.this);
//                EditText editText = (EditText) findViewById(R.id.edit_message);
//                String message = editText.getText().toString();
//                intent.putExtra(EXTRA_MESSAGE, message);
                pin.show();
//                AlertDialog.Builder pin = new AlertDialog.Builder(HomeScreen.this);
//
//                pin.setTitle("PIN");
//                pin.setMessage("Please Enter Your Pin");
//                final EditText input = new EditText(HomeScreen.this);
//                InputFilter[] filter = new InputFilter[1];
//                filter[0] = new InputFilter.LengthFilter(4);
//                input.setFilters(filter);
////                final EditText input2 = (EditText) findViewById(R.id.pinPassword);
//                input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
////                input
//                pin.setView(input);
//                pin.setPositiveButton("OK",new DialogInterface.OnClickListener(){
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                pin.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                pin.show();
//                AlertDialog alertDialog = pin.create();
//                alertDialog.getWindow().setLayout(200,200);
//                alertDialog.show();


   //             text.setText((String) (employeeGrid.getItemAtPosition(position)));
               // Intent intent = new Intent(getContext(), SaleActivity.class);
//                EditText editText = (EditText) findViewById(R.id.edit_message);
//                String message = editText.getText().toString();
//                intent.putExtra(EXTRA_MESSAGE, message);
               // startActivity(intent);
                Toast.makeText( getApplicationContext(),(String)employeeGrid.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }
        });

        employeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), employeeActivity.class);
//                EditText editText = (EditText) findViewById(R.id.edit_message);
//                String message = editText.getText().toString();
//                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
                Toast.makeText( getApplicationContext(),"employee button did click", Toast.LENGTH_SHORT).show();

            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                gridAdapter.getFilter().filter(newText);

                return false;
            }
        });
        
    }
    private Context getContext()
    {
        return getApplicationContext();
    }

    private void insertEmployee(String firstName, String lastName,String address, String phoneNumber, String SSN, String PIN) {
        ContentValues values = new ContentValues();
        values.put(DbHandler.KEY_FIRSTNAME, firstName);
        values.put(DbHandler.KEY_LASTNAME, lastName);
        values.put(DbHandler.KEY_ADDRESS, address);
        values.put(DbHandler.KEY_PHONENUMBER, phoneNumber);
        values.put(DbHandler.KEY_SSN, SSN);
        values.put(DbHandler.KEY_PIN, PIN);
//        values.put(DbHandler.KEY_DATECREATED, dateCreated);


        //Uri employeeUri = getContentResolver().insert(DataProvider.CONTENT_URI, values);
        //Log.d("HomeScreen", "InsertedNote" + employeeUri.getLastPathSegment());
    }
}