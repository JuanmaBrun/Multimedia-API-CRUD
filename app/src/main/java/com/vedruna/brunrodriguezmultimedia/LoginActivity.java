package com.vedruna.brunrodriguezmultimedia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vedruna.brunrodriguezmultimedia.RetrofitService.RetrofitService;
import com.vedruna.brunrodriguezmultimedia.auth.AuthResponse;
import com.vedruna.brunrodriguezmultimedia.auth.LoginRequest;
import com.vedruna.brunrodriguezmultimedia.interfaces.UserInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * LoginActivity: Actividad que permite a los usuarios iniciar sesión en la aplicación.
 */
public class LoginActivity extends AppCompatActivity {

    /** Token de autorización para la sesión de usuario. */
    public static String authToken;

    /**
     * Método llamado cuando se crea la actividad.
     *
     * @param savedInstanceState Datos de estado de la actividad que se puede usar para recrear la actividad.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializa los componentes de la interfaz de usuario.
        initializeComponents();
    }

    /**
     * Inicializa los componentes de la interfaz de usuario.
     */
    private void initializeComponents() {
        EditText log_Username = findViewById(R.id.login_username);
        EditText log_Password = findViewById(R.id.login_password);
        Button buttonLogin = findViewById(R.id.btn_login);

        RetrofitService retrofitService = new RetrofitService();
        UserInterface userInterface = retrofitService.getRetrofit().create(UserInterface.class);

        buttonLogin.setOnClickListener(view -> {
            String username = log_Username.getText().toString();
            String password = log_Password.getText().toString();

            loginUser(userInterface, username, password);
        });
    }

    /**
     * Envía una solicitud para iniciar sesión al servidor.
     *
     * @param userInterface Interfaz de usuario para interactuar con la API.
     * @param username      Nombre de usuario ingresado por el usuario.
     * @param password      Contraseña ingresada por el usuario.
     */
    private void loginUser(UserInterface userInterface, String username, String password) {
        Call<AuthResponse> call = userInterface.login(new LoginRequest(username, password));
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    AuthResponse authResponse = response.body();
                    authToken = authResponse.getToken();
                    // Si la respuesta es exitosa, inicia la actividad principal
                    Intent intent = new Intent(LoginActivity.this, FrameLayout.class);
                    startActivity(intent);
                } else {
                    // Si hay un error, muestra un mensaje de error
                    Toast.makeText(LoginActivity.this, "Error al iniciar sesión. Verifica tus credenciales.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                // Si hay un error de conexión, muestra un mensaje de error
                Toast.makeText(LoginActivity.this, "Error de conexión. Inténtalo de nuevo más tarde.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Método llamado cuando se hace clic en el botón "Registrarse".
     *
     * @param view La vista del botón "Registrarse".
     */
    public void goToRegister(View view) {
        // Inicia la actividad de registro
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}

