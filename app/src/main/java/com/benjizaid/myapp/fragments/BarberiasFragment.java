package com.benjizaid.myapp.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.benjizaid.myapp.BarberiaDetalleActivity;
import com.benjizaid.myapp.BarberosDetallesActivity;
import com.benjizaid.myapp.NavigationActivity;
import com.benjizaid.myapp.R;
import com.benjizaid.myapp.adapters.BarberiaAdapter;
import com.benjizaid.myapp.app.WebService;
import com.benjizaid.myapp.eventos.RecyclerTouchListener;
import com.benjizaid.myapp.interfaces.ClickListener;
import com.benjizaid.myapp.interfaces.OnBarberiaListener;
import com.benjizaid.myapp.interfaces.OnBarberosListener;
import com.benjizaid.myapp.interfaces.OnTabListener;
import com.benjizaid.myapp.model.BarberiaEntity;
import com.benjizaid.myapp.model.BarberosEntity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BarberiasFragment extends Fragment implements BarberiaAdapter.AdapterCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    private int IdUsuario;

    //private OnFragmentInteractionListener mListener;

    //private OnTabListener mListener;
    private OnBarberosListener mListener;
    private ListView lstBarberia;
    private List<BarberiaEntity> listaBaberias;

    private RecyclerView recyclerViewBarberias;
    private BarberiaAdapter barberiaAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    ProgressBar progressBar;

    private static final int MAKE_CALL_PERMISSION_REQUEST_CODE = 1;

    public BarberiasFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static BarberiasFragment newInstance(int id) {
        BarberiasFragment fragment = new BarberiasFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            IdUsuario = getArguments().getInt(ARG_PARAM1);
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listaBaberias = new ArrayList<>();
        barberiaAdapter = new BarberiaAdapter(getActivity(), this.listaBaberias, this);
        recyclerViewBarberias.setAdapter(barberiaAdapter);
        getBarberias();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_barberias, container, false);
        ui(view);

        if (!checkPermission(Manifest.permission.CALL_PHONE)) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, MAKE_CALL_PERMISSION_REQUEST_CODE);
        }

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnBarberosListener) {
            mListener = (OnBarberosListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    private void ui(View view) {

        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        recyclerViewBarberias = (RecyclerView) view.findViewById(R.id.lstBarberias);
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewBarberias.setLayoutManager(mLayoutManager);

        progressBar.setVisibility(View.VISIBLE);
    }

    private void getBarberias() {

        AndroidNetworking.get(WebService.ListarBarberias(IdUsuario))
                //.setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {

                            //listaBaberias
                            BarberiaEntity item;
                            for (int i = 0; i < response.length(); i++) {
                                item = new BarberiaEntity();
                                item
                                        .setbActivo(response.getJSONObject(i).getInt("bActivo"))
                                        .setId(response.getJSONObject(i).getInt("id"))
                                        .setvDescripcion(response.getJSONObject(i).getString("vDescripcion"))
                                        .setvDireccion(response.getJSONObject(i).getString("vDireccion"))
                                        .setvEmail(response.getJSONObject(i).getString("vEmail"))
                                        .setvFoto(response.getJSONObject(i).getString("vFoto"))
                                        .setvLatitud(response.getJSONObject(i).getDouble("vLatitud"))
                                        .setvLongitud(response.getJSONObject(i).getDouble("vLongitud"))
                                        .setvName(response.getJSONObject(i).getString("vName"))
                                        .setvTelefono(response.getJSONObject(i).getString("vTelefono"));

                                listaBaberias.add(item);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        progressBar.setVisibility(View.GONE);

                        barberiaAdapter.setBarberiaEntities(listaBaberias);
                        barberiaAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(ANError error) {
                        progressBar.setVisibility(View.GONE);
                    }
                });

    }

    private void goToDetalleBarberia(BarberiaEntity barberiaEntity) {
        Intent intent = new Intent(getActivity(), BarberiaDetalleActivity.class);
        intent.putExtra("BARBERIA", barberiaEntity);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(intent);
        }
    }

    private BarberiaEntity first() {
        if (listaBaberias != null) {
            return listaBaberias.get(0);
        }
        return null;
    }

    private boolean checkPermission(String permission) {
        return ContextCompat.checkSelfPermission(getActivity(), permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onClickCallback(BarberiaEntity item) {
        if (checkPermission(Manifest.permission.CALL_PHONE)) {
            String dial = "tel:" + item.getvTelefono();
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        } else {
            Toast.makeText(getActivity(), "Permission Call Phone denied", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MAKE_CALL_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    Toast.makeText(getActivity(), "You can call the number by clicking on the button", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }

    @Override
    public void onClickNameBarberia(BarberiaEntity item) {
        if (listaBaberias != null) {
            Intent intent = new Intent(getActivity(), BarberiaDetalleActivity.class);
            intent.putExtra("BARBERIA", item);
            intent.putExtra("IdUsuario", IdUsuario);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                startActivity(intent);
            }
        }


    }

    @Override
    public void onClickFavorito(BarberiaEntity item, final int position) {
        String Url = "";

        if (item.getbActivo() == 1)
            Url = WebService.desactivarFavoritoBarberia(IdUsuario, item.getId());
        else
            Url = WebService.agregarFavoritoBarberia(IdUsuario, item.getId());


        AndroidNetworking.post(Url)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        barberiaAdapter.changeFavorito(position);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getContext(),"Error en Servicio",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
