package com.martintecno.inmobiliarialab3.ui.inquilino;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.martintecno.inmobiliarialab3.API.ApiClientRetroFit;
import com.martintecno.inmobiliarialab3.modelo.Inmueble;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinoViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<List<Inmueble>> listaInmuebleAlquiladosM;


    public InquilinoViewModel(@NonNull Application application) {
        super(application);
        this.context=application.getApplicationContext();
    }


    public LiveData<List<Inmueble>> getListaM(){
        if(listaInmuebleAlquiladosM==null){

            listaInmuebleAlquiladosM=new MutableLiveData<>();
        }
        return listaInmuebleAlquiladosM;

    }

    public void cargarLista(){


        String token = ApiClientRetroFit.LeerToken(context);

        ApiClientRetroFit.ApiInmobiliaria end = ApiClientRetroFit.getApiInmobiliaria();
        Call<List<Inmueble>> llamada = end.ObtenerInmueblesAlquilados(token);

        llamada.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if(response.isSuccessful()){


                    listaInmuebleAlquiladosM.postValue(response.body());

                }else {

                    Log.d("salida respuesta ",response.body().toString());
                }
            }

            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                Log.d("salida falla ",t.getMessage());
            }

        });



    }






}