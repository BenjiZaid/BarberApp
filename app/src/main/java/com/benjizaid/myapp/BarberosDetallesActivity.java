package com.benjizaid.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.widget.ANImageView;
import com.benjizaid.myapp.adapters.CortesAdapter;
import com.benjizaid.myapp.app.WebService;
import com.benjizaid.myapp.model.BarberosEntity;
import com.benjizaid.myapp.model.CorteEntity;

import org.json.JSONArray;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class BarberosDetallesActivity extends BaseActivity {

    private BarberosEntity barberosEntity;
    ANImageView fotoBarbero;
    TextView tvDescripcion, tvNombre;
    RecyclerView rvCortes;
    ProgressBar progress_bar;

    CortesAdapter cortesAdapter;
    List<CorteEntity> listaCortes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barberos_detalles);
        enabledBack();
        extras();
        ui();
        populate();

        getCortesBarbero();
    }

    public void getCortesBarbero() {
        progress_bar.setVisibility(View.VISIBLE);
        AndroidNetworking.get(WebService.listarCortesBarberos(barberosEntity.getId()))
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            CorteEntity item;
                            for (int i = 0; i < response.length(); i++) {
                                item = new CorteEntity();
                                item.setId(response.getJSONObject(i).getInt("id"))
                                        .setvDescripcion(response.getJSONObject(i).getString("vDescripcion"))
                                        .setvFoto(response.getJSONObject(i).getString("vFoto"));
                                listaCortes.add(item);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        progress_bar.setVisibility(View.GONE);
                        cortesAdapter.setListaCortes(listaCortes);
                        cortesAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(ANError anError) {
                        progress_bar.setVisibility(View.GONE);

                    }
                });
    }

    private void ui() {
        fotoBarbero = (ANImageView) findViewById(R.id.fotoBarbero);
        tvDescripcion = (TextView) findViewById(R.id.tvDescripcion);
        tvNombre = (TextView) findViewById(R.id.tvNombre);
        rvCortes = (RecyclerView) findViewById(R.id.rvCortes);
        progress_bar = (ProgressBar) findViewById(R.id.progress_bar);

        listaCortes = new ArrayList<>();
        cortesAdapter = new CortesAdapter(listaCortes);
        rvCortes.setLayoutManager(new LinearLayoutManager(this));
        rvCortes.setAdapter(cortesAdapter);
    }

    private void populate() {
        if (barberosEntity != null) {
            tvNombre.setText(barberosEntity.getvName());
            tvDescripcion.setText(barberosEntity.getvDescripcion());

            fotoBarbero.setDefaultImageResId(R.mipmap.ic_launcher);
            fotoBarbero.setErrorImageResId(R.drawable.ic_error_outline_black_24dp);
            fotoBarbero.setImageUrl(barberosEntity.getvFoto());


        }

    }

    private void extras() {
        if (getIntent() != null && getIntent().getExtras() != null) {
            barberosEntity = (BarberosEntity) getIntent().getExtras().getSerializable("BARBEROS");
        }
    }

}
