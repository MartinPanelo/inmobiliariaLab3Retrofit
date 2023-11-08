package com.martintecno.inmobiliarialab3.ui.contrato.detalle.pago;

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
import com.martintecno.inmobiliarialab3.databinding.FragmentInquilinoBinding;
import com.martintecno.inmobiliarialab3.databinding.FragmentListaPagosBinding;
import com.martintecno.inmobiliarialab3.modelo.Inmueble;
import com.martintecno.inmobiliarialab3.modelo.Pago;
import com.martintecno.inmobiliarialab3.ui.inquilino.InquilinoAdapter;
import com.martintecno.inmobiliarialab3.ui.inquilino.InquilinoViewModel;

import java.util.List;

public class ListaPagosFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        FragmentListaPagosBinding binding = FragmentListaPagosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ListaPagosViewModel listaPagosViewModel =
                new ViewModelProvider(this).get(ListaPagosViewModel.class);


        listaPagosViewModel.getListaM().observe(getActivity(), new Observer<List<Pago>>() {
            @Override
            public void onChanged(List<Pago> listaPago) {
                RecyclerView rv = binding.RVListaPago;
                GridLayoutManager glm=new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);
                rv.setLayoutManager(glm);

                PagosAdapter pagosAdapter =new PagosAdapter(listaPago,getActivity(),getLayoutInflater());
                rv.setAdapter(pagosAdapter);
            }
        });


        Bundle bundle = getArguments();

        if(bundle != null) {
            listaPagosViewModel.cargarLista(bundle);
        }






        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}