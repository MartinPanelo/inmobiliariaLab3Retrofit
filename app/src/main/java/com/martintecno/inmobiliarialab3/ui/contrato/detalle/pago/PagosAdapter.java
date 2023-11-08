package com.martintecno.inmobiliarialab3.ui.contrato.detalle.pago;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.martintecno.inmobiliarialab3.R;

import com.martintecno.inmobiliarialab3.modelo.Pago;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PagosAdapter extends RecyclerView.Adapter<PagosAdapter.ViewHolder> {

    private List<Pago> listaPagos;
    private Context context;

    private LayoutInflater li;


    public PagosAdapter(List<Pago> listaP, Context context, LayoutInflater li) {
        this.listaPagos = listaP;
        this.context = context;
        this.li = li;
    }

    @NonNull
    @Override
    public PagosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=li.inflate(R.layout.item_pago,parent,false);

        return new PagosAdapter.ViewHolder(view);
    }



    @Override
    public int getItemCount() {
        return listaPagos.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView CodigoPago;
        private TextView NroPago;
        private TextView CodigoContrato;
        private TextView Importe;
        private TextView FechaPago;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            CodigoPago = itemView.findViewById(R.id.TVCodigoPago);
            NroPago = itemView.findViewById(R.id.TVNroPago);
            CodigoContrato = itemView.findViewById(R.id.TVCodigoContrato);
            Importe = itemView.findViewById(R.id.TVInportePago);
            FechaPago = itemView.findViewById(R.id.TVFechaPago);

        }
    }
    @Override
    public void onBindViewHolder(@NonNull PagosAdapter.ViewHolder holder, int position) {

        holder.CodigoPago.setText(String.valueOf(listaPagos.get(position).getIdPago()));
        holder.NroPago.setText(String.valueOf(listaPagos.get(position).getNumero()));
        holder.CodigoContrato.setText(String.valueOf(listaPagos.get(position).getContrato().getIdContrato()));
        holder.Importe.setText(String.valueOf(listaPagos.get(position).getImporte()));

        LocalDate fechaPago = LocalDate.parse(listaPagos.get(position).getFechaDePago(), DateTimeFormatter.ISO_DATE_TIME);
        String SfechaPago = fechaPago.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        holder.FechaPago.setText(SfechaPago);

    }
}