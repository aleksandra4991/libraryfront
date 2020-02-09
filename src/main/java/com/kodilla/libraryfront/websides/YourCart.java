package com.kodilla.libraryfront.websides;

import com.kodilla.libraryfront.client.LibraryBackendClient;
import com.kodilla.libraryfront.dto.BookDto;
import com.kodilla.libraryfront.dto.CartBookAdderDto;
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
    private Button bookPutInCartChecker;
    private Button bookDeleterFromACart;
    private Button reservationMaker;

    private Long idOfCart;
    private Long bookId;

    public YourCart(LibraryBackendClient libraryBackendClient) {
        this.libraryBackendClient = libraryBackendClient;

        booksInCart = new ArrayList<>();
        booksInYourCart = new Grid<>(BookDto.class);
        booksInYourCart.setItems(booksInCart);
        booksInYourCart.addColumn(BookDto::getTitle).setHeader("Title");
        booksInYourCart.addColumn(BookDto::getAuthor).setHeader("Author");
        booksInYourCart.addColumn(BookDto::getYear).setHeader("Year");
        booksInYourCart.addColumn(BookDto::getSignature).setHeader("Signature");
        booksInYourCart.addColumn(BookDto::getGenreId).setHeader("GenreId");

        bookPutInCartChecker = new Button();
        bookDeleterFromACart = new Button();
        reservationMaker = new Button();

        bookPutInCartChecker.addActionListener(e->showBooksPutInReaderCart());
        bookDeleterFromACart.addActionListener(e->deleteBookFromACart());
        reservationMaker.addActionListener(e->makeAReservation());

    }

    public void showBooksPutInReaderCart(){
        CartBookAdderDto cartBookAdderDto = libraryBackendClient.getCartById(idOfCart);
        booksInYourCart.setItems(libraryBackendClient.getBooksPutinCart(cartBookAdderDto));
    }

    public void deleteBookFromACart(){
        CartBookAdderDto cartBookAdderDto = libraryBackendClient.getCartById(idOfCart);
        BookDto bookDto = libraryBackendClient.getSpecifiedBook(bookId);
        libraryBackendClient.deleteBookFromACart(bookDto,cartBookAdderDto.getCartId());
    }

    public void makeAReservation(){
        CartBookAdderDto cartBookAdderDto = libraryBackendClient.getCartById(idOfCart);
        libraryBackendClient.createReservationOnCartBasis(cartBookAdderDto.getBookDtoList(),idOfCart);
    }

}
