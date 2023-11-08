package com.martintecno.inmobiliarialab3.ui.inquilino;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.martintecno.inmobiliarialab3.API.ApiClientRetroFit;
import com.martintecno.inmobiliarialab3.R;
import com.martintecno.inmobiliarialab3.modelo.Inmueble;

import java.util.List;


public class InquilinoAdapter extends RecyclerView.Adapter<InquilinoAdapter.ViewHolder> {

private List<Inmueble> listaInmuebles;
private Context context;

private LayoutInflater li;


public InquilinoAdapter(List<Inmueble> listaI, Context context, LayoutInflater li) {
        this.listaInmuebles = listaI;
        this.context = context;
        this.li = li;
        }

@NonNull
@Override
public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=li.inflate(R.layout.item_generico,parent,false);

        return new ViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

      holder.direccion.setText(listaInmuebles.get(position).getDireccion() );

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
    private TextView direccion;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        direccion = itemView.findViewById(R.id.TVDescripcionGenerico);
        imagen = itemView.findViewById(R.id.IMGInmuebleGenerico);


        itemView.findViewById(R.id.BTNDetalleGenerico).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int posicion = getAdapterPosition();
                Inmueble inmueble = listaInmuebles.get(posicion);



                            Bundle bundle = new Bundle();

                            bundle.putSerializable("itemalquilado",inmueble);

                            Navigation.findNavController(view).
                                    navigate(R.id.detalleInquilinoFragment,bundle);



            }
        });





    }
}
}