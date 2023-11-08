package com.martintecno.inmobiliarialab3.ui.Inmueble;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.martintecno.inmobiliarialab3.API.ApiClientRetroFit;
import com.martintecno.inmobiliarialab3.R;
import com.martintecno.inmobiliarialab3.dialogo.Dialogos;
import com.martintecno.inmobiliarialab3.modelo.Inmueble;

import java.util.List;

public class InmuebleAdapter extends RecyclerView.Adapter<InmuebleAdapter.ViewHolder> {

    private List<Inmueble> listaInmuebles;
    private Context context;

    private LayoutInflater li;

 /*   private Activity activity;*/


    public InmuebleAdapter(List<Inmueble> listaI, Context context, LayoutInflater li/*, Activity activity*/) {
        this.listaInmuebles = listaI;
        this.context = context;
        this.li = li;
    /*    this.activity = activity;*/
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=li.inflate(R.layout.item_inmueble,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.descripcion.setText(listaInmuebles.get(position).getDireccion() + "\n $ "+
                                    listaInmuebles.get(position).getPrecio());

        Glide.with(context)
                .load(ApiClientRetroFit.URLBASE + listaInmuebles.get(position).getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .override(210,238)
                .into(holder.imagen);

    }


    @Override
    public int getItemCount() {
        return listaInmuebles.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imagen;
        private TextView descripcion;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            descripcion = itemView.findViewById(R.id.TVDescripcionInmueble);
            imagen = itemView.findViewById(R.id.IMGInmuebleInmueble);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int posicion = getAdapterPosition();
                    Inmueble inmueble = listaInmuebles.get(posicion);

                    Bundle bundle = new Bundle();

                    bundle.putSerializable("iteminmueble", inmueble);

                    Navigation.findNavController(view).
                            navigate(R.id.detalleInmuebleFragment, bundle);
                }
            });

        }
    }
}