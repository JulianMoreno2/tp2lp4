package com.example.ddavi.memotest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


/**
 * Created by Rain on 24/03/2016.
 */
public class MenuActivity extends Activity {

    private MemoTest memoTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onButtonClick(View view) {

        if(view.getId() == R.id.bStart){
            //obtiene el texto del editText y lo guarda en el atributo del memotest
            memoTest.getInstance().setPlayerName( ((EditText)findViewById(R.id.editTextName)).getText().toString());
            Intent intent = new Intent(MenuActivity.this, MainActivity.class);
            startActivity(intent);
        }

        if(view.getId() == R.id.bExit){
            finish();
            System.exit(0);
        }
    }

}
