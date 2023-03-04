package com.example.exam2bookapp.model;

import java.io.Serializable;

public class BookData implements Serializable {
    private String name;
    private String author;
    private String description;

    public BookData(String name, String author, String description) {
        this.name = name;
        this.author = author;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }
}
