package com.prostoiiefffectivniisecundomer.secundomer.db;

public class MyConstans {
    public static final String TABLE_NAME = "my_table";
    public static final String _ID = "_id";
    public static final String TITLETIME = "time";
    public static final String TIMED = "timed";
    public static final String DB_NAME = "my_db.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_STRUCTURE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY," + TITLETIME +
            " TEXT," + TIMED + " TEXT)";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
