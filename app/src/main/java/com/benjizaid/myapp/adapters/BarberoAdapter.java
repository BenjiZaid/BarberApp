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

import com.benjizaid.myapp.R;
import com.benjizaid.myapp.model.BarberosEntity;

import java.util.List;

public class BarberoAdapter extends RecyclerView.Adapter<BarberoAdapter.ViewHolder> {

    private List<BarberosEntity> barberosEntities;
    private Context context;
    private AdapterCallBackBarbero callback;

    public interface AdapterCallBackBarbero{
        void onClickCallback(BarberosEntity item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tviNombreBarbero;
        //public ImageView iviPhoto;
        public View view;
        public ViewHolder(View  v) {
            super(v);
            this.view = v;
            tviNombreBarbero = (TextView) v.findViewById(R.id.tviNombreBarbero);
            //iviPhoto= (ImageView) v.findViewById(R.id.iviPhoto);
        }
    }

    public BarberoAdapter(List<BarberosEntity> barberosEntities, Context context, AdapterCallBackBarbero adapterCallBackBarbero) {
        this.barberosEntities = barberosEntities;
        this.context = context;
        this.callback = adapterCallBackBarbero;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_barbero,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(BarberoAdapter.ViewHolder holder, int position) {
        BarberosEntity barberosEntity = barberosEntities.get(position);
        final int itemPosition = position;
        final String barberoName = barberosEntity.getName();
        holder.tviNombreBarbero.setText(barberoName);
    }

    @Override
    public int getItemCount() {
        return barberosEntities.size();
    }
    /*
    @Override
    public int getCount() {
        return this.barberosEntities.size();
    }

    @Override
    public Object getItem(int position) {
        return this.barberosEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Dibujar la celda
        LayoutInflater inflater=LayoutInflater.from(context);
        //View container= inflater.inflate(R.layout.row_contact, null);
        View container = inflater.inflate(R.layout.row_barbero, null);

        //ImageView imgContact= (ImageView)container.findViewById(R.id);
        TextView tviName = (TextView)container.findViewById(R.id.tviNombreBerbero);

        //Extraer la entidad
        BarberosEntity barberosEntity= this.barberosEntities.get(position);
        //Asociar la entidad con el XML
        tviName.setText(barberosEntity.getName());
        //imgContact.setImageResource(contactEntity.getPhoto());

        return container;
    }
    */
}
