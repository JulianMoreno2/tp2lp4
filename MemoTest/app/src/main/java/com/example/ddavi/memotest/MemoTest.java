package com.example.ddavi.memotest;

import java.util.ArrayList;

public class MemoTest {
    private static MemoTest instance;
    private Player player;
    private Image[] images;

    private int[] initialImage = {
            (R.drawable.emiticon_1),
            (R.drawable.emiticon_2),
            (R.drawable.emiticon_3),
            (R.drawable.emiticon_4),
            (R.drawable.emiticon_5),
            (R.drawable.emiticon_6),
            (R.drawable.emiticon_7),
            (R.drawable.emiticon_8),
            (R.drawable.emiticon_9),
            (R.drawable.emiticon_10),
            (R.drawable.emiticon_11),
            (R.drawable.emiticon_12),
            (R.drawable.emiticon_13),
            (R.drawable.emiticon_14),
            (R.drawable.emiticon_15),
    };

    private MemoTest() {
    }

    public static MemoTest getInstance() {
        if (instance == null) {
            instance = new MemoTest();
            instance.setPlayer(new Player());
            instance.setImages(new Image[0]);
        }

        return instance;
    }

    public void setImages(Image[] newImages) {
        this.images = newImages;
    }

    public Image[] getImages() {
        return this.images;
    }

    public int getCountSelectedImages() {
        int count = 0;
        for (int i = 0; i < this.getImages().length; i++) {
            if (this.getImages()[i].getSelected() && !this.getImages()[i].getFinded())
                count++;
        }
        return count;
    }

    /**
     * Se fija si el contador esta en dos(significa que tengo dos imagenes seleccionadas)
     * Entonces busca las dos imagenes que fueron seleccionadas y NO fueros encontradas.Las comparo para ver si son iguales
     * Si no son iguales las oculto denuevo, poniendo su atributo selected en false
     * Si son iguales coloco su atributo isFinded en true
     * Finalmente seteo el contador de nuevo en cero
     */
    public void compareSelectedImages() {

        Image imagen1 = null, imagen2 = null, current;

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

    public void hideSelectedImagesNotFound() {

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
     *
     * @return
     */
    public boolean isGameOver() {

        boolean gameState = true;
        int i = 0;
        Image current;
        while (i < this.getImages().length && gameState) {

            current = this.getImages()[i];
            if (!current.getFinded())
                gameState = false;

            i++;
        }
        return gameState;
    }

    private Image[] getRandomImagesGrid(Image[] imagenes) {

        Image[] matriz = new Image[imagenes.length];
        ArrayList<Integer> escogidos = new ArrayList<Integer>();
        Integer numRadom = (int) Math.floor(Math.random() * (imagenes.length - 0)) + 0;

        for (int i = 0; i < imagenes.length; i++) {

            //Verifico que el numero random no este
            while (escogidos.contains(numRadom))
                numRadom = (int) Math.floor(Math.random() * (imagenes.length - 0) + 0);

            //Guardo numero random
            escogidos.add(i, numRadom);
            //Cargo la matriz
            matriz[i] = imagenes[numRadom];
        }

        return matriz;
    }

    private Image[] getAndLoadImages(int cantImages){

        Image[] imagenes = new Image[cantImages * 2];
        int j,i;

        i=0;
        while (i<=cantImages) {
            for (j = 0; j < cantImages; j++)
                imagenes[j+i] = new Image(String.valueOf(j+i),initialImage[j]);

            i=i+cantImages;
        }

        return this.getRandomImagesGrid(imagenes);
    }

    public Image[] getBeginnerLevelImages() {
        return this.getAndLoadImages(8);
    }

    public Image[] getNormalLevelImages() {
        return this.getAndLoadImages(12);
    }

    public Image[] getRandomImagesExpertLevel() {
        return this.getAndLoadImages(15);
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

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player newPlayer) {
        this.player = newPlayer;
    }
}