package com.benjizaid.myapp;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.benjizaid.myapp.fragments.BarberiasFragment;
import com.benjizaid.myapp.fragments.BarberosFragment;
import com.benjizaid.myapp.interfaces.OnBarberosListener;
import com.benjizaid.myapp.interfaces.OnTabListener;
import com.benjizaid.myapp.model.BarberiaEntity;
import com.benjizaid.myapp.model.BarberosEntity;

public class NavigationActivity extends AppCompatActivity implements OnBarberosListener {
    private FragmentTransaction fragmentTransaction;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        bottomNavigationView= findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment= null;
                int tab=0;
                switch (item.getItemId()){
                    case R.id.actionBarberos:
                        tab=0;
                        fragment = BarberosFragment.newInstance(null, null);
                        break;
                    case R.id.actionBarberias:
                        fragment = BarberiasFragment.newInstance(null,null);
                        tab=1;
                        break;

                }

                if(fragment!=null){
                    changeFragment(fragment);
                }
                return true;
            }
        });

        changeFragment(BarberosFragment.newInstance(null,null));
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
}
