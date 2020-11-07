package com.kodilla.libraryfront.websides;

import com.kodilla.libraryfront.client.LibraryBackendClient;
import com.kodilla.libraryfront.dto.CartBookAdderDto;
import com.kodilla.libraryfront.dto.VolumeDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
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
    private Grid<VolumeDto> googleVolumeList;
    private TextField book;
    private Dialog lookingForABookDialog;
    private Dialog incorrectLookingForABookDialog;
    private Label lookingForABookLabel;
    private Label incorrectLookingForABookLabel;
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

        googleVolumeList = new Grid<>();
        book = new TextField();
        findABook = new Button("Find Book");
        lookingForABookDialog = new Dialog();
        incorrectLookingForABookDialog = new Dialog();
        lookingForABookLabel = new Label();
        incorrectLookingForABookLabel = new Label();
        addBookToCart = new Button("Add to cart");

        lookingForABookDialog.add(lookingForABookLabel);
        incorrectLookingForABookDialog.add(incorrectLookingForABookLabel);

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
        selection.addComponentAsFirst(book);
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


      book.setValueChangeMode(ValueChangeMode.EAGER);
      book.setAutocomplete(Autocomplete.ON);
      book.setAutoselect(true);
      book.setRequiredIndicatorVisible(true);
      book.setLabel("Book");





      findABook.addClickListener(e -> {
                    String selectedBook = book.getValue();
                    if (selectedBook != null) {
                        libraryBackendClient.getVolumeByTitleAndAuthor(selectedBook);
                        lookingForABookLabel.setText("Volume: " + "\"" + selectedBook + "\"" + " have found");
                        lookingForABookDialog.open();
                    }
                    else{
                        incorrectLookingForABookLabel.setText("Unfortunately volume: " + "\"" + selectedBook + "\"" + " have not found");
                        incorrectLookingForABookDialog.open();
                    }
                });

      addBookToCart.addClickListener(e->putABookInACart());

      cartDetails.addContent();

      goToCart.addClickListener(e-> viewYourCart());

    }

    public void putABookInACart(){
        CartBookAdderDto cartBookAdderDto = libraryBackendClient.getCartById(idOfCart);
        VolumeDto volumeDto = libraryBackendClient.getSpecifiedBook(bookId);
        libraryBackendClient.putBookInACart(volumeDto,cartBookAdderDto.getCartId());
    }

    public void viewYourCart(){
        CartBookAdderDto cartBookAdderDto = libraryBackendClient.getCartById(idOfCart);
        libraryBackendClient.getBooksAlreadyPutInCart(cartBookAdderDto);
    }

}

