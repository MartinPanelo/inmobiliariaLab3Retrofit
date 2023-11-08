package com.martintecno.inmobiliarialab3.ui.contrato.detalle;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.martintecno.inmobiliarialab3.API.ApiClientRetroFit;
import com.martintecno.inmobiliarialab3.R;
import com.martintecno.inmobiliarialab3.modelo.Contrato;
import com.martintecno.inmobiliarialab3.modelo.Inmueble;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleContratoViewModel extends AndroidViewModel {


    private Context context;

    private MutableLiveData<Contrato> itemM;
    public DetalleContratoViewModel(@NonNull Application application) {
        super(application);
        context = application;
    }


    public LiveData<Contrato> getItemM(){
        if(itemM==null){

            itemM=new MutableLiveData<>();
        }
        return itemM;

    }


    public void CargarItem(Bundle bundle) {

        Inmueble item = (Inmueble) bundle.getSerializable("contrato");


        String token = ApiClientRetroFit.LeerToken(context);

        ApiClientRetroFit.ApiInmobiliaria end = ApiClientRetroFit.getApiInmobiliaria();
        Call<Contrato> llamada = end.ObtenerContratoPorInmueble(token, item.getIdInmueble());

        llamada.enqueue(new Callback<Contrato>() {
            @Override
            public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                if(response.isSuccessful()){

                    Log.d("salida respuesta ",response.body().toString());

                    // no estan funcionando las fechas

                    itemM.postValue(response.body());

                }else {

                    Log.d("salida respuesta ",response.body().toString());
                }
            }

            public void onFailure(Call<Contrato> call, Throwable t) {
                Log.d("salida falla ",t.getMessage());
            }

        });

    }


    public void verPagos(View view) {



        Bundle bundle = new Bundle();

        Contrato contrato = itemM.getValue();

        bundle.putSerializable("contratoPago", contrato);


        Navigation.findNavController(view).
                navigate(R.id.listaPagosFragment,bundle);

    }
}