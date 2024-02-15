package com.vedruna.brunrodriguezmultimedia.interfaces;

import com.vedruna.brunrodriguezmultimedia.auth.AuthResponse;
import com.vedruna.brunrodriguezmultimedia.auth.LoginRequest;
import com.vedruna.brunrodriguezmultimedia.dto.UserDTO;
import com.vedruna.brunrodriguezmultimedia.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Interfaz que define los métodos para interactuar con la API de usuarios.
 */
public interface UserInterface {

    /**
     * Obtiene una lista de todos los usuarios.
     *
     * @return Una llamada asíncrona que devuelve una lista de usuarios.
     */
    @GET("/api/v1/users/getAllUsers")
    Call<List<User>> getAllUsers();

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param user Los datos del usuario a registrar.
     * @return Una llamada asíncrona que devuelve el usuario registrado.
     */
    @POST("auth/register")
    Call<User> register(@Body User user);

    /**
     * Inicia sesión en el sistema con las credenciales proporcionadas.
     *
     * @param loginRequest Los datos de inicio de sesión del usuario.
     * @return Una llamada asíncrona que devuelve la respuesta de autenticación.
     */
    @POST("auth/login")
    Call<AuthResponse> login(@Body LoginRequest loginRequest);

    /**
     * Edita la descripción de un usuario.
     *
     * @param authorization El token de autorización del usuario.
     * @param userDTO        Los datos del usuario con la descripción actualizada.
     * @return Una llamada asíncrona que devuelve el usuario con la descripción actualizada.
     */
    @PUT("/api/v1/users/editDescription")
    Call<UserDTO> editDescription(@Header("Authorization") String authorization, @Body UserDTO userDTO);

    /**
     * Elimina un usuario del sistema.
     *
     * @param authorization El token de autorización del usuario.
     * @return Una llamada asíncrona sin resultado.
     */
    @DELETE("/api/v1/users/deleteUser")
    Call<Void> deleteUser(@Header("Authorization") String authorization);

    /**
     * Verifica si un nombre de usuario ya existe en el sistema.
     *
     * @param username El nombre de usuario a verificar.
     * @return Una llamada asíncrona que devuelve verdadero si el nombre de usuario existe, falso de lo contrario.
     */
    @POST("auth/checkUsername")
    Call<Boolean> existsByUsername(@Body String username);

    /**
     * Verifica si un correo electrónico ya está registrado en el sistema.
     *
     * @param email El correo electrónico a verificar.
     * @return Una llamada asíncrona que devuelve verdadero si el correo electrónico existe, falso de lo contrario.
     */
    @POST("auth/checkEmail")
    Call<Boolean> existsByEmail(@Body String email);
}
