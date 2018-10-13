package com.benjizaid.myapp.adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
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

import java.util.List;

public class BarberiaAdapter extends RecyclerView.Adapter<BarberiaAdapter.ViewHolder> {

    private List<BarberiaEntity> barberiaEntities;
    private final Context context;
    private AdapterCallback callback;

    public interface AdapterCallback {
        void onClickCallback(BarberiaEntity item);

        void onClickNameBarberia(BarberiaEntity item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tviNombreBerberia;
        public ImageView callBarberia;
        public View view;

        private ANImageView fotoBarberia;

        public ViewHolder(View v) {
            super(v);
            this.view = v;
            tviNombreBerberia = (TextView) v.findViewById(R.id.tviNombreBarberia);
            callBarberia = (ImageView) v.findViewById(R.id.callBarberia);
            fotoBarberia = (ANImageView) v.findViewById(R.id.iviFotoBarberia);
        }
    }

    public void setBarberiaEntities(List<BarberiaEntity> barberiaEntities) {
        this.barberiaEntities = barberiaEntities;
    }

    public BarberiaAdapter(Context context, List<BarberiaEntity> barberosEntityList, AdapterCallback adapterCallback) {
        this.context = context;
        this.barberiaEntities = barberosEntityList;
        this.callback = adapterCallback;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_barberia, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(BarberiaAdapter.ViewHolder holder, int position) {
        final BarberiaEntity barberiaEntity = barberiaEntities.get(position);
        final int itemPosition = position;
        final String barberiaName = barberiaEntity.getvName();
        holder.tviNombreBerberia.setText(barberiaName);

        holder.fotoBarberia.setDefaultImageResId(R.mipmap.ic_launcher);
        holder.fotoBarberia.setErrorImageResId(R.mipmap.ic_launcher);
        //holder.fotoBarberia.setImageUrl(barberiaEntity.getvFoto());
        holder.fotoBarberia.setImageUrl("https://picsum.photos/300/300/?image=" + barberiaEntity.getId());

        holder.callBarberia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onClickCallback(barberiaEntity);
                }
            }
        });

        holder.tviNombreBerberia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callback != null) {
                    callback.onClickNameBarberia(barberiaEntity);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return barberiaEntities.size();
    }


}