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
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH );
    // Constant to identify the requested operation
    private static final int NOTES = 1;
    private static final int NOTES_ID = 2;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static
    {
        uriMatcher.addURI(AUTHORITY, BASE_PATH,NOTES);
        uriMatcher.addURI(AUTHORITY,BASE_PATH + "/#",NOTES_ID);
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
                DbHandler.KEY_DATECREATED + " DESC");
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
