package com.martintecno.inmobiliarialab3.ui.inquilino.detalle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.martintecno.inmobiliarialab3.databinding.FragmentDetalleInquilinoBinding;
import com.martintecno.inmobiliarialab3.modelo.Inquilino;

public class DetalleInquilinoFragment extends Fragment {

    private DetalleInquilinoViewModel mViewModel;

    private FragmentDetalleInquilinoBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentDetalleInquilinoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mViewModel = new ViewModelProvider(this).get(DetalleInquilinoViewModel.class);


        mViewModel.getItemM().observe(getActivity(), new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino inquilino) {


                binding.TVNroInquilinoDetalle.setText(String.valueOf(inquilino.getIdInquilino()));
                binding.TVNombreInquilinoDetalle.setText(inquilino.getNombre());
                binding.TVApellidoInquilinoDetalle.setText(inquilino.getApellido());
                binding.TVDniInquilinoDetalle.setText(inquilino.getDni());
                binding.TVEmailInquilinoDetalle.setText(inquilino.getEmail());
                binding.TVTelefonoInquilinoDetalle.setText(String.valueOf(inquilino.getTelefono()));
                binding.TVGaranteInquilinoDetalle.setText(inquilino.getNombreGarante());
                binding.TVTelefonoGaranteeInquilinoDetalle.setText(String.valueOf(inquilino.getTelefonoGarante()));


            }
        });


        Bundle bundle = getArguments();
    //    if(bundle.getSize("itemalquilado") != null) {
        if(bundle != null) {
            mViewModel.CargarItem(bundle);
        }






        return root;



    }

}