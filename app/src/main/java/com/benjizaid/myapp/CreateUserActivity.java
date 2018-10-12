package com.benjizaid.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CreateUserActivity extends AppCompatActivity {

    private ImageView iviBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        ui();
    }

    private void ui(){
        iviBack= findViewById(R.id.iviBack);

        iviBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToLogIn();
            }
        });
    }

    private void backToLogIn() {
        finish();
    }
}
