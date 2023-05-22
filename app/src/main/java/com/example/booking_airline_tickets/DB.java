package com.example.booking_airline_tickets;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;

import com.example.booking_airline_tickets.Flight;
import com.example.booking_airline_tickets.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DB extends SQLiteOpenHelper {
    public static String IDBOOK="";
    public static final String DATABASE_NAME = "flight_booking.db";
    public static final int DATABASE_VERSION = 1;

    // Table names
    public static final String TABLE_USERS = "users_table";
    public static final String TABLE_FLIGHTS = "flights_table";

    // Columns for the users_table
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_USER_NAME = "user_name";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_ID_FLIGHT = "id_flight";

    // Columns for the flights_table
    public static final String COLUMN_FLIGHT_ID = "id_flights";
    public static final String COLUMN_TAKE_OFF = "take_off";
    public static final String COLUMN_LANDING = "landing";
    public static final String COLUMN_START_TIME = "start_time";
    public static final String COLUMN_END_TIME = "end_time";
    public static final String COLUMN_PRICE = "price";

    public DB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the users_table
        String createUserTable = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USER_NAME + " TEXT,"
                + COLUMN_PASSWORD + " TEXT,"
                + COLUMN_ID_FLIGHT + " INTEGER"
                + ")";
        db.execSQL(createUserTable);

        // Create the flights_table
        String createFlightsTable = "CREATE TABLE " + TABLE_FLIGHTS + "("
                + COLUMN_FLIGHT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TAKE_OFF + " TEXT,"
                + COLUMN_LANDING + " TEXT,"
                + COLUMN_START_TIME + " TEXT,"
                + COLUMN_END_TIME + " TEXT,"
                + COLUMN_PRICE + " INTEGER"
                + ")";
        db.execSQL(createFlightsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if they exist and create fresh
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FLIGHTS);
        onCreate(db);
    }

