package com.martintecno.inmobiliarialab3.ui.salir;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.martintecno.inmobiliarialab3.API.ApiClientRetroFit;
import com.martintecno.inmobiliarialab3.R;
import com.martintecno.inmobiliarialab3.databinding.FragmentSalirBinding;
import com.martintecno.inmobiliarialab3.dialogo.Dialogos;

import retrofit2.Call;

public class salirFragment extends Fragment {


    private FragmentSalirBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSalirBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.BTNSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialogos.DialogoSalir(getActivity());




            }
        });



        return root;


    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}