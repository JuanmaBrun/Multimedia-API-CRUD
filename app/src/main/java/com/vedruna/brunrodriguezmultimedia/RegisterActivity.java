package com.vedruna.brunrodriguezmultimedia;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vedruna.brunrodriguezmultimedia.RetrofitService.RetrofitService;
import com.vedruna.brunrodriguezmultimedia.interfaces.UserInterface;
import com.vedruna.brunrodriguezmultimedia.model.User;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Esta actividad permite a los usuarios registrarse proporcionando su nombre de usuario, contraseña, correo electrónico y descripción.
 */
public class RegisterActivity extends AppCompatActivity {

    /**
     * Método llamado cuando se crea la actividad.
     *
     * @param savedInstanceState datos de estado de la actividad que se puede usar para recrear la actividad.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inicializa los componentes de la interfaz de usuario y configura el OnClickListener para el botón de guardar
        initializeComponents();
    }

    /**
     * Inicializa los componentes de la interfaz de usuario y configura el OnClickListener para el botón de guardar.
     */
    private void initializeComponents() {
        EditText create_Username = findViewById(R.id.form_name);
        EditText create_Password = findViewById(R.id.form_password);
        EditText create_Email = findViewById(R.id.form_email);
        EditText create_Description = findViewById(R.id.form_description);
        Button buttonSave = findViewById(R.id.btn_saveUser);

        // Servicio Retrofit para comunicarse con la API backend
        RetrofitService retrofitService = new RetrofitService();
        UserInterface userInterface = retrofitService.getRetrofit().create(UserInterface.class);

        // OnClickListener para el botón de guardar
        buttonSave.setOnClickListener(view -> {
            String username = String.valueOf(create_Username.getText());
            String password = String.valueOf(create_Password.getText());
            String email = String.valueOf(create_Email.getText());
            String description = String.valueOf(create_Description.getText());

            // Crea un nuevo objeto User con la información proporcionada
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setDescription(description);

            // Llama al endpoint de registro para crear el usuario
            userInterface.register(user).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        // Muestra un mensaje de éxito si la creación del usuario es exitosa
                        Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    } else {
                        // Muestra un mensaje de error si la creación del usuario falla
                        Toast.makeText(RegisterActivity.this, "Registro fallido", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(EditFragment.class.getName()).log(Level.SEVERE, "Err: ", response.errorBody().toString());
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    // Muestra un mensaje de error si la creación del usuario falla
                    Toast.makeText(RegisterActivity.this, "Registro fallido", Toast.LENGTH_SHORT).show();
                    Logger.getLogger(EditFragment.class.getName()).log(Level.SEVERE, "Err: ", t);
                }
            });
        });
    }

    /**
     * Redirige al usuario a la actividad de inicio de sesión.
     *
     * @param view La vista que activó este método.
     */
    public void goToLogin(View view) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
