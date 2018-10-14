package com.benjizaid.myapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.benjizaid.myapp.app.WebService;
import com.benjizaid.myapp.model.UsuarioEntity;

import org.json.JSONObject;

public class CreateUserActivity extends AppCompatActivity {

    EditText txtApellidosUsuario,txtNombresUsuario,txtEmail,txtDireccion,txtTelefono,txtPassword,txtRepetirPassword;
    Button btnCrearUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        ui();
    }

    private void ui(){
        txtApellidosUsuario = (EditText) findViewById(R.id.txtApellidosUsuario);
        txtNombresUsuario= (EditText) findViewById(R.id.txtNombresUsuario);
        txtEmail= (EditText) findViewById(R.id.txtEmail);
        txtDireccion= (EditText) findViewById(R.id.txtDireccion);
        txtTelefono= (EditText) findViewById(R.id.txtTelefono);
        txtPassword= (EditText) findViewById(R.id.txtPassword);
        txtRepetirPassword= (EditText) findViewById(R.id.txtRepetirPassword);

        btnCrearUsuario = (Button) findViewById(R.id.btnCrearUsuario);

        btnCrearUsuario.setOnClickListener(btnCrearusuarioOnClickListenerOnClickListener);
    }

    View.OnClickListener btnCrearusuarioOnClickListenerOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //vams a asumirq ue registra todos


            final ProgressDialog mProgressDialog = new ProgressDialog(CreateUserActivity.this);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setMessage("Registrando...");
            mProgressDialog.show();


            AndroidNetworking.post(WebService.registerUser())
                    .addQueryParameter("vPassword",txtPassword.getText().toString())
                    .addQueryParameter("vNombres", txtNombresUsuario.getText().toString())
                    .addQueryParameter("vApellidos",txtApellidosUsuario.getText().toString())
                    .addQueryParameter("vEmail",txtEmail.getText().toString())
                    .addQueryParameter("vDireccion",txtDireccion.getText().toString())
                    .addQueryParameter("vTelefono",txtTelefono.getText().toString())
                    .setPriority(Priority.LOW)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {

                            mProgressDialog.dismiss();

                            try {

//                                Intent intent = new Intent(LoginActivity.this, NavigationActivity.class);
//                                intent.putExtra("id",item.getId());
//                                startActivity(intent);
//                                finish();
                            } catch (Exception ex) {
//                                Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            mProgressDialog.dismiss();
//                            Toast.makeText(LoginActivity.this, "Error en el servicio", Toast.LENGTH_SHORT).show();

                        }
                    });

        }
    };



    private void backToLogIn() {
        finish();
    }
}
