package com.onesol.pointofsale;


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;

public class DataProvider extends ContentProvider
{
    private static final String AUTHORITY = "com.onesol.pointofsale.DataProvider";
   // private static final String BASE_PATH = "pointOfSale";

    // creating multiple content uri's for each db table
    public static final Uri URI_EMPLOYEE = Uri.parse("content://" + AUTHORITY + "/employeeuri");
    public static final Uri URI_INVENTORY = Uri.parse("content://" + AUTHORITY + "/inventoryuri");
    public static final Uri URI_SALES = Uri.parse("content://" + AUTHORITY + "/salesuri");
    public static final Uri URI_SALESDISCRIPTION = Uri.parse("content://" + AUTHORITY + "/salesdiscriptionuri");
    //public static final Uri URI_EXPENSES = Uri.parse("content://" + AUTHORITY + "/expensesuri");

    // ENUM to identify the requested operation
    private static final int EMPLOYEE = 1;
    private static final int EMPLOYEE_ID = 2;
    private static final int INVENTORY = 3;
    private static final int INVENTORY_ID = 4;
    private static final int SALES = 5;
    private static final int SALES_ID = 6;
    private static final int SALESDISCRIPTION = 7;
    private static final int SALESDISCRIPTION_ID = 8;
    //private static final int EXPENSES = 9;
    //private static final int EXPENSES_ID = 10;

    private static final UriMatcher uriMatcher;
    static
    {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "employeeuri", EMPLOYEE);
        uriMatcher.addURI(AUTHORITY, "employeeuri/#", EMPLOYEE_ID);
        uriMatcher.addURI(AUTHORITY, "inventoryuri", INVENTORY);
        uriMatcher.addURI(AUTHORITY, "inventoryuri/#", INVENTORY_ID);
        uriMatcher.addURI(AUTHORITY, "salesuri", SALES);
        uriMatcher.addURI(AUTHORITY, "salesuri/#", SALES_ID);
        uriMatcher.addURI(AUTHORITY, "salesdiscriptionuri", SALESDISCRIPTION);
        uriMatcher.addURI(AUTHORITY, "salesdiscriptionuri/#", SALESDISCRIPTION_ID);
        //uriMatcher.addURI(AUTHORITY, "expensesuri", EXPENSES);
        //uriMatcher.addURI(AUTHORITY, "expensesuri/#", EXPENSES_ID);
    }

    private SQLiteDatabase db;
    DbHandler handler;

    @Override
    public boolean onCreate()
    {
        handler = new DbHandler(getContext());
        db = handler.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
    {
        // Using SQLiteQueryBuilder instead of query() method
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        int uriType = uriMatcher.match(uri);

        switch (uriType)
        {
            case EMPLOYEE_ID:
                // Adding the ID to the original query
                queryBuilder.appendWhere(DbHandler.EMPLOYEE_KEY_ID + "="
                        + uri.getLastPathSegment());
                //$FALL-THROUGH$
            case EMPLOYEE:
                queryBuilder.setTables(DbHandler.EMPLOYEE_TABLE);
                break;

            case INVENTORY_ID:
                // Adding the ID to the original query
                queryBuilder.appendWhere(DbHandler.INVENTORY_KEY_ID + "="
                        + uri.getLastPathSegment());
                //$FALL-THROUGH$
            case INVENTORY:
                queryBuilder.setTables(DbHandler.INVENTORY_TABLE);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        Cursor cursor = queryBuilder.query(handler.getReadableDatabase(),
            projection, selection, selectionArgs, null, null,
            sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(),
                uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri)
    {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values)
    {
        Uri _uri = null;
        switch (uriMatcher.match(uri))
        {
            case EMPLOYEE:
                long _employee = db.insert(DbHandler.EMPLOYEE_TABLE, null , values);
                if(_employee > 0)
                {
                    _uri = ContentUris.withAppendedId(URI_EMPLOYEE, _employee);
                    getContext().getContentResolver().notifyChange(_uri, null);
                }
                break;

            case INVENTORY:
                long _inventory = db.insert(DbHandler.INVENTORY_TABLE, null, values);
                if(_inventory > 0)
                {
                    uri = ContentUris.withAppendedId(URI_INVENTORY, _inventory);
                    getContext().getContentResolver().notifyChange(_uri, null);
                }
                break;

            case SALES:
                long _sales = db.insert(DbHandler.SALES_TABLE, null, values);
                if(_sales > 0)
                {
                    uri = ContentUris.withAppendedId(URI_SALES, _sales);
                    getContext().getContentResolver().notifyChange(_uri, null);
                }
                break;

            case SALESDISCRIPTION:
                long _salesdiscription = db.insert(DbHandler.SALEDESCRIPTION_TABLE, null, values);
                if(_salesdiscription > 0)
                {
                    uri = ContentUris.withAppendedId(URI_SALESDISCRIPTION, _salesdiscription);
                    getContext().getContentResolver().notifyChange(_uri, null);
                }
                break;

            default:
                throw new SQLiteException("Failed to insert row into "+ uri);
        }

        return _uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs)
    {
        String deleteTable = null;
        switch (uriMatcher.match(uri))
        {
            case EMPLOYEE:
                deleteTable = DbHandler.EMPLOYEE_TABLE;
                break;

            case INVENTORY:
                deleteTable = DbHandler.INVENTORY_TABLE;
                break;

            case SALES:
                deleteTable = DbHandler.SALES_TABLE;
                break;

            case SALESDISCRIPTION:
                deleteTable = DbHandler.SALEDESCRIPTION_TABLE;
                break;

            default:
                throw new SQLiteException("Failed to delete row into "+ uri);
        }

        return db.delete(deleteTable,selection,selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs)
    {
        return db.update(DbHandler.EMPLOYEE_TABLE,values,selection,selectionArgs);
    }
}
