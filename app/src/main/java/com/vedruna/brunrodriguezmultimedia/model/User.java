package com.vedruna.brunrodriguezmultimedia.model;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.List;

/**
 * Clase que representa a un usuario en el sistema.
 */
public class User {

    private Long id;
    private String username;
    private String password;
    private String email;
    private Date createDate;
    private String description;
    private List<Post> posts;
    private List<User> following;
    private List<User> followers;
    private Role role;

    /**
     * Constructor por defecto de la clase User.
     */
    public User() {}

    /**
     * Constructor de la clase User que inicializa todos los atributos.
     *
     * @param id          El ID del usuario.
     * @param username    El nombre de usuario del usuario.
     * @param password    La contraseña del usuario.
     * @param email       El correo electrónico del usuario.
     * @param createDate  La fecha de creación del usuario.
     * @param description La descripción del usuario.
     * @param posts       La lista de publicaciones del usuario.
     * @param following   La lista de usuarios que sigue el usuario.
     * @param followers   La lista de usuarios seguidores del usuario.
     * @param role        El rol del usuario en el sistema.
     */
    public User(Long id, String username, String password, String email, Date createDate,
                String description, List<Post> posts, List<User> following, List<User> followers,
                Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.createDate = createDate;
        this.description = description;
        this.posts = posts;
        this.following = following;
        this.followers = followers;
        this.role = role;
    }

    // Getters y setters para los atributos


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
