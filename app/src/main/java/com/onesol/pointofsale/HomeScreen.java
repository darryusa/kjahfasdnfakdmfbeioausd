package com.onesol.pointofsale;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.support.v4.app.LoaderManager;
import android.widget.FilterQueryProvider;
import android.widget.GridView;
import android.widget.SearchView;


import java.util.ArrayList;

import android.widget.Toast;


import com.google.android.gms.common.api.GoogleApiClient;

import static android.R.layout.simple_list_item_1;

public class HomeScreen extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>
{

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private CursorAdapter cursorAdapter;
    private String filter;
    private String[] arg;
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

        String[] from = {DbHandler.EMPLOYEE_KEY_FIRSTNAME ,  DbHandler.EMPLOYEE_KEY_LASTNAME};
        int[] to = {R.id.grid_item};

        cursorAdapter = new CustomCursorLoader(this,null,0);

        employeeGrid.setAdapter(cursorAdapter);

        employeeGrid.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
//                Cursor c = getContentResolver().query(Uri.parse(DataProvider.URI_EMPLOYEE+"/"+id)
//                        ,null,null,null,null);
                final String[] pinEnter = {null};
                Cursor c = getContentResolver().query(Uri.parse(DataProvider.URI_EMPLOYEE+"/"+id),new String[] {DbHandler.EMPLOYEE_KEY_PIN},null,null,null);
                c.moveToFirst();
                Cursor c2 = getContentResolver().query(Uri.parse(DataProvider.URI_EMPLOYEE+"/"),new String[] {DbHandler.EMPLOYEE_KEY_PIN}
                        ,DbHandler.EMPLOYEE_KEY_ROLE +"!=?",new String[]  {"Employee"},null);
//                c2.moveToFirst();
                int i =0;
                final ArrayList<String> pinNumber = new ArrayList<String>();
                while(c2.moveToNext())
                {
                     pinNumber.add(c2.getString(0));
                }

                final String pinNumber2 = c.getString(0);
                PinDialog pin = new PinDialog(HomeScreen.this, new PinDialog.ICustomDialogEventListener() {
                    @Override
                    public void customDialogEvent(String valueYouWantToSendBackToTheActivity) {
                        pinEnter[0] = valueYouWantToSendBackToTheActivity;
                        for(int i = 0; i < pinNumber2.length();i++)
                        {
                            if (pinEnter[0].equalsIgnoreCase(pinNumber.get(i)) || pinEnter[0].equalsIgnoreCase(pinNumber2)) {
                                //move to sale window
                                Toast.makeText(getApplicationContext(), "items " + pinEnter[0], Toast.LENGTH_SHORT).show();

                            }
                        }

                    }
                });

                pin.show();

            }
        });
        getSupportLoaderManager().initLoader(0,null,this);
        employeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), employeeActivity.class);
//                EditText editText = (EditText) findViewById(R.id.edit_message);
//                String message = editText.getText().toString();
//                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
//                Toast.makeText( getApplicationContext(),"employee button did click", Toast.LENGTH_SHORT).show();

            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                cursorAdapter.setFilterQueryProvider();
                filter = newText;

                getSupportLoaderManager().restartLoader(1,null,HomeScreen.this);
                Log.i("HOMESCREEN",newText);
//                cursorAdapter.getFilter().filter(newText);

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
        values.put(DbHandler.EMPLOYEE_KEY_FIRSTNAME, firstName);
        values.put(DbHandler.EMPLOYEE_KEY_LASTNAME, lastName);
        values.put(DbHandler.EMPLOYEE_KEY_ADDRESS, address);
        values.put(DbHandler.EMPLOYEE_KEY_PHONENUMBER, phoneNumber);
        values.put(DbHandler.EMPLOYEE_KEY_SSN, SSN);
        values.put(DbHandler.EMPLOYEE_KEY_PIN, PIN);
//        values.put(DbHandler.EMPLOYEE_KEY_DATECREATED, dateCreated);


        //Uri employeeUri = getContentResolver().insert(DataProvider.CONTENT_URI, values);
        //Log.d("HomeScreen", "InsertedNote" + employeeUri.getLastPathSegment());
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader loader = null;
        switch (id)
        {
            case 0:
                loader =  new CursorLoader(this, DataProvider.URI_EMPLOYEE, null, null, null, null);
            break;
            case 1:
                loader = new CursorLoader(this,DataProvider.URI_EMPLOYEE,null, DbHandler.EMPLOYEE_KEY_FIRSTNAME + " OR " + DbHandler.EMPLOYEE_KEY_LASTNAME
                        + " LIKE ?", new String[]  {"%"+ filter+"%"},null);
            break;
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        cursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cursorAdapter.swapCursor(null);
    }
}