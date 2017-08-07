package com.example.mariya.mapapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mariya on 04.08.17.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "tagDb";
    public static final String TABLE_GROUPS = "groups";
    public static final String TABLE_GEO = "geo";

    public static final String KEY_ID = "_id";
    public static final String KEY_TAGNAME = "tagname";
    public static final String KEY_ICON = "icon";
    public static final String KEY_COMMENT = "comment";
    public static final String KEY_GEO = "geo";
    public static final String KEY_TAG = "tag";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_GROUPS + "(" + KEY_ID
                + " integer primary key autoincrement," + KEY_TAGNAME + " text," + KEY_COMMENT + " text," + KEY_ICON + " text" + ")");
        db.execSQL("create table " + TABLE_GEO + "(" + KEY_ID
                + " integer primary key autoincrement," + KEY_TAG + " integer," + KEY_GEO + " text,"
                + "foreign key(" + KEY_TAG + ") references " + TABLE_GROUPS + "(" + KEY_ID
                + ")" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //db.execSQL("drop table if exists " + TABLE_CONTACTS);

        //onCreate(db);
    }
}
