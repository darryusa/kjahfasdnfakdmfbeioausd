package com.onesol.pointofsale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neal on 8/13/2016.
 */
public class DbHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION =1;
    private static final String DATABABSE_NAME = "pointOfSale.db";
    public static final String EMPLOYEE_TABLE = "employees";
    public static final String KEY_ID = "id";
    public static final String KEY_FIRSTNAME = "firstname";
    public static final String KEY_LASTNAME = "lastname";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_PHONENUMBER = "phonenumber";
    public static final String KEY_SSN = "ssn";
    public static final String KEY_PIN = "pin";
    public static final String KEY_DATECREATED = "datecreated";
    public static final String KEY_ACTIVE = "active";
    public static final String KEY_ROLE = "role";

    public static final String[] ALL_COLUMNS =
            {KEY_ID, KEY_FIRSTNAME, KEY_LASTNAME, KEY_ADDRESS, KEY_PHONENUMBER, KEY_SSN, KEY_PIN, KEY_DATECREATED, KEY_ACTIVE, KEY_ROLE};

    public DbHandler(Context context) {
        super(context, DATABABSE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + EMPLOYEE_TABLE +
        " ( " + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_FIRSTNAME + " TEXT,"
        + KEY_LASTNAME + " TEXT, "
                + KEY_ADDRESS + " TEXT,"
        + KEY_PHONENUMBER + " TEXT,"
        + KEY_SSN + " TEXT,"
        + KEY_PIN + " TEXT,"
        + KEY_DATECREATED + " TEXT default CURRENT_TIMESTAMP,"
        + KEY_ACTIVE + " INTEGER,"
                + KEY_ROLE + " INTEGER" + " )";
        db.execSQL(CREATE_CONTACTS_TABLE);
//        db.insert()
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS"  + EMPLOYEE_TABLE);
// Creating tables again
        onCreate(db);
    }
    public void addShop(Employee newEmployee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME, newEmployee.getFirstName());
        values.put(KEY_LASTNAME, newEmployee.getLastName());
        values.put(KEY_ADDRESS, newEmployee.getAddress());
        values.put(KEY_PHONENUMBER, newEmployee.getPhoneNumber());
        values.put(KEY_SSN, newEmployee.getSSN());
        values.put(KEY_PIN, newEmployee.getPIN());
        values.put(KEY_DATECREATED, newEmployee.getDateCreated());
        values.put(KEY_ACTIVE, newEmployee.isActivate()? 1:0);
        // Inserting Row
        db.insert(EMPLOYEE_TABLE, null, values);
        db.close(); // Closing database connection
    }
    public Employee getEmployee(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(EMPLOYEE_TABLE, new String[] { KEY_ID,
                        KEY_FIRSTNAME, KEY_LASTNAME, KEY_ADDRESS, KEY_PHONENUMBER, KEY_SSN,KEY_PIN,KEY_DATECREATED,KEY_ACTIVE}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Employee contact = new Employee(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),
                cursor.getString(6),cursor.getString(7),Integer.parseInt(cursor.getString(8)));

        return contact;
    }
    public List<Employee> getAllShops()
    {
        List<Employee> employeeList = new ArrayList<Employee>();

        String selectQuery = "SELECT * FROM " + EMPLOYEE_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Employee employee = new Employee();
                employee.setEmployeeID(Integer.parseInt(cursor.getString(0)));
                employee.setFirstName(cursor.getString(1));
                employee.setLastName(cursor.getString(2));
                employee.setAddress(cursor.getString(3));
                employee.setPhoneNumber(cursor.getString(4));
                employee.setSSN(cursor.getString(5));
                employee.setPIN(cursor.getString(6));
                employee.setDateCreated(cursor.getString(7));
                employee.setActivate(Integer.parseInt(cursor.getString(8)) == 0? false:true);
// Adding contact to list
                employeeList.add(employee);
            } while (cursor.moveToNext());
        }
// return contact list
        return employeeList;
    }
    public int getShopsCount()
    {
        String countQuery = "SELECT * FROM " + EMPLOYEE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
// return count
        return cursor.getCount();
    }
    public int updateShop(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME, employee.getFirstName());
        values.put(KEY_LASTNAME, employee.getLastName());
        values.put(KEY_ADDRESS, employee.getAddress());
        values.put(KEY_PHONENUMBER, employee.getPhoneNumber());
        values.put(KEY_SSN, employee.getSSN());
        values.put(KEY_PIN, employee.getPIN());
        values.put(KEY_DATECREATED, employee.getDateCreated());
        values.put(KEY_ACTIVE, employee.isActivate()? 1:0);
        // Inserting Row
        db.insert(EMPLOYEE_TABLE, null, values);
// updating row
        return db.update(EMPLOYEE_TABLE, values, KEY_ID + " = ?",
                new String[]{String.valueOf(employee.getEmployeeID())});
    }

    public void deleteShop(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(EMPLOYEE_TABLE, KEY_ID + " = ?",
                new String[] { String.valueOf(employee.getEmployeeID()) });
        db.close();
    }
}
