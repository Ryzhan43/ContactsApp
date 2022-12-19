package com.example.contactsapp;

import com.example.contactsapp.datamodel.Contact;
import com.example.contactsapp.datamodel.ContactData;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


public class HelloController {
    @FXML
    private TableView<Contact> contactsTable;

    @FXML
    private AnchorPane mainBorderPane;

    private Contact contact;

    public void initialize() {
        List<Contact> listContacts = List.of(
                new Contact("Mykyta", "Ryzhan", "4164599676", "Such a cool guy"),
                new Contact("Karen", "Cane", "4164423423", "None"),
                new Contact("Mykyta", "Ryzhan", "4412312313", "Such a cool guy"),
                new Contact("Mykyta", "Ryzhan", "4412312313", "Such a cool guy"));


        //Setting all the columns up
        TableColumn<Contact, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn<Contact, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn<Contact, String> phoneNumCol = new TableColumn<>("Phone Number");
        phoneNumCol.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
        TableColumn<Contact, String> notesCol = new TableColumn<>("Notes");
        notesCol.setCellValueFactory(new PropertyValueFactory<>("notes"));
        contactsTable.getColumns().setAll(firstNameCol, lastNameCol,phoneNumCol,notesCol);

        ObservableList<Contact> teamMembers = FXCollections.observableArrayList(listContacts);
        contactsTable.getItems().addAll(listContacts);

        TableView.TableViewSelectionModel<Contact> selectionModel = contactsTable.getSelectionModel();

        ObservableList<Contact> selectedItems = selectionModel.getSelectedItems();

        ObservableList<Contact> osvblCnktkList = FXCollections.observableList(listContacts);
        ContactData.getInstance().setContactsList(osvblCnktkList);
        selectedItems.addListener(
                new ListChangeListener<Contact>() {
                    @Override
                    public void onChanged(
                            Change<? extends Contact> change) {
                        System.out.println(
                                "Selection changed: " + change.getList());
                    }
                });
    }

    @FXML
    public void showNewItemDialog(){
        System.out.println("Add Item Clicked");
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Add a new item");
        dialog.setHeaderText("You can set a header here");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("contactsItemDialog.fxml"));
        try {
            System.out.print(".... and we are here");
            dialog.getDialogPane().setContent(fxmlLoader.load());
            System.out.println(dialog.toString());
        } catch (IOException e) {
            System.out.println("Coudnt print dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();

        if(result.isPresent() && result.get() ==ButtonType.OK) {
            System.out.println("OK");
            DialogController controller = fxmlLoader.getController();
            Contact newContact = controller.processResault();
            contactsTable.getItems().add(newContact);
            ContactData.getInstance().addContact(newContact);
        } else {
            System.out.println("Cancel pressed");
        }

    }
}

