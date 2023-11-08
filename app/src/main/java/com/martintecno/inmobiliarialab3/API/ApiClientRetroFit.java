package com.martintecno.inmobiliarialab3.API;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.martintecno.inmobiliarialab3.modelo.Contrato;
import com.martintecno.inmobiliarialab3.modelo.Inmueble;
import com.martintecno.inmobiliarialab3.modelo.Inquilino;
import com.martintecno.inmobiliarialab3.modelo.Pago;
import com.martintecno.inmobiliarialab3.modelo.Propietario;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

public class ApiClientRetroFit {

    public static final String URLBASE = "http://192.168.0.5:5014/";

    private static ApiInmobiliaria apiInmobiliaria;

    public static ApiInmobiliaria getApiInmobiliaria(){


        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();



        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLBASE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build();

        apiInmobiliaria = retrofit.create(ApiInmobiliaria.class);

        return apiInmobiliaria;

    }

    public interface ApiInmobiliaria {


        /*------------Propietario-------------------*/
        @FormUrlEncoded
        @POST("Propietario/login")
        Call<String> login(@Field("Usuario") String Usuario, @Field("Clave") String Clave);


        @GET("Propietario/ObtenerPerfil")
        Call<Propietario> obtenerUsuarioActual(@Header("Authorization") String token);


        @PUT("Propietario/ActualizarPropietario")
        Call<Propietario> ActualizarPropietario(@Header("Authorization") String token, @Body Propietario propietario);


        /*--------------Inmueble-----------------*/

        @GET("Inmueble/ObtenerInmuebles")
        Call<List<Inmueble>> ObtenerInmuebles(@Header("Authorization") String token);

        @PUT("Inmueble/ActualizarInmueble")
        Call<Inmueble> ActualizarInmueble(@Header("Authorization") String token, @Body Inmueble inmueble);

        @GET("Inmueble/ObtenerAlquilados")
        Call<List<Inmueble>> ObtenerInmueblesAlquilados(@Header("Authorization") String token);
        @Multipart
        @POST("Inmueble/AgregarInmueble")
        Call<Inmueble> AgregarInmueble(@Header("Authorization") String token,
                                       @Part("Direccion") RequestBody Direccion,
                                       @Part("Uso") RequestBody Uso,
                                       @Part("Tipo") RequestBody Tipo,
                                       @Part("Ambientes") RequestBody Ambientes,
                                       @Part("Precio") RequestBody Precio,
                                       @Part("Estado") RequestBody Estado,
                                       @Part MultipartBody.Part imagen);


        /*-------------Inquilino------------------*/

        @GET("Inquilino/ObtenerInquilinoPorInmueble")
        Call<Inquilino> ObtenerInquilinoPorInmueble(@Header("Authorization") String token, @Query("idInmueble") int idInmueble);

        /*-------------Contrato------------------*/

        @GET("Contrato/ObtenerContratoPorInmueble")
        Call<Contrato> ObtenerContratoPorInmueble(@Header("Authorization") String token, @Query("idInmueble") int idInmueble);


        /*-------------Pago------------------*/

        @GET("Pago/ObtenerPagosPorContrato")
        Call<List<Pago>> ObtenerPagosPorContrato(@Header("Authorization") String token, @Query("idContrato") int idContrato);



    }


    public static void GuardarToken(Context context, String token){

        SharedPreferences sp = context.getSharedPreferences("token.xml", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("token", token);
        editor.commit();
    }


    public static String LeerToken(Context context){

        SharedPreferences sp = context.getSharedPreferences("token.xml", Context.MODE_PRIVATE);
        return sp.getString("token", "");

    }






}
