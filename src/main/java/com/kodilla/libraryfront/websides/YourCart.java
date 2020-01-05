package com.kodilla.libraryfront.websides;

import com.kodilla.libraryfront.client.LibraryBackendClient;
import com.kodilla.libraryfront.dto.BookDto;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.router.Route;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Route
public class YourCart {

    private final LibraryBackendClient libraryBackendClient;

    private List<BookDto> booksInCart;
    private Grid<BookDto> booksInYourCart;
    private Button makeAReservation;

    private long defaultReader;

    public YourCart(LibraryBackendClient libraryBackendClient) {
        this.libraryBackendClient = libraryBackendClient;

        defaultReader = 1;

        booksInCart = new ArrayList<>();
        booksInYourCart = new Grid<>(BookDto.class);
        booksInYourCart.setItems(booksInCart);
        booksInYourCart.addColumn(BookDto::getTitle).setHeader("Title");
        booksInYourCart.addColumn(BookDto::getAuthor).setHeader("Author");
        booksInYourCart.addColumn(BookDto::getYear).setHeader("Year");
        booksInYourCart.addColumn(BookDto::getSignature).setHeader("Signature");
        booksInYourCart.addColumn(BookDto::getGenreId).setHeader("GenreId");

        showBooksPutInReaderCart();

        makeAReservation = new Button();
        //makeAReservation.addActionListener(libraryBackendClient.createReservationOnCartBasis());
    }

    public void showBooksPutInReaderCart(){
        //booksInYourCart.setItems(libraryBackendClient.getBooksPutInCart(defaultReader));
    }
}
