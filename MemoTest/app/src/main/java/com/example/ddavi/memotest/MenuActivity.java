package com.example.ddavi.memotest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


/**
 * Created by Rain on 24/03/2016.
 */
public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onButtonClick(View view) {

        if(view.getId() == R.id.bStart){
            Intent intent = new Intent(MenuActivity.this, MainActivity.class);
            startActivity(intent);
        }

        if(view.getId() == R.id.bExit){
            //Buscar metodo para cerrar aplicacion
        }
    }

}
