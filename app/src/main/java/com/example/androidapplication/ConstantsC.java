package com.example.androidapplication;

public class ConstantsC {
    //db name
    public static final String DB_NAME ="CAR_MART_DB";
    //db version
    public static final int DB_VERSION =1;
    //db table
    public static final String TABLE_NAME ="CAR_DETAILS";
    //table columns
    public static final String C_ID ="ID";
    public static final String C_BRAND ="BRAND";
    public static final String C_MODEL ="MODEL";
    public static final String C_TRANSMISSION ="TRANSMISSION";
    public static final String C_MODYEAR ="MODYEAR";
    public static final String C_IMAGE ="IMAGE";
    public static final String C_MILEAGE ="MILEAGE";
    public static final String C_FUEL ="FUEL";
    public static final String C_CONTACT ="CONTACT";
    public static final String C_ADD_CAR ="ADD_CAR";
    public static final String C_UPDATE_CAR ="UPDATE_CAR";
    //crate query for table
    public static final String CREATE_TABLE ="CREATE TABLE "+ TABLE_NAME +" ("
            + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + C_BRAND +" TEXT,"
            + C_MODEL + " TEXT,"
            + C_TRANSMISSION +" TEXT,"
            + C_MODYEAR +" TEXT,"
            + C_IMAGE +" TEXT,"
            + C_MILEAGE +" TEXT,"
            + C_FUEL +" TEXT,"
            + C_CONTACT +" TEXT,"
            + C_ADD_CAR +" TEXT,"
            + C_UPDATE_CAR +" TEXT"
            + ");";

}


