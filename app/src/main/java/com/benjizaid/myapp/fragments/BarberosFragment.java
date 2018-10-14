package com.benjizaid.myapp.fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.benjizaid.myapp.BarberiaDetalleActivity;
import com.benjizaid.myapp.BarberosDetallesActivity;
import com.benjizaid.myapp.R;
import com.benjizaid.myapp.adapters.BarberiaAdapter;
import com.benjizaid.myapp.adapters.BarberoAdapter;
import com.benjizaid.myapp.app.WebService;
import com.benjizaid.myapp.interfaces.OnBarberosListener;
import com.benjizaid.myapp.model.BarberiaEntity;
import com.benjizaid.myapp.model.BarberosEntity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {} interface
 * to handle interaction events.
 * Use the {@link BarberosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BarberosFragment extends Fragment implements BarberoAdapter.AdapterCallBackBarbero {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private int IdUsuario;

    //private OnTabListener mListener;
    private OnBarberosListener mListener;
    private ListView lstBarberos;

    private List<BarberosEntity> listaBarberos;
    private BarberoAdapter barberoAdapter;

    private ProgressBar progressBar;


    private RecyclerView recyclerViewBarbero;
    private RecyclerView.LayoutManager mLayoutManager;

    private static final int MAKE_CALL_PERMISSION_REQUEST_CODE = 1;

    public BarberosFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static BarberosFragment newInstance(int id) {
        BarberosFragment fragment = new BarberosFragment();
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
        progressBar.setVisibility(View.GONE);
        listaBarberos = new ArrayList<>();
        barberoAdapter = new BarberoAdapter(this.listaBarberos, getActivity(), this);
        recyclerViewBarbero.setAdapter(barberoAdapter);

        getBarberos();

        /*
        BarberoAdapter barberosAdapter = new BarberoAdapter(data, getActivity(), this);
        lstBarberos.setAdapter(barberosAdapter);

        if(mListener!=null)mListener.renderFirstBarberos(first());
        */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_barberos, container, false);
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
            //mListener = (OnFragmentInteractionListener) context;
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


    private void getBarberos() {


        AndroidNetworking.get(WebService.ListarBarberos(IdUsuario))
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        try {


                            BarberosEntity item;
                            for (int i = 0; i < jsonArray.length(); i++) {
                                item = new BarberosEntity();
                                item.setIDBarberia(jsonArray.getJSONObject(i).getInt("IDBarberia"))
                                        .setbActivo(jsonArray.getJSONObject(i).getInt("bActivo"))
                                        .setId(jsonArray.getJSONObject(i).getInt("id"))
                                        .setvDescripcion(jsonArray.getJSONObject(i).getString("vDescripcion"))
                                        .setvEmail(jsonArray.getJSONObject(i).getString("vEmail"))
                                        .setvFoto(jsonArray.getJSONObject(i).getString("vFoto"))
                                        .setvName(jsonArray.getJSONObject(i).getString("vName"))
                                        .setvTelefono(jsonArray.getJSONObject(i).getString("vTelefono"));
                                listaBarberos.add(item);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        progressBar.setVisibility(View.GONE);
                        barberoAdapter.setBarberosEntities(listaBarberos);
                        barberoAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    private void ui(View view) {
        recyclerViewBarbero = (RecyclerView) view.findViewById(R.id.lstBarberos);
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewBarbero.setLayoutManager(mLayoutManager);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);


    }

    private void goToDetallesBarberos(BarberosEntity barberosEntity) {
        Intent intent = new Intent(getActivity(), BarberosDetallesActivity.class);
        intent.putExtra("BARBEROS", barberosEntity);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(intent);
        }
    }

    private BarberosEntity first() {
        if (listaBarberos != null) {
            return listaBarberos.get(0);
        }
        return null;
    }

    private boolean checkPermission(String permission) {
        return ContextCompat.checkSelfPermission(getActivity(), permission) == PackageManager.PERMISSION_GRANTED;
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
    public void onClickCallback(BarberosEntity item) {
        if (checkPermission(Manifest.permission.CALL_PHONE)) {
            String dial = "tel:" + item.getvTelefono();
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        } else {
            Toast.makeText(getActivity(), "Permission Call Phone denied", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClickNameBarbero(BarberosEntity item) {
        if (listaBarberos != null) {
            Intent intent = new Intent(getActivity(), BarberosDetallesActivity.class);
            intent.putExtra("BARBEROS", item);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                startActivity(intent);
            }
        }
    }

    @Override
    public void onClickFavorito(BarberosEntity item, final int position) {

        if (IdUsuario==0){
            AlertDialog.Builder dialogo = new AlertDialog.Builder(getActivity())
                    .setTitle(getString(R.string.app_name))
                    .setMessage("Debe logearse o Registrarse para grabar favoritos")
                    .setIcon(R.mipmap.ic_launcher)
                    .setCancelable(false)

                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
            dialogo.show();
            return;
        }
        String Url = "";

        if (item.getbActivo() == 1)
            Url = WebService.desactivarFavoritoBarbero(IdUsuario, item.getId());
        else
            Url = WebService.agregarFavoritoBarbero(IdUsuario, item.getId());


        AndroidNetworking.post(Url)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        barberoAdapter.changeFavorito(position);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getContext(),"Error en Servicio",Toast.LENGTH_SHORT).show();
                    }
                });
    }


}
