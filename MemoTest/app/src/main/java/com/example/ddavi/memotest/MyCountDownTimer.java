package com.example.ddavi.memotest;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.widget.TextView;

public class MyCountDownTimer extends CountDownTimer {

    private TextView textCounter;
    private AdaptadorDeMemoTest adaptador;

    public MyCountDownTimer(AdaptadorDeMemoTest adaptador, long startTime, long interval) {
        super(startTime, interval);
        this.textCounter = (TextView)((MainActivity)adaptador.getContext()).findViewById(R.id.cronometro);
        this.adaptador = adaptador;
    }

    @Override
    public void onFinish() {
        this.textCounter.setText("Time Out");
        this.textCounter.setTextColor(Color.RED);
        this.adaptador.gameOver();
    }

    @Override
    public void onTick(long millisUntilFinished) {
        textCounter.setText(this.getTime(millisUntilFinished));
    }

    //Convierte los millisegundo al formato adecuado
    private String getTime(long timecurrent){

        long minutes = (timecurrent/(1000*60))%60;
        long seconds = (timecurrent / 1000) % 60;

        String currentDate = String.valueOf(minutes)
                + ":"+ String.valueOf(seconds);

        return currentDate;
    }
}