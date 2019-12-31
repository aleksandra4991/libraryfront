package com.kodilla.libraryfront.websides;

import com.kodilla.libraryfront.client.LibraryBackendClient;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.Route;

@Route
public class ReaderAccount extends VerticalLayout {

    private final LibraryBackendClient libraryBackendClient;

    private Button logOut;
    //private Button editingData;
    private Button checkYourActiveReservations;
    private Button checkBooksRentedNowByYou;
    private Select<String> authorSelect;
    private Button findABook;
    private Button addBookToCart;

    private Long defaultReaderId;

    public ReaderAccount(LibraryBackendClient libraryBackendClient) {
        this.libraryBackendClient = libraryBackendClient;

        logOut = new Button( "Log out");
        //editingData = new Button("Edit your profile");
        checkYourActiveReservations = new Button("My Reservations");
        checkBooksRentedNowByYou = new Button("My Books");
        addBookToCart = new Button("Add to cart");
        findABook = new Button("Find Book Of This Author");

        findABook.addClickListener(e -> {
            String location;
            String selectedAuthor = authorSelect.getValue();
            if (selectedAuthor != null) {
                location = "list/" + selectedAuthor /*+ "&" + uuid*/;
                findABook.getUI().ifPresent(ui ->
                        ui.navigate(location));
            } else if (selectedAuthor == null) {
                location = "list/" + "&null" + "&"/* + uuid*/;
                findABook.getUI().ifPresent(ui ->
                        ui.navigate(location));
            }});

        authorSelect = new Select<>();
        authorSelect.setEmptySelectionAllowed(true);
        authorSelect.setRequiredIndicatorVisible(true);
        authorSelect.setLabel("Author");
        authorSelect.setPlaceholder("Select the Author");
        authorSelect.setItems();


        defaultReaderId = new Long(1);

        logOut.addClickListener((event -> {
                    getUI().get().navigate(MainPage.class);
                }));

        //checkYourActiveReservations.addClickListener(libraryBackendClient.getBooksReservedByUseer(defaultReaderId));

        //checkBooksRentedNowByYou.addClickListener(libraryBackendClient.getBooksRentedByUseer(defaultReaderId));
    /*private BookService bookService = BookService.getInstance();
    private Grid<Book> grid = new Grid<>(Book.class);
    private TextField filter = new TextField();
    private Binder<Reader> binderOfReader = new Binder<>(Reader.class);
    //private Binder<Reservation> binderOfReservation = new Binder<>(Reservation.class);
    private ReaderService readerService = ReaderService.getInstance();
    private void getReaderReservation() {
        Reader reader = binderOfReader.getBean();
        readerService.getReservationsOfSpecifiedReader(reader.getId());
    }
    private Binder<Cart> binderOfCart= new Binder<>(Cart.class);
    private CartService cartService = CartService.getInstance();
    private void AddBookToCart() {
        Cart cart = binderOfCart.getBean();
        //cartService.addBookWithSpecifiedIdToSpecifiedCart(cart.getId(),);
    }
    public ReaderAccount(){
        logOut.addClickListener(event -> getUI().get().navigate(String.valueOf(MainPage.class)));
        grid.setColumns("title", "author", "year", "rented","genre");
        add(logOut,editingData,grid,checkYourActiveReservations,addBookToCart);
    }
    private void update() {
        grid.setItems(bookService.findByTitle(filter.getValue()));
    }
    public void refresh() {
        grid.setItems(bookService.getAllBooks());
    }
*/

    }
}