package com.kodilla.libraryfront.websides;

import com.kodilla.libraryfront.client.LibraryBackendClient;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route
public class LogIn extends VerticalLayout {

    private final LibraryBackendClient libraryBackendClient;
    private boolean logged = true;

    private TextField email;
    private TextField password;
    private Button logIn;
    private Dialog loginIncorrectDialog;
    private Label loginIncorrectLabel;
    private VerticalLayout layoutOfLogInPage = new VerticalLayout();

    public LogIn(LibraryBackendClient libraryBackendClient) {
        this.libraryBackendClient=libraryBackendClient;

        email = new TextField("Email");
        password = new TextField("Password");
        logIn = new Button("Log In");
        loginIncorrectDialog = new Dialog();
        loginIncorrectLabel = new Label("Podane imię lub hasło są nieprawidłowe." +
                " Spróbuj ponownie.");
        loginIncorrectDialog.add(loginIncorrectLabel);

        add(layoutOfLogInPage);
        layoutOfLogInPage.add(email,password,logIn);
        layoutOfLogInPage.setAlignItems(Alignment.CENTER);

        logIn.addClickListener(event -> {
            logged = libraryBackendClient.login(email.getValue(),password.getValue());
            if(logged){
                String location = "" + libraryBackendClient.getReaderByLoginData(email.getValue(), password.getValue());
                logIn.getUI().ifPresent(ui ->
                        ui.navigate(location));
            } else {
                loginIncorrectDialog.open();
            }

        });


    }

}




