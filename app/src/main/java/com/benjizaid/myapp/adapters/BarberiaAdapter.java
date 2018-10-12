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
import android.widget.ListView;
import android.widget.TextView;

import com.benjizaid.myapp.R;
import com.benjizaid.myapp.model.BarberiaEntity;
import com.benjizaid.myapp.model.BarberosEntity;

import java.util.List;

public class BarberiaAdapter extends RecyclerView.Adapter<BarberiaAdapter.ViewHolder>{

    private List<BarberiaEntity> barberiaEntities;
    private final Context context;
    private AdapterCallback callback;

    public interface AdapterCallback {
        void onClickCallback(BarberiaEntity item);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tviNombreBerberia;
        //public ImageView iviPhoto;
        public View view;
        public ViewHolder(View  v) {
            super(v);
            this.view = v;
            tviNombreBerberia = (TextView) v.findViewById(R.id.tviNombreBarberia);
            //iviPhoto= (ImageView) v.findViewById(R.id.iviPhoto);
        }
    }

    public BarberiaAdapter(Context context, List<BarberiaEntity> barberosEntityList, AdapterCallback adapterCallback) {
        this.context = context;
        this.barberiaEntities = barberosEntityList;
        this.callback = adapterCallback;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_barberia,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(BarberiaAdapter.ViewHolder holder, int position) {
        BarberiaEntity barberiaEntity = barberiaEntities.get(position);
        final int itemPosition = position;
        final String barberiaName = barberiaEntity.getName();
        holder.tviNombreBerberia.setText(barberiaName);
    }

    @Override
    public int getItemCount() {
        return barberiaEntities.size();
    }


}

/*
public class BarberiaAdapter extends BaseAdapter {

    private List<BarberiaEntity> barberiaEntities;
    private Context context;


    public BarberiaAdapter(List<BarberiaEntity> barberiaEntities, Context context) {
        this.barberiaEntities = barberiaEntities;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.barberiaEntities.size();
    }

    @Override
    public Object getItem(int position) {
        return this.barberiaEntities.get(position);
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
        View container = inflater.inflate(R.layout.row_barberia, null);

        //ImageView imgContact= (ImageView)container.findViewById(R.id);
        TextView tviName = (TextView)container.findViewById(R.id.tviNombreBerberia);

        //Extraer la entidad
        BarberiaEntity barberiaEntity= this.barberiaEntities.get(position);
        //Asociar la entidad con el XML
        tviName.setText(barberiaEntity.getName());
        //imgContact.setImageResource(contactEntity.getPhoto());

        return container;
    }


}
*/
