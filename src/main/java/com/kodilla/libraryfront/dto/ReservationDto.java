package com.kodilla.libraryfront.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.concurrent.atomic.AtomicBoolean;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservationDto {

    private AtomicBoolean active;
    private Long reservationId;
    private String reader;
    private String reservedBooks;
    private Long cartId;

    public ReservationDto() {
    }

    public ReservationDto(AtomicBoolean active, String reader, String reservedBooks) {
        this.active = active;
        this.reader = reader;
        this.reservedBooks = reservedBooks;
    }

    public Long geReservationId() { return reservationId; }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public String getReader() {
        return reader;
    }

    public String getReservedBooks() {
        return reservedBooks;
    }

    public AtomicBoolean getActive() {
        return active;
    }

    public void setActive(AtomicBoolean active) {
        this.active = active;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }
}
