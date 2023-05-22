package com.example.booking_airline_tickets;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {
    private DB d = new DB(this);
    EditText username,password;
    TextView signup;
    Button loginbtn;
    ProgressBar bar;
    public static String USERNAME;
    public static boolean loginflag = false;
   // FirebaseAuth auth;
   Intent intent ,home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //auth = FirebaseAuth.getInstance();
        intent =  new Intent(this, SignUp.class);
        home = new Intent(this,Home.class);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        loginbtn = (Button) findViewById(R.id.loginbtn);
        bar = (ProgressBar) findViewById(R.id.bar);
        signup= (TextView) findViewById(R.id.SIGNUP);
        SpannableString SIGNUP = new SpannableString(signup.getText());
        ClickableSpan CS = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                startActivity(intent);
            }
        };

        SIGNUP.setSpan(CS,7,10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        signup.setText(SIGNUP);
        signup.setMovementMethod(LinkMovementMethod.getInstance());

        loginbtn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                            String USER_NAME = username.getText().toString();
                            String PASSWORD = password.getText().toString();
                            boolean auth = d.authenticateUser(USER_NAME,PASSWORD);
                                            if (auth){
                                                loginflag=true;
                                                bar.setVisibility(View.VISIBLE);
                                                startActivity(home);
                                                USERNAME=USER_NAME;
                                            } else {
                                                AlertDialog.Builder dialog = new AlertDialog.Builder(login.this);
                                                dialog.setTitle("انت ليس لديك حساب                  ").show();
                                            }


                                        }
                                    }
        );
    }
    public void checkValidation(){
        if(checkEmpty(username)){
            username.setError("Empty email");
        }
        if(checkEmpty(password)){
            password.setError("Empty password");
        }
    }

    boolean checkEmpty(EditText text){
        CharSequence str=text.getText().toString();
        return TextUtils.isEmpty(str);
    }}
