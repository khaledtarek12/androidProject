package com.example.booking_airline_tickets;

import static com.example.booking_airline_tickets.DB.IDBOOK;
import static com.example.booking_airline_tickets.login.USERNAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class show extends AppCompatActivity {
    DB db = new DB(this);
EditText num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        num=(EditText) findViewById(R.id.inputNum);
        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
       String ID_FLIGHT= num.getText().toString();
                if (ID_FLIGHT.equals(IDBOOK)){
                Intent i=new Intent(show.this ,tableOfBooks.class);
                    ArrayList flights_book = db.getflight(IDBOOK);
                i.putExtra("flights_book",flights_book);
                startActivity(i);

                } else {
                    Toast.makeText(show.this, "لا توجد رحلة محجوزة بهذا الرقم", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}