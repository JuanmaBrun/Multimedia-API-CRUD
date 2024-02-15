package com.vedruna.brunrodriguezmultimedia.dto;

import androidx.annotation.NonNull;

/**
 * Clase que representa un objeto DTO (Data Transfer Object) para un usuario.
 */
public class UserDTO {
    /** Identificador del usuario. */
    Long id;
    /** Nombre de usuario. */
    String username;
    /** Correo electrónico del usuario. */
    String email;
    /** Descripción del usuario. */
    String description;

    /**
     * Constructor por defecto.
     */
    public UserDTO() {}

    /**
     * Constructor con parámetros.
     *
     * @param id          El identificador del usuario.
     * @param username    El nombre de usuario.
     * @param description La descripción del usuario.
     */
    public UserDTO(Long id, String username, String description) {
        this.id = id;
        this.username = username;
        this.email = setEmail(username);
        this.description = description;
    }

    /**
     * Obtiene el identificador del usuario.
     *
     * @return El identificador del usuario.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador del usuario.
     *
     * @param id El identificador del usuario.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return El nombre de usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param username El nombre de usuario.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Establece el correo electrónico del usuario basado en su nombre de usuario.
     *
     * @param username El nombre de usuario.
     * @return El correo electrónico generado.
     */
    public String setEmail(String username) {
        this.email = username + "@gmail.com";
        return email;
    }

    /**
     * Obtiene la descripción del usuario.
     *
     * @return La descripción del usuario.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece la descripción del usuario.
     *
     * @param description La descripción del usuario.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Devuelve una representación en forma de cadena de este objeto UserDTO.
     *
     * @return Representación en forma de cadena de este objeto UserDTO.
     */
    @NonNull
    @Override
    public String toString() {
        return "Id: " + id + ", Username: " + username + ", Email: " + email +
                ", Description: " + description;
    }
}
