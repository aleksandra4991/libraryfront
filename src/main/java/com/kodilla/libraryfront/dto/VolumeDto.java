package com.kodilla.libraryfront.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Objects;


@JsonIgnoreProperties(ignoreUnknown = true)
public class VolumeDto implements Serializable {

    private Long id;
    private String title;
    private String authors;
    private String publishedDate;
    private String description;
    private String imageUrl;
    private GenreDto genreDto;

    public VolumeDto() {
    }

    public VolumeDto(String title, String authors, String publishedDate, String description, String imageUrl) {
        this.title = title;
        this.authors = authors;
        this.publishedDate = publishedDate;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public VolumeDto(Long id, String title, String authors, String publishedDate, String description, String imageUrl, GenreDto genreDto) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.publishedDate = publishedDate;
        this.description = description;
        this.imageUrl = imageUrl;
        this.genreDto = genreDto;
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

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public GenreDto getGenreDto() {
        return genreDto;
    }

    public void setGenreDto(GenreDto genreDto) {
        this.genreDto = genreDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VolumeDto)) return false;
        VolumeDto volumeDto = (VolumeDto) o;
        return getId().equals(volumeDto.getId()) &&
                getTitle().equals(volumeDto.getTitle()) &&
                Objects.equals(getAuthors(), volumeDto.getAuthors()) &&
                Objects.equals(getPublishedDate(), volumeDto.getPublishedDate()) &&
                Objects.equals(getDescription(), volumeDto.getDescription()) &&
                Objects.equals(getImageUrl(), volumeDto.getImageUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getAuthors(), getPublishedDate(), getDescription(), getImageUrl());
    }
}
