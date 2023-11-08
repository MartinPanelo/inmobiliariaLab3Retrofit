package com.martintecno.inmobiliarialab3;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


import com.martintecno.inmobiliarialab3.API.ApiClientRetroFit;
import com.martintecno.inmobiliarialab3.databinding.ActivityMainBinding;
import com.martintecno.inmobiliarialab3.modelo.Propietario;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    private MainActivityViewModel vm;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            vm = new ViewModelProvider(this).get(MainActivityViewModel.class);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

 /*       binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_perfil, R.id.nav_inmueble,R.id.nav_inquilino,R.id.nav_contrato,R.id.nav_salir)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        vm.getpropietarioM().observe(this, new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario p) {
                View header = navigationView.getHeaderView(0);
                ImageView avatar = header.findViewById(R.id.IMGAvatarNav);
                TextView nombre = header.findViewById(R.id.TVNombreNav);
                TextView mail = header.findViewById(R.id.TVCorreoNav);

                nombre.setText(p.getNombre()+ " " + p.getApellido());
                mail.setText(p.getMail());
            //    avatar.setImageResource(p.getAvatar()); AHORA ES UN STRING
                Glide.with(getApplicationContext())
                        .load(ApiClientRetroFit.URLBASE + p.getAvatar())
                        .into(avatar);
            }
        });

    vm.cargarPropietario();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

          vm.cargarPropietario();
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);



        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {


   //     vm.cargarPropietario();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}