package com.kodilla.libraryfront.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservationDto {

    private AtomicBoolean active;
    private Long reservationId;
    private ReaderDto reader;
    private List<VolumeDto> reservedBooks;
    private Long cartId;

    public ReservationDto(AtomicBoolean active, List<VolumeDto> reservedBooks) {
        this.active = active;
        this.reservedBooks = reservedBooks;
    }

    public AtomicBoolean getActive() {
        return active;
    }

    public void setActive(AtomicBoolean active) {
        this.active = active;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public ReaderDto getReader() {
        return reader;
    }

    public List<VolumeDto> getReservedBooks() {
        return reservedBooks;
    }

    public void setReservedBooks(List<VolumeDto> reservedBooks) {
        this.reservedBooks = reservedBooks;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }
}
