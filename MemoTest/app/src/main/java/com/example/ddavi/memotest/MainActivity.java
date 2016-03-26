package com.example.ddavi.memotest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final GridView gridview = (GridView) findViewById(R.id.grid);
        AdaptadorDeMemoTest adaptador = new AdaptadorDeMemoTest(this);
<<<<<<< HEAD
        int milisegundos = 20000;
        final MyCountDownTimer counter =new MyCountDownTimer(adaptador,milisegundos, 1000);
=======
        int milisegundos = 120000;
        //final TextView chrono = (TextView) findViewById(R.id.cronometro);
        final MyCountDownTimer counter = new MyCountDownTimer(adaptador,milisegundos, 1000);
>>>>>>> 12e8b47c38f96164ed241dd67a8917689d440f82
        counter.start();

        //Pone el nombre del jugador, ya ingresado en el textView de activity_menu
        final TextView player = (TextView) findViewById(R.id.playerView);
        player.setText(MemoTest.getInstance().getPlayerName());
        gridview.setAdapter(adaptador);


        gridview.setOnItemClickListener(new OnItemClickListener() {
            // dentro de este listener difinimos la función que se ejecuta al
            // hacer click en un item
            // el metodo pertenece a AdapterView, es decir, es
            // AdapterView.OnItemClickListener
            // dentro de este, tenemos el método onItemClick, que es el que se
            // invoca al pulsar un item del AdapterView
            // esa función recibe el objeto padre, que es un adapterview en el
            // que se ha pulasdo, una vista, que es el elemento sobre el que se
            // ha pulsado,
            // una posicion, que es la posicion del elemento dentro del adapter,
            // y un id, que es el id de fila del item que se ha pulsado

            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Image item = (Image)parent.getAdapter().getItem(position);
                ((AdaptadorDeMemoTest)parent.getAdapter()).updateImages(item);

                if (MemoTest.getInstance().isGameOver()) {
                    ((AdaptadorDeMemoTest) parent.getAdapter()).gameOver(counter.getTimeStop());
                    counter.cancel();
                }
                gridview.invalidateViews();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
