package com.kodilla.libraryfront.websides;

import com.kodilla.libraryfront.client.LibraryBackendClient;
import com.kodilla.libraryfront.dto.BookDto;
import com.kodilla.libraryfront.dto.CartBookAdderDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.Autocomplete;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;


@Route
public class ReaderAccount extends VerticalLayout {

    private final LibraryBackendClient libraryBackendClient;
    private String uuid = "1";
    private long idOfCart;
    private long bookId;

    private VerticalLayout pageLayout = new VerticalLayout();
    private HorizontalLayout mainUpMenu = new HorizontalLayout();
    private HorizontalLayout detailsMenu = new HorizontalLayout();
    private VerticalLayout lookForAndChooseLeftMenu = new VerticalLayout();
    private HorizontalLayout selection = new HorizontalLayout();
    private HorizontalLayout choosingABook = new HorizontalLayout();
    private HorizontalLayout cartRightBottomMenu = new HorizontalLayout();

    //reader(right) menu
    private Button checkYourActiveReservations;
    private Button checkBooksNowRented;
    private Button editingData;
    private Button logOut;

    //lookingForABook(left)manu
    private TextField titleSelect;
    private TextField authorSelect;
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

        titleSelect = new TextField();
        authorSelect = new TextField();
        findABook = new Button("Find Book");
        addBookToCart = new Button("Add to cart");

        cartDetails = new Details();
        goToCart = new Button("Cart");

        add(pageLayout);
        pageLayout.addComponentAtIndex(0,mainUpMenu);
        pageLayout.addComponentAtIndex(1,detailsMenu);
        mainUpMenu.addComponentAtIndex(0,checkBooksNowRented);
        mainUpMenu.addComponentAtIndex(1,checkYourActiveReservations);
        mainUpMenu.addComponentAtIndex(2,editingData);
        mainUpMenu.addComponentAtIndex(3,logOut);
        detailsMenu.addComponentAtIndex(0,lookForAndChooseLeftMenu);
        detailsMenu.addComponentAtIndex(1,cartRightBottomMenu);
        lookForAndChooseLeftMenu.addComponentAtIndex(0,selection);
        lookForAndChooseLeftMenu.addComponentAtIndex(1,choosingABook);
        selection.addComponentAtIndex(0,titleSelect);
        selection.addComponentAtIndex(1,authorSelect);
        choosingABook.addComponentAtIndex(0,findABook);
        choosingABook.addComponentAtIndex(1,addBookToCart);
        cartRightBottomMenu.addComponentAtIndex(0,cartDetails);
        cartRightBottomMenu.addComponentAtIndex(1,goToCart);
        pageLayout.setAlignItems(Alignment.CENTER);

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

        titleSelect.setValueChangeMode(ValueChangeMode.EAGER);
        titleSelect.setAutocomplete(Autocomplete.ON);
        titleSelect.setRequiredIndicatorVisible(true);
        titleSelect.setLabel("Title");
        titleSelect.setPlaceholder("Select The Title");

        authorSelect.setValueChangeMode(ValueChangeMode.EAGER);
        authorSelect.setAutocomplete(Autocomplete.ON);
        authorSelect.setAutoselect(true);
        authorSelect.setRequiredIndicatorVisible(true);
        authorSelect.setLabel("Author");
        authorSelect.setPlaceholder("Select the Author");

        findABook.addClickListener(e -> {
            String location;
            String selectedTitle = titleSelect.getValue();
            String selectedAuthor = authorSelect.getValue();
            if (selectedTitle != null && selectedAuthor != null) {
                location = "list/" + selectedTitle + "&" + selectedAuthor + "&" + libraryBackendClient.getReaderByUid(uuid);
                findABook.getUI().ifPresent(ui ->
                        ui.navigate(location));
            } else if (selectedTitle == null && selectedAuthor != null) {
                location = "list/" + "null&" + selectedAuthor + "&" + libraryBackendClient.getReaderByUid(uuid);
                findABook.getUI().ifPresent(ui ->
                        ui.navigate(location));
            } else if (selectedTitle != null && selectedAuthor == null) {
                location = "list/" + selectedTitle + "&null&" + libraryBackendClient.getReaderByUid(uuid);
                findABook.getUI().ifPresent(ui ->
                        ui.navigate(location));
            }
        });

        addBookToCart.addClickListener(e->putABookInACart());

        cartDetails.addContent();

        goToCart.addClickListener(e-> viewYourCart());

    }

    public void putABookInACart(){
        CartBookAdderDto cartBookAdderDto = libraryBackendClient.getCartById(idOfCart);
        BookDto bookDto = libraryBackendClient.getSpecifiedBook(bookId);
        libraryBackendClient.putBookInACart(bookDto,cartBookAdderDto.getCartId());
    }

    public void viewYourCart(){
        CartBookAdderDto cartBookAdderDto = libraryBackendClient.getCartById(idOfCart);
        libraryBackendClient.getBooksAlreadyPutInCart(cartBookAdderDto);
    }
}

