package com.kodilla.libraryfront.websides;

import com.kodilla.libraryfront.client.LibraryBackendClient;
import com.kodilla.libraryfront.dto.BookDto;
import com.kodilla.libraryfront.dto.ReaderDto;
import com.kodilla.libraryfront.dto.ReservationDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route
public class ReservationsAndRents extends VerticalLayout {

    private final LibraryBackendClient libraryBackendClient;

    private List<BookDto> rentedBooks;
    private List<ReservationDto> doneReservations;
    private Grid<BookDto> gridBooks;
    private Grid<ReservationDto> gridReservations;
    private Button books;
    private Button reservations;
    private Button deleteReservation;

    private HorizontalLayout pageLayOut = new HorizontalLayout();
    private VerticalLayout rentsLayOut = new VerticalLayout();
    private VerticalLayout reservationsLayOut = new VerticalLayout();


    private String readerId;
    private Long reservationId;


    public ReservationsAndRents(LibraryBackendClient libraryBackendClient) {
        this.libraryBackendClient = libraryBackendClient;

        rentedBooks = new ArrayList<>();
        doneReservations = new ArrayList<>();
        gridBooks = new Grid<>();
        gridReservations = new Grid<>();
        books = new Button("Show my rented");
        reservations = new Button("Show my reserved");
        deleteReservation = new Button("deleteReservation");

        add(pageLayOut);
        pageLayOut.addComponentAsFirst(rentsLayOut);
        pageLayOut.add(reservationsLayOut);
        setAlignItems(Alignment.CENTER);
        rentsLayOut.addComponentAtIndex(0,books);
        rentsLayOut.addComponentAtIndex(1,gridBooks);
        rentsLayOut.setWidth("600px");
        rentsLayOut.setHeight("600px");
        reservationsLayOut.setWidth("600px");
        reservationsLayOut.setHeight("600px");
        reservationsLayOut.addComponentAtIndex(0,reservations);
        reservationsLayOut.addComponentAtIndex(1,gridReservations);
        reservationsLayOut.addComponentAtIndex(2,deleteReservation);

        gridBooks.setItems(rentedBooks);
        gridBooks.addColumn(BookDto::getTitle).setHeader("Title");
        gridBooks.addColumn(BookDto::getAuthor).setHeader("Author");
        gridBooks.addColumn(BookDto::getYear).setHeader("Year");
        gridBooks.addColumn(BookDto::getSignature).setHeader("Signature");
        gridBooks.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        gridReservations.setItems(doneReservations);
        gridReservations.addColumn(ReservationDto::getActive).setHeader("Active");
        gridReservations.addColumn(ReservationDto::getReservedBooks).setHeader("ReservedBooks");
        gridReservations.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);

        books.addClickListener(e->showReaderRentedBooks());
        reservations.addClickListener(e->showReaderReservations());
        deleteReservation.addClickListener(e->deleteSpecifiedReservation());

    }

    public void showReaderRentedBooks() {
        ReaderDto readerDto = libraryBackendClient.getReaderByUid(readerId);
        rentedBooks = libraryBackendClient.getBooksRentedByUseer(readerDto) ;
    }

    public void showReaderReservations() {
        ReaderDto readerDto = libraryBackendClient.getReaderByUid(readerId);
        doneReservations = libraryBackendClient.getBooksReservedByUseer(readerDto);
    }

    public void deleteSpecifiedReservation(){
        ReservationDto reservationDto = libraryBackendClient.getSpecifiedByIdReservation(reservationId);
        libraryBackendClient.deleteReservation(reservationDto.getReservationId());
    }

}
