package com.martintecno.inmobiliarialab3.ui.contrato.detalle;

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

import com.martintecno.inmobiliarialab3.R;
import com.martintecno.inmobiliarialab3.databinding.FragmentContratoBinding;
import com.martintecno.inmobiliarialab3.databinding.FragmentDetalleContratoBinding;
import com.martintecno.inmobiliarialab3.modelo.Contrato;
import com.martintecno.inmobiliarialab3.modelo.Inquilino;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DetalleContratoFragment extends Fragment {

    private DetalleContratoViewModel mViewModel;

    private FragmentDetalleContratoBinding binding;

    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentDetalleContratoBinding.inflate(inflater,container,false);
        View root = binding.getRoot();

        mViewModel = new ViewModelProvider(this).get(DetalleContratoViewModel.class);

        mViewModel.getItemM().observe(getActivity(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato contrato) {


                binding.TVNroContratoContrato.setText(String.valueOf(contrato.getIdContrato()));



             //   binding.TVFechaInicioContrato.setText(contrato.getFechaInicio());

                LocalDate dateInicio = LocalDate.parse(contrato.getFechaInicio(), DateTimeFormatter.ISO_DATE_TIME);
                String SdateInicio = dateInicio.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                binding.TVFechaInicioContrato.setText(SdateInicio);


                LocalDate dateFin = LocalDate.parse(contrato.getFechaFin(), DateTimeFormatter.ISO_DATE_TIME);
                String SdateFin = dateFin.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                binding.TVFechaFinalizacionContrato.setText(SdateFin);





                binding.TVMontoContrato.setText(String.valueOf(contrato.getMontoAlquiler()));
                binding.TVInquilinoContrato.setText(contrato.getInquilino().getNombre()+ "  "+contrato.getInquilino().getApellido() );
                binding.TVInmuebleContrato.setText(String.valueOf(contrato.getInmueble().getDireccion()));

            }
        });

        binding.BTNPagosContrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // desde aca me voy a la vista que tiene una lista de pagos
                mViewModel.verPagos(view);
            }
        });


        Bundle bundle = getArguments();

        if(bundle != null) {
            mViewModel.CargarItem(bundle);
        }





        return root;
    }

}