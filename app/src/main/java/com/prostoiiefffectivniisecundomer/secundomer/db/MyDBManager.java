package com.prostoiiefffectivniisecundomer.secundomer.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.prostoiiefffectivniisecundomer.secundomer.ListItem;

import java.util.ArrayList;
import java.util.List;

public class MyDBManager {
    private Context context;
    private DbHelper dbHelper;
    private SQLiteDatabase db;

    public MyDBManager(Context context) {
        this.context = context;
        dbHelper = new DbHelper(context);
    }

    public void openDb(){
        db = dbHelper.getWritableDatabase() ;
    }

    public void insertToDb(String title, String disc){
        ContentValues cv = new ContentValues();
        cv.put(MyConstans.TITLETIME, title);
        cv.put(MyConstans.TIMED, disc);
        db.insert(MyConstans.TABLE_NAME, null, cv);
    }
    public void updateItem(String title, String disc, int id){
        String selection = MyConstans._ID + "=" + id;
        ContentValues cv = new ContentValues();
        cv.put(MyConstans.TITLETIME, title);
        cv.put(MyConstans.TIMED, disc);
        db.update(MyConstans.TABLE_NAME,cv,selection,null);
    }
    public void delete(){
        db.delete(MyConstans.TABLE_NAME,null,null);
    }

    public List<ListItem> getFromDb(){
        List<ListItem> tempList = new ArrayList<>();
        Cursor cursor = db.query(MyConstans.TABLE_NAME,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            ListItem item = new ListItem();
            String time1 = cursor.getString(cursor.getColumnIndex(MyConstans.TITLETIME));
            String time2 = cursor.getString(cursor.getColumnIndex(MyConstans.TIMED));
            item.setTime1(time1);
            item.setTime2(time2);
            tempList.add(item);
        }
        cursor.close();
        return(tempList);
    }

    public void closeDb(){
        dbHelper.close();
    }
}
