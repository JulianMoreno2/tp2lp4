package com.example.ddavi.memotest;

import android.content.Context;
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
        }
    }

    public void updateImages(Image item){

        if (MemoTest.getInstance().getCountSelectedImages() == 2)
            MemoTest.getInstance().hideSelectedImagesNotFound();

        item.setSelected(true);
        MemoTest.getInstance().compareSelectedImages();
    }
}
