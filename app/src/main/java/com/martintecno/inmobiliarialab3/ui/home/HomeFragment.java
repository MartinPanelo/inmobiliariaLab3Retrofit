package com.martintecno.inmobiliarialab3.ui.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.google.android.gms.maps.SupportMapFragment;
import com.martintecno.inmobiliarialab3.R;
import com.martintecno.inmobiliarialab3.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        homeViewModel.getMapa().observe(getActivity(), new Observer<HomeViewModel.MapaActual>() {
                    @Override
                    public void onChanged(HomeViewModel.MapaActual mapaActual) {
                        SupportMapFragment SMF = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

                        SMF.getMapAsync(mapaActual);
                    }
                });
        homeViewModel.ObtenerMapa();



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}