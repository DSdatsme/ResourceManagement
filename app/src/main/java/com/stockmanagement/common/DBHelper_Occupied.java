package com.stockmanagement.common;

/**
 * Created by Admin on 4/2/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper_Occupied extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName_Occupied.db";
    public static final String CONTACTS_TABLE_NAME = "occupied";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_MODEL_NO = "model";
    public static final String CONTACTS_COLUMN_SERIAL_NO = "serial";
    public static final String CONTACTS_COLUMN_DESCRIPTION = "description";
    public static final String CONTACTS_COLUMN_DEPARTMENT = "department";
    private HashMap hp;

    public DBHelper_Occupied(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table occupied " +
                        "(id integer primary key autoincrement, name text,model text,serial text, description text, department text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS occupied");
        onCreate(db);
    }

    public boolean insertContact(String name, String model, String serial, String description, String department) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTACTS_COLUMN_NAME, name);
        contentValues.put(CONTACTS_COLUMN_MODEL_NO, model);
        contentValues.put(CONTACTS_COLUMN_SERIAL_NO, serial);
        contentValues.put(CONTACTS_COLUMN_DESCRIPTION, description);
        contentValues.put(CONTACTS_COLUMN_DEPARTMENT, department);
        db.insert("occupied", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from occupied where id=" + id + "", null);
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }

    public boolean updateContact(String id, String department) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put(CONTACTS_COLUMN_NAME, name);
//        contentValues.put(CONTACTS_COLUMN_MODEL_NO, model);
//        contentValues.put(CONTACTS_COLUMN_SERIAL_NO, serial);
//        contentValues.put(CONTACTS_COLUMN_DESCRIPTION, description);
        contentValues.put(CONTACTS_COLUMN_DEPARTMENT, department);
        db.update(CONTACTS_TABLE_NAME, contentValues, "id = ? ", new String[]{id});
        return true;
    }

    //    public Integer deleteContact(Integer id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        return db.delete(CONTACTS_TABLE_NAME,
//                "id = ? ",
//                new String[]{Integer.toString(id)});
//    }
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
        Cursor res = db.rawQuery("select * from occupied where name =? ", new String[]{name1});
        //  Cursor res =  db.rawQuery( "select * from occupied where name="+name+"", null );
        //Cursor res =  db.rawQuery( "select * from occupied where name ="+name+"", null );
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getAllCotactslist(String name1, String dep) {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from occupied where name =? AND department =?", new String[]{name1, dep});
        //  Cursor res =  db.rawQuery( "select * from occupied where name="+name+"", null );
        //Cursor res =  db.rawQuery( "select * from occupied where name ="+name+"", null );
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
        Cursor res = db.rawQuery("select * from occupied ", null);
        //  Cursor res =  db.rawQuery( "select * from occupied where name="+name+"", null );
        //Cursor res =  db.rawQuery( "select * from occupied where name ="+name+"", null );
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<HashMap<String, String>> getAllAnimals(String name1, String dep) {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT  * FROM occupied";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from occupied where name =? AND department =?", new String[]{name1, dep});
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
}
