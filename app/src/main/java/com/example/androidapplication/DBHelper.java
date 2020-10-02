package com.example.androidapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "CarMart.db";
    public static final String TABLE1 = "Register_table";
    public static final String col_1 = "CusID";
    public static final String col_2 = "FullName";
    public static final String col_3 = "PhoneNumber";
    public static final String col_4 = "Email";
    public static final String col_5 = "Address";
    public static final String col_6 = "UserName";
    public static final String col_7 = "Password";
    public static final String col_8 = "RePassword";


    public static final String TABLE2 = "Payment_table";
    public static final String col_14= "CardID";
    public static final String col_9 = "CardType";
    public static final String col_10 = "CardNumber";
    public static final String col_11 = "Cvv";
    public static final String col_12 = "ExpDate";
    public static final String col_13 = "CardHolderName";


    public DBHelper( Context context) {

        super(context, "Customer.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL(" create table "+ TABLE1 +" (CusID INTEGER PRIMARY KEY AUTOINCREMENT,FullName Text,PhoneNumber Integer,Email Text,Address Text,UserName Text,Password Text,RePassword Text) ");
        MyDB.execSQL(" create table "+ TABLE2 +" (CardID INTEGER PRIMARY KEY AUTOINCREMENT,CardType Text,CardNumber Integer,Cvv Text,ExpDate Integer,CardHolderName Text) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("DROP TABLE IF EXISTS "+TABLE1);
        MyDB.execSQL("DROP TABLE IF EXISTS "+TABLE2);
        onCreate(MyDB);

    }

    //INSERT REGISTER DETAILS
    public boolean insertRegisterData(String fullname,String phoneno,String email,String address,String username,String password,String repassword){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2,fullname);
        contentValues.put(col_3,phoneno);
        contentValues.put(col_4,email);
        contentValues.put(col_5,address);
        contentValues.put(col_6,username);
        contentValues.put(col_7,password);
        contentValues.put(col_8,repassword);
        long result = db.insert(TABLE1,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    //INSERT PAYMENT DETAILS
    public boolean insertPaymentData(String cardtype,String cardnumber,String cvv,String expdate,String cardholdername){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_9,cardtype);
        contentValues.put(col_10,cardnumber);
        contentValues.put(col_11,cvv);
        contentValues.put(col_12,expdate);
        contentValues.put(col_13,cardholdername);
        long result = db.insert(TABLE2,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    //DELETE REGISTER DETAILS
    public Integer deleteData1(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE1," CusID = ? ",new String[] {id});
    }
    //DELETE PAYMENT DETAILS
    public Integer deleteData2(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE2," CardID = ? ",new String[] {id});
    }
    //VIEW REGISTER DETAILS
    public Cursor getRegisterData(String cusID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(" select * from "+ TABLE1 + " where CusID == " + cusID,null);
        return res;


    }
    //VIEW PAYMENT DETAILS
    public Cursor getPaymentData(String cardNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(" select * from " + TABLE2 + " where CardNumber == " + cardNumber, null);
        return res;
    }

    //view payment edited info for paymentView page
    public Cursor getPaymentViewData(String cardid) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(" select * from " + TABLE2 + " where CardID == " + cardid, null);
        return res;
    }

    //UPDATE REGISTER DETAILS
    public boolean updateData(String cusid,String fullname,String phoneno,String email,String address,String username,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_1,cusid);
        contentValues.put(col_2,fullname);
        contentValues.put(col_3,phoneno);
        contentValues.put(col_4,email);
        contentValues.put(col_5,address);
        contentValues.put(col_6,username);
        contentValues.put(col_7,password);
        db.update(TABLE1,contentValues," CusID  = ? ",new String [] { cusid });
        return true;

    }
    //UPDATE PAYMENT DETAILS
    public boolean updatePaymentData(String cardid,String cardtype,String cardnumber,String cvv,String expdate,String cardholdername) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_9,cardid);
        contentValues.put(col_9,cardtype);
        contentValues.put(col_10,cardnumber);
        contentValues.put(col_11,cvv);
        contentValues.put(col_12,expdate);
        contentValues.put(col_13,cardholdername);
        db.update(TABLE2, contentValues, " CardID = ? ", new String[] {cardid});
        return true;
    }
}