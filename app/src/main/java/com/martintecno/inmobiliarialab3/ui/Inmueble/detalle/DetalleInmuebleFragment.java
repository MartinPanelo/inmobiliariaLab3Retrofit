package com.martintecno.inmobiliarialab3.ui.Inmueble.detalle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.martintecno.inmobiliarialab3.API.ApiClientRetroFit;
import com.martintecno.inmobiliarialab3.R;
import com.martintecno.inmobiliarialab3.databinding.FragmentDetalleInmuebleBinding;
import com.martintecno.inmobiliarialab3.modelo.Inmueble;

public class DetalleInmuebleFragment extends Fragment {

    private FragmentDetalleInmuebleBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentDetalleInmuebleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        DetalleInmuebleViewModel vm = new ViewModelProvider(this).get(DetalleInmuebleViewModel.class);

        vm.getItemM().observe(getActivity(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inmueble) {


                    binding.TVNroPropietarioDetalle.setText(String.valueOf(inmueble.getIdInmueble()));
                    binding.TVAmbientesDetalle.setText(String.valueOf(inmueble.getAmbientes()));
                    binding.TVDireccionDetalle.setText(inmueble.getDireccion());
                    binding.TVPrecioDetalle.setText(String.valueOf(inmueble.getPrecio()));
                    binding.TVUsoDetalle.setText(inmueble.getUso());
                    binding.TVTipoDetalle.setText(inmueble.getTipo());
                    binding.CBDisponibleDetalle.setChecked(inmueble.isEstado());
                    Glide.with(getActivity())
                            .load(ApiClientRetroFit.URLBASE + inmueble.getImagen())
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .fitCenter()
                            .override(210,238)
                            .into(binding.IMGDetalleItem);


            }
        });

        binding.CBDisponibleDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.setEstado(binding.CBDisponibleDetalle.isChecked());
            }
        });


        Bundle bundle = getArguments();

        if(bundle != null) {
            vm.CargarItem(bundle);
        }





    return root;
    }


}