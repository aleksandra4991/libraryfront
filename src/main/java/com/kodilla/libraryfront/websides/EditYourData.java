package com.kodilla.libraryfront.websides;

import com.kodilla.libraryfront.client.LibraryBackendClient;
import com.kodilla.libraryfront.dto.ReaderDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;

@Route
public class EditYourData extends VerticalLayout {

    private final LibraryBackendClient libraryBackendClient;

    private VerticalLayout editPageLayOut = new VerticalLayout();
    private VerticalLayout fields = new VerticalLayout();

    private TextField name;
    private TextField phoneNumber;
    private TextField emailAddress;
    private PasswordField password;
    private Button saveTheData;

    private String readerId;

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
        name.setValue(libraryBackendClient.getReaderByUid(readerId).getReaderName());
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

        add(fields,editPageLayOut);
        editPageLayOut.addComponentAsFirst(fields);
        editPageLayOut.add(saveTheData);
        fields.addComponentAtIndex(0,name);
        fields.addComponentAtIndex(1,phoneNumber);
        fields.addComponentAtIndex(2,emailAddress);
        fields.addComponentAtIndex(3,password);
        editPageLayOut.setHorizontalComponentAlignment(Alignment.CENTER,name,phoneNumber,emailAddress,password,saveTheData);

        saveTheData.addClickListener(e->editTheReaderData());

    }

    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
        if (parameter != null && !parameter.equals("null")) {
            String readerId = parameter;
            ReaderDto readerDto = libraryBackendClient.getReaderByUid(readerId);
            name.setValue(readerDto.getReaderName());
            phoneNumber.setValue(readerDto.getPhoneNumber());
            emailAddress.setValue(readerDto.getEmailAdress());
            password.setValue(readerDto.getPassword());
        }
    }

    private void editTheReaderData() {
        ReaderDto readerDto = libraryBackendClient.getReaderByUid(readerId);
        libraryBackendClient.changeReaderData(new ReaderDto(readerDto.getReaderId(),name.getValue(),phoneNumber.getValue(),emailAddress.getValue(),password.getValue()));
    }
}
