package com.kodilla.libraryfront;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route
public class CreateNewAccount extends VerticalLayout {

    TextField name = new TextField("Name");
    TextField phoneNumber = new TextField("Phone Number");
    TextField emailAddress = new TextField("Email Address");
    TextField password = new TextField("Password");
    Button createNewAccount = new Button("Create an Account");
    Button goBackToLogIn = new Button("Log In");
    /*private Binder<Reader> binder = new Binder<>(Reader.class);
    private ReaderService readerService = ReaderService.getInstance();
    private void save() {
        Reader reader = binder.getBean();
        readerService.createReader(reader);
    }
    public CreateNewAccount(){
        HorizontalLayout buttons = new HorizontalLayout(createNewAccount);
        add(name,phoneNumber,emailAddress,password,createNewAccount);
        binder.bindInstanceFields(this);
        createNewAccount.addClickListener(event -> save());
        goBackToLogIn.addClickListener(event -> getUI().get().navigate(String.valueOf(LogIn.class)));
    }
*/
}


