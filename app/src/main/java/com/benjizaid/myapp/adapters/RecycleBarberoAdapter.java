package com.benjizaid.myapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;
import com.benjizaid.myapp.R;
import com.benjizaid.myapp.model.BarberosEntity;

import java.util.List;

public class RecycleBarberoAdapter extends RecyclerView.Adapter<RecycleBarberoAdapter.ViewHolder> {

    private final List<BarberosEntity> barberosEntityList;
    private final Context context;

    public RecycleBarberoAdapter(List<BarberosEntity> barberosEntityList, Context context) {
        this.barberosEntityList = barberosEntityList;
        this.context = context;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_barbero,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BarberosEntity barberosEntity = barberosEntityList.get(position);
        final int itemPosition = position;
        final String barberoName = barberosEntity.getvName();

        holder.tviNombreBerberia.setText(barberoName);

        holder.fotoBarbero.setDefaultImageResId(R.mipmap.ic_launcher);
        holder.fotoBarbero.setErrorImageResId(R.mipmap.ic_launcher);
        //holder.fotoBarberia.setImageUrl(barberiaEntity.getvFoto());
        holder.fotoBarbero.setImageUrl("https://picsum.photos/300/300/?image=" + barberosEntity.getId());
    }

    @Override
    public int getItemCount() {
        return barberosEntityList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tviNombreBerberia;
        public ANImageView  fotoBarbero;
        //public ImageView iviPhoto;
        public View view;
        public ViewHolder(View  v) {
            super(v);
            this.view = v;
            tviNombreBerberia = (TextView) v.findViewById(R.id.tviNombreBarberia);
            fotoBarbero = (ANImageView) v.findViewById(R.id.iviFotoBarbero);
            //iviPhoto= (ImageView) v.findViewById(R.id.iviPhoto);
        }
    }
}
