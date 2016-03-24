package com.example.ddavi.memotest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

/**
 * Created by Rain on 23/03/2016.
 * Clase para la base de datos sqlite
 */
public class Helper extends SQLiteAssetHelper{


    private static final String DATABASE_NAME = "puntuacion.sqlite";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    public Helper(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    public ArrayList<Object[]> getAll(){
        ArrayList<Object[]> array = new ArrayList();
        String columnas[] = new String[]{"idJugador, nombreJugador, puntuacionJugador"};
        //db.query("nombre de la tabla", columnas,
        Cursor c = db.query("puntuacion", columnas, null, null, null, null, null);
        if (c.moveToFirst()) {
            do{
                Object[] obj = new Object[3];
                obj[0] = c.getInt(0);
                obj[1] = c.getString(1);
                obj[2] = c.getInt(2);
                array.add(obj);

            }while(c.moveToNext());
        }

        return array;
    }

}
