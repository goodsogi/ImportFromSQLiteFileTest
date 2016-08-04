package com.seriouscompany.importfromsqlitefiletest;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * �޺��ڽ� �����ͺ��̽� �Ŵ���
 * - ���� �����͸� ������ �����ͺ��̽� ��.
 */
public class DatabaseManager extends SQLiteOpenHelper {

    private SQLiteDatabase database;

    public DatabaseManager(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);

        this.database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void closeDatabase() {
        database.close();
    }


    public String getName() {


       Cursor cursor =  database.rawQuery("SELECT * FROM test", null);

        String id = null;
        String name = null;

        if (cursor.getCount() == 1) {
            if (cursor.moveToFirst()) {
                do {
                    id = cursor.getString(cursor.getColumnIndex("id"));
                    name = cursor.getString(cursor.getColumnIndex("name"));
                } while (cursor.moveToNext());
            }
        }
        cursor.close();


        return name;
    }

    public String getAge() {


        Cursor cursor =  database.rawQuery("SELECT * FROM pjk", null);

        String id = null;
        String age = null;

        if (cursor.getCount() == 1) {
            if (cursor.moveToFirst()) {
                do {
                    id = cursor.getString(cursor.getColumnIndex("id"));
                    age = cursor.getString(cursor.getColumnIndex("age"));
                } while (cursor.moveToNext());
            }
        }
        cursor.close();


        return age;
    }

    public String getLgortList() {

        String sql = "SELECT * FROM tb_productType ";


        Cursor cursor = null;



        cursor = database.rawQuery(sql, null);



        if (cursor.moveToFirst()) {
            do {
                return cursor.getString(cursor.getColumnIndex("typeName"));


            } while (cursor.moveToNext());
        }
        cursor.close();

        return null;

    }

}

