package com.example.ddavi.memotest;

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
            + CN_ID + " Integer primary key autoincrement,"
            + CN_NAME + " text not_null,"
            + CN_RECORD + " text not_null);";

}
