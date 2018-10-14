package com.benjizaid.myapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.widget.ANImageView;
import com.benjizaid.myapp.adapters.BarberiaAdapter;
import com.benjizaid.myapp.adapters.BarberoAdapter;
import com.benjizaid.myapp.app.WebService;
import com.benjizaid.myapp.model.BarberiaEntity;
import com.benjizaid.myapp.model.BarberosEntity;
import com.benjizaid.myapp.model.CorteEntity;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class BarberiaDetalleActivity extends BaseActivity  implements BarberoAdapter.AdapterCallBackBarbero{

    private BarberiaEntity barberiaEntity;
    private int IdUsuario;

    private ANImageView fotoBarberiaGps;
    ImageView fotGps;
    private TextView tvNombre,tvDescripcion;
    private ProgressBar progress_bar;
    private RecyclerView rvBarberos;
    BarberoAdapter barberoAdapter;
    List<BarberosEntity> listaBarberos;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barberia_detalle2);
        enabledBack();
        extras();
        ui();
        populate();
        getBarberosXBarberia();
    }

    private void ui() {
        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvDescripcion = (TextView) findViewById(R.id.tvDescripcion);
        fotoBarberiaGps = (ANImageView) findViewById(R.id.fotoBarberiaGps);
        fotGps= (ImageView) findViewById(R.id.fotGps);


        rvBarberos = (RecyclerView) findViewById(R.id.rvBarberos);
        listaBarberos = new ArrayList<>();

        barberoAdapter = new BarberoAdapter(listaBarberos,this,this);
        rvBarberos.setLayoutManager(new LinearLayoutManager(this));
        rvBarberos.setAdapter(barberoAdapter);

        progress_bar = (ProgressBar) findViewById(R.id.progress_bar);


//        ivGPSBarberia.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplication(), "Implementar GPS", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    public void getBarberosXBarberia() {
        progress_bar.setVisibility(View.VISIBLE);
        AndroidNetworking.get(WebService.listarBarberosXBarberia(IdUsuario,barberiaEntity.getId()))
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        try {
                            BarberosEntity item;
                            for (int i = 0; i < jsonArray.length(); i++) {
                                item = new BarberosEntity();
                                item.setIDBarberia(jsonArray.getJSONObject(i).getInt("IDBarberia"))
                                        .setbActivo(jsonArray.getJSONObject(i).getInt("bActivo"))
                                        .setId(jsonArray.getJSONObject(i).getInt("id"))
                                        .setvDescripcion(jsonArray.getJSONObject(i).getString("vDescripcion"))
                                        .setvEmail(jsonArray.getJSONObject(i).getString("vEmail"))
                                        .setvFoto(jsonArray.getJSONObject(i).getString("vFoto"))
                                        .setvName(jsonArray.getJSONObject(i).getString("vName"))
                                        .setvTelefono(jsonArray.getJSONObject(i).getString("vTelefono"));
                                listaBarberos.add(item);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        progress_bar.setVisibility(View.GONE);
                        barberoAdapter.setBarberosEntities(listaBarberos);
                        barberoAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(ANError anError) {
                        progress_bar.setVisibility(View.GONE);

                    }
                });
    }

    private void populate() {
        if (barberiaEntity != null) {
            tvNombre.setText(barberiaEntity.getvName());
            tvDescripcion.setText(barberiaEntity.getvDescripcion());

            fotoBarberiaGps.setDefaultImageResId(R.mipmap.ic_launcher);
            fotoBarberiaGps.setErrorImageResId(R.drawable.ic_error_outline_black_24dp);
            fotoBarberiaGps.setImageUrl(barberiaEntity.getvFoto());
            //fotGps.setImageUrl(getStaticmap(barberiaEntity.getvLatitud(),barberiaEntity.getvLongitud()));
        }

    }

    private static String getStaticmap(double latitud, double longitude) {
        return "https://maps.googleapis.com/maps/api/staticmap?center="+ latitud + ","+ longitude +
                "&zoom=16&size=500x300&markers=icon:http%3A%2F%2Fgoo.gl%2FGjVUSC|"+ latitud+ ","+longitude + "&key=AIzaSyB8GPKBmbH-8GC5iK3UgD2o2ExtrZiFXyg";
    }

    private void extras() {
        Intent intent = getIntent();

        if (intent != null && intent.getExtras() != null) {
            barberiaEntity = (BarberiaEntity) intent.getExtras().getSerializable("BARBERIA");
            IdUsuario = intent.getExtras().getInt("IdUsuario");
        }
    }

    @Override
    public void onClickCallback(BarberosEntity item) {

    }

    @Override
    public void onClickNameBarbero(BarberosEntity item) {

    }

    @Override
    public void onClickFavorito(BarberosEntity item, int position) {

    }
}
