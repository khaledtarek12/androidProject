package com.example.booking_airline_tickets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Contact extends AppCompatActivity {
    ImageView vfacebook,vinstagram,vtwitter;
    EditText name ,email , phone,message;
    Button showMap ,sendbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        vtwitter=findViewById(R.id.iv_twitter);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        message = (EditText) findViewById(R.id.message);
        vfacebook=findViewById(R.id.iv_facebook);
        vinstagram=findViewById(R.id.iv_instagram);
        sendbtn = (Button) findViewById(R.id.sendbtn);
        showMap=findViewById(R.id.showMap);
        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidation()){
                    Toast.makeText(Contact.this, "تم الارسال بنجاح", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Intent intent=new Intent(this, MapActivity.class);
        showMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

        vfacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sAppLink="fb://page/237564710351658";
                String sPackage="com.facebook.katana";
                String sWebLink="https://m.facebook.com/EGYPTAIR";
                //create method
                openLink(sAppLink,sPackage,sWebLink);
            }
        });
        vinstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sAppLink="https://www.instagram.com/Egyptair/";
                String sPackage="com.instagram.android";
                openLink(sAppLink,sPackage,sAppLink);
            }
        });




        vtwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sAppLink="twitter://user?screen_name=AndroidCoding_";
                String sPackage="com.twitter.android";
                String sWebLink="https://twitter.com/EGYPTAIR";
                openLink(sAppLink,sPackage,sWebLink);
            }
        });

    }

    private void openLink(String sAppLink,String sPackage,String sWebLink){
        try {
            Uri uri =Uri.parse(sAppLink);
            Intent intent =new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            intent.setPackage(sPackage);
            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }catch (ActivityNotFoundException activityNotFoundException ){
            Uri uri=Uri.parse(sWebLink);
            Intent intent=new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    public boolean checkValidation(){
        int flag = 0;
        if(checkEmpty(name)){
            name.setError("برجاء ادخال الاسم");
            flag=1;
        }
        if(checkEmpty(email)){
            email.setError("برجاء ادخال البريد الالكتروني");
            flag=1;
        }
        if(checkEmpty(phone)){
            phone.setError("برجاء ادخال رقم التليفون");
            flag=1;
        }
        if(checkEmpty(message)){
            message.setError("برجاء كتابة الرسالة");
            flag=1;
        }
        if (flag==1){
            return false;
        } else
            return true;
    }
    boolean checkEmpty(EditText text){
        CharSequence str=text.getText().toString();
        return TextUtils.isEmpty(str);
    }

}