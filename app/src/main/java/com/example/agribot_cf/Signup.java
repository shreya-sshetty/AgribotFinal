package com.example.agribot_cf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    Button callLogin, signup_btn;
    TextInputLayout fname, user_name, passwd, regemail, regphoneNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);

        callLogin = findViewById(R.id.retlogin_btn);
        signup_btn = findViewById(R.id.signup_btn);

        fname = findViewById(R.id.fname);
        user_name = findViewById(R.id.user_name);
        passwd = findViewById(R.id.passwd);
        regemail = findViewById(R.id.regemail);
        regphoneNo = findViewById(R.id.regphoneNo);


        callLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup.this, Login.class);
                startActivity(intent);
            }
        });

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateUser();
            }
        });
    }

    private void validateUser() {

        String name = fname.getEditText().getText().toString();
        String username = user_name.getEditText().getText().toString();
        String email = regemail.getEditText().getText().toString();
        String phoneNo = regphoneNo.getEditText().getText().toString();
        String password = passwd.getEditText().getText().toString();

        //Validate Name
        if (name.isEmpty()) {
            fname.setError("Field cannot be empty");
        } else {
            fname.setError(null);
            fname.setErrorEnabled(false);
        }

        //Validate username
        String noWhiteSpace = "(?=\\s+$)";

        if (username.isEmpty()) {
            user_name.setError("Field cannot be empty");
        } else if (username.length() >= 15) {
            user_name.setError("Username too long");
        } else if (!username.matches(noWhiteSpace)) {
            user_name.setError("White Spaces are not allowed");
        } else {
            user_name.setError(null);
            user_name.setErrorEnabled(false);
        }

        //Validate email
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (email.isEmpty()) {
            regemail.setError("Field cannot be empty");

        } else if (!email.matches(emailPattern)) {
            regemail.setError("Invalid email address");

        } else {
            regemail.setError(null);
            regemail.setErrorEnabled(false);
        }

        //Validate Password
        String passwordVal = "^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (password.isEmpty()) {
            passwd.setError("Field cannot be empty");
        } else if (!password.matches(passwordVal)) {
            passwd.setError("Password is too weak");
        } else {
            passwd.setError(null);
            passwd.setErrorEnabled(false);
        }

        //Validate phone no
        if (phoneNo.isEmpty()) {
            regphoneNo.setError("Field cannot be empty");

        } else {
            regphoneNo.setError(null);
            regphoneNo.setErrorEnabled(false);
        }

//        if ((!(TextUtils.isEmpty(name))) & (!(TextUtils.isEmpty(username))) & (!(TextUtils.isEmpty(email))) & (!(TextUtils.isEmpty(phoneNo))) & (!(TextUtils.isEmpty(password)))) {
//                User user = new User(name, username, email, phoneNo, password);
//                root.setValue(user);
//                Toast.makeText(this, "User Account Created", Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(this, "Account creation failed", Toast.LENGTH_LONG).show();
//            }



    }

//        private void regData(String addName, String addUsername, String addEmail, String addPhoneNo, String addPassword) {
//
//
//
//        }


}