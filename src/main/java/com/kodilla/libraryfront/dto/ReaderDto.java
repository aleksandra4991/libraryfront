package com.kodilla.libraryfront.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReaderDto {

    private Long readerId;
    private String uid;
    private String readerName;
    private String phoneNumber;
    private String emailAddress;
    private boolean status;
    private String password;

    @JsonIgnore
    private List<ReservationDto> reservationDtoList = new ArrayList<>();

    @JsonIgnore
    private List<BookDto> bookDtoList = new ArrayList<>();

    public ReaderDto() {
    }

    public ReaderDto(Long readerId, String readerName, String phoneNumber, String emailAddress, boolean status, String password,String uid) {
        this.readerId = readerId;
        this.uid = uid;
        this.readerName = readerName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.status = status;
        this.password = password;
    }

    public ReaderDto(Long readerId, String uid, String readerName, String phoneNumber, String emailAddress, boolean status, String password, List<ReservationDto> reservationDtoList, List<BookDto> bookDtoList) {
        this.readerId = readerId;
        this.uid = uid;
        this.readerName = readerName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.status = status;
        this.password = password;
        this.reservationDtoList = reservationDtoList;
        this.bookDtoList = bookDtoList;
    }

    public ReaderDto(Long readerId, String readerName, String phoneNumber, String emailAddress, String password) {
        this.readerId = readerId;
        this.readerName = readerName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public ReaderDto(String readerName, String phoneNumber, String emailAddress, String password) {
        this.readerName = readerName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public ReaderDto(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public ReaderDto(Long readerId, List<ReservationDto> reservationDtoList, List<BookDto> bookDtoList) {
        this.readerId = readerId;
        this.reservationDtoList = reservationDtoList;
        this.bookDtoList = bookDtoList;
    }

    public void blockUser(){
        this.status = false;
    }

    public Long getReaderId() {
        return readerId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getemailAddress() {
        return emailAddress;
    }

    public void setemailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean isStatus() {
        return status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ReservationDto> getReservationDtoList() {
        return reservationDtoList;
    }

    public void setReservationDtoList(List<ReservationDto> reservationDtoList) {
        this.reservationDtoList = reservationDtoList;
    }

    public List<BookDto> getBookDtoList() {
        return bookDtoList;
    }

    public void setBookDtoList(List<BookDto> bookDtoList) {
        this.bookDtoList = bookDtoList;
    }
}
