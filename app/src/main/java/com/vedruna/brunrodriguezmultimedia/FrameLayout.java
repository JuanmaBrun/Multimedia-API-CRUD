package com.vedruna.brunrodriguezmultimedia;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * Actividad principal que contiene un FrameLayout como contenedor de fragmentos.
 */
public class FrameLayout extends AppCompatActivity {

    /**
     * Método llamado cuando se crea la actividad.
     *
     * @param savedInstanceState Datos de estado de la actividad que se puede usar para recrear la actividad.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_framelayout);
        OnNavigationItemSelectedListener();
    }

    /**
     * Método utilizado para configurar el evento de selección del menú de navegación inferior.
     */
    protected void OnNavigationItemSelectedListener() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();

        bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.navigation_home) {
                navController.navigate(R.id.homeFragment);
            } else if (item.getItemId() == R.id.navigation_edit) {
                navController.navigate(R.id.editFragment);
            } else if (item.getItemId() == R.id.navigation_delete) {
                navController.navigate(R.id.deleteFragment);
            } else if (item.getItemId() == R.id.navigation_exit) {
                navController.navigate(R.id.exitFragment);
                finish();
            }
            return true;
        });
    }
}
