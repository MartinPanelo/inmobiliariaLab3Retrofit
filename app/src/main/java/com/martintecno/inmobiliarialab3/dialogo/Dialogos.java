package com.martintecno.inmobiliarialab3.dialogo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Handler;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.martintecno.inmobiliarialab3.modelo.Inmueble;

import java.time.LocalTime;


public class Dialogos {

    public static void DialogoSalir(Activity activity){

        new AlertDialog.Builder(activity)
                .setTitle("Salir de la aplicacion")
                .setMessage("Esta seguro que quiere salir?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                     //   activity.finish();
                        SharedPreferences sp = activity.getSharedPreferences("token.xml", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();

                        editor.putString("token", "");
                        editor.commit();
                        activity.finishAndRemoveTask();
                        activity.finishAffinity();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(activity,"Continua la aplicacion", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }


/*

    public static void DialogoBorrarInmueble(Activity activity, Inmueble inmueble){

        new AlertDialog.Builder(activity)
                .setTitle("Borrar inmueble")
                .setMessage("Esta seguro que desea borrar el inmueble?")
                .setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(activity, inmueble.getIdInmueble() + "", Toast.LENGTH_LONG).show();

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(activity,"Operacion cancelada", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }*/



}
