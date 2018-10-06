package com.benjizaid.myapp.adapters;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
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

public class BarberoAdapter extends BaseAdapter {

    private List<BarberosEntity> barberosEntities;
    private Context context;

    public BarberoAdapter(List<BarberosEntity> barberosEntities, Context context) {
        this.barberosEntities = barberosEntities;
        this.context = context;
    }

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
}
