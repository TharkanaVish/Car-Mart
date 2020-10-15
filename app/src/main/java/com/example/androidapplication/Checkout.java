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
import android.widget.Toast;

public class Checkout extends AppCompatActivity {
    public static final String TAG = "TAG";
    DataBaseHelper2 myDb;
    EditText ccname,ccmodel,cmodelyear,ccbrand,cccolour,ccusID;
    Button cdelete,cedit,cpay,cview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        myDb = new DataBaseHelper2(this);

        ccname = (EditText)findViewById(R.id.checkcarName);
        ccmodel = (EditText)findViewById(R.id.checkcarModel);
        cmodelyear = (EditText)findViewById(R.id.checkmodelYear);
        ccbrand = (EditText)findViewById(R.id.checkcarBrand);
        cccolour = (EditText)findViewById(R.id.checkcarColour);
        ccusID = (EditText)findViewById(R.id.checkcusID);

        cdelete = (Button)findViewById(R.id.checkDelete);
        cedit = (Button)findViewById(R.id.checkEdit);
        cview = (Button)findViewById(R.id.checkView);
        cpay = (Button)findViewById(R.id.checkPay);

        //catching data
        Intent data = getIntent();
        String checkcarname = data.getStringExtra("editcarname");
        String checkcarmodel = data.getStringExtra("editcarmodel");
        String checkmodelyear = data.getStringExtra("editmodelyear");
        String checkbrand = data.getStringExtra("editcarbrand");
        String checkcarcolour = data.getStringExtra("editcarcolour");
        String checkcusID = data.getStringExtra("editcusid");

        ccname.setText(checkcarname);
        ccmodel.setText(checkcarmodel);
        cmodelyear.setText(checkmodelyear);
        ccbrand.setText(checkbrand);
        cccolour.setText(checkcarcolour);
        ccusID.setText(checkcusID);



        //logging the data receiving
        Log.d(TAG,"onCreate :" + ccname + " " + ccmodel + " " + cmodelyear + " " + ccbrand + " " + cccolour + " " + ccusID);

        DeleteRowData();
        ViewRowData();
        UpdateRowData();

        cpay.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(Checkout.this,register.class);
                        startActivity(i);
                    }
                }
        );
    }


    public void DeleteRowData(){
        cdelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer isDeleted = myDb.deleteRowData(ccusID.getText().toString());
                        if (isDeleted > 0)
                            Toast.makeText(Checkout.this,"Order is deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Checkout.this,"Order is not deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void UpdateRowData(){

        cedit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isUpdated = myDb.updateRowData(ccusID.getText().toString(),ccname.getText().toString(),
                                ccmodel.getText().toString(),cmodelyear.getText().toString(),ccbrand.getText().toString(),
                                cccolour.getText().toString());

                        if (isUpdated == true)
                            Toast.makeText(Checkout.this,"Data is updated",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Checkout.this,"Data is not updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }


    public void ViewRowData(){
        cview.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Cursor res = myDb.getRowData(ccusID.getText().toString());
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