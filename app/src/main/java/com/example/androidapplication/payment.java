package com.example.androidapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentProviderClient;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class payment extends AppCompatActivity {

    DBHelper myDb;
    EditText cardtype, cardnumber, cvv, expdate, cardholdername;
    Button cancel,proceedpayment,viewPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        myDb = new DBHelper(this);

        cardtype = (EditText) findViewById(R.id.cardtype);
        cardnumber = (EditText) findViewById(R.id.cardnumber);
        cvv = (EditText) findViewById(R.id.cvv);
        expdate = (EditText) findViewById(R.id.month);
        cardholdername = (EditText) findViewById(R.id.holdername);
        cancel = (Button) findViewById(R.id.paymentcancle);

        proceedpayment = (Button) findViewById(R.id.proceed);

        //navigating cancel button to register view page
        cancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(view.getContext(),Registerview.class);
                        startActivity(i);
                    }
                }
        );


        AddPaymentData();


    }


    //payment adding function
    public void AddPaymentData() {
        proceedpayment.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //validation
                        String ctype = cardtype.getText().toString();
                        String cnumb = cardnumber.getText().toString();
                        String ccvv = cvv.getText().toString();
                        String cexdate = expdate.getText().toString();
                        String chname = cardholdername.getText().toString();

                        if (ctype.matches("")|| cnumb.matches("") || ccvv.matches("") || cexdate.matches("") || chname.matches("") )
                        {
                            Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
                            return;
                        }
                        else
                            {
                            Intent m = new Intent(view.getContext(),paymentview.class);
                            m.putExtra("cardtype",cardtype.getText().toString());
                            m.putExtra("cardnumber",cardnumber.getText().toString());
                            m.putExtra("cvv",cvv.getText().toString());
                            m.putExtra("expdate",expdate.getText().toString());
                            m.putExtra("cardholdername",cardholdername.getText().toString());
                            startActivity(m);

                            boolean isInserted = myDb.insertPaymentData(cardtype.getText().toString(),
                                    cardnumber.getText().toString(),
                                    cvv.getText().toString(),
                                    expdate.getText().toString(),
                                    cardholdername.getText().toString());

                            if (isInserted = true)
                                Toast.makeText(payment.this, "Payment Details are Inserted", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(payment.this, "Payment details are not Inserted", Toast.LENGTH_LONG).show();

                        }
                    }
                });

    }





}