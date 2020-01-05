package com.kodilla.libraryfront.websides;

import com.kodilla.libraryfront.client.LibraryBackendClient;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.Route;

@Route
public class ReaderAccount extends VerticalLayout {

    private final LibraryBackendClient libraryBackendClient;
    private long defaultReaderId = 1;
  
    //reader(left) menu
    private Button checkYourActiveReservations;
    private Button checkBooksNowRented;
    private Button editingData;
    private Button logOut;

    //lookingForABook(left)manu
    private Select<String> titleSelect;
    private Select<String> authorSelect;
    private Button findABook;
    private Button addBookToCart;


    //Cart
    private Details cartDetails;
    private Button goToCart;

    
  public ReaderAccount(LibraryBackendClient libraryBackendClient) {
        this.libraryBackendClient = libraryBackendClient;


        checkYourActiveReservations = new Button("My Reservations");
        checkBooksNowRented = new Button("My Books");
        editingData = new Button("Edit your profile");
        logOut = new Button("Log out");

        titleSelect = new Select<>();
        authorSelect = new Select<>();
        findABook = new Button("Find Book");
        addBookToCart = new Button("Add to cart");

        cartDetails = new Details();
        goToCart = new Button();

        checkYourActiveReservations.addClickListener(e->{
            getUI().get().navigate(ReservationsAndRents.class);
        });

        checkBooksNowRented.addClickListener(e->{
                getUI().get().navigate(ReservationsAndRents.class);
  });

        editingData.addClickListener(event -> {
            getUI().get().navigate(EditYourData.class);
        });

        logOut.addClickListener(event -> {
            getUI().get().navigate(MainPage.class);
        });

        titleSelect.setEmptySelectionAllowed(true);
        titleSelect.setRequiredIndicatorVisible(true);
        titleSelect.setLabel("Title");
        titleSelect.setPlaceholder("Select The Title");
        titleSelect.setItems();

        authorSelect.setEmptySelectionAllowed(true);
        authorSelect.setRequiredIndicatorVisible(true);
        authorSelect.setLabel("Author");
        authorSelect.setPlaceholder("Select the Author");
        authorSelect.setItems();

        findABook.addClickListener(e -> {
            String location;
            String selectedTitle = titleSelect.getValue();
            String selectedAuthor = authorSelect.getValue();
            if (selectedTitle != null && selectedAuthor != null) {
                location = "list/" + selectedTitle + "&" + selectedAuthor + "&" + defaultReaderId;
                findABook.getUI().ifPresent(ui ->
                        ui.navigate(location));
            } else if (selectedTitle == null && selectedAuthor != null) {
                location = "list/" + "&null" + selectedAuthor + "&" + defaultReaderId;
                findABook.getUI().ifPresent(ui ->
                        ui.navigate(location));
            } else if (selectedTitle != null && selectedAuthor == null) {
                location = "list/null&" + selectedTitle + "&" + defaultReaderId;
                findABook.getUI().ifPresent(ui ->
                        ui.navigate(location));
            }
        });

        //addBookToCart.addClickListener();

        cartDetails.addContent();

        goToCart.addClickListener(event -> {
            getUI().get().navigate(String.valueOf(EditYourData.class));
        });
    }
}

