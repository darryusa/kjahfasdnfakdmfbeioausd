package com.onesol.pointofsale;


import android.app.SearchManager;
import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filterable;
import android.widget.GridLayout;

import android.widget.GridView;
import android.widget.SearchView;


import java.util.ArrayList;

import java.util.List;
import android.view.LayoutInflater;


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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        SearchView searchView = (SearchView) findViewById(R.id.searchView);
        final ArrayList<String> textView = new ArrayList<String>();
        final GridView employeeGrid = (GridView) findViewById(R.id.employeeGridView);
        final CustomGridAdapter gridAdapter;

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT

        );
        params.height = 100;
        params.width = 350;

        for (int i = 0; i < 100; i++) {
            textView.add("employee" + (i + 1));
        }
        gridAdapter = new CustomGridAdapter(HomeScreen.this, textView);
        employeeGrid.setAdapter(gridAdapter);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, simple_list_item_1, textView);

//        employeeGrid.setOnClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
//            {
////                text.setText((String) (employeeGrid.getItemAtPosition(position)));
//                Log.i("ITEM_CLICKED", "" + employeeGrid.getItemAtPosition(position));
//            }
//        });
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

//            employeeGrid.setOnItemClickListener(new AdapterView.OnItemClickListener()
//
//            {
//                @Override
//                public void onItemClick (AdapterView < ? > parent, View view,int position, long id){
//
////                    }
//
//                });
//                // ATTENTION: This was auto-generated to implement the App Indexing API.
//                // See https://g.co/AppIndexing/AndroidStudio for more information.
////            }
//
//
//            }
    }
}