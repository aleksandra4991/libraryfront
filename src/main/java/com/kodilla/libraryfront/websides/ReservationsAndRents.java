package com.kodilla.libraryfront.websides;

import com.kodilla.libraryfront.client.LibraryBackendClient;
import com.kodilla.libraryfront.dto.BookDto;
import com.kodilla.libraryfront.dto.ReaderDto;
import com.kodilla.libraryfront.dto.ReservationDto;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route
public class ReservationsAndRents extends HorizontalLayout {

    private final LibraryBackendClient libraryBackendClient;

    private List<BookDto> rentedBooks;
    private List<ReservationDto> doneReservations;
    private Grid<BookDto> yourBooks;
    private Grid<ReservationDto> yourReservations;
    private Button deleteReservation;

    private VerticalLayout layoutOfReservationsAndRents = new VerticalLayout();

    private String uid;
    private Long reservationId;


    public ReservationsAndRents(LibraryBackendClient libraryBackendClient) {
        this.libraryBackendClient = libraryBackendClient;

        rentedBooks = new ArrayList<>();
        doneReservations = new ArrayList<>();
        yourBooks = new Grid<>(BookDto.class);
        yourReservations = new Grid<>(ReservationDto.class);
        deleteReservation = new Button();

        yourBooks.setItems(rentedBooks);
        yourBooks.addColumn(BookDto::getTitle).setHeader("Title");
        yourBooks.addColumn(BookDto::getAuthor).setHeader("Author");
        yourBooks.addColumn(BookDto::getYear).setHeader("Year");
        yourBooks.addColumn(BookDto::getSignature).setHeader("Signature");
        yourBooks.addColumn(BookDto::getGenreId).setHeader("GenreId");
        yourReservations.setItems(doneReservations);
        yourReservations.addColumn(ReservationDto::getActive).setHeader("Active");
        yourReservations.addColumn(ReservationDto::getReservedBooks).setHeader("ReservedBooks");
        deleteReservation.addClickListener(e->deleteSpecifiedReservation());

        add(layoutOfReservationsAndRents);
        layoutOfReservationsAndRents.add(yourBooks,yourReservations, (Component) deleteReservation);
        showReaderRentedBooks();
        showReaderReservations();
        deleteSpecifiedReservation();

    }

    public void showReaderRentedBooks() {
        ReaderDto readerDto = libraryBackendClient.getReaderByUid(uid);
        yourBooks.setItems(libraryBackendClient.getBooksRentedByUseer(readerDto));
    }

    public void showReaderReservations() {
        ReaderDto readerDto = libraryBackendClient.getReaderByUid(uid);
        yourReservations.setItems(libraryBackendClient.getBooksReservedByUseer(readerDto));
    }

    public void deleteSpecifiedReservation(){
        ReservationDto reservationDto = libraryBackendClient.getSpecifiedByIdReservation(reservationId);
        libraryBackendClient.deleteReservation(reservationDto.getReservationId());
    }

}
