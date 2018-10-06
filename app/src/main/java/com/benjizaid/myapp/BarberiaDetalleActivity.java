package com.benjizaid.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.benjizaid.myapp.adapters.BarberiaAdapter;
import com.benjizaid.myapp.model.BarberiaEntity;
import com.benjizaid.myapp.model.BarberosEntity;

public class BarberiaDetalleActivity extends BaseActivity {

    private BarberiaEntity barberiaEntity;

    private TextView textView;

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
        textView = (TextView) findViewById(R.id.txtNombreBarberiaDetalle);
    }

    private void populate(){
        if(barberiaEntity!=null){
            textView.setText(barberiaEntity.getName());
        }

    }

    private void extras(){
        if(getIntent()!= null && getIntent().getExtras()!=null){
            barberiaEntity = (BarberiaEntity) getIntent().getExtras().getSerializable("BARBERIA");
        }
    }
}
