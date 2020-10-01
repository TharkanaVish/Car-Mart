package com.example.androidapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper2 extends SQLiteOpenHelper{
    public static  final String DATABASE_NAME = "carmart.db";
    public static  final String TABLE_NAME1 = "car_table";
    public static  final String Column_0 = "ORDER_ID";
    public static  final String Column_6 = "CUS_ID";
    public static  final String Column_1 = "CAR_NAME";
    public static  final String Column_2 = "CAR_MODEL";
    public static  final String Column_3 = "MODEL_YEAR";
    public static  final String Column_4 = "CAR_BRAND";
    public static  final String Column_5 = "CAR_COLOUR";

    //creating database
    public DataBaseHelper2(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //create the table
        sqLiteDatabase.execSQL("create table " + TABLE_NAME1 + " (ORDER_ID INTEGER PRIMARY KEY AUTOINCREMENT,CUS_ID TEXT,CAR_NAME TEXT, CAR_MODEL TEXT, MODEL_YEAR INTEGER, CAR_BRAND TEXT, CAR_COLOUR TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //delete table if exists before creating
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        onCreate(sqLiteDatabase);
    }

    public boolean insertRowData(String cname, String cmodel, String modelyear, String cbrand, String ccolour, String cusid){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Column_1,cname);
        contentValues.put(Column_2,cmodel);
        contentValues.put(Column_3,modelyear);
        contentValues.put(Column_4,cbrand);
        contentValues.put(Column_5,ccolour);
        contentValues.put(Column_6,cusid);

        long result = sqLiteDatabase.insert(TABLE_NAME1,null,contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }


    public Cursor getRowData(String cusID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME1 + " WHERE CUS_ID == " + cusID,null);
        return res;
    }

    public boolean updateRowData(String cid,String cname, String cmodel, String modelyear, String cbrand, String ccolour){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Column_1,cname);
        contentValues.put(Column_2,cmodel);
        contentValues.put(Column_3,modelyear);
        contentValues.put(Column_4,cbrand);
        contentValues.put(Column_5,ccolour);
        contentValues.put(Column_6,cid);

        sqLiteDatabase.update(TABLE_NAME1, contentValues, " CUS_ID = ? ", new String[] {cid});

        return true;
    }

    public Integer deleteRowData(String cid){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME1," CUS_ID = ? ", new String[] { cid });
    }
}
