package com.martintecno.inmobiliarialab3.ui.login;

import static java.security.AccessController.getContext;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.google.gson.Gson;
import com.martintecno.inmobiliarialab3.API.ApiClientRetroFit;
import com.martintecno.inmobiliarialab3.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginactivityViewModel extends AndroidViewModel {

    private Context context;

    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 1000;

    public LoginactivityViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
    }

    public void IniciarSesion(String usuario, String contraseña) {

        ApiClientRetroFit.ApiInmobiliaria end = ApiClientRetroFit.getApiInmobiliaria();

        Call<String> llamada = end.login(usuario, contraseña);

        llamada.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {

                    String tokenParaGuardar="Bearer "+response.body();

                    ApiClientRetroFit.GuardarToken(context,tokenParaGuardar);

                    Intent intent = new Intent(context, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }else{

                    try {
                        String errorResponse = response.errorBody().string();
                        String errorMessage = "";

                        try {
                            JSONObject jsonObject = new JSONObject(errorResponse);
                            if (jsonObject.has("errors")) {
                                JSONObject errors = jsonObject.getJSONObject("errors");
                                if (errors.has("Clave")) {
                                    errorMessage = errors.getJSONArray("Clave").getString(0);
                                } else if (errors.has("Usuario")) {
                                    errorMessage = errors.getJSONArray("Usuario").getString(0);
                                }
                            }
                        } catch (JSONException e) {
                            // Si no se pudo analizar como JSON, el mensaje de error es la respuesta tal cual
                            errorMessage = errorResponse;
                        }

                        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Log.d("response fail", t.getMessage());
            }
        });
    }

    public void HacerLlamada(SensorEvent sensor) {

        if(sensor.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            float x = sensor.values[0];
            float y = sensor.values[1];
            float z = sensor.values[2];
            long curTime = System.currentTimeMillis();
            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;
                float speed = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 10000;
                if (speed > SHAKE_THRESHOLD) {

                    String TelefonoI = "26648743520000";

                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:"+TelefonoI));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
                last_x = x;
                last_y = y;
                last_z = z;
            }
        }

    }


}
