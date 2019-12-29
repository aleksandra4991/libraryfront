package com.kodilla.libraryfront.websides;

import com.kodilla.libraryfront.client.LibraryBackendClient;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
@StyleSheet("webapp/styles/Background.css")
public class MainPage extends VerticalLayout {

    private final LibraryBackendClient libraryBackendClient;
    private VerticalLayout layoutOfPage = new VerticalLayout();
    private HorizontalLayout startingButtons = new HorizontalLayout();
    //private Image logo;
    private Label myLibrary;
    private Button logIn;
    private Button createAccount;

    public MainPage(LibraryBackendClient libraryBackendClient){
        this.libraryBackendClient=libraryBackendClient;

        add(layoutOfPage,startingButtons);

        //Image logo = new Image("file:webapp/images/logo.jpg");
        //addClassName("mainPage");
        //setSizeFull();

        myLibrary = new Label("Aleksandra Library");
        myLibrary.setHeight("15000");
        myLibrary.setWidth("30000");
        layoutOfPage.addComponentAsFirst(myLibrary);
        layoutOfPage.add(startingButtons);
        layoutOfPage.setHorizontalComponentAlignment(Alignment.CENTER,myLibrary,startingButtons);

        createAccount = new Button("Create Account");
        createAccount.addClickListener(event -> {
            getUI().get().navigate(CreateNewAccount.class);
        });
        createAccount.setHeight("200");
        createAccount.setWidth("200");

        logIn = new Button("Log In");
        logIn.addClickListener(event -> {
            getUI().get().navigate(LogIn.class);
        });

        logIn.setHeight("200");
        logIn.setWidth("200");

        startingButtons.addComponentAtIndex(0,createAccount);
        startingButtons.addComponentAtIndex(1,logIn);

    }

}








