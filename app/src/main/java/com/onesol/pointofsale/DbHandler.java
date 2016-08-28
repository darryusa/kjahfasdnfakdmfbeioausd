package com.onesol.pointofsale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neal on 8/13/2016.
 */
public class DbHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION =1;
    private static final String DATABABSE_NAME = "pointOfSale.db";
    public static final String EMPLOYEE_TABLE = "employees";
    public static final String EMPLOYEE_KEY_ID = "_id";
    public static final String EMPLOYEE_KEY_FIRSTNAME = "firstname";
    public static final String EMPLOYEE_KEY_LASTNAME = "lastname";
    public static final String EMPLOYEE_KEY_ADDRESS = "address";
    public static final String EMPLOYEE_KEY_PHONENUMBER = "phonenumber";
    public static final String EMPLOYEE_KEY_SSN = "ssn";
    public static final String EMPLOYEE_KEY_PIN = "pin";
    public static final String EMPLOYEE_KEY_DATECREATED = "datecreated";
    public static final String EMPLOYEE_KEY_ACTIVE = "active";
    public static final String EMPLOYEE_KEY_ROLE = "role";
    public static final String EMPLOYEE_KEY_DRIVERLICENSE = "driverlicense";
    public static final String EMPLOYEE_KEY_DATEOFBIRTH = "dateofbirth";
    
    public static final String INVENTORY_TABLE = "inventory";
    public static final String INVENTORY_KEY_ID = "_id";
    public static final String INVENTORY_KEY_NAME ="name";
    public static final String INVENTORY_KEY_PRICE = "price";
    public static final String INVENTORY_KEY_DESCRIPTION = "description";
    public static final String INVENTORY_KEY_QUANTITY = "quantity";

    public static final String SALES_TABLE = "sales";
    public static final String SALES_KEY_ID = "_id";
    public static final String SALES_KEY_EMPLOYEEID = "employeeid";
    public static final String SALES_KEY_TOTAL = "total";
    public static final String SALES_KEY_SUBTOTAL = "subtotal";
    public static final String SALES_KEY_SALETAX = "saletax";
    public static final String SALES_KEY_DATECREATED = "date";

    public static final String SALEDESCRIPTION_TABLE = "saledecription";
    public static final String SALEDESCRIPTION_ID = "_id";
    public static final String SALEDESCRIPTION_SALEID = "saleid";
    public static final String SALEDESCRIPTION_ITEMID = "itemid";




    public static final String[] EMPLOYEE_TABLE_ALL_COLUMNS =
            {EMPLOYEE_KEY_ID, EMPLOYEE_KEY_FIRSTNAME, EMPLOYEE_KEY_LASTNAME, EMPLOYEE_KEY_ADDRESS, EMPLOYEE_KEY_PHONENUMBER, EMPLOYEE_KEY_SSN, EMPLOYEE_KEY_PIN, EMPLOYEE_KEY_DATECREATED, EMPLOYEE_KEY_ACTIVE, EMPLOYEE_KEY_ROLE, EMPLOYEE_KEY_DRIVERLICENSE, EMPLOYEE_KEY_DATEOFBIRTH};
    public static final String[] INVENTORY_TABLE_ALL_COLUMNS =
            {INVENTORY_KEY_ID, INVENTORY_KEY_NAME,INVENTORY_KEY_PRICE,INVENTORY_KEY_DESCRIPTION,INVENTORY_KEY_QUANTITY};
    public static final String[] SALES_TABLE_ALL_COLUMNS =
            {SALES_KEY_ID,SALES_KEY_EMPLOYEEID,SALES_KEY_TOTAL,SALES_KEY_SUBTOTAL,SALES_KEY_SALETAX,SALES_KEY_DATECREATED};
    public static final String[] SALEDESCRIPTION_TABLE_ALL_COLUMNS =
            {SALEDESCRIPTION_ID,SALEDESCRIPTION_SALEID,SALEDESCRIPTION_ITEMID};

    public DbHandler(Context context) {
        super(context, DATABABSE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EMPLOYEE_TABLE = "CREATE TABLE " + EMPLOYEE_TABLE +
        " ( " + EMPLOYEE_KEY_ID + " INTEGER PRIMARY KEY, "
                + EMPLOYEE_KEY_FIRSTNAME + " TEXT, "
        + EMPLOYEE_KEY_LASTNAME + " TEXT, "
                + EMPLOYEE_KEY_ADDRESS + " TEXT, "
        + EMPLOYEE_KEY_PHONENUMBER + " TEXT, "
        + EMPLOYEE_KEY_SSN + " TEXT, "
        + EMPLOYEE_KEY_PIN + " TEXT, "
        + EMPLOYEE_KEY_DATECREATED + " TEXT default CURRENT_TIMESTAMP, "
        + EMPLOYEE_KEY_ACTIVE + " INTEGER,"
                + EMPLOYEE_KEY_ROLE + " INTEGER,"
                + EMPLOYEE_KEY_DRIVERLICENSE + " TEXT, "
        + EMPLOYEE_KEY_DATEOFBIRTH + " TEXT" + ")";

        String CREATE_INVENTORY_TABLE = "CREATE TABLE " + INVENTORY_TABLE +
                " ( " + INVENTORY_KEY_ID + " INTEGER PRIMARY KEY,"
                + INVENTORY_KEY_NAME + " TEXT,"
                + INVENTORY_KEY_PRICE + " REAL, "
                + INVENTORY_KEY_DESCRIPTION + " TEXT, "
                + INVENTORY_KEY_QUANTITY + " INTEGER " + ")";

        String CREATE_SALES_TABLE = "CREATE TABLE " + SALES_TABLE +
                " ( " + SALES_KEY_ID + " INTEGER PRIMARY KEY, "
                + SALES_KEY_EMPLOYEEID + " TEXT, "
                + SALES_KEY_TOTAL + " REAL, "
                + SALES_KEY_SUBTOTAL + " REAL, "
                + SALES_KEY_SALETAX + " REAL, "
                + SALES_KEY_DATECREATED + " TEXT default CURRENT_TIMESTAMP" + ")";

        String CREATE_SALEDESCRIPTION_TABLE = "CREATE TABLE " + SALEDESCRIPTION_TABLE +
                " ( " + SALEDESCRIPTION_ID + " INTEGER PRIMARY KEY,"
                + SALEDESCRIPTION_SALEID + " INTEGER,"
                + SALEDESCRIPTION_ITEMID + " INTEGER " + ")";
        db.execSQL(CREATE_EMPLOYEE_TABLE);
        db.execSQL(CREATE_INVENTORY_TABLE);
        db.execSQL(CREATE_SALES_TABLE);
        db.execSQL(CREATE_SALEDESCRIPTION_TABLE);

//        db.insert()
//        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbHandler.EMPLOYEE_KEY_ROLE, "Admin");
        values.put(DbHandler.EMPLOYEE_KEY_PIN, "5555");
//        getContent().getContentResolver().insert(DataProvider.URI_EMPLOYEE, values);
        db.insert(DbHandler.EMPLOYEE_TABLE, null , values);
//        db1.insert(EMPLOYEE_TABLE, null, values);
//        db1.close(); // Closing database connection
//        addEmployee(new Employee(0,"admin",null,null,null,null,"5555",null,1));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS"  + EMPLOYEE_TABLE);
// Creating tables again
        onCreate(db);
    }
//    public void addEmployee(Employee newEmployee) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(EMPLOYEE_KEY_FIRSTNAME, newEmployee.getFirstName());
//        values.put(EMPLOYEE_KEY_LASTNAME, newEmployee.getLastName());
//        values.put(EMPLOYEE_KEY_ADDRESS, newEmployee.getAddress());
//        values.put(EMPLOYEE_KEY_PHONENUMBER, newEmployee.getPhoneNumber());
//        values.put(EMPLOYEE_KEY_SSN, newEmployee.getSSN());
//        values.put(EMPLOYEE_KEY_PIN, newEmployee.getPIN());
//        values.put(EMPLOYEE_KEY_DATECREATED, newEmployee.getDateCreated());
//        values.put(EMPLOYEE_KEY_ACTIVE, newEmployee.isActivate()? 1:0);
//        values.put(EMPLOYEE_KEY_DATECREATED, newEmployee)
//        // Inserting Row
//        db.insert(EMPLOYEE_TABLE, null, values);
//        db.close(); // Closing database connection
//    }
//    public Employee getEmployee(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query(EMPLOYEE_TABLE, new String[] { EMPLOYEE_KEY_ID,
//                        EMPLOYEE_KEY_FIRSTNAME, EMPLOYEE_KEY_LASTNAME, EMPLOYEE_KEY_ADDRESS, EMPLOYEE_KEY_PHONENUMBER, EMPLOYEE_KEY_SSN,EMPLOYEE_KEY_PIN,EMPLOYEE_KEY_DATECREATED,EMPLOYEE_KEY_ACTIVE,EMPLOYEE_KEY_DRIVERLICENSE,EMPLOYEE_KEY_DATEOFBIRTH}, EMPLOYEE_KEY_ID + "=?",
//                new String[] { String.valueOf(id) }, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//        Employee contact = new Employee(Integer.parseInt(cursor.getString(0)),
//                cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),
//                cursor.getString(6),cursor.getString(7),Integer.parseInt(cursor.getString(8)));
//
//        return contact;
//    }
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
        values.put(EMPLOYEE_KEY_FIRSTNAME, employee.getFirstName());
        values.put(EMPLOYEE_KEY_LASTNAME, employee.getLastName());
        values.put(EMPLOYEE_KEY_ADDRESS, employee.getAddress());
        values.put(EMPLOYEE_KEY_PHONENUMBER, employee.getPhoneNumber());
        values.put(EMPLOYEE_KEY_SSN, employee.getSSN());
        values.put(EMPLOYEE_KEY_PIN, employee.getPIN());
        values.put(EMPLOYEE_KEY_DATECREATED, employee.getDateCreated());
        values.put(EMPLOYEE_KEY_ACTIVE, employee.isActivate()? 1:0);
        // Inserting Row
        db.insert(EMPLOYEE_TABLE, null, values);
// updating row
        return db.update(EMPLOYEE_TABLE, values, EMPLOYEE_KEY_ID + " = ?",
                new String[]{String.valueOf(employee.getEmployeeID())});
    }

    public void deleteShop(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(EMPLOYEE_TABLE, EMPLOYEE_KEY_ID + " = ?",
                new String[] { String.valueOf(employee.getEmployeeID()) });
        db.close();
    }


}
