package com.vedruna.brunrodriguezmultimedia.model;

import androidx.annotation.NonNull;

import java.util.Date;

public class Post {

    private Long id;
    private User author;
    private String text;
    private Date createDate;
    private Date editDate;

    public Post() {}

    public Post(Long id, User author, String text, Date createDate, Date editDate) {
        this.id = id;
        this.author = author;
        this.text = text;
        this.createDate = createDate;
        this.editDate = editDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", author=" + author.getUsername() + // Assuming getUsername() returns the username of the author
                ", text='" + text + '\'' +
                ", createDate=" + createDate +
                ", editDate=" + editDate +
                '}';
    }
}
