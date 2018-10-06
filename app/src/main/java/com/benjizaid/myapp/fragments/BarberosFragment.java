package com.benjizaid.myapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.benjizaid.myapp.BarberosDetallesActivity;
import com.benjizaid.myapp.R;
import com.benjizaid.myapp.adapters.BarberoAdapter;
import com.benjizaid.myapp.interfaces.OnBarberosListener;
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
public class BarberosFragment extends Fragment {
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


    private RecyclerView recyclerView;

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

        BarberoAdapter barberosAdapter = new BarberoAdapter(data, getActivity());
        lstBarberos.setAdapter(barberosAdapter);

        if(mListener!=null)mListener.renderFirstBarberos(first());
    }

    private void getBarberos() {
        //1. DATA
        BarberosEntity barberosEntity= new BarberosEntity();
        barberosEntity.setId(1);
        barberosEntity.setName("Pedro Palotes");
        barberosEntity.setEmail("pedro@gmail.com");
        barberosEntity.setTelefono("92835056");
        barberosEntity.setDescripcion("XXXXXX");

        BarberosEntity barberosEntity1= new BarberosEntity();
        barberosEntity1.setId(2);
        barberosEntity1.setName("Carlos Palotes");
        barberosEntity1.setEmail("carlos@gmail.com");
        barberosEntity1.setTelefono("96859685");
        barberosEntity1.setDescripcion("AAAAAAAAA");

        data = new ArrayList<>();
        data.add(barberosEntity);
        data.add(barberosEntity1);
    }

    private void ui(View view){
        lstBarberos = (ListView) view.findViewById(R.id.lstBarberos);

        //events
        lstBarberos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(mListener!=null && data!=null){
                    BarberosEntity barberosEntity = (BarberosEntity) adapterView.getAdapter().getItem(i);
                    goToDetallesBarberos(barberosEntity);
                    //mListener.selectedItemBarberos(barberosEntity);
                    //mListener.goToDetallesBarberos(barberosEntity);
                }
            }
        });
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
}