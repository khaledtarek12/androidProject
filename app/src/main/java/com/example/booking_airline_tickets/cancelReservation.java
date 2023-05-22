package com.example.booking_airline_tickets;

import static com.example.booking_airline_tickets.DB.IDBOOK;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class cancelReservation extends AppCompatActivity {
    DB db = new DB(this);
    private Button cancelButton;
    EditText user_name,reservationNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_reservation);
        cancelButton= (Button) findViewById(R.id.cancelButton);
        user_name = (EditText) findViewById(R.id.user_name);
        reservationNumber = (EditText) findViewById(R.id.reservationNumber);
    String USER_name = user_name.getText().toString();

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
    String ID_Travel = reservationNumber.getText().toString();
                if (ID_Travel.equals(IDBOOK)){
                    db.CancelFlight(USER_name);
                    IDBOOK=null;
                    Toast.makeText(cancelReservation.this, "تم إلغاء الرحلة بنجاح", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}