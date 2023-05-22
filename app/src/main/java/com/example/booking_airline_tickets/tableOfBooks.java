package com.example.booking_airline_tickets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class tableOfBooks extends AppCompatActivity {
    Bundle extras;
    EditText ID_TRAVEL,FROM,TO,TAKE_OFF,LANDING,PRICE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_of_books);
        ID_TRAVEL = (EditText)findViewById(R.id.ID_TRAVEL);
        FROM = (EditText)findViewById(R.id.FROM);
        TO = (EditText) findViewById(R.id.TO);
        TAKE_OFF = (EditText) findViewById(R.id.TAKE_OFF);
        LANDING = (EditText) findViewById(R.id.LANDING);
        PRICE = (EditText) findViewById(R.id.PRICE);
        Intent data=new Intent(this , Home.class);
        extras = getIntent().getExtras();
        if (extras != null) {
            ArrayList arr = extras.getStringArrayList("flights_book");
            ID_TRAVEL.setText(arr.get(0).toString());
            PRICE.setText(arr.get(1).toString());
            LANDING.setText(arr.get(2).toString());
            TAKE_OFF.setText(arr.get(3).toString());
            TO.setText(arr.get(4).toString());
            FROM.setText(arr.get(5).toString());
        }
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(data);
            }
        });
    }
}
