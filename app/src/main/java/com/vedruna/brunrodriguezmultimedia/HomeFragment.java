package com.vedruna.brunrodriguezmultimedia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.vedruna.brunrodriguezmultimedia.RetrofitService.RetrofitService;
import com.vedruna.brunrodriguezmultimedia.dto.UserDTO;
import com.vedruna.brunrodriguezmultimedia.interfaces.UserInterface;
import com.vedruna.brunrodriguezmultimedia.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Fragmento para mostrar una lista de usuarios en la pantalla de inicio.
 */
public class HomeFragment extends Fragment {

    /** ListView para mostrar la lista de usuarios. */
    private ListView userListView;

    /**
     * Llamado para que el fragmento instancie su vista de interfaz de usuario.
     *
     * @param inflater           El objeto LayoutInflater que se puede usar para inflar cualquier vista en el fragmento.
     * @param container          El vista padre a la que se debe adjuntar la UI del fragmento.
     * @param savedInstanceState Si no es nulo, este fragmento se está reconstruyendo a partir de un estado guardado anteriormente.
     * @return la Vista para la UI del fragmento, o null.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        userListView = rootView.findViewById(R.id.userListView);
        loadUsers();
        return rootView;
    }

    /**
     * Carga la lista de usuarios desde el servidor.
     */
    private void loadUsers() {
        RetrofitService retrofitService = new RetrofitService();
        UserInterface userInterface = retrofitService.getRetrofit().create(UserInterface.class);
        userInterface.getAllUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<User> userList = response.body();
                    List<UserDTO> dtoList = new ArrayList<>();
                    for (User user : userList){
                        UserDTO userDTO = new UserDTO();
                        userDTO.setId(user.getId());
                        userDTO.setUsername(user.getUsername());
                        userDTO.setEmail(user.getUsername());
                        userDTO.setDescription(user.getDescription());
                        dtoList.add(userDTO);
                    }
                    showUserList(dtoList);
                } else {
                    Toast.makeText(getActivity(), "Failed to retrieve user list", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getActivity(), "Network error. Please try again later.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Muestra la lista de usuarios en el ListView.
     *
     * @param userList La lista de usuarios a mostrar.
     */
    private void showUserList(List<UserDTO> userList) {
        ArrayAdapter<UserDTO> adapter = new ArrayAdapter<>(requireContext(), R.layout.list_item_user, userList);
        userListView.setAdapter(adapter);
    }
}


