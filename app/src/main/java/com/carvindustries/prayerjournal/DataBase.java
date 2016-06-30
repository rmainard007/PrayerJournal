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

        value.put(ENTRY_DATE, this.generateDate());
        db.insert(PRAYER_TABLE, null, value);
        //db.close();
    }
    public void editPrayerEntry(int id, String update){
        //need to throw exception if the id does not exist
        SQLiteDatabase db = getWritableDatabase();

        String sqlupdt = "UPDATE " + PRAYER_TABLE + " SET " + PRAYER_ENTRY_TXT + " = " +
                update + ", " + ENTRY_DATE + " = " + this.generateDate() + " WHERE " + ID_COL + " = " + id;
        db.rawQuery(sqlupdt,null);
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
    public List<PrayerEntry> getAllPrayer(){

        SQLiteDatabase db = getReadableDatabase();
        List<PrayerEntry> p = new ArrayList<>();
        String sql = "SELECT "+ID_COL+", "+PRAYER_ENTRY_TXT+ ", "+ ENTRY_DATE+ " FROM "+PRAYER_TABLE;
        Cursor cursor = db.rawQuery(sql, null);
        int i = 0;

        while(cursor.moveToNext()){
            p.add(new PrayerEntry(cursor.getString(1),cursor.getString(2),cursor.getInt(0)));
            Log.d("RM ", String.valueOf(p.get(i).getPid()));
            i++;
        }
        db.close();
        cursor.close();

        return p;
    }
    private String generateDate(){
        Date now = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss");
        String theDate = dateFormat.format(now);
        return theDate;
    }
}
