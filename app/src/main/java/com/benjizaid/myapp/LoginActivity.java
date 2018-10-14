package com.benjizaid.myapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.benjizaid.myapp.app.WebService;
import com.benjizaid.myapp.model.UsuarioEntity;

import org.json.JSONObject;

public class LoginActivity extends BaseActivity {

    private View btnNext, framengLayout;
    Button btnLogin;
    EditText txtUsuario, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences mPrefs = getSharedPreferences(getString(R.string.keypreference), MODE_PRIVATE); //add key
        if (mPrefs.getInt("USUARIO", 0) > 0) {
            Intent intent = new Intent(LoginActivity.this, NavigationActivity.class);
            intent.putExtra("id", mPrefs.getInt("USUARIO", 0));
            startActivity(intent);
            finish();
        }

        setContentView(R.layout.activity_login);
        ui();
    }

    private void ui() {
        btnNext = findViewById(R.id.btnNext);
        framengLayout = findViewById(R.id.framengLayout);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoMain();
            }
        });

        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        framengLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoSignUp();
            }
        });

        btnLogin.setOnClickListener(btnLoginOnClickListener);

    }


    View.OnClickListener btnLoginOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (txtUsuario.getText().toString().trim().length() == 0) {
                Toast.makeText(LoginActivity.this, "Debe ingresar un Usuario", Toast.LENGTH_SHORT).show();
                return;
            }
            if (txtPassword.getText().toString().trim().length() == 0) {
                Toast.makeText(LoginActivity.this, "Debe ingresar un Password", Toast.LENGTH_SHORT).show();
                return;
            }

            final ProgressDialog mProgressDialog = new ProgressDialog(LoginActivity.this);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setMessage("Conectando...");
            mProgressDialog.show();


            AndroidNetworking.get(WebService.login(txtUsuario.getText().toString(), txtPassword.getText().toString()))
                    .setPriority(Priority.LOW)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {

                            mProgressDialog.dismiss();

                            try {
                                UsuarioEntity item = new UsuarioEntity();
                                item.setId(jsonObject.getInt("id"))
                                        .setvNombres(jsonObject.getString("vNombres"))
                                        .setvApellidos(jsonObject.getString("vApellidos"));

                                Toast.makeText(LoginActivity.this, "Bienvenido " + item.getvNombres() + " " + item.getvApellidos(), Toast.LENGTH_SHORT).show();



                                SharedPreferences mPrefs = getSharedPreferences(getString(R.string.keypreference), MODE_PRIVATE); //add key
                                SharedPreferences.Editor editor = mPrefs.edit();
                                editor.putInt("USUARIO", item.getId());
                                editor.apply();


                                Intent intent = new Intent(LoginActivity.this, NavigationActivity.class);
                                intent.putExtra("id", item.getId());
                                startActivity(intent);
                                finish();
                            } catch (Exception ex) {
                                Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            mProgressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "Error en el servicio", Toast.LENGTH_SHORT).show();

                        }
                    });

        }
    };


    private void gotoMain() {
        next(NavigationActivity.class, null, true);
    }

    private void gotoSignUp() {

        Intent intent = new Intent(this, CreateUserActivity.class);
        startActivity(intent);

    }
}
