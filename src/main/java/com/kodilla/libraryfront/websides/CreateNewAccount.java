package com.kodilla.libraryfront.websides;

import com.kodilla.libraryfront.client.LibraryBackendClient;
import com.kodilla.libraryfront.dto.ReaderDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route
public class CreateNewAccount extends VerticalLayout {

    private final LibraryBackendClient libraryBackendClient;
    private VerticalLayout registration = new VerticalLayout();
    private HorizontalLayout accountButtons = new HorizontalLayout();
    private TextField name;
    private TextField phoneNumber;
    private TextField emailAddress;
    private PasswordField password;
    private Button createNewAccount;
    private Button goBackToLogIn;

    private Label registrationLabel = new Label ("Rejestracja przebiegła pomyślnie,zaloguj się na swoje konto");

    private Label registrationIncorrectLabel = new Label("Podany email lub hasło są nieprawidłowe." +
            " Spróbuj ponownie.");

    private Dialog registrationIncorrectDialog = new Dialog();
    private Dialog registrationDialog = new Dialog();

    private String uuid;

    public CreateNewAccount(LibraryBackendClient libraryBackendClient){
        this.libraryBackendClient=libraryBackendClient;

        add(registration,accountButtons);

        name = new TextField("Name");
        phoneNumber = new TextField("Phone Number");
        emailAddress = new TextField("Email Address");
        password= new PasswordField("Password","Min.8liter,1 znak specjalny");

        createNewAccount= new Button("Create an Account");
        goBackToLogIn = new Button("Log In");
        accountButtons.add(createNewAccount,goBackToLogIn);

        registration.add(name,phoneNumber,emailAddress,password);
        registration.add(accountButtons);
        registration.setHorizontalComponentAlignment(Alignment.CENTER,name,phoneNumber,emailAddress,password,accountButtons);

        name.setClearButtonVisible(true);
        name.setMaxLength(20);
        name.setValueChangeMode(ValueChangeMode.EAGER);
        phoneNumber.setClearButtonVisible(true);
        phoneNumber.setMaxLength(9);
        phoneNumber.setValueChangeMode(ValueChangeMode.EAGER);
        emailAddress.setClearButtonVisible(true);
        emailAddress.setMaxLength(30);
        emailAddress.setValueChangeMode(ValueChangeMode.EAGER);
        password.setClearButtonVisible(true);
        password.setMaxLength(15);
        password.setValueChangeMode(ValueChangeMode.EAGER);

        registrationIncorrectDialog.add(registrationIncorrectLabel);
        registrationDialog.add(registrationLabel);

        createNewAccount.addClickListener(e->{
            createNewReaderAccount();
            registrationDialog.open();
            try{
                createNewAccount.getUI().get().navigate(LogIn.class);

            } catch (Exception n) {
                registrationIncorrectDialog.open();
            }
            name.clear();
            phoneNumber.clear();
            emailAddress.clear();
            password.clear();
        });

        goBackToLogIn.addClickListener(event -> getUI().get().navigate((LogIn.class)));
    }

    private ReaderDto createNewReaderAccount() {
        ReaderDto readerDto = new ReaderDto(name.getValue(),phoneNumber.getValue(),emailAddress.getValue(),password.getValue());
        return libraryBackendClient.createReader(readerDto);
    }
}


