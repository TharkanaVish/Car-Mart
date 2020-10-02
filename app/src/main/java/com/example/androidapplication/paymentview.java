package com.example.androidapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class paymentview extends AppCompatActivity {

    DBHelper myDb;
    EditText cardid,cardtype, cardnumber, cvv, expdate, name;
    Button cancel,editdetails,pay,view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentview);

        myDb = new DBHelper(this);

        cardid = (EditText) findViewById(R.id.pv_cardid);
        name = (EditText) findViewById(R.id.pv_name);
        cardnumber = (EditText) findViewById(R.id.pv_cardnumber);
        cvv = (EditText) findViewById(R.id.pv_cvv);
        expdate = (EditText) findViewById(R.id.pv_month);
        cardtype = (EditText) findViewById(R.id.pv_cardtype);

        cancel = (Button) findViewById(R.id.button1);
        view = (Button) findViewById(R.id.button4);
        editdetails = (Button) findViewById(R.id.button2);
        pay = (Button) findViewById(R.id.button3);

        //catch data from Payment
        Intent values = getIntent();
        String viewCardType = values.getStringExtra("cardtype");
        String viewCardNumber = values.getStringExtra("cardnumber");
        String viewCvv = values.getStringExtra("cvv");
        String viewExpDate = values.getStringExtra("expdate");
        String viewCardHolderName = values.getStringExtra("cardholdername");


        cardtype.setText(viewCardType);
        cardnumber.setText(viewCardNumber);
        cvv.setText(viewCvv);
        expdate.setText(viewExpDate);
        name.setText(viewCardHolderName);

        DeleteData();
        UpdateData();
        viewPaymentDetails();

    }
    public void DeleteData(){
        cancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer deleteRows = myDb.deleteData2(cardid.getText().toString());
                        if (deleteRows > 0)
                            Toast.makeText(paymentview.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(paymentview.this,"Data not Deleted",Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

    public void UpdateData(){
        editdetails.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isUpdate = myDb.updatePaymentData(cardid.getText().toString(),cardtype.getText().toString()
                                ,cardnumber.getText().toString(),cvv.getText().toString(),
                                expdate.getText().toString(),name.getText().toString());

                        if (isUpdate == true)
                            Toast.makeText(paymentview.this, "Data Updated", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(paymentview.this, "Data not Updated", Toast.LENGTH_LONG).show();

                    }

                }
        );
    }

    //view payment
    public void viewPaymentDetails() {
        view.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Cursor res = myDb.getPaymentViewData(cardid.getText().toString());
                        if (res.getCount() == 0) {
                            //show message
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();

                        while (res.moveToNext()) {
                            buffer.append("CardID :" + res.getString(0) + "\n");
                            buffer.append("CardType :" + res.getString(1) + "\n");
                            buffer.append("CardNumber :" + res.getString(2) + "\n");
                            buffer.append("Cvv :" + res.getString(3) + "\n");
                            buffer.append("ExpDate :" + res.getString(4) + "\n");
                            buffer.append("CardHolderName :" + res.getString(5) + "\n\n");

                            //show message to view register details
                            showMessage("Payment Details", buffer.toString());
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
}