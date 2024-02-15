package com.vedruna.brunrodriguezmultimedia;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vedruna.brunrodriguezmultimedia.RetrofitService.RetrofitService;
import com.vedruna.brunrodriguezmultimedia.dto.UserDTO;
import com.vedruna.brunrodriguezmultimedia.interfaces.UserInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Fragmento utilizado para permitir al usuario editar su descripción.
 */
public class EditFragment extends Fragment {

    // Elemento de interfaz de usuario para la entrada de la nueva descripción del usuario
    private EditText editDescriptionEditText;

    /**
     * Método llamado cuando se crea la vista del fragmento.
     *
     * @param inflater           El LayoutInflater utilizado para inflar la vista.
     * @param container          El ViewGroup en el que se debe inflar la vista.
     * @param savedInstanceState La instancia previamente guardada del fragmento, si la hay.
     * @return La vista inflada para el fragmento.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edit, container, false);

        // Vincular los elementos de la interfaz de usuario
        editDescriptionEditText = rootView.findViewById(R.id.edit_description);
        Button editButton = rootView.findViewById(R.id.btn_edit);

        // Agregar un OnClickListener al botón para editar la descripción
        editButton.setOnClickListener(view -> {
            String newDescription = editDescriptionEditText.getText().toString();
            String authorizationToken = LoginActivity.authToken;
            editDescription(authorizationToken, newDescription);
        });

        return rootView;
    }

    /**
     * Método utilizado para enviar una solicitud para editar la descripción del usuario.
     *
     * @param authorizationToken El token de autorización del usuario.
     * @param newDescription     La nueva descripción del usuario.
     */
    private void editDescription(String authorizationToken, String newDescription) {
        RetrofitService retrofitService = new RetrofitService();
        UserInterface userInterface = retrofitService.getRetrofit().create(UserInterface.class);

        // Crear un objeto UserDTO con la nueva descripción
        UserDTO userDTO = new UserDTO();
        userDTO.setDescription(newDescription);

        // Realizar la llamada a la API para editar la descripción del usuario
        userInterface.editDescription("Bearer " + authorizationToken, userDTO).enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // La descripción del usuario se actualizó con éxito
                    UserDTO updatedUser = response.body();
                    Toast.makeText(getActivity(), "Description changed successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // La solicitud al servidor falló
                    Toast.makeText(getActivity(), "Failed to update user description", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {
                // Error de red o error en la solicitud
                Toast.makeText(getActivity(), "Network error. Please try again later.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}