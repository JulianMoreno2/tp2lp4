package com.rain.testdb;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtDB;
    private DataBaseManager manager;
    private Cursor cursor;
    private ListView listView;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = new DataBaseManager(this);
        listView = (ListView) findViewById(R.id.listView);

        String[] from = new String[]{manager.CN_NAME, manager.CN_RECORD};
        //text1 y text2 son del layout two line list item
        int[] to = new int[]{android.R.id.text1, android.R.id.text2};

        manager.insert("Juli", "20");
        manager.insert("Pepe", "10");
        manager.insert("David", "30");

        cursor = manager.loadCursorRecords();
        //el cero es una bandera por defecto para que el simpleCursorAdapter no este deprecated
        adapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,cursor,from,to,0);
    }


}
