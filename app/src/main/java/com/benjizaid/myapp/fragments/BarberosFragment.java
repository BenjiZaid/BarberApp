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
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.benjizaid.myapp.BarberosDetallesActivity;
import com.benjizaid.myapp.R;
import com.benjizaid.myapp.adapters.BarberoAdapter;
import com.benjizaid.myapp.interfaces.OnBarberosListener;
import com.benjizaid.myapp.model.BarberiaEntity;
import com.benjizaid.myapp.model.BarberosEntity;

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
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //private OnTabListener mListener;
    private OnBarberosListener mListener;
    private ListView lstBarberos;
    private List<BarberosEntity> data;

    //private OnFragmentInteractionListener mListener;


    private RecyclerView recyclerViewBarbero;
    private RecyclerView.LayoutManager mLayoutManager;

    private static final int MAKE_CALL_PERMISSION_REQUEST_CODE = 1;

    public BarberosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BarberosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BarberosFragment newInstance(String param1, String param2) {
        BarberosFragment fragment = new BarberosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getBarberos();
        recyclerViewBarbero.setAdapter(new BarberoAdapter(this.data, getActivity(),this ));
        /*
        BarberoAdapter barberosAdapter = new BarberoAdapter(data, getActivity(), this);
        lstBarberos.setAdapter(barberosAdapter);

        if(mListener!=null)mListener.renderFirstBarberos(first());
        */
    }

    private void getBarberos() {
        //1. DATA
        BarberosEntity barberosEntity= new BarberosEntity();
        barberosEntity.setId(1);
        barberosEntity.setName("Pedro Palotes");
        barberosEntity.setEmail("pedro@gmail.com");
        barberosEntity.setTelefono("92835056");
        barberosEntity.setDescripcion("XXXXXX");
        barberosEntity.setFoto("mipmap-hdpi/ic_next-png");

        BarberosEntity barberosEntity1= new BarberosEntity();
        barberosEntity1.setId(2);
        barberosEntity1.setName("Carlos Palotes");
        barberosEntity1.setEmail("carlos@gmail.com");
        barberosEntity1.setTelefono("96859685");
        barberosEntity1.setDescripcion("AAAAAAAAA");
        barberosEntity1.setFoto("mipmap-hdpi/ic_next-png");

        data = new ArrayList<>();
        data.add(barberosEntity);
        data.add(barberosEntity1);
    }

    private void ui(View view){
        recyclerViewBarbero = (RecyclerView) view.findViewById(R.id.lstBarberos);
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewBarbero.setLayoutManager(mLayoutManager);

    }

    private void goToDetallesBarberos (BarberosEntity barberosEntity){
        Intent intent = new Intent(getActivity(), BarberosDetallesActivity.class);
        intent.putExtra("BARBEROS", barberosEntity);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(intent);
        }
    }

    private BarberosEntity first(){
        if(data!=null){
            return data.get(0);
        }
        return null;
    }

    private boolean checkPermission(String permission) {
        return ContextCompat.checkSelfPermission(getActivity(), permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case MAKE_CALL_PERMISSION_REQUEST_CODE :
                if (grantResults.length > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    Toast.makeText(getActivity(), "You can call the number by clicking on the button", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }

    @Override
    public void onClickCallback(BarberosEntity item) {
        if (checkPermission(Manifest.permission.CALL_PHONE)) {
            String dial = "tel:" + item.getTelefono();
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        } else {
            Toast.makeText(getActivity(), "Permission Call Phone denied", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClickNameBarbero(BarberosEntity item) {
        if(data!=null){
            Intent intent = new Intent(getActivity(), BarberosDetallesActivity.class);
            intent.putExtra("BARBEROS", item);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                startActivity(intent);
            }
        }
    }
}
