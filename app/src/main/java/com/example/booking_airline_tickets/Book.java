package com.example.booking_airline_tickets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class Book extends AppCompatActivity {
    private DB d;
    private TableLayout tableLayout;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        // Create an instance of the database helper
        d = new DB(this);
        Intent i = new Intent(this,Booking.class);
//        insertDataIntoDatabase();
        displayFlightsData();
        listView =(ListView) findViewById(R.id.listv);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
//                    Toast.makeText(Book.this, "first", Toast.LENGTH_SHORT).show();
                    ArrayList arrayList = d.getflight("1159");
                    i.putExtra("flight",arrayList);
                    startActivity(i);
                } else if (position==1){
                    ArrayList arrayList = d.getflight("18059");
                    i.putExtra("flight",arrayList);
                    startActivity(i);
                } else if (position==2){
                    ArrayList arrayList = d.getflight("18859");
                    i.putExtra("flight",arrayList);
                    startActivity(i);
                } else if (position==3){
                    ArrayList arrayList = d.getflight("18959");
                    i.putExtra("flight",arrayList);
                    startActivity(i);
                } else if (position==4){
                    ArrayList arrayList = d.getflight("115879");
                    i.putExtra("flight",arrayList);
                    startActivity(i);
                } else if (position==5){
                    ArrayList arrayList = d.getflight("115880");
                    i.putExtra("flight",arrayList);
                    startActivity(i);
                } else if (position==6){
                    ArrayList arrayList = d.getflight("115881");
                    i.putExtra("flight",arrayList);
                    startActivity(i);
                } else if (position==7){
                    ArrayList arrayList = d.getflight("1158889");
                    i.putExtra("flight",arrayList);
                    startActivity(i);
                }
            }
        });
//        boolean ress = d.insertDataIntoDatabase();
//        if (ress){
//            Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();
            show();
//
//        }
        // Insert data into the database
    }
    public void  show(){
        ArrayList<String> listdata = d.getAllFlights();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this , android.R.layout.simple_list_item_1,listdata);
        listView.setAdapter(arrayAdapter);

    }

    private void displayFlightsData() {
        // Get a readable database instance

        SQLiteDatabase db = d.getReadableDatabase();

        // Define the columns to retrieve from the flights table
        String[] projection = {
                d.COLUMN_FLIGHT_ID,
                d.COLUMN_TAKE_OFF,
                d.COLUMN_LANDING,
        };

        // Perform the query to retrieve all rows from the flights table
        Cursor cursor = db.query(
                d.TABLE_FLIGHTS,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        // Check if the cursor is valid and contains data
        if (cursor != null && ((Cursor) cursor).moveToFirst()) {
            do {
                // Retrieve the column values from the cursor
                String flightId = cursor.getString(cursor.getColumnIndexOrThrow(d.COLUMN_FLIGHT_ID));
                String takeOff = cursor.getString(cursor.getColumnIndexOrThrow(d.COLUMN_TAKE_OFF));
                String landing = cursor.getString(cursor.getColumnIndexOrThrow(d.COLUMN_LANDING));

                // Create a Flight object with the retrieved data
                Flight flight = new Flight(flightId, takeOff, landing,null,null,null);

                // Display the flight data (you can update this part according to your UI)

            } while (cursor.moveToNext());
        }

        // Close the cursor and database connection
        cursor.close();
        db.close();
    }



}