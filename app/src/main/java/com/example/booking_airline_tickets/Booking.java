package com.example.booking_airline_tickets;

import static com.example.booking_airline_tickets.login.USERNAME;
import static com.example.booking_airline_tickets.login.loginflag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Booking extends AppCompatActivity {
    TextView from , to,takeoff,Landing , price,id_travel;
    Button book;
    Bundle extras;
    String ID;
    DB db = new DB(this);
    Intent home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        home = new Intent(this,Home.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        from = (TextView) findViewById(R.id.from);
        to = (TextView) findViewById(R.id.to);
        takeoff = (TextView) findViewById(R.id.takeoff);
        Landing = (TextView) findViewById(R.id.TAKE_OFF);
        price = (TextView) findViewById(R.id.price);
        id_travel = (TextView) findViewById(R.id.id_travel);
        book = (Button) findViewById(R.id.book);
        extras = getIntent().getExtras();
        if (extras != null) {
          ArrayList arr = extras.getStringArrayList("flight");
            id_travel.setText(arr.get(0).toString());
            price.setText(arr.get(1).toString());
            Landing.setText(arr.get(2).toString());
            takeoff.setText(arr.get(3).toString());
            to.setText(arr.get(4).toString());
            from.setText(arr.get(5).toString());
            ID = id_travel.getText().toString();
        }
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   if (loginflag){
                       if (db.book(USERNAME,ID)){
                           Toast.makeText(Booking.this, "تم الحجز بنجاح", Toast.LENGTH_SHORT).show();
                           startActivity(home);
                       }
                   } else {
                       Toast.makeText(Booking.this, "يجب عليك تسجيل الدخول أولا", Toast.LENGTH_SHORT).show();
                   }
            }
        });

    }
}