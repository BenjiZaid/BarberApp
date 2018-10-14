package com.benjizaid.myapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.benjizaid.myapp.fragments.BarberiasFragment;
import com.benjizaid.myapp.fragments.BarberosFragment;
import com.benjizaid.myapp.interfaces.OnBarberosListener;
import com.benjizaid.myapp.model.BarberiaEntity;
import com.benjizaid.myapp.model.BarberosEntity;

public class NavigationActivity extends AppCompatActivity implements OnBarberosListener {
    private FragmentTransaction fragmentTransaction;
    private BottomNavigationView bottomNavigationView;

    private int IdUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle b = getIntent().getExtras();
        if(b != null)
            IdUsuario = b.getInt("id");


        bottomNavigationView= findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment= null;
                int tab=0;
                switch (item.getItemId()){
                    case R.id.actionBarberos:
                        tab=0;
                        fragment = BarberosFragment.newInstance(IdUsuario);
                        break;
                    case R.id.actionBarberias:
                        fragment = BarberiasFragment.newInstance(IdUsuario);
                        tab=1;
                        break;

                }

                if(fragment!=null){
                    changeFragment(fragment);
                }
                return true;
            }
        });

        changeFragment(BarberosFragment.newInstance(IdUsuario));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void changeFragment(Fragment fragment){
        fragmentTransaction= getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment,null);
        fragmentTransaction.commit();
    }

    @Override
    public void selectedItemBarberos(BarberosEntity barberosEntity) {
        //contactDetailFragment.renderContact(barberosEntity);
    }

    @Override
    public void renderFirstBarberos(BarberosEntity barberosEntity) {
        selectedItemBarberos(barberosEntity);
    }

    @Override
    public void selectedItemBarberia(BarberiaEntity barberiaEntity) {
        //contactDetailFragment.renderContact(barberosEntity);
    }

    @Override
    public void renderFirstBarberia(BarberiaEntity barberiaEntity) {
        selectedItemBarberia(barberiaEntity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout) {
            SharedPreferences sharedPreferences  = getApplicationContext().getSharedPreferences(getString(R.string.keypreference), MODE_PRIVATE);
            sharedPreferences.edit().clear().commit();

            Intent intent = new Intent(NavigationActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
