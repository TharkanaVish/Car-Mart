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

public class BrandNewForm extends AppCompatActivity {
    DataBaseHelper2 myDb;
    EditText editcarname,editcarmodel,editmodelyear,editcarbrand,editcarcolour,editcusid;
    Button ordercancel,brandorder,orderview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_new_form);
        myDb = new DataBaseHelper2(this);

        editcarname = (EditText)findViewById(R.id.checkcarName);
        editcarmodel = (EditText)findViewById(R.id.checkcarModel);
        editmodelyear = (EditText)findViewById(R.id.checkmodelYear);
        editcarbrand = (EditText)findViewById(R.id.checkcarBrand);
        editcarcolour = (EditText)findViewById(R.id.carColour);
        editcusid = (EditText)findViewById(R.id.cusID);

        ordercancel = (Button)findViewById(R.id.checkDelete);
        orderview = (Button)findViewById(R.id.viewOrder);
        brandorder = (Button)findViewById(R.id.checkPay);

        ordercancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(BrandNewForm.this,Type.class);
                        startActivity(i);
                    }
                }
        );

        AddData();
        ViewRawData();
    }

    public void AddData(){
        brandorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),Checkout.class);
                i.putExtra("editcarname",editcarname.getText().toString());
                i.putExtra("editcarmodel",editcarmodel.getText().toString());
                i.putExtra("editmodelyear",editmodelyear.getText().toString());
                i.putExtra("editcarbrand",editcarbrand.getText().toString());
                i.putExtra("editcarcolour",editcarcolour.getText().toString());
                i.putExtra("editcusid",editcusid.getText().toString());
                startActivity(i);

                boolean isInserted = myDb.insertRowData(editcarname.getText().toString(),
                        editcarmodel.getText().toString(),
                        editmodelyear.getText().toString(),
                        editcarbrand.getText().toString(),
                        editcarcolour.getText().toString(),
                        editcusid.getText().toString());

                if(isInserted = true)
                    Toast.makeText(BrandNewForm.this,"Data is inserted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(BrandNewForm.this,"Data is not inserted",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void ViewRawData(){
        orderview.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Cursor res = myDb.getRowData(editcusid.getText().toString());
                        if (res.getCount() == 0){
                            //show message
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()) {
                            buffer.append("ORDER_ID :" + res.getString(0) + "\n");
                            buffer.append("CUS_ID :" + res.getString(1) + "\n");
                            buffer.append("CAR_NAME :" + res.getString(2) + "\n");
                            buffer.append("CAR_MODEL :" + res.getString(3) + "\n");
                            buffer.append("MODEL_YEAR :" + res.getString(4) + "\n");
                            buffer.append("CAR_BRAND :" + res.getString(5) + "\n");
                            buffer.append("CAR_COLOUR :" + res.getString(6) + "\n");

                            //show message
                            showMessage("Data", buffer.toString());
                        }
                    }
                }
        );
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}