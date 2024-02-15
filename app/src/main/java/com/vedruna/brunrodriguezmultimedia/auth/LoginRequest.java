package com.vedruna.brunrodriguezmultimedia.auth;

/**
 * Clase que representa una solicitud de inicio de sesión.
 */
public class LoginRequest {

    /** Nombre de usuario. */
    private String username;
    /** Contraseña. */
    private String password;

    /**
     * Constructor de la clase LoginRequest.
     *
     * @param username El nombre de usuario proporcionado en la solicitud de inicio de sesión.
     * @param password La contraseña proporcionada en la solicitud de inicio de sesión.
     */
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
