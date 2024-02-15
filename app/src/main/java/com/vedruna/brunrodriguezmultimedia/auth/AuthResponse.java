package com.vedruna.brunrodriguezmultimedia.auth;

/**
 * Clase que representa una respuesta de autenticación del servidor.
 */
public class AuthResponse {

    /** Token de autenticación recibido del servidor. */
    private String token;

    /**
     * Obtiene el token de autenticación.
     *
     * @return El token de autenticación recibido del servidor.
     */
    public String getToken() {
        return token;
    }
}
