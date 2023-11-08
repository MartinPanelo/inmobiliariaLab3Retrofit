package com.martintecno.inmobiliarialab3.ui.perfil;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.SupportMapFragment;
import com.martintecno.inmobiliarialab3.API.ApiClientRetroFit;
import com.martintecno.inmobiliarialab3.R;
import com.martintecno.inmobiliarialab3.databinding.FragmentHomeBinding;
import com.martintecno.inmobiliarialab3.databinding.FragmentPerfilBinding;
import com.martintecno.inmobiliarialab3.modelo.Propietario;
import com.martintecno.inmobiliarialab3.ui.home.HomeViewModel;

public class PerfilFragment extends Fragment {

    FragmentPerfilBinding binding;
    PerfilViewModel perfilViewModel;

    private Propietario PActual = null;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);

        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        perfilViewModel.getpropietarioM().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                PActual = propietario;
                binding.ETPerfilNroPropietario.setText(String.valueOf(propietario.getIdPropietario()));
                binding.ETPerfinDni.setText(propietario.getDni());
                binding.ETPerfilNombre.setText(propietario.getNombre());
                binding.ETPerfilApellido.setText(propietario.getApellido());
                binding.ETPerfilEmail.setText(propietario.getMail());
                binding.ETPerfilPass.setHint("*******");
                binding.ETPerfilTelefono.setText(propietario.getTelefono());



                Glide.with(requireContext())
                        .load(ApiClientRetroFit.URLBASE + propietario.getAvatar())
                        .into(binding.IVPerfilFoto);
            }
        });


        perfilViewModel.getModoEdicionM().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.BTNPerfilEditar.setText("GUARDAR PERFIL");
                binding.ETPerfinDni.setEnabled(aBoolean);
                binding.ETPerfilNombre.setEnabled(aBoolean);
                binding.ETPerfilApellido.setEnabled(aBoolean);
                //    binding.ETPerfilEmail.setEnabled(aBoolean);
                binding.ETPerfilTelefono.setEnabled(aBoolean);
                binding.ETPerfilPass.setEnabled(aBoolean);
                binding.TVPerfilPass.setText("Contraseña - Ingrese una contraseña si quiere cambiar la actual");
                binding.ETPerfilPass.setHint("");
            }
        });

        perfilViewModel.getModoGuardarM().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {


                binding.BTNPerfilEditar.setText("EDITAR PERFIL");
                binding.ETPerfinDni.setEnabled(aBoolean);
                binding.ETPerfilNombre.setEnabled(aBoolean);
                binding.ETPerfilApellido.setEnabled(aBoolean);
           //     binding.ETPerfilEmail.setEnabled(aBoolean);
                binding.ETPerfilTelefono.setEnabled(aBoolean);
                binding.ETPerfilPass.setEnabled(aBoolean);
                binding.TVPerfilPass.setText("Contraseña");
                binding.ETPerfilPass.setHint("*******");

                // Propietario propietario = new Propietario()
                perfilViewModel.actualizarPerfil(
                        binding.ETPerfilNroPropietario.getText().toString(),
                        binding.ETPerfinDni.getText().toString(),
                        binding.ETPerfilApellido.getText().toString(),
                        binding.ETPerfilNombre.getText().toString(),
                        binding.ETPerfilEmail.getText().toString(),
                        binding.ETPerfilPass.getText().toString(),
                        binding.ETPerfilTelefono.getText().toString(),
                   //     binding.ETPerfilPass.getText().toString(),
                        PActual.getAvatar()
                     );

            }
        });



        binding.BTNPerfilEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perfilViewModel.ModoModificarPerfil(binding.BTNPerfilEditar.getText().toString());
            }
        });

        perfilViewModel.cargarPropietario();



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}