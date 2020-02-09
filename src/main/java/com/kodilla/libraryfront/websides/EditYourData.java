package com.kodilla.libraryfront.websides;

import com.kodilla.libraryfront.client.LibraryBackendClient;
import com.kodilla.libraryfront.dto.ReaderDto;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;

import java.awt.*;

@Route
public class EditYourData extends HorizontalLayout {

    private final LibraryBackendClient libraryBackendClient;

    private TextField name;
    private TextField phoneNumber;
    private TextField emailAddress;
    private PasswordField password;
    private Button saveTheData;

    private String uid;

    public EditYourData(LibraryBackendClient libraryBackendClient) {
        this.libraryBackendClient = libraryBackendClient;

        name = new TextField("Name");
        phoneNumber = new TextField("Phone Number");
        emailAddress = new TextField("Email Address");
        password= new PasswordField("Password","Min.8liter,1 znak specjalny");
        saveTheData = new Button("Save");

        name.setClearButtonVisible(true);
        name.setMaxLength(20);
        name.setValueChangeMode(ValueChangeMode.EAGER);
        name.setEnabled(false);
        phoneNumber.setClearButtonVisible(true);
        phoneNumber.setMaxLength(9);
        phoneNumber.setValueChangeMode(ValueChangeMode.EAGER);
        emailAddress.setClearButtonVisible(true);
        emailAddress.setMaxLength(30);
        emailAddress.setValueChangeMode(ValueChangeMode.EAGER);
        password.setClearButtonVisible(true);
        password.setMaxLength(15);
        password.setValueChangeMode(ValueChangeMode.EAGER);

        saveTheData.addActionListener(e->editTheReaderData());

    }

    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
        if (parameter != null && !parameter.equals("null")) {
            uid = parameter;
            ReaderDto readerDto = libraryBackendClient.getReaderByUid(uid);
            name.setValue(readerDto.getReaderName());
            phoneNumber.setValue(readerDto.getPhoneNumber());
            emailAddress.setValue(readerDto.getEmailAdress());
            password.setValue(readerDto.getPassword());
        }
    }

    private void editTheReaderData() {
        ReaderDto readerDto = libraryBackendClient.getReaderByUid(uid);
        libraryBackendClient.changeReaderData(new ReaderDto(readerDto.getReaderId(),name.getValue(),phoneNumber.getValue(),emailAddress.getValue(),password.getValue()));
    }
}
