package com.example.ddavi.memotest;

import android.app.AlertDialog;
import android.content.Context;
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
    public static int timer = 0;

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

        ImageView imagenCoche = (ImageView) view.findViewById(R.id.imagen_icon);
        final Image item = getItem(position);

        Glide.with(imagenCoche.getContext())
                .load((item.getSelected()) ? item.getIdDrawable() : R.drawable.icon_memotest)
                .into(imagenCoche);

        return view;
    }

    public void updateImages(Image item){

        if (MemoTest.getInstance().getCountSelectedImages() == 2)
            MemoTest.getInstance().hideSelectedImagesNotFound();

        item.setSelected(true);
        MemoTest.getInstance().compareSelectedImages();
    }

    public void gameOver(String record){

        MemoTest.getInstance().getPlayer().setPuntuacion(record);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Tu tiempo fue:" + MemoTest.getInstance().getPlayer().getPuntuacion());

        manager = new DataBaseManager(context);
        //Agrega el player y su tiempo a la base de datos
        manager.insert(
                MemoTest.getInstance().getPlayer().getNombre(),
                String.valueOf(MemoTest.getInstance().getPlayer().getPuntuacion()));

        Intent intent = new Intent(context, RecordActivity.class);
        context.startActivity(intent);

    }
}
