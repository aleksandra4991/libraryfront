package com.kodilla.libraryfront.websides;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route
public class ReaderAccount extends VerticalLayout {

    Button logOut = new Button("Log out");
    Button editingData = new Button("Edit your profile");
    Button checkYourActiveReservations = new Button("My Reservations");
    Button addBookToCart = new Button("Add to cart");
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

