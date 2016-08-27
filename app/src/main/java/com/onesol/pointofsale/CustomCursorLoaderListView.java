package com.onesol.pointofsale;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Neal on 8/27/2016.
 */
public class CustomCursorLoaderListView extends CursorAdapter {
    public CustomCursorLoaderListView(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.listviewbutton,parent,false);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        String name = cursor.getString(cursor.getColumnIndex(DbHandler.EMPLOYEE_KEY_FIRSTNAME)) + " "
                + cursor.getString(cursor.getColumnIndex(DbHandler.EMPLOYEE_KEY_LASTNAME));
        TextView textView = (TextView) view.findViewById(R.id.listViewButton);
        textView.setText(name);

    }
}
