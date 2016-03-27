package com.example.ddavi.memotest;

/**
 * Created by ddavi on 26/3/2016.
 */

public class Player {

    private String nombre;
    private String puntuacion;
    private NivelUsuario nivel;
    private String time;

    public Player(){
        this.nombre = "";
        this.puntuacion = "0";
        this.time = "0:0";
    }

    public void setNombre(String newName){
        this.nombre = newName;
    }

    public void setPuntuacion(String record){
        this.puntuacion = record;
    }

    public void setNivel(NivelUsuario nivel){
        this.nivel = nivel;
    }

    public  void setTime(String time){
        this.time = time;
    }

    public String getNombre(){
        return this.nombre;
    }

    public String getTime(){
        return this.getTime();
    }

    public String getPuntuacion(){
        return this.puntuacion;
    }

    public NivelUsuario getNivel(){
        return this.nivel;
    }

    public int getTime_clock(){

        int time = 180000;
        if (nivel == NivelUsuario.Pricipiante){
            time = 60000;
        }else if (nivel == NivelUsuario.Normal){
            time = 120000;
        }

        return time;
    }

}
