package com.example.ddavi.memotest;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;


/**
 * Created by Rain on 24/03/2016.
 */
public class MenuActivity extends Activity {

    private static final int timerBeginner = 90000;
    private static final int timerNormal = 120000;
    private static final int timerExpert = 180000;

    private MemoTest memoTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onRecordClick(View view){
        finish();
        Intent intent = new Intent(MenuActivity.this, HistoryActivity.class);
        startActivity(intent);
    }

    public void onExitClick(View view){
        finish();
        System.exit(0);
    }

    public void onButtonClick(View view) {

        MemoTest juego = MemoTest.getInstance();
        finish();

        if(view.getId() == R.id.b_beginner){
            juego.setImages(MemoTest.getInstance().getBeginnerLevelImages());
            juego.getPlayer().setNivel(NivelUsuario.Pricipiante);
            AdaptadorDeMemoTest.timer = timerBeginner;
        }

        if(view.getId() == R.id.b_normal){
            juego.setImages(MemoTest.getInstance().getNormalLevelImages());
            juego.getPlayer().setNivel(NivelUsuario.Normal);
            AdaptadorDeMemoTest.timer = timerNormal;
        }

        if(view.getId() == R.id.b_expert){
            juego.setImages(MemoTest.getInstance().getRandomImagesExpertLevel());
            juego.getPlayer().setNivel(NivelUsuario.Experto);
            AdaptadorDeMemoTest.timer = timerExpert;
        }

        juego.getPlayer().setNombre(((EditText) findViewById(R.id.editTextName)).getText().toString());
        Intent intent = new Intent(MenuActivity.this, MainActivity.class);
        startActivity(intent);

    }

}
