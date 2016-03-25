package com.rain.minibd;

/**
 * Created by Rain on 25/03/2016.
 */
public class DataBaseManager {

    public static final String TABLE_NAME = "history_record";
    public static final String CN_ID = "_id";
    public static final String CN_NAME = "player_name";
    public static final String CN_RECORD = "player_record";

    public static final String CREATE_TABLE = "create table " + TABLE_NAME + " ("
            + CN_ID + " integer primary key autoincrement,"
            + CN_NAME + " text not null,"
            + CN_RECORD + " text not null);";


}
