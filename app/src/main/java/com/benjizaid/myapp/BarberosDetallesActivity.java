package com.benjizaid.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.benjizaid.myapp.model.BarberosEntity;

import org.w3c.dom.Text;

public class BarberosDetallesActivity extends BaseActivity {

    private BarberosEntity barberosEntity;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barberos_detalles);
        enabledBack();
        extras();
        ui();
        populate();
    }

    private void ui(){
        textView = (TextView) findViewById(R.id.txtNombreBarberoDetalle);
    }

    private void populate(){
        if(barberosEntity!=null){
            textView.setText(barberosEntity.getvName());
        }

    }

    private void extras(){
        if(getIntent()!= null && getIntent().getExtras()!=null){
            barberosEntity = (BarberosEntity) getIntent().getExtras().getSerializable("BARBEROS");
        }
    }

}
