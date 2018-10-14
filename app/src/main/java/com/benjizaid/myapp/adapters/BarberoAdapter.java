package com.benjizaid.myapp.adapters;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;
import com.benjizaid.myapp.R;
import com.benjizaid.myapp.model.BarberiaEntity;
import com.benjizaid.myapp.model.BarberosEntity;

import java.nio.file.attribute.PosixFileAttributes;
import java.util.List;

public class BarberoAdapter extends RecyclerView.Adapter<BarberoAdapter.ViewHolder> {

    private List<BarberosEntity> barberosEntities;
    private Context context;
    private AdapterCallBackBarbero callback;

    public interface AdapterCallBackBarbero {
        void onClickCallback(BarberosEntity item);

        void onClickNameBarbero(BarberosEntity item);

        void onClickFavorito(BarberosEntity item, int position);
    }

    public BarberoAdapter setBarberosEntities(List<BarberosEntity> barberosEntities) {
        this.barberosEntities = barberosEntities;
        return this;
    }

    public BarberoAdapter(List<BarberosEntity> barberosEntities, Context context, AdapterCallBackBarbero adapterCallBackBarbero) {
        this.barberosEntities = barberosEntities;
        this.context = context;
        this.callback = adapterCallBackBarbero;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_barbero, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(BarberoAdapter.ViewHolder holder, final int position) {
        final BarberosEntity barberosEntity = barberosEntities.get(position);
        final int itemPosition = position;
        final String barberoName = barberosEntity.getvName();
        holder.tviNombreBarbero.setText(barberoName);


        holder.fotoBarbero.setDefaultImageResId(R.mipmap.ic_launcher);
        holder.fotoBarbero.setErrorImageResId(R.mipmap.ic_launcher);
        holder.fotoBarbero.setImageUrl(barberosEntity.getvFoto());
        //holder.fotoBarbero.setImageUrl("https://picsum.photos/300/300/?image=" + barberosEntity.getId());

        if (barberosEntity.getbActivo() == 0) {
            holder.sendFavorito.setImageResource(R.drawable.ic_star_border_black_24dp);
        } else {
            holder.sendFavorito.setImageResource(R.drawable.start_active);
        }

        holder.sendFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onClickFavorito(barberosEntity, position);
            }
        });



        holder.tviNombreBarbero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callback != null) {
                    callback.onClickNameBarbero(barberosEntity);
                }
            }
        });

        holder.callBarbero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callback != null) {
                    callback.onClickCallback(barberosEntity);
                }
            }
        });
    }


    public void changeFavorito(int position) {
        if (barberosEntities.get(position).getbActivo() == 1)
            barberosEntities.get(position).setbActivo(0);
        else
            barberosEntities.get(position).setbActivo(1);

        this.notifyItemRangeChanged(position, 1);
    }

    @Override
    public int getItemCount() {
        return barberosEntities.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tviNombreBarbero;
        public ImageView callBarbero;
        public View view;
        public ANImageView fotoBarbero;

        public ImageView sendFavorito;

        public ViewHolder(View v) {
            super(v);
            this.view = v;
            tviNombreBarbero = (TextView) v.findViewById(R.id.tviNombreBarbero);
            callBarbero = (ImageView) v.findViewById(R.id.callBarbero);
            fotoBarbero = (ANImageView) v.findViewById(R.id.iviFotoBarbero);
            sendFavorito = (ImageView) v.findViewById(R.id.sendFavorito);
        }
    }
}
