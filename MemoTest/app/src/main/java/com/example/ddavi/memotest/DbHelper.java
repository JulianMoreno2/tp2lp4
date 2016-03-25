package com.example.ddavi.memotest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

/**
 * Created by Rain on 23/03/2016.
 * Clase para la base de datos sqlite
 */
public class DbHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "puntuacion.sqlite";
    private static final int DATABASE_SCHEME_VERSION = 1;
    private SQLiteDatabase db;

    public DbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_SCHEME_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataBaseManager.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
