package com.iquinteros.taller_prototipos;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.iquinteros.taller_prototipos.api.ConexionBase;
import com.iquinteros.taller_prototipos.api.MiPerfilConexion;
import com.iquinteros.taller_prototipos.databinding.ActivityMainBinding;
import com.iquinteros.taller_prototipos.interfaces.ICallbackFunciono;
import com.iquinteros.taller_prototipos.interfaces.ICallbackMiPerfil;
import com.iquinteros.taller_prototipos.modelos.MiPerfilModelo;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cache;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        MiPerfilConexion miPerfilConexion = new MiPerfilConexion();
        miPerfilConexion.seleccionar(new ICallbackMiPerfil() {
            @Override
            public void retornar(List<MiPerfilModelo> resultado) {
                Log.d("RESULTADO: ", resultado.toString());
                miPerfilConexion.eliminar(resultado.get(0), new ICallbackFunciono() {
                    @Override
                    public void retornar(boolean funciono) {

                    }
                });
            }
        });

        miPerfilConexion.insertar(new MiPerfilModelo("Ignacio", "Quinteros", 342, "", null, false, null, 20.0), new ICallbackFunciono() {
            @Override
            public void retornar(boolean funciono) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}