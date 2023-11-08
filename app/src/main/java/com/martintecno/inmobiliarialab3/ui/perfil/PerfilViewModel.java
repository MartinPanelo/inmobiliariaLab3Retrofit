package com.martintecno.inmobiliarialab3.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.martintecno.inmobiliarialab3.API.ApiClientRetroFit;
import com.martintecno.inmobiliarialab3.modelo.Propietario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {

    private MutableLiveData<Propietario> propietarioM;

    private MutableLiveData<Boolean> modoEdicionM;

    private MutableLiveData<Boolean> modoGuardarM;

    private Context context;



    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context= application;
    }

    public LiveData<Propietario> getpropietarioM() {
        if (propietarioM == null) {
            propietarioM = new MutableLiveData<>();
        }
        return propietarioM;
    }

    public LiveData<Boolean> getModoGuardarM() {
        if (modoGuardarM == null) {
            modoGuardarM = new MutableLiveData<>();
        }
        return modoGuardarM;
    }

    public LiveData<Boolean> getModoEdicionM() {
        if (modoEdicionM == null) {
            modoEdicionM = new MutableLiveData<>();
        }
        return modoEdicionM;
    }

    public void cargarPropietario() {


        String token = ApiClientRetroFit.LeerToken(context);

        ApiClientRetroFit.ApiInmobiliaria end = ApiClientRetroFit.getApiInmobiliaria();
        Call<Propietario> llamada = end.obtenerUsuarioActual(token);

        llamada.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()){

                    propietarioM.postValue(response.body());

                    Log.d("salida ",response.body().getApellido());

                }else {

                    Log.d("salida respuesta ",response.raw().message());
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Log.d("salida falla ",t.getMessage());

            }
        });

    }

    public void actualizarPerfil(String id,String dni, String apellido, String nombre, String email, String password, String telefono, String avatar) {
        {

            if (id == null || dni == null || apellido == null || nombre == null || email == null || telefono == null) {
                Toast.makeText(context, "No esta permitido dejar campos vacios", Toast.LENGTH_SHORT).show();
            } else {
                Propietario propietario = new Propietario(
                Integer.parseInt(id),
                dni,
                nombre,
                apellido,
                email,
                password,
                telefono,
                avatar
                );

                /*------------------------*/

                String token = ApiClientRetroFit.LeerToken(context);

                ApiClientRetroFit.ApiInmobiliaria end = ApiClientRetroFit.getApiInmobiliaria();
                Call<Propietario> llamada = end.ActualizarPropietario(token, propietario);

                llamada.enqueue(new Callback<Propietario>() {
                    @Override
                    public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                        if(response.isSuccessful()){
                            propietarioM.postValue(response.body());
                        }else{
                            Toast.makeText(context, "Error al modificar el propietario", Toast.LENGTH_SHORT).show();
                            Log.d("salida", response.raw().message());
                        }
                    }

                    @Override
                    public void onFailure(Call<Propietario> call, Throwable t) {
                        Toast.makeText(context, "ERROR DE ACTUALIZACION", Toast.LENGTH_SHORT).show();
                    }
                });

            }


        }

    }

    public void ModoModificarPerfil(String modo) {
        if (modo.equals("EDITAR PERFIL")) {
            modoEdicionM.setValue(true);
        } else {
            modoGuardarM.setValue(false);
        }
    }


}