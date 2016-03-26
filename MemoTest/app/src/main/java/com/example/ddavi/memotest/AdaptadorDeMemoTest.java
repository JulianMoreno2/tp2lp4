package com.example.ddavi.memotest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * {@link BaseAdapter} para poblar coches en un grid view
 */

public class AdaptadorDeMemoTest extends BaseAdapter {
    private Context context;
    private DataBaseManager manager;

    public AdaptadorDeMemoTest(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public int getCount() {
        return MemoTest.getInstance().getImages().length;
    }

    @Override
    public Image getItem(int position) {
        return MemoTest.getInstance().getImages()[position];
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item, viewGroup, false);
        }

        ImageView imagenCoche = (ImageView) view.findViewById(R.id.imagen_coche);

        final Image item = getItem(position);

        Glide.with(imagenCoche.getContext())
                .load((item.getSelected()) ? item.getIdDrawable() : R.drawable.icon_memotest)
                .into(imagenCoche);

        return view;
    }

    public void unselectedImages(){

        for (int i=0; i<MemoTest.getInstance().getImages().length;i++){
            MemoTest.getInstance().getImages()[i].setSelected(false);
            MemoTest.getInstance().getImages()[i].setFinded(false);
        }
    }

    public void updateImages(Image item){

        if (MemoTest.getInstance().getCountSelectedImages() == 2)
            MemoTest.getInstance().hideSelectedImagesNotFound();

        item.setSelected(true);
        MemoTest.getInstance().compareSelectedImages();
    }

    public void gameOver(String record){

        //Aca tambien deberia mandarme a la pantalla de fin de juego
        this.unselectedImages();
        //Funciona pero hay que calibrarlo para que no sea un numero grande
        MemoTest.getInstance().setRecordPlayer(record);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Tu tiempo fue:" + MemoTest.getInstance().getRecordPlayer());

        manager = new DataBaseManager(context);
        //Agrega el player y su tiempo a la base de datos
        manager.insert(MemoTest.getInstance().getPlayerName(), String.valueOf(MemoTest.getInstance().getRecordPlayer()));

        Intent intent = new Intent(context, RecordActivity.class);
        context.startActivity(intent);

        /*
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Record",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        Intent intent = new Intent(context, RecordActivity.class);
                        context.startActivity(intent);
                    }
                });

        builder.setNegativeButton(
                "Try again",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        Intent intent = new Intent(context, MenuActivity.class);
                        context.startActivity(intent);
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();*/
    }
}
