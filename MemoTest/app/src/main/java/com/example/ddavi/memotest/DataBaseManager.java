package com.example.ddavi.memotest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Rain on 25/03/2016.
 */
public class DataBaseManager {
    public static final String TABLE_NAME = "history_record";
    public static final String CN_ID = "_id";
    public static final String CN_NAME = "player_name";
    public static final String CN_RECORD = "player_record";

    //create table history_record(
    //                              _id integer primary key autoincrement,
    //                              player_name text not_null,
    //                              player_record text not_null);

    public static final String CREATE_TABLE = "create table " + TABLE_NAME + " ("
            + CN_ID + " integer primary key autoincrement, "
            + CN_NAME + " text not null, "
            + CN_RECORD + " text not null);";

    private DbHelper helper;
    private SQLiteDatabase db;

    public DataBaseManager(Context context) {
        this.helper = new DbHelper(context);
        this.db = helper.getWritableDatabase();
    }

    public ContentValues generateContentValues(String name, String record){
        ContentValues values = new ContentValues();
        values.put(CN_NAME, name);
        values.put(CN_RECORD, record);
        return values;
    }

    public void insert(String name, String record){
        db.insert(TABLE_NAME, null, generateContentValues(name, record));
    }

    public void delete(String name){
        db.delete(TABLE_NAME, CN_NAME + "=?", new String[]{name});
    }

    //El cursor sirve para crear una lista con los datos de la db
    public Cursor loadCursorRecords(){

        String[] columns = new String[]{CN_ID,CN_NAME,CN_RECORD};
        //query (String table, String[] columns, String selection,
        // String selectionArgs, String groupBy, String having, String orderBy(
        return db.query(TABLE_NAME, columns, null,null,null,null,CN_RECORD+" ASC");
    }

}
