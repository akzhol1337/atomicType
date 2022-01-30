package com.example.atomictype.Business.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Base64;

@Entity
public class Quote {
    @Id
    private Long id;
    private String title;
    @Column(columnDefinition = "text")
    private String content;
    private String author;
    //Link to image hosting
    private String cover;

    public Quote(Long id, String title, String content, String author, String cover) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.cover = cover;
    }

    public Quote() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
