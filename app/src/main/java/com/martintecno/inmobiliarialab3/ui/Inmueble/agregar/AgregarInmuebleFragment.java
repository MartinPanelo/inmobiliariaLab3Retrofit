package com.martintecno.inmobiliarialab3.ui.Inmueble.agregar;

import static android.app.Activity.RESULT_OK;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.martintecno.inmobiliarialab3.R;
import com.martintecno.inmobiliarialab3.databinding.FragmentAgregarInmuebleBinding;
import com.martintecno.inmobiliarialab3.databinding.FragmentListaPagosBinding;
import com.martintecno.inmobiliarialab3.modelo.Inmueble;
import com.martintecno.inmobiliarialab3.modelo.Pago;
import com.martintecno.inmobiliarialab3.ui.contrato.detalle.pago.ListaPagosViewModel;
import com.martintecno.inmobiliarialab3.ui.contrato.detalle.pago.PagosAdapter;

import java.util.List;

public class AgregarInmuebleFragment extends Fragment {

    private FragmentAgregarInmuebleBinding binding;

    private AgregarInmuebleViewModel vm;

    private static final int PICK_IMAGE = 100;
    Uri imageUri;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentAgregarInmuebleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm = new ViewModelProvider(this).get(AgregarInmuebleViewModel.class);


        binding.BTNAgregarInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               vm.cargarInmueble(view,
                        binding.TVDireccionDetalleAgregar.getText().toString(),
                        binding.TVUsoDetalleAgregar.getText().toString(),
                        binding.TVTipoDetalleAgregar.getText().toString(),
                        binding.TVAmbientesDetalleAgregar.getText().toString(),
                        binding.TVPrecioDetalleAgregar.getText().toString(),
                        binding.IMGInmueble);


            }
        });




        binding.IMGInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery, PICK_IMAGE);


            }
        });





        return root;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            binding.IMGInmueble.setImageURI(imageUri);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}