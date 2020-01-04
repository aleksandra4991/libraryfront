package com.kodilla.libraryfront.websides;

import com.kodilla.libraryfront.client.LibraryBackendClient;
import com.vaadin.flow.router.Route;

import java.awt.*;

@Route
public class YourCart {

    private final LibraryBackendClient libraryBackendClient;

    private Button makeAReservation;

    public YourCart(LibraryBackendClient libraryBackendClient) {
        this.libraryBackendClient = libraryBackendClient;

        makeAReservation = new Button();
        //makeAReservation.addActionListener(libraryBackendClient.createReservationOnCartBasis());
    }
}
