package com.stockmanagement.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Admin on 4/2/2018.
 */

public class DBHelper_Available extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName_available.db";
    public static final String CONTACTS_TABLE_NAME = "available";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_MODEL_NO = "model";
    public static final String CONTACTS_COLUMN_SERIAL_NO = "serial";
    public static final String CONTACTS_COLUMN_DESCRIPTION = "description";
    private HashMap hp;

    public DBHelper_Available(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table available " +
                        "(id integer primary key autoincrement, name text,model text,serial text, description text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS available");
        onCreate(db);
    }

    public boolean insertContact(String name, String model, String serial, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTACTS_COLUMN_NAME, name);
        contentValues.put(CONTACTS_COLUMN_MODEL_NO, model);
        contentValues.put(CONTACTS_COLUMN_SERIAL_NO, serial);
        contentValues.put(CONTACTS_COLUMN_DESCRIPTION, description);

        db.insert("available", null, contentValues);
        return true;
    }

    public Cursor getData(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from available where id=" + id + "", null);
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }

    public boolean updateContact(Integer id, String name, String phone, String email, String street, String place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.update(CONTACTS_TABLE_NAME, contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public boolean deleteContact(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(CONTACTS_TABLE_NAME, "id = ? ", new String[]{id});
    //    db.close();
        return true;
    }

    public ArrayList<String> getAllCotacts(String name1) {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from available where name =? ", new String[]{name1});
        //  Cursor res =  db.rawQuery( "select * from available where name="+name+"", null );
        //Cursor res =  db.rawQuery( "select * from available where name ="+name+"", null );
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getAll() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from available ", null);
        //  Cursor res =  db.rawQuery( "select * from available where name="+name+"", null );
        //Cursor res =  db.rawQuery( "select * from available where name ="+name+"", null );
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
    public ArrayList<HashMap<String, String>> getAllAnimals(String name1 ) {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT  * FROM available";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from available where name =? ", new String[]{name1});
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
              //  map.put("animalId", cursor.getString(0));
                //map.put("animalName", cursor.getString(1));
                map.put("id", cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_ID)));
                map.put("name", cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_NAME)));
                map.put("model", cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_MODEL_NO)));
                map.put("serial", cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_SERIAL_NO)));
                map.put("description", cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_DESCRIPTION)));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        return wordList;
    }

    public HashMap<String, String> getAlldata() {
        //ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> temp = new HashMap<String, String>();
        //hp = new HashMap();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from available ", null);
        //  Cursor res =  db.rawQuery( "select * from available where name="+name+"", null );
        //Cursor res =  db.rawQuery( "select * from available where name ="+name+"", null );
      //  res.moveToFirst();
        if (res.moveToFirst()) {
            do {
                temp.put("id", res.getString(res.getColumnIndex(CONTACTS_COLUMN_ID)));
                temp.put("name", res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
                temp.put("model", res.getString(res.getColumnIndex(CONTACTS_COLUMN_MODEL_NO)));
                temp.put("serial", res.getString(res.getColumnIndex(CONTACTS_COLUMN_SERIAL_NO)));
                temp.put("description", res.getString(res.getColumnIndex(CONTACTS_COLUMN_DESCRIPTION)));
                //wordList.put("animalName", cursor.getString(1));
            } while (res.moveToNext());
        }
//        return wordList;
//        while (res.isAfterLast() == false) {
//            //  array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
//
//          //  arrayList.add(temp);
//            res.moveToNext();
//        }
//      //  arrayList.add(temp);
        return temp;
    }
}

