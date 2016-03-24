package com.example.ddavi.memotest;

/**
 * Created by Rain on 23/03/2016.
 */
public class Image {

    private final String name;
    private boolean isSelected;
    private boolean isFinded;
    private int position;
    private int idDrawable;

    public Image(String name, int idDrawable){

        this.name = name;
        this.idDrawable = idDrawable;
        this.isSelected = false;
    }

    public void setIdDrawable(int id){
        this.idDrawable = id;
    }

    public String getName(){
        return this.name;
    }

    public int getId() {
        return name.hashCode();
    }

    public int getPosition(){
        return this.position;
    }

    public void setPosition(int position){
        this.position = position;
    }

    public boolean getSelected(){
        return isSelected;
    }

    public void setSelected(boolean bool){
        this.isSelected = bool;
    }

    public boolean getFinded(){
        return isFinded;
    }

    public void setFinded(boolean bool){
        this.isFinded = bool;
    }

    public int getIdDrawable() {
        return idDrawable;
    }

}