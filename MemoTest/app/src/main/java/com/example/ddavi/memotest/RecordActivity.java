package com.example.ddavi.memotest;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Rain on 24/03/2016.
 */
public class RecordActivity extends Activity {

    TextView txtDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        DbHelper helper = new DbHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

    }
}
