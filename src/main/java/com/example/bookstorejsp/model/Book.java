package com.example.bookstorejsp.model;

public class Book {
    protected int id;
    protected String title;
    protected String author;

    protected String genre;
    protected double price;

    public Book() {
    }

    public Book(String title, String author, String genre, double price) {
        super();
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.price = price;
    }

    public Book(int id, String title, String author, String genre, double price) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
