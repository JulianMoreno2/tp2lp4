package com.example.ddavi.memotest;

import java.util.ArrayList;
import java.util.Random;

public class MemoTest {
    private static MemoTest instance;
    private String playerName;
    private String recordPlayer;
    private Image[] images;

    private static Image[] IMAGES = {
            new Image("Jaguar F-Type 2015", R.drawable.jaguar_f_type_2015),
            new Image("Mercedes AMG-GT", R.drawable.mercedes_benz_amg_gt),
            new Image("Mazda MX-5", R.drawable.mazda_mx5_2015),
            new Image("Porsche 911 GTS", R.drawable.porsche_911_gts),
            new Image("Jaguar F-Type 2015", R.drawable.jaguar_f_type_2015),
            new Image("BMW Serie 6", R.drawable.bmw_serie6_cabrio_2015),
            new Image("Mercedes AMG-GT", R.drawable.mercedes_benz_amg_gt),
            new Image("Ford Mondeo", R.drawable.ford_mondeo),
            new Image("Mazda MX-5", R.drawable.mazda_mx5_2015),
            new Image("Volvo V60 Cross Country", R.drawable.volvo_v60_crosscountry),
            new Image("BMW Serie 6", R.drawable.bmw_serie6_cabrio_2015),
            new Image("Jaguar XE", R.drawable.jaguar_xe),
            new Image("Volvo V60 Cross Country", R.drawable.volvo_v60_crosscountry),
            new Image("Porsche 911 GTS", R.drawable.porsche_911_gts),
            new Image("Jaguar XE", R.drawable.jaguar_xe),
            new Image("Ford Mondeo", R.drawable.ford_mondeo),

    };

    private MemoTest() {}

    public static MemoTest getInstance() {
        if (instance == null){
            instance = new MemoTest();
            instance.setImages(getImagesGrid(16));
        }

        return instance;
    }

    private void setImages(Image[] newImages){
        this.images = newImages;
    }

    public Image[] getImages(){
        return this.images;
    }

    public int getCountSelectedImages(){
        int count = 0;
        for (int i = 0; i < this.getImages().length; i++) {
            if(this.getImages()[i].getSelected() && !this.getImages()[i].getFinded())
                count++;
        }
        return count;
    }

    /**
     * Se fija si el contador esta en dos(significa que tengo dos imagenes seleccionadas)
     * Entonces busca las dos imagenes que fueron seleccionadas y NO fueros encontradas.Las comparo para ver si son iguales
     *      Si no son iguales las oculto denuevo, poniendo su atributo selected en false
     *      Si son iguales coloco su atributo isFinded en true
     * Finalmente seteo el contador de nuevo en cero
     */
    public void compareSelectedImages(){

        Image imagen1=null,imagen2=null,current;

        //Si tengo menos de dos seleccionados no hago nada
        if (MemoTest.getInstance().getCountSelectedImages() == 2) {

            for (int i = 0; i < this.getImages().length; i++) {

                current = this.getImages()[i];
                if (current.getSelected() && !current.getFinded()) {
                    if (imagen1 == null)
                        imagen1 = current;
                    else
                        imagen2 = current;
                }
            }

            if (imagen1.getIdDrawable() == imagen2.getIdDrawable()) {
                imagen1.setFinded(true);
                imagen2.setFinded(true);
            }
        }
    }

    public void hideSelectedImagesNotFound(){

        Image current;
        for (int i = 0; i < this.getImages().length; i++) {

            current = this.getImages()[i];
            if (!current.getFinded()) {
                current.setSelected(false);
            }
        }
    }

    /**
     * Verifica si todas las imagenes estan seleccionadas
     * @return
     */
    public boolean isGameOver(){

        boolean gameState = true;
        int i = 0;
        Image current;
        while (i< this.getImages().length && gameState){

            current = this.getImages()[i];
            if(!current.getFinded())
                gameState = false;

            i++;
        }
        return gameState;
    }

    private static Image[] getImagesGrid(int cantImages){

        Image[] matriz4x4 = new Image[cantImages];
        ArrayList<Integer> escogidos = new ArrayList<Integer>();
        Integer numRadom = (int)Math.floor(Math.random()*(cantImages-0))+0;

        for (int i=0; i< IMAGES.length; i++){

            //Verifico que el numero random no este
           while (escogidos.contains(numRadom))
                numRadom = (int)Math.floor(Math.random()*(cantImages-0)+0);

            //Guardo numero random
            escogidos.add(i,numRadom);
            //Cargo la matriz
            matriz4x4[i] = IMAGES[numRadom];
        }

        return matriz4x4;
    }

    /**
     * Obtiene item basado en su identificador
     *
     * @param id identificador
     * @return MemoTest
     */
    public static Image getItem(int id) {
        for (Image item : getInstance().getImages()) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getRecordPlayer() { return recordPlayer; }

    public void setRecordPlayer(String recordPlayer) {
        this.recordPlayer = recordPlayer;
    }
}