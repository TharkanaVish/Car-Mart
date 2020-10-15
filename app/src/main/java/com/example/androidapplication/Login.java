package com.example.androidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Button login;
    Button logRegister;
    DBHelper myDB;
    EditText uName,pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myDB = new DBHelper(this);
        uName = (EditText) findViewById(R.id.logusername);
        pwd = (EditText) findViewById(R.id.logpassword);
        login = (Button) findViewById(R.id.login);
        logRegister = (Button) findViewById(R.id.loginregister);

        checkUser();

        logRegister.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(Login.this,register.class);
                        startActivity(i);
                    }
                }
        );
    }

    //checking a user
    public void checkUser(){
        //get username and password
        login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //validation
                        String username = uName.getText().toString();
                        String password = pwd.getText().toString();

                        if (username.matches("") || password.matches("")){
                            Toast.makeText(getApplicationContext(), "Please enter the username and Password", Toast.LENGTH_LONG).show();
                            return;
                        }
                        else{
                            Cursor res = myDB.getRegisterDetails(uName.getText().toString(),pwd.getText().toString());
                            if (res.getCount() == 0)
                                Toast.makeText(Login.this, "You are not a member/or username and password not matched", Toast.LENGTH_LONG).show();
                            else{
                                Toast.makeText(Login.this, "Welcome Back", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(Login.this,payment.class);
                                startActivity(i);
                            }
                        }
                    }
                }
        );
    }
}