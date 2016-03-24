package com.example.ddavi.memotest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.os.SystemClock;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final GridView gridview = (GridView) findViewById(R.id.grid);// crear el
        // gridview a partir del elemento del xml gridview
        final Chronometer chrono = (Chronometer) findViewById(R.id.cronometro);
        chrono.setBase(SystemClock.elapsedRealtime());
        chrono.start();

        //Pone el nombre del jugador, ya ingresado en el textView de activity_menu
        final TextView player = (TextView) findViewById(R.id.playerView);
        player.setText(MemoTest.getInstance().getPlayerName());



        gridview.setAdapter(new AdaptadorDeMemoTest(this));

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
                // Toast para mostrar un mensaje. Escribe el nombre de tu clase
                // si no la llamaste MainActivity.
                // Al hacer click, esta mensaje muestra la posición
                /*Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT)
                        .show();*/
                Image item = (Image)parent.getAdapter().getItem(position);
                ((AdaptadorDeMemoTest)parent.getAdapter()).updateImages(item);

                if (MemoTest.getInstance().juegoTerminado()) {
                    //Aca tambien deberia mandarme a la pantalla de fin de juego
                    chrono.stop();
                    //Funciona pero hay que calibrarlo para que no sea un numero grande
                    MemoTest.getInstance().setRecordPlayer((SystemClock.elapsedRealtime() - chrono.getBase()/1000));


                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Tu puntuacion es:" + MemoTest.getInstance().getRecordPlayer());

                    //Aca tiene que guardar los datos en la db

                    builder.setCancelable(true);

                    builder.setPositiveButton(
                            "Record",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    Intent intent = new Intent(MainActivity.this, RecordActivity.class);
                                    startActivity(intent);
                                }
                            });

                    builder.setNegativeButton(
                            "Try again",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                                    startActivity(intent);
                                }
                            });

                    AlertDialog alert = builder.create();
                    alert.show();
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