//    public void addUser(User user) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_USER_NAME, user.getUserName());
//        values.put(COLUMN_PASSWORD, user.getPassword());
//        values.put(COLUMN_ID_FLIGHT, user.getFlightId());
//        db.insert(TABLE_USERS, null, values);
//        db.close();
//    }
//
//    public void addFlight(Flight flight) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_TAKE_OFF, flight.getTakeOff());
//        values.put(COLUMN_LANDING, flight.getLanding());
//        values.put(COLUMN_START_TIME, flight.getStartTime());
//        values.put(COLUMN_END_TIME, flight.getEndTime());
//        values.put(COLUMN_PRICE, flight.getPrice());
//        db.insert(TABLE_FLIGHTS, null, values);
//        db.close();
//    }
    public boolean registerUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Check if the username is already taken
        if (isUsernameTaken(username)) {
        db.close();
            return false; // Username is already taken
        }

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, username);
        values.put(COLUMN_PASSWORD, password);

        long result = db.insert(TABLE_USERS, null, values);
        db.close();

        return result != -1;
    }

    public boolean book(String username, String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Check if the username is already taken
        if (isUsernameTaken(username)) {
            System.out.println("dsdsdaasccc:  "+id);
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID_FLIGHT, id);
            IDBOOK=id;
            long result = db.insert(TABLE_USERS, null, values);
            db.close();
            db.close();
            return  result != -1;
        }



        return false;
    }





    @SuppressLint("Range")
    public void assignColumnValues(SQLiteDatabase database) {
        String selectQuery = "SELECT id_flights FROM flights_table";
        Cursor cursor = database.rawQuery(selectQuery, null);

        String updateQuery = "UPDATE users_table SET id_flight = ? WHERE user_name = ?";
        SQLiteStatement statement = database.compileStatement(updateQuery);

        if (cursor.moveToFirst()) {
            do {
                 String flightValue = cursor.getString(cursor.getColumnIndex("id_flights"));
                String USERNAME = cursor.getString(cursor.getColumnIndex("user_name"));

                        statement.bindString(1, flightValue);
                statement.bindString(2, USERNAME);
                statement.execute();
            } while (cursor.moveToNext());
        }

        cursor.close();
        statement.close();
    }


    private boolean isUsernameTaken(String username) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = COLUMN_USER_NAME + " = ?";
        String[] selectionArgs = {username};

        Cursor cursor = db.query(
                TABLE_USERS,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        boolean isTaken = cursor.getCount() > 0;


        return isTaken;
    }
    @SuppressLint("Range")
    public String getFlightbook(String username) {
        String id = "Not found"; // Default value if no row is found
        if (username!=null){

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"id_flight"};
        String selection = COLUMN_USER_NAME + " = ?";
        String[] selectionArgs = {username};
        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            id = cursor.getString(cursor.getColumnIndex("id_flight"));
            System.out.println("adssss: "+id);
            cursor.close();
        return IDBOOK;
        }
        }
        return id;

    }

    @SuppressLint("Range")
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_USER_ID)));
                user.setUserName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
                user.setFlightId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID_FLIGHT)));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userList;
    }
    @SuppressLint("Range")
    public ArrayList getflight(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {COLUMN_FLIGHT_ID, COLUMN_TAKE_OFF, COLUMN_LANDING,COLUMN_START_TIME,COLUMN_END_TIME,COLUMN_PRICE};
        String selection = COLUMN_FLIGHT_ID + " = ?";
        String[] selectionArgs = {id};

        Cursor cursor = db.query(
                TABLE_FLIGHTS,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        ArrayList data = new ArrayList();

        if (cursor.moveToFirst()) {
            String FLIGHT_ID = cursor.getString(cursor.getColumnIndex(COLUMN_FLIGHT_ID));
            String takeoff = cursor.getString(cursor.getColumnIndex(COLUMN_TAKE_OFF));
            String landin = cursor.getString(cursor.getColumnIndex(COLUMN_LANDING));
            String START_TIME = cursor.getString(cursor.getColumnIndex(COLUMN_START_TIME));
            String END_TIME = cursor.getString(cursor.getColumnIndex(COLUMN_END_TIME));
            String PRICE = cursor.getString(cursor.getColumnIndex(COLUMN_PRICE));

            // Create an instance of YourDataModel with retrieved data
            data.add(FLIGHT_ID);
            data.add(PRICE);
            data.add(END_TIME);
            data.add(START_TIME);
            data.add(landin);
            data.add(takeoff);


        }

        cursor.close();
        db.close();

        return data;
    }

    public void CancelFlight(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = "user_name=?";
        String[] whereArgs = { username };
        db.delete(TABLE_USERS, whereClause, whereArgs);
    }


    public void deleteFlightsByUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Define the WHERE clause
        String whereClause = "id_flight = ?";

        // Define the values for the WHERE clause
        String[] whereArgs = { String.valueOf(user.getFlightId()) };

        // Delete the flights from the flights table
        db.delete("flights", whereClause, whereArgs);

        // Close the database connection
        db.close();
    }
    public boolean authenticateUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = COLUMN_USER_NAME + " = ? AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query(
                TABLE_USERS,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        boolean isAuthenticated = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return isAuthenticated;
    }
    public boolean insertDataIntoDatabase() {
        // Get a writable database instance
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a ContentValues object to hold the column values for the new row
        ContentValues values = new ContentValues();
//        values.put(COLUMN_FLIGHT_ID,"18959");
//        values.put(COLUMN_TAKE_OFF, "القاهرة");
//        values.put(COLUMN_LANDING, "الصين");
//        values.put(COLUMN_START_TIME, "1:00 م");
//        values.put(COLUMN_END_TIME, "7:00 م");
//        values.put(COLUMN_PRICE, "10000ج");



//        values.put(COLUMN_FLIGHT_ID,"1159");
//        values.put(COLUMN_TAKE_OFF, "القاهرة");
//        values.put(COLUMN_LANDING, "تركيا");
//        values.put(COLUMN_START_TIME, "1:00 م");
//        values.put(COLUMN_END_TIME, "3:00 م");
//        values.put(COLUMN_PRICE, "9000ج");

//        values.put(COLUMN_FLIGHT_ID,"18059");
//        values.put(COLUMN_TAKE_OFF, "برج العرب");
//        values.put(COLUMN_LANDING, "المانيا");
//        values.put(COLUMN_START_TIME, "3:00 م");
//        values.put(COLUMN_END_TIME, "7:00 م");
//        values.put(COLUMN_PRICE, "9500ج");

//        values.put(COLUMN_FLIGHT_ID,"18859");
//        values.put(COLUMN_TAKE_OFF, "اسكندرية");
//        values.put(COLUMN_LANDING, "فرنا");
//        values.put(COLUMN_START_TIME, "10:00 م");
//        values.put(COLUMN_END_TIME, "1:00 ص");
//        values.put(COLUMN_PRICE, "11000ج");

//        values.put(COLUMN_FLIGHT_ID,"115879");
//        values.put(COLUMN_TAKE_OFF, "الاسكندرية");
//        values.put(COLUMN_LANDING, "القاهرة");
//        values.put(COLUMN_START_TIME, "1:00 م");
//        values.put(COLUMN_END_TIME, "2:00 م");
//        values.put(COLUMN_PRICE, "500ج");

//        values.put(COLUMN_FLIGHT_ID,"115880");
//        values.put(COLUMN_TAKE_OFF, "القاهرة");
//        values.put(COLUMN_LANDING, "الاسكندرية");
//        values.put(COLUMN_START_TIME, "7:00 م");
//        values.put(COLUMN_END_TIME, "8:00 م");
//        values.put(COLUMN_PRICE, "500ج");
//
//
//        values.put(COLUMN_FLIGHT_ID,"115881");
//        values.put(COLUMN_TAKE_OFF, "القاهرة");
//        values.put(COLUMN_LANDING, "شرم الشيخ");
//        values.put(COLUMN_START_TIME, "6:00 م");
//        values.put(COLUMN_END_TIME, "8:00 م");
//        values.put(COLUMN_PRICE, "2500ج");
//
//        values.put(COLUMN_FLIGHT_ID,"115889");
//        values.put(COLUMN_TAKE_OFF, "القاهرة");
//        values.put(COLUMN_LANDING, "السعودية");
//        values.put(COLUMN_START_TIME, "7:00 ص");
//        values.put(COLUMN_END_TIME, "10:00 ص");
//        values.put(COLUMN_PRICE, "20000ج");

        // Insert the values into the flights table
        long newRowId = db.insert(TABLE_FLIGHTS, null, values);

        if (newRowId != -1) {
            // Insertion successful
            return true;
        } else {
            // Insertion failed
            return false;
        }

    }
    @SuppressLint("Range")
    public ArrayList getAllFlights(){
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_FLIGHTS,null);
        res.moveToFirst();
        while (res.isAfterLast()==false){
            String s1 = res.getString(res.getColumnIndex(COLUMN_FLIGHT_ID));
            String s2 = res.getString(res.getColumnIndex(COLUMN_TAKE_OFF));
            String s3 = res.getString(res.getColumnIndex(COLUMN_LANDING));


            arrayList.add("رقم الرحلة"+s1+" من "+s2+" إلى "+s3);
            res.moveToNext();
        }
        return arrayList;
    }
}
