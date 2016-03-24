package com.example.ddavi.memotest;

public class MemoTest {
    private static MemoTest instance;
    private String playerName;
    private int recordPlayer;

    private MemoTest() {}

    public static MemoTest getInstance(){
        if (instance == null)
            instance = new MemoTest();

        return instance;
    }

    public int getCountSelectedImages(){
        int count = 0;
        for (int i = 0; i < getInstance().IMAGES.length; i++) {
            if(getInstance().IMAGES[i].getSelected() && !getInstance().IMAGES[i].getFinded())
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

            for (int i = 0; i < getInstance().IMAGES.length; i++) {

                current = getInstance().IMAGES[i];
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
        for (int i = 0; i < getInstance().IMAGES.length; i++) {

            current = getInstance().IMAGES[i];
            if (!current.getFinded()) {
                current.setSelected(false);
            }
        }
    }

    /**
     * Verifica si todas las imagenes estan seleccionadas
     * @return
     */
    public boolean juegoTerminado(){

        boolean gameState = true;
        int i = 0;
        Image current;
        while (i<getInstance().IMAGES.length && gameState){

            current = getInstance().IMAGES[i];
            if(!current.getFinded())
                gameState = false;

            i++;
        }
        return gameState;
    }

    public Image[] IMAGES = {
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

    /**
     * Obtiene item basado en su identificador
     *
     * @param id identificador
     * @return MemoTest
     */
    public static Image getItem(int id) {
        for (Image item : getInstance().IMAGES) {
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

    public int getRecordPlayer() {
        return recordPlayer;
    }

    public void setRecordPlayer(int recordPlayer) {
        this.recordPlayer = recordPlayer;
    }
}