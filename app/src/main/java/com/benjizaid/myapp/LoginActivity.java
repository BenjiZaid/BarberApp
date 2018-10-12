package com.benjizaid.myapp;

import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends BaseActivity {

    private View btnNext, framengLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ui();
    }

    private void ui(){
        btnNext = findViewById(R.id.btnNext);
        framengLayout = findViewById(R.id.framengLayout);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoMain();
            }
        });

        framengLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoSignUp();
            }
        });

    }

    private void gotoMain() {
        next(NavigationActivity.class,null,true);
    }

    private void gotoSignUp(){

        Intent intent= new Intent(this,CreateUserActivity.class);
        startActivity(intent);

    }
}
