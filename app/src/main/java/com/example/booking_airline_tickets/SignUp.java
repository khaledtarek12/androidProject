package com.example.booking_airline_tickets;

import static com.example.booking_airline_tickets.Home.btn_login;
import static com.example.booking_airline_tickets.login.loginflag;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {
    private Button registerButton;
    private DB db = new DB(this);
    EditText username,password,confirmpassword;
    private Button SignInTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        registerButton = findViewById(R.id.signupbtn);
        SignInTextView =(Button) findViewById(R.id.accountbtn);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        confirmpassword = (EditText) findViewById(R.id.confirmpassword);
        SignInTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, login.class);
                startActivity(intent);

            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String USERNAME = username.getText().toString();
                String pass1 = password.getText().toString();
                String pass2 = confirmpassword.getText().toString();
                if (checkValidation()&&checkconfirm(pass1,pass2)&&db.registerUser(USERNAME,pass1)){
                    if (!loginflag){
                        Toast.makeText(SignUp.this, "تم الاشتراك بنجاح", Toast.LENGTH_SHORT).show();
                        loginflag=true;
                        btn_login.setText("خروج");
                        Intent intent = new Intent(SignUp.this, Home.class);
                        startActivity(intent);
                    }else {
                        Intent mor=new Intent(getApplicationContext(), login.class);
                        startActivity(mor);
                    }
                } else if (!checkconfirm(pass1,pass2)){
                    Toast.makeText(SignUp.this, "Not Match Password", Toast.LENGTH_SHORT).show();
                }
                else if (!db.registerUser(USERNAME,pass1)){
                    Toast.makeText(SignUp.this, "User Name Already Used", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
        public boolean checkValidation(){
            int flag = 0;
            if(checkEmpty(username)){
                username.setError("Empty email");
                flag=1;
            }
            if(checkEmpty(password)){
                password.setError("Empty password");
                flag=1;
            }
            if(checkEmpty(confirmpassword)){
                confirmpassword.setError("Empty password");
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
    boolean checkconfirm(String pass1,String pass2 ){
        return pass1.equals(pass2);
    }
}