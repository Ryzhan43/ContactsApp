package com.example.contactsapp.datamodel;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ContactData {

    private static ContactData instance = new ContactData();
    private static String filename = "ContactList.txt";
    private ObservableList<Contact> contactsList;



}
