package com.martintecno.inmobiliarialab3.ui.inquilino.detalle;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.martintecno.inmobiliarialab3.API.ApiClientRetroFit;
import com.martintecno.inmobiliarialab3.modelo.Inmueble;
import com.martintecno.inmobiliarialab3.modelo.Inquilino;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleInquilinoViewModel extends AndroidViewModel {

    private Context context;

    private MutableLiveData<Inquilino> itemM;
    public DetalleInquilinoViewModel(@NonNull Application application) {
        super(application);
        context = application;
    }


    public LiveData<Inquilino> getItemM(){
        if(itemM==null){

            itemM=new MutableLiveData<>();
        }
        return itemM;

    }


    public void CargarItem(Bundle bundle) {


        Inmueble item = (Inmueble) bundle.getSerializable("itemalquilado");

        String token = ApiClientRetroFit.LeerToken(context);

        ApiClientRetroFit.ApiInmobiliaria end = ApiClientRetroFit.getApiInmobiliaria();
        Call<Inquilino> llamada = end.ObtenerInquilinoPorInmueble(token, item.getIdInmueble());

        llamada.enqueue(new Callback<Inquilino>() {
            @Override
            public void onResponse(Call<Inquilino> call, Response<Inquilino> response) {
                if(response.isSuccessful()){

                    itemM.postValue(response.body());

                }else {

                    Log.d("salida respuesta ",response.body().toString());
                }
            }

            public void onFailure(Call<Inquilino> call, Throwable t) {
                Log.d("salida falla ",t.getMessage());
            }

        });



    }
















}