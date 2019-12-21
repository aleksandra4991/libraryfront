package com.kodilla.libraryfront.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDto {

    private Long bookId;
    private String title;
    private String author;
    private Long year;
    private String signature;
    private boolean rented;
    private String genreId;

    public BookDto() {
    }

    public BookDto(String title, String author, Long year, String signature, boolean rented, String genreId) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.signature = signature;
        this.rented = rented;
        this.genreId = genreId;
    }

    public BookDto(String title, String author, Long year, String signature, String genreId) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.signature = signature;
        this.genreId = genreId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Long getYear() {
        return year;
    }

    public String getSignature() {
        return signature;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public String getGenreId() {
        return genreId;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }

}
