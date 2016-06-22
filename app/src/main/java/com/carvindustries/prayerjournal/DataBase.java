package com.carvindustries.prayerjournal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ryan on 6/19/2016.
 */
public class DataBase extends SQLiteOpenHelper {
    public static final String DBNAME= "PrayerJournal.db";
    public static final String PRAYER_TABLE = "PRAYERS";
    public static final String ID_COL ="ID";
    public static final String PRAYER_ENTRY_TXT = "PRAYER";
    public static final String ENTRY_DATE = "DATE";

    public DataBase(Context context){
        super(context,DBNAME,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table "+PRAYER_TABLE +" ("+ID_COL+" INTEGER PRIMARY KEY NOT NULL, "+PRAYER_ENTRY_TXT+" TEXT, "+ENTRY_DATE+" TEXT)";
        db.execSQL(sql); //used to create the DB

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void addPrayer(String pryr){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();

        value.put(ID_COL, getID() +1);
        value.put(PRAYER_ENTRY_TXT, pryr);
        Date now = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss");
        String theDate = dateFormat.format(now);
        value.put(ENTRY_DATE, theDate);
        db.insert(PRAYER_TABLE, null, value);
        //db.close();
    }
    public int getID(){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT "+ID_COL+" FROM "+PRAYER_TABLE;
        Cursor cursor = db.rawQuery(sql,null);
        int idVal;
        //The code below checks to see if the ID column is empty
        if(!cursor.moveToFirst()){
            return idVal = 0;
        }
        cursor.moveToLast();
        idVal = cursor.getInt(0);
        //db.close();

        return idVal;
    }
    public List<String> getAllPrayer(){
        List<String> p = new ArrayList<String>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT "+PRAYER_ENTRY_TXT+ ", "+ ENTRY_DATE+ " FROM "+PRAYER_TABLE;
        Cursor cursor = db.rawQuery(sql,null);

        while(cursor.moveToNext()){
            String entry = cursor.getString(1)+": "+cursor.getString(0);
            p.add(entry);
        }
        db.close();

        return p;
    }
}
