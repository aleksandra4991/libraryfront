package com.kodilla.libraryfront.websides;

import com.kodilla.libraryfront.client.LibraryBackendClient;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route
public class LogIn extends VerticalLayout {

    private final LibraryBackendClient libraryBackendClient;

    public LogIn(LibraryBackendClient libraryBackendClient) {
        this.libraryBackendClient=libraryBackendClient;

        TextField name = new TextField("Name");
        TextField password = new TextField("Password");
        Button logIn = new Button("Log In");

        /*logIn.addClickListener(event -> {
            getUI().get().navigate(String.valueOf(ReaderAccount.class));
        });

        add(name,password,logIn);

    }

         */
    }
}
