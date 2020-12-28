package com.kodilla.libraryfront.websides;

import com.kodilla.libraryfront.client.LibraryBackendClient;
import com.kodilla.libraryfront.dto.VolumeDto;
import com.kodilla.libraryfront.dto.CartBookAdderDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route
public class YourCart extends VerticalLayout {

    private final LibraryBackendClient libraryBackendClient;

    private VerticalLayout layoutOfCart = new VerticalLayout();
    private HorizontalLayout panelButtons = new HorizontalLayout();

    private List<VolumeDto> booksInCart;
    private Grid<VolumeDto> booksInYourCart;
    private Button bookPutInCartChecker;
    private Button bookDeleterFromACart;
    private Button reservationMaker;

    private Long idOfCart;
    private Long bookId;

    public YourCart(LibraryBackendClient libraryBackendClient) {
        this.libraryBackendClient = libraryBackendClient;

        booksInCart = new ArrayList<>();
        booksInYourCart = new Grid<>(VolumeDto.class);
        booksInYourCart.setItems(booksInCart);
        booksInYourCart.addColumn(VolumeDto::getTitle).setHeader("Title");
        booksInYourCart.addColumn(VolumeDto::getAuthors).setHeader("Author");
        booksInYourCart.addColumn(VolumeDto::getPublishedDate).setHeader("Published Date");
        booksInYourCart.addColumn(VolumeDto::getDescription).setHeader("Description");

        bookPutInCartChecker = new Button("Put in a Cart");
        bookDeleterFromACart = new Button("Delete From a Cart");
        reservationMaker = new Button("Make a Reservation");

        bookPutInCartChecker.addClickListener(e->showBooksPutInReaderCart());
        bookDeleterFromACart.addClickListener(e->deleteBookFromACart());
        reservationMaker.addClickListener(e->{
            makeAReservation();
            reservationMaker.getUI().get().navigate((ReservationsAndRents.class));
        });

        add(layoutOfCart,panelButtons);
        panelButtons.add(bookPutInCartChecker,bookDeleterFromACart,reservationMaker);
        layoutOfCart.addComponentAsFirst(booksInYourCart);
        layoutOfCart.add(panelButtons);
        layoutOfCart.setHorizontalComponentAlignment(Alignment.CENTER,booksInYourCart,panelButtons);
    }

    public void showBooksPutInReaderCart(){
        CartBookAdderDto cartBookAdderDto = libraryBackendClient.getCartById(idOfCart);
        booksInYourCart.setItems(libraryBackendClient.getBooksPutInCart(cartBookAdderDto));
    }

    public void deleteBookFromACart(){
        CartBookAdderDto cartBookAdderDto = libraryBackendClient.getCartById(idOfCart);
        VolumeDto VolumeDto = libraryBackendClient.getSpecifiedBook(bookId);
        libraryBackendClient.deleteBookFromACart(VolumeDto,cartBookAdderDto.getCartId());
    }

    public void makeAReservation(){
        CartBookAdderDto cartBookAdderDto = libraryBackendClient.getCartById(idOfCart);
        libraryBackendClient.createReservationOnCartBasis(cartBookAdderDto.getVolumeDto(),idOfCart);
    }

}
