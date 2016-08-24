package com.onesol.pointofsale;


import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

public class DataProvider extends ContentProvider
{
    private static final String AUTHORITY = "com.onesol.pointofsale.DataProvider";
    private static final String BASE_PATH = "pointOfSale";

    // creating multiple content uri's for each db table
    public static final Uri URI_EMPLOYEE = Uri.parse("content://" + AUTHORITY + "/employeeuri");
    public static final Uri URI_INVENTORY = Uri.parse("content://" + AUTHORITY + "/inventoryuri");
    public static final Uri URI_SALES = Uri.parse("content://" + AUTHORITY + "/salesuri");
    public static final Uri URI_SALESDISCRIPTION = Uri.parse("content://" + AUTHORITY + "/salesdiscriptionuri");
    public static final Uri URI_EXPENSES = Uri.parse("content://" + AUTHORITY + "/expensesuri");
    // ENUM to identify the requested operation
    private static final int EMPLOYEE = 1;
    private static final int EMPLOYEE_ID = 2;
    private static final int INVENTORY = 3;
    private static final int INVENTORY_ID = 4;
    private static final int SALES = 5;
    private static final int SALES_ID = 6;
    private static final int SALESDISCRIPTION = 7;
    private static final int SALESDISCRIPTION_ID = 8;

    private static final UriMatcher uriMatcher;
    static
    {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "employeeuri", EMPLOYEE);

    }

    private SQLiteDatabase db;
    @Override
    public boolean onCreate() {
        DbHandler handler = new DbHandler(getContext());

        db = handler.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        return db.query(DbHandler.EMPLOYEE_TABLE, DbHandler.ALL_COLUMNS, selection, null,null,null,
                DbHandler.EMPLOYEE_KEY_DATECREATED + " DESC");
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = db.insert(DbHandler.EMPLOYEE_TABLE, null , values);
        return Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return db.delete(DbHandler.EMPLOYEE_TABLE,selection,selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return db.update(DbHandler.EMPLOYEE_TABLE,values,selection,selectionArgs);
    }
}
