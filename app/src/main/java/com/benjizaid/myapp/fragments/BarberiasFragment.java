package com.benjizaid.myapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.benjizaid.myapp.BarberiaDetalleActivity;
import com.benjizaid.myapp.BarberosDetallesActivity;
import com.benjizaid.myapp.NavigationActivity;
import com.benjizaid.myapp.R;
import com.benjizaid.myapp.adapters.BarberiaAdapter;
import com.benjizaid.myapp.eventos.RecyclerTouchListener;
import com.benjizaid.myapp.interfaces.ClickListener;
import com.benjizaid.myapp.interfaces.OnBarberiaListener;
import com.benjizaid.myapp.interfaces.OnBarberosListener;
import com.benjizaid.myapp.interfaces.OnTabListener;
import com.benjizaid.myapp.model.BarberiaEntity;
import com.benjizaid.myapp.model.BarberosEntity;

import java.util.ArrayList;
import java.util.List;

public class BarberiasFragment extends Fragment implements BarberiaAdapter.AdapterCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //private OnFragmentInteractionListener mListener;

    //private OnTabListener mListener;
    private OnBarberosListener mListener;
    private ListView lstBarberia;
    private List<BarberiaEntity> data;

    private RecyclerView recyclerViewBarberias;
    private RecyclerView.LayoutManager mLayoutManager;


    public BarberiasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BarberiasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BarberiasFragment newInstance(String param1, String param2) {
        BarberiasFragment fragment = new BarberiasFragment();
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
        View view = inflater.inflate(R.layout.fragment_barberias, container, false);
        ui(view);
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getBarberias();
        recyclerViewBarberias.setAdapter(new BarberiaAdapter(getActivity(), this.data, this ));
        //BarberiaAdapter barberiaAdapter = new BarberiaAdapter(data, getActivity());


        /*BarberiaAdapter barberiaAdapter = new BarberiaAdapter(data, getActivity());
        lstBarberia.setAdapter(barberiaAdapter);

        if(mListener!=null)mListener.renderFirstBarberia(first());*/
    }



    private void ui(View view){

        recyclerViewBarberias = (RecyclerView) view.findViewById(R.id.lstBarberias);
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewBarberias.setLayoutManager(mLayoutManager);
/*
        recyclerViewBarberias.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerViewBarberias, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                if(data!=null){
                    BarberiaEntity barberiaEntity = data.get(position);
                    goToDetalleBarberia(barberiaEntity);
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
*/

    }

    private void getBarberias() {
        //1. DATA
        BarberiaEntity barberiaEntity= new BarberiaEntity();
        barberiaEntity.setId(1);
        barberiaEntity.setName("Pedro Barberia");
        barberiaEntity.setEmail("pedro@gmail.com");
        barberiaEntity.setTelefono("92835056");
        barberiaEntity.setDireccion("AAAAAAAAAAAAAA");
        barberiaEntity.setFoto("mipmap-hdpi/ic_next-png");


        BarberiaEntity barberiaEntity1= new BarberiaEntity();
        barberiaEntity1.setId(2);
        barberiaEntity1.setName("Carlos Barberia2");
        barberiaEntity1.setEmail("carlos@gmail.com");
        barberiaEntity1.setTelefono("96859685");
        barberiaEntity1.setDireccion("AX22222222222222");
        barberiaEntity1.setFoto("mipmap-hdpi/ic_next-png");

        data = new ArrayList<>();
        data.add(barberiaEntity);
        data.add(barberiaEntity1);
    }

    private void goToDetalleBarberia(BarberiaEntity barberiaEntity){
        Intent intent = new Intent(getActivity(), BarberiaDetalleActivity.class);
        intent.putExtra("BARBERIA", barberiaEntity);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(intent);
        }

    }

    private BarberiaEntity first(){
        if(data!=null){
            return data.get(0);
        }
        return null;
    }


    @Override
    public void onClickCallback(BarberiaEntity item) {
        Toast.makeText(getActivity(), item.getName(), Toast.LENGTH_SHORT).show();

        Intent i = new Intent(getActivity(), BarberiaDetalleActivity.class);

    }
}
