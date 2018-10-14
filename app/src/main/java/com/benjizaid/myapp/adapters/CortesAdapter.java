package com.benjizaid.myapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;
import com.benjizaid.myapp.R;
import com.benjizaid.myapp.model.CorteEntity;

import java.util.List;

public class CortesAdapter extends RecyclerView.Adapter<CortesAdapter.ViewHolder> {

    List<CorteEntity> listaCortes;

    public CortesAdapter(List<CorteEntity> listaCortes) {
        this.listaCortes = listaCortes;
    }

    @Override
    public CortesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.row_barberia, parent, false);
//        ViewHolder vh = new ViewHolder(v);
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_corte, parent, false));
    }

    @Override
    public void onBindViewHolder(CortesAdapter.ViewHolder holder, int position) {
        CorteEntity item = listaCortes.get(position);
        holder.updateCorte(item);
    }

    @Override
    public int getItemCount() {
        return listaCortes.size();
    }

    public CortesAdapter setListaCortes(List<CorteEntity> listaCortes) {
        this.listaCortes = listaCortes;
        return this;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ANImageView fotoCorte;
        private TextView tvDescripcion;

        public ViewHolder(View itemView) {
            super(itemView);

            fotoCorte = (ANImageView) itemView.findViewById(R.id.fotoCorte);
            tvDescripcion = (TextView) itemView.findViewById(R.id.tvDescripcion);
        }

        public void updateCorte(CorteEntity item) {
            fotoCorte.setDefaultImageResId(R.mipmap.ic_launcher);
            fotoCorte.setErrorImageResId(R.drawable.ic_error_outline_black_24dp);
            fotoCorte.setImageUrl(item.getvFoto());
            tvDescripcion.setText(item.getvDescripcion());
        }
    }
}
