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
    private long titleId;

    private VerticalLayout pageLayout = new VerticalLayout();
    private HorizontalLayout mainUpMenu = new HorizontalLayout();
    private HorizontalLayout detailsMenu = new HorizontalLayout();
    private VerticalLayout lookForAndChooseLeftMenu = new VerticalLayout();
    private HorizontalLayout selection = new HorizontalLayout();
    private HorizontalLayout choosingAtitle = new HorizontalLayout();
    private HorizontalLayout cartRightBottomMenu = new HorizontalLayout();

    //reader(right) menu
    private Button checkYourActiveReservations;
    private Button checktitlesNowRented;
    private Button editingData;
    private Button logOut;

    //lookingForAtitle(left)manu
    private TextField title;
    private Dialog lookingForAtitleDialog;
    private Dialog incorrectLookingForAtitleDialog;
    private Label lookingForAtitleLabel;
    private Label incorrectLookingForAtitleLabel;
    private Button findAtitle;
    private Button addtitleToCart;


    //Cart
    private Details cartDetails;
    private Button goToCart;

    //Google Books
    private Grid<VolumeDto> googleBooks;

    
  public ReaderAccount(LibraryBackendClient libraryBackendClient) {
        this.libraryBackendClient = libraryBackendClient;

        checkYourActiveReservations = new Button("My Reservations");
        checktitlesNowRented = new Button("My titles");
        editingData = new Button("Edit your profile");
        logOut = new Button("Log out");

        title = new TextField();
        findAtitle = new Button("Find title");
        lookingForAtitleDialog = new Dialog();
        incorrectLookingForAtitleDialog = new Dialog();
        lookingForAtitleLabel = new Label();
        incorrectLookingForAtitleLabel = new Label();
        addtitleToCart = new Button("Add to cart");

        lookingForAtitleDialog.add(lookingForAtitleLabel);
        incorrectLookingForAtitleDialog.add(incorrectLookingForAtitleLabel);

        cartDetails = new Details();
        goToCart = new Button("Cart");

        googleBooks = new Grid<>(VolumeDto.class);
        googleBooks.setColumns("title","authors","publishedDate","description");
        googleBooks.setSelectionMode(Grid.SelectionMode.MULTI);

        add(pageLayout);
        pageLayout.addComponentAtIndex(0,mainUpMenu);
        pageLayout.addComponentAtIndex(1,detailsMenu);
        pageLayout.addComponentAtIndex(2, googleBooks);
        mainUpMenu.addComponentAtIndex(0,checktitlesNowRented);
        mainUpMenu.addComponentAtIndex(1,checkYourActiveReservations);
        mainUpMenu.addComponentAtIndex(2,editingData);
        mainUpMenu.addComponentAtIndex(3,logOut);
        detailsMenu.addComponentAtIndex(0,lookForAndChooseLeftMenu);
        detailsMenu.addComponentAtIndex(1,cartRightBottomMenu);
        lookForAndChooseLeftMenu.addComponentAtIndex(0,selection);
        lookForAndChooseLeftMenu.addComponentAtIndex(1,choosingAtitle);
        selection.addComponentAtIndex(0,title);
        choosingAtitle.addComponentAtIndex(0,findAtitle);
        choosingAtitle.addComponentAtIndex(1,addtitleToCart);
        cartRightBottomMenu.addComponentAtIndex(0,cartDetails);
        cartRightBottomMenu.addComponentAtIndex(1,goToCart);
        pageLayout.setAlignItems(Alignment.CENTER);

        checkYourActiveReservations.addClickListener(e->{
            getUI().get().navigate(ReservationsAndRents.class);
        });

        checktitlesNowRented.addClickListener(e->{
                getUI().get().navigate(ReservationsAndRents.class);
  });

        editingData.addClickListener(event -> {
            getUI().get().navigate(EditYourData.class);
        });

        logOut.addClickListener(event -> {
            getUI().get().navigate(MainPage.class);
        });


      title.setValueChangeMode(ValueChangeMode.EAGER);
      title.setAutocomplete(Autocomplete.ON);
      title.setAutoselect(true);
      title.setRequiredIndicatorVisible(true);
      title.setLabel("title - type into 1 word");

      findAtitle.addClickListener(e -> {
                    String titleOfSelectedtitle = title.getValue();
                    if ((titleOfSelectedtitle != null)){
                        libraryBackendClient.getVolumeByTitle(titleOfSelectedtitle);
                        lookingForAtitleLabel.setText("Volumes containg requested title: " + "\"" + titleOfSelectedtitle + "\" "  + " have found");
                        lookingForAtitleDialog.open();
                        getFoundBooks();
                    }
                    else{
                        incorrectLookingForAtitleLabel.setText("Unfortunately volume: " + "\"" + titleOfSelectedtitle + " " + " have not found");
                        incorrectLookingForAtitleDialog.open();
                    }


      });


      //addtitleToCart.addClickListener(e->putAtitleInACart());

      cartDetails.addContent();

      goToCart.addClickListener(e-> viewYourCart());

    }

    public void getFoundBooks(){
        googleBooks.setItems(libraryBackendClient.getVolumeByTitle(title.getValue()));
        googleBooks.asMultiSelect();

    }
    /*public void putAtitleInACart(){
        CarttitleAdderDto carttitleAdderDto = libraryBackendClient.getCartById(idOfCart);
        VolumeDto volumeDto = libraryBackendClient.getVolumeBytitleAndAuthor(title.getValue(),authors.getValue());
        libraryBackendClient.puttitleInCart(carttitleAdderDto.getCartId(),volumeDto);
    }*/

    public void viewYourCart(){
        CartBookAdderDto cartBookAdderDto = libraryBackendClient.getCartById(idOfCart);
        libraryBackendClient.getBooksPutInCart(cartBookAdderDto);
    }

}

