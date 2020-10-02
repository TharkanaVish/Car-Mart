package com.example.androidapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Registerview extends AppCompatActivity {

    public static final String TAG = "TAG";
    DBHelper myDb;
    EditText cusid,fullname, phoneno, email, address, username, password;
    Button delete,confirm,changedetails,view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerview);

        myDb = new DBHelper(this);

        cusid = (EditText) findViewById(R.id.v_cusID);
        fullname = (EditText)findViewById(R.id.v_fullname);
        phoneno = (EditText) findViewById(R.id.v_phonenumber);
        email = (EditText) findViewById(R.id.v_email);
        address = (EditText) findViewById(R.id.v_address);
        username = (EditText) findViewById(R.id.v_username);
        password = (EditText) findViewById(R.id.v_password);

        view = (Button) findViewById(R.id.view);
        delete = (Button) findViewById(R.id.delete1);
        confirm = (Button) findViewById(R.id.confirm1);
        changedetails = (Button) findViewById(R.id.change1);

        //catch data from register
        Intent values = getIntent();
        String viewFullname = values.getStringExtra("fullname");
        String viewPhoneNo = values.getStringExtra("phoneno");
        String viewEmail = values.getStringExtra("email");
        String viewAddress = values.getStringExtra("address");
        String viewUsername = values.getStringExtra("username");
        String viewPassword = values.getStringExtra("Password");


        fullname.setText(viewFullname);
        phoneno.setText(viewPhoneNo);
        email.setText(viewEmail);
        address.setText(viewAddress);
        username.setText(viewUsername);
        password.setText(viewPassword);

        // log the data receives
        Log.d(TAG,"onCreate : " + fullname + " " + phoneno + " " + email + " " + address + " " + username + " " + password);


        //navigate to payment page
        confirm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(view.getContext(),payment.class);
                        startActivity(i);
                    }
                }
        );


        viewRegisterDetails();
        DeleteData();
        UpdateData();
    }


    public void viewRegisterDetails(){
        view.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = myDb.getRegisterData(cusid.getText().toString());
                        if (res.getCount() == 0){
                            //show message
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("CusID :" + res.getString(0) + "\n");
                            buffer.append("FullName :" + res.getString(1) + "\n");
                            buffer.append("PhoneNumber :" + res.getString(2) + "\n");
                            buffer.append("Email :" + res.getString(3) + "\n");
                            buffer.append("Address :" + res.getString(4) + "\n");
                            buffer.append("UserName :" + res.getString(5) + "\n");
                            buffer.append("Password :" + res.getString(6) + "\n");

                            //show message to view register details
                            showMessage("Register Details",buffer.toString());
                        }
                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void UpdateData(){
        changedetails.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isUpdate = myDb.updateData(cusid.getText().toString(),
                                fullname.getText().toString(),
                                phoneno.getText().toString(),
                                email.getText().toString(),
                                address.getText().toString(),
                                username.getText().toString(),
                                password.getText().toString());

                        if (isUpdate == true)
                            Toast.makeText(Registerview.this, "Data Updated", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Registerview.this, "Data not Updated", Toast.LENGTH_LONG).show();

                    }

                }
        );
    }
    public void DeleteData(){
        delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer deleteRows = myDb.deleteData1(cusid.getText().toString());
                        if (deleteRows > 0)
                            Toast.makeText(Registerview.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Registerview.this,"Data not Deleted",Toast.LENGTH_LONG).show();

                    }
                }
        );
    }
}