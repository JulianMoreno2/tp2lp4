package com.example.ddavi.memotest;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created by Rain on 24/03/2016.
 */
public class RecordActivity extends Activity {

    TextView txtDB;
    private DataBaseManager manager;
    private Cursor cursor;
    private ListView listView;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        manager = new DataBaseManager(this);
        listView = (ListView) findViewById(R.id.listView);

        String[] from = new String[]{manager.CN_NAME, manager.CN_RECORD};
        //text1 y text2 son del layout two line list item
        int[] to = new int[]{android.R.id.text1, android.R.id.text2};

        cursor = manager.loadCursorRecords();
        //el cero es una bandera por defecto para que el simpleCursorAdapter no este deprecated
        adapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,cursor,from,to,0);
        listView.setAdapter(adapter);
    }

    public void onButtonClick(View view) {
        if(view.getId() == R.id.bMenu){
            finish();
            Intent intent = new Intent(RecordActivity.this, MenuActivity.class);
            startActivity(intent);
        }
    }
}
