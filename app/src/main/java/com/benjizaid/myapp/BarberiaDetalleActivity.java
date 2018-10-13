package com.benjizaid.myapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.benjizaid.myapp.adapters.BarberiaAdapter;
import com.benjizaid.myapp.model.BarberiaEntity;
import com.benjizaid.myapp.model.BarberosEntity;

public class BarberiaDetalleActivity extends BaseActivity {

    private BarberiaEntity barberiaEntity;

    private TextView txtNombreBarberiaDetalle;
    private TextView txtDescripcionBarberiaDetalle;
    private ImageView ivGPSBarberia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barberia_detalle2);
        enabledBack();
        extras();
        ui();
        populate();
    }

    private void ui(){
        txtNombreBarberiaDetalle = (TextView) findViewById(R.id.txtNombreBarberiaDetalle);
        txtDescripcionBarberiaDetalle = (TextView) findViewById(R.id.txtDescripcionBarberiaDetalle);
        ivGPSBarberia = (ImageView) findViewById(R.id.ivGPSBarberia);

        ivGPSBarberia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplication(), "Implementar GPS", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populate(){
        if(barberiaEntity!=null){
            txtNombreBarberiaDetalle.setText(barberiaEntity.getvName());
            txtDescripcionBarberiaDetalle.setText(barberiaEntity.getvDescripcion());
        }

    }

    private void extras(){
        if(getIntent()!= null && getIntent().getExtras()!=null){
            barberiaEntity = (BarberiaEntity) getIntent().getExtras().getSerializable("BARBERIA");
        }
    }
}
