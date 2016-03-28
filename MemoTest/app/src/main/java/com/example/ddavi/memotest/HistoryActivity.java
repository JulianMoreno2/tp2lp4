package com.example.ddavi.memotest;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created by Rain on 27/03/2016.
 */
public class HistoryActivity extends Activity{

    private DataBaseManager manager;
    private Cursor cursor;
    private ListView listView;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        manager = new DataBaseManager(this);
        listView = (ListView) findViewById(R.id.listView);

        String[] from = new String[]{manager.CN_NAME, manager.CN_RECORD};
        //text1 y text2 son del layout two line list item
        int[] to = new int[]{R.id.player, R.id.record};

        cursor = manager.loadCursorRecords();

        //el cero es una bandera por defecto para que el simpleCursorAdapter no este deprecated
        adapter = new SimpleCursorAdapter(this,R.layout.column_row,cursor,from,to,0);
        listView.setAdapter(adapter);
    }

    public void onButtonClick(View view) {

        finish();

        if (view.getId() == R.id.bMenu) {
            Intent intent = new Intent(HistoryActivity.this, MenuActivity.class);
            startActivity(intent);
        }
    }
}
