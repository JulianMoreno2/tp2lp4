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

    public AdaptadorDeMemoTest(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public int getCount() {
        return MemoTest.getInstance().IMAGES.length;
    }

    @Override
    public Image getItem(int position) {
        return MemoTest.getInstance().IMAGES[position];
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

        for (int i=0; i<MemoTest.getInstance().IMAGES.length;i++){
            MemoTest.getInstance().IMAGES[i].setSelected(false);
            MemoTest.getInstance().IMAGES[i].setFinded(false);
        }
    }

    public void updateImages(Image item){

        if (MemoTest.getInstance().getCountSelectedImages() == 2)
            MemoTest.getInstance().hideSelectedImagesNotFound();

        item.setSelected(true);
        MemoTest.getInstance().compareSelectedImages();
    }

    public void gameOver(){

        //Aca tambien deberia mandarme a la pantalla de fin de juego
        this.unselectedImages();
        //Funciona pero hay que calibrarlo para que no sea un numero grande
        //MemoTest.getInstance().setRecordPlayer((SystemClock.elapsedRealtime() - chrono.getBase()/1000));

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Tu puntuacion es:" + MemoTest.getInstance().getRecordPlayer());

        //Aca tiene que guardar los datos en la db

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
        alert.show();
    }
}
