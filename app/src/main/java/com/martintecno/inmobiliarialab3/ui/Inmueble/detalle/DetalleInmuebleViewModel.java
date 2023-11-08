package com.martintecno.inmobiliarialab3.ui.Inmueble.detalle;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.martintecno.inmobiliarialab3.API.ApiClientRetroFit;
import com.martintecno.inmobiliarialab3.modelo.Inmueble;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleInmuebleViewModel extends AndroidViewModel {

    private Context context;

    private Inmueble item;


    private MutableLiveData<Inmueble> itemM;
    public DetalleInmuebleViewModel(@NonNull Application application) {
        super(application);
        context = application;
    }

    public LiveData<Inmueble> getItemM(){
        if(itemM==null){

            itemM=new MutableLiveData<>();
        }
        return itemM;

    }



    public void CargarItem(Bundle bundle) {

        item = (Inmueble) bundle.getSerializable("iteminmueble");

        itemM.setValue(item);

    }

    public void setEstado(boolean checked) {

        item.setEstado(checked);



        String token = ApiClientRetroFit.LeerToken(context);

        ApiClientRetroFit.ApiInmobiliaria end = ApiClientRetroFit.getApiInmobiliaria();
        Call<Inmueble> llamada = end.ActualizarInmueble(token, item);

        llamada.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if(response.isSuccessful()){

                 //   item.setEstado(checked);
                    itemM.postValue(response.body());
                    Toast.makeText(context, "Actualizado", Toast.LENGTH_SHORT).show();
                }else{
                    item.setEstado(!checked);
                    itemM.postValue(item);
                    Toast.makeText(context, "Error al modificar el inmueble", Toast.LENGTH_SHORT).show();

                    Log.d("salida inmueble", response.toString());

                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Toast.makeText(context, "ERROR DE ACTUALIZACION", Toast.LENGTH_SHORT).show();


            }
        });




    }
}