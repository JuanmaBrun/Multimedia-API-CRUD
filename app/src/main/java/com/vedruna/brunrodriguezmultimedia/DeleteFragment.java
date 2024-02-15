package com.vedruna.brunrodriguezmultimedia;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.vedruna.brunrodriguezmultimedia.RetrofitService.RetrofitService;
import com.vedruna.brunrodriguezmultimedia.interfaces.UserInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Fragmento utilizado para eliminar al usuario actualmente autenticado.
 */
public class DeleteFragment extends Fragment {

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
        View rootView = inflater.inflate(R.layout.fragment_delete, container, false);

        Button deleteButton = rootView.findViewById(R.id.btn_delete);

        // Agregar un OnClickListener al botón para eliminar al usuario
        deleteButton.setOnClickListener(view -> {
            String authorizationToken = LoginActivity.authToken;
            deleteUser(authorizationToken);
        });

        return rootView;
    }

    /**
     * Método utilizado para enviar una solicitud para eliminar al usuario actualmente autenticado.
     *
     * @param authorizationToken El token de autorización del usuario.
     */
    private void deleteUser(String authorizationToken) {
        RetrofitService retrofitService = new RetrofitService();
        UserInterface userInterface = retrofitService.getRetrofit().create(UserInterface.class);

        // Realizar la llamada a la API para eliminar al usuario
        userInterface.deleteUser("Bearer " + authorizationToken).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // El usuario se eliminó exitosamente
                    Toast.makeText(getActivity(), "Usuario eliminado exitosamente", Toast.LENGTH_SHORT).show();

                    // Volver a LoginActivity
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    getActivity().finish();
                } else {
                    // La solicitud al servidor falló
                    Toast.makeText(getActivity(), "Error al eliminar usuario", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Error de red o error en la solicitud
                Toast.makeText(getActivity(), "Error de red. Por favor, inténtalo de nuevo más tarde.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
