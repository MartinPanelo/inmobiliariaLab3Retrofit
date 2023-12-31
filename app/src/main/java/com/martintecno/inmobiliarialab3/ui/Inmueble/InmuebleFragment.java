package com.martintecno.inmobiliarialab3.ui.Inmueble;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.martintecno.inmobiliarialab3.R;
import com.martintecno.inmobiliarialab3.databinding.FragmentInmuebleBinding;
import com.martintecno.inmobiliarialab3.modelo.Inmueble;

import java.util.List;

public class InmuebleFragment extends Fragment {

    FragmentInmuebleBinding binding;

    InmuebleViewModel InmuebleViewModel;
    RecyclerView rv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentInmuebleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        rv = binding.RVInmueble;

        InmuebleViewModel = new ViewModelProvider(this).get(InmuebleViewModel.class);


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).
                        navigate(R.id.agregarInmuebleFragment);


            }
        });


        InmuebleViewModel.getListaM().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> listaInmueble) {

                GridLayoutManager glm=new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
                rv.setLayoutManager(glm);

                InmuebleAdapter inmuebleAdapter=new InmuebleAdapter(listaInmueble,getContext(),getLayoutInflater()/*,getActivity()*/);
                rv.setAdapter(inmuebleAdapter);
            }
        });
        InmuebleViewModel.cargarLista();



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}