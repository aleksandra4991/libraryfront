package com.kodilla.libraryfront.websides;

import com.kodilla.libraryfront.client.LibraryBackendClient;
import com.kodilla.libraryfront.dto.BookDto;
import com.kodilla.libraryfront.dto.ReservationDto;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route
public class ReservationsAndRents {

    private final LibraryBackendClient libraryBackendClient;

    private List<BookDto> rentedBooks;
    private List<ReservationDto> doneReservations;
    private Grid<BookDto> yourBooks;
    private Grid<ReservationDto> yourReservations;

    private long defaultReader;

    public ReservationsAndRents(LibraryBackendClient libraryBackendClient) {
        this.libraryBackendClient = libraryBackendClient;

        defaultReader =1;

        rentedBooks = new ArrayList<>();
        doneReservations = new ArrayList<>();
        yourBooks = new Grid<>(BookDto.class);
        yourReservations = new Grid<>(ReservationDto.class);

        yourBooks.setItems(rentedBooks);
        yourBooks.addColumn(BookDto::getTitle).setHeader("Title");
        yourBooks.addColumn(BookDto::getAuthor).setHeader("Author");
        yourBooks.addColumn(BookDto::getYear).setHeader("Year");
        yourBooks.addColumn(BookDto::getSignature).setHeader("Signature");
        yourBooks.addColumn(BookDto::getGenreId).setHeader("GenreId");
        yourReservations.setItems(doneReservations);
        yourReservations.addColumn(ReservationDto::getActive).setHeader("Active");
        yourReservations.addColumn(ReservationDto::getReservedBooks).setHeader("ReservedBooks");

        showReaderRentedBooks();
        showReaderReservations();

    }

    public void showReaderRentedBooks() {
        yourBooks.setItems(libraryBackendClient.getBooksRentedByUseer(defaultReader));
    }

    public void showReaderReservations() {
        yourReservations.setItems(libraryBackendClient.getBooksReservedByUseer(defaultReader));
    }

}
