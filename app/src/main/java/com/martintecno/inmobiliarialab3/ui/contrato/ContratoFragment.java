package com.martintecno.inmobiliarialab3.ui.contrato;

import androidx.fragment.app.FragmentContainerView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.martintecno.inmobiliarialab3.R;
import com.martintecno.inmobiliarialab3.databinding.FragmentContratoBinding;
import com.martintecno.inmobiliarialab3.modelo.Contrato;
import com.martintecno.inmobiliarialab3.modelo.Inmueble;
import com.martintecno.inmobiliarialab3.ui.inquilino.InquilinoAdapter;

import java.util.List;

public class ContratoFragment extends Fragment {

    RecyclerView rv;
    ContratoViewModel vm;
    FragmentContratoBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentContratoBinding.inflate(inflater,container,false);

        View root = binding.getRoot();

        vm = new ViewModelProvider(this).get(ContratoViewModel.class);

        vm.getListaM().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> listaInmueble) {
                rv = binding.RVContrato;
                GridLayoutManager glm=new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
                rv.setLayoutManager(glm);

                ContratoAdapter contratoAdapter =new ContratoAdapter(listaInmueble,getActivity(),getLayoutInflater());
                rv.setAdapter(contratoAdapter);
            }
        });

        vm.cargarLista();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}