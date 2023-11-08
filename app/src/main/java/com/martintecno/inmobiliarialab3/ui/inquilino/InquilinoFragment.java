package com.martintecno.inmobiliarialab3.ui.inquilino;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.martintecno.inmobiliarialab3.databinding.FragmentInquilinoBinding;
import com.martintecno.inmobiliarialab3.modelo.Inmueble;


import java.util.List;

public class InquilinoFragment extends Fragment {

    FragmentInquilinoBinding binding;
    InquilinoViewModel inquilinoViewModel;
    RecyclerView rv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentInquilinoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        inquilinoViewModel = new ViewModelProvider(this).get(InquilinoViewModel.class);


        inquilinoViewModel.getListaM().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> listaInmueble) {
                rv = binding.RVInquilino;
                GridLayoutManager glm=new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
                rv.setLayoutManager(glm);

                InquilinoAdapter inquilinoAdapter =new InquilinoAdapter(listaInmueble,getActivity(),getLayoutInflater());
                rv.setAdapter(inquilinoAdapter);
            }
        });


        inquilinoViewModel.cargarLista();



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}