package com.onesol.pointofsale;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SearchView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class employeeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>
{
    CursorAdapter cursorAdapter;
    String filter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        SearchView searchView = (SearchView) findViewById(R.id.searchViewEmployee);
        ListView listView = (ListView) findViewById(R.id.listView);
        Button addButton = (Button) findViewById(R.id.addButton);
        cursorAdapter = new CustomCursorLoaderListView(this,null,0);
        listView.setAdapter(cursorAdapter);
        getSupportLoaderManager().initLoader(0,null,this);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filter = s;

                getSupportLoaderManager().restartLoader(1,null,employeeActivity.this);
                return false;
            }
        });
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

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader loader = null;
        switch (id)
        {
            case 0:
                loader =  new CursorLoader(this, DataProvider.URI_EMPLOYEE, null, null, null, null);
                break;
            case 1:
                loader = new CursorLoader(this,DataProvider.URI_EMPLOYEE,null, DbHandler.EMPLOYEE_KEY_FIRSTNAME
                        + " LIKE ?" + " OR " + DbHandler.EMPLOYEE_KEY_LASTNAME + " LIKE ?",
                        new String[]  {"%"+ filter+"%","%"+ filter+"%"},null);
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
