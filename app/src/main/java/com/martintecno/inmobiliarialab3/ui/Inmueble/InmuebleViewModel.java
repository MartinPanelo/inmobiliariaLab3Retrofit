package com.martintecno.inmobiliarialab3.ui.Inmueble;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.martintecno.inmobiliarialab3.API.ApiClientRetroFit;
import com.martintecno.inmobiliarialab3.modelo.Inmueble;
import com.martintecno.inmobiliarialab3.modelo.Propietario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmuebleViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<List<Inmueble>> listaInmuebleM;


    public InmuebleViewModel(@NonNull Application application) {
        super(application);
        this.context=application.getApplicationContext();
    }


    public LiveData<List<Inmueble>> getListaM(){
        if(listaInmuebleM==null){

            listaInmuebleM=new MutableLiveData<>();
        }
        return listaInmuebleM;

    }

    public void cargarLista(){

        String token = ApiClientRetroFit.LeerToken(context);

        ApiClientRetroFit.ApiInmobiliaria end = ApiClientRetroFit.getApiInmobiliaria();
        Call<List<Inmueble>> llamada = end.ObtenerInmuebles(token);

        llamada.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if(response.isSuccessful()){


                    listaInmuebleM.postValue(response.body());

                }else {

                    Log.d("salida respuesta ",response.raw().message());
                }
            }

            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                Log.d("salida falla ",t.getMessage());
            }

        });





    }



}