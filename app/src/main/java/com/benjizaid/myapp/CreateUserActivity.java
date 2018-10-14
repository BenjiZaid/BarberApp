package com.benjizaid.myapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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

import org.json.JSONException;
import org.json.JSONObject;

public class CreateUserActivity extends AppCompatActivity {

    EditText txtApellidosUsuario, txtNombresUsuario, txtEmail, txtDireccion, txtTelefono, txtPassword, txtRepetirPassword;
    Button btnCrearUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        ui();
    }

    private void ui() {
        txtApellidosUsuario = (EditText) findViewById(R.id.txtApellidosUsuario);
        txtNombresUsuario = (EditText) findViewById(R.id.txtNombresUsuario);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtDireccion = (EditText) findViewById(R.id.txtDireccion);
        txtTelefono = (EditText) findViewById(R.id.txtTelefono);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtRepetirPassword = (EditText) findViewById(R.id.txtRepetirPassword);

        btnCrearUsuario = (Button) findViewById(R.id.btnCrearUsuario);

        btnCrearUsuario.setOnClickListener(btnCrearusuarioOnClickListenerOnClickListener);
    }

    View.OnClickListener btnCrearusuarioOnClickListenerOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final ProgressDialog mProgressDialog = new ProgressDialog(CreateUserActivity.this);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setMessage("Registrando...");
            mProgressDialog.show();

            String password = txtPassword.getText().toString(),
                    nombres = txtNombresUsuario.getText().toString(),
                    apellidos = txtApellidosUsuario.getText().toString(),
                    email = txtEmail.getText().toString(),
                    direccion = txtDireccion.getText().toString(),
                    telefono = txtTelefono.getText().toString();


            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("vPassword", password);
                jsonObject.put("vNombres", nombres);
                jsonObject.put("vApellidos", apellidos);
                jsonObject.put("vEmail", email);
                jsonObject.put("vDireccion", direccion);
                jsonObject.put("vTelefono", telefono);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            AndroidNetworking.post(WebService.registerUser())
                    .addJSONObjectBody(jsonObject) // posting json
                    .setPriority(Priority.LOW)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {

                            mProgressDialog.dismiss();

                            try {

                                int IdUsuario = jsonObject.getInt("iRespuesta");

                                if (IdUsuario == -2){
                                    AlertDialog.Builder dialogo = new AlertDialog.Builder(CreateUserActivity.this)
                                            .setTitle(getString(R.string.app_name))
                                            .setMessage(jsonObject.getString("vRespuesta"))
                                            .setIcon(R.mipmap.ic_launcher)
                                            .setCancelable(false)

                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    dialogInterface.dismiss();
                                                }
                                            });
                                    dialogo.show();
                                }else if (IdUsuario < 0) {
                                    AlertDialog.Builder dialogo = new AlertDialog.Builder(CreateUserActivity.this)
                                            .setTitle(getString(R.string.app_name))
                                            .setMessage(jsonObject.getString("vRespuesta"))
                                            .setIcon(R.mipmap.ic_launcher)
                                            .setCancelable(false)

                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    dialogInterface.dismiss();
                                                }
                                            });
                                    dialogo.show();
                                } else {
                                    Intent intent = new Intent(CreateUserActivity.this, NavigationActivity.class);
                                    intent.putExtra("id", IdUsuario);

                                    /*PARA QUE NO SE REGRESE AL LOGIN*/
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);

                                    finish();



                                }
                            } catch (Exception ex) {
                                AlertDialog.Builder dialogo = new AlertDialog.Builder(CreateUserActivity.this)
                                        .setTitle(getString(R.string.app_name))
                                        .setMessage("Error")
                                        .setIcon(R.mipmap.ic_launcher)
                                        .setCancelable(false)

                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.dismiss();
                                            }
                                        });
                                dialogo.show();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            mProgressDialog.dismiss();

                        }
                    });

        }
    };


    private void backToLogIn() {
        finish();
    }
}
