package com.mrpanda2.infosecfinal.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mrpanda2.infosecfinal.database.ServiceDBSchema.ServiceTable;

public class ServiceBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "serviceBase.db";

    public ServiceBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    //Builds/opens login db

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ServiceTable.NAME + " (" + ServiceDBSchema.Columns.SERVICE + " primary key, " + ServiceDBSchema.Columns.USERNAME + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
