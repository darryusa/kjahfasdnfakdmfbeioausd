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
        int[] to = {android.R.id.text1};

        cursorAdapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,null,from,to,0);

        gridAdapter = new CustomGridAdapter(HomeScreen.this, textView);
        employeeGrid.setAdapter(cursorAdapter);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, simple_list_item_1, textView);

        employeeGrid.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                PinDialog pin = new PinDialog(HomeScreen.this);

                pin.show();

//                Toast.makeText( getApplicationContext(),, Toast.LENGTH_SHORT).show();
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

                cursorAdapter.getFilter().filter(newText);
                cursorAdapter.notifyDataSetChanged();
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
        return new CursorLoader(this, DataProvider.URI_EMPLOYEE,null,null,null,null);
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