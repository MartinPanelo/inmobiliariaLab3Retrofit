package com.martintecno.inmobiliarialab3.ui.Inmueble.agregar;




import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.google.gson.Gson;
import com.martintecno.inmobiliarialab3.API.ApiClientRetroFit;
import com.martintecno.inmobiliarialab3.R;
import com.martintecno.inmobiliarialab3.modelo.Inmueble;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarInmuebleViewModel extends AndroidViewModel {

    private Context context;

    public AgregarInmuebleViewModel(@NonNull Application application) {
        super(application);
        context = application;
    }

    public void cargarInmueble(View view, String direccion, String uso, String tipo, String ambientes, String precio, ImageView imagen) {


        if (imagen.getDrawable() instanceof BitmapDrawable) {


            Bitmap bitmap = ((BitmapDrawable) imagen.getDrawable()).getBitmap();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), byteArrayOutputStream.toByteArray());
            // Creo una parte multipart con el RequestBody.
            MultipartBody.Part imagePart = MultipartBody.Part.createFormData("archivoFoto", "image.jpg", requestFile);

            RequestBody Direccion = RequestBody.create(MediaType.parse("application/json"), direccion);
            RequestBody Uso = RequestBody.create(MediaType.parse("application/json"), uso);
            RequestBody Tipo = RequestBody.create(MediaType.parse("application/json"), tipo);
            RequestBody Ambientes = RequestBody.create(MediaType.parse("application/json"), ambientes);
            RequestBody Precio = RequestBody.create(MediaType.parse("application/json"), precio);
            RequestBody Estado = RequestBody.create(MediaType.parse("application/json"), "false");

            String token = ApiClientRetroFit.LeerToken(context);

            ApiClientRetroFit.ApiInmobiliaria end = ApiClientRetroFit.getApiInmobiliaria();
            Call<Inmueble> llamada = end.AgregarInmueble(token,Direccion,Uso,Tipo,Ambientes,Precio,Estado,imagePart);

            llamada.enqueue(new Callback<Inmueble>() {
                @Override
                public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                    if (response.isSuccessful()) {

                        Toast.makeText(context, "Inmueble Agregado", Toast.LENGTH_LONG).show();
                        Navigation.findNavController(view).navigate(R.id.nav_inmueble);

                    } else {

                        try {
                            String errorResponse = response.errorBody().string();
                            String errorMessage = "";

                            try {
                                JSONObject jsonObject = new JSONObject(errorResponse);
                                if (jsonObject.has("errors")) {

                                    Toast.makeText(context, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
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

                public void onFailure(Call<Inmueble> call, Throwable t) {
                    Log.d("salida falla ",t.getMessage());
                }

            });


        }else{
            Toast.makeText(context, "La imagen es obligatoria", Toast.LENGTH_SHORT).show();
        }






    }

}