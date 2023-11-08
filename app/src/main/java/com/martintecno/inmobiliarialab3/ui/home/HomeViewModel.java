package com.martintecno.inmobiliarialab3.ui.home;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class HomeViewModel extends AndroidViewModel {

    private Context context;

    private MutableLiveData<MapaActual> MAMutable;

    private static final LatLng  DireccionInmobiliaria=new LatLng(-33.26818,-66.337465);

    public HomeViewModel(@NonNull Application application) {
        super(application);
        context = application;
    }


    public LiveData<MapaActual> getMapa(){
        if(MAMutable == null){
            MAMutable = new MutableLiveData<>();
        }
        return MAMutable;
    }

    public void ObtenerMapa(){
        MapaActual MA = new MapaActual();
        MAMutable.setValue(MA);
    }


    public class MapaActual implements OnMapReadyCallback {

        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            googleMap.addMarker(new MarkerOptions().position(DireccionInmobiliaria).title("Inmobiliaria El hormero"));


            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(DireccionInmobiliaria)
                    .zoom(19)
                    .bearing(45)
                    .tilt(70)
                    .build();

            CameraUpdate Cupdate = CameraUpdateFactory.newCameraPosition(cameraPosition);

            googleMap.animateCamera(Cupdate);
        }
    }













}