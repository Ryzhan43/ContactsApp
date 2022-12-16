package com.example.contactsapp;

import com.example.contactsapp.datamodel.Contact;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class DialogController {
        @FXML
        private TextField firstNameInput;
        @FXML
        private TextField lastNameInput;

        @FXML
        private TextField phoneNumInput;
        @FXML

        private TextArea notesInput;

        @FXML
        private DatePicker deadLinePicker;

        public Contact processResault(){
            String firstName = firstNameInput.getText().trim();
            String lastName = lastNameInput.getText().trim();
            String phoneNum = phoneNumInput.getText().trim();
            String notes = notesInput.getText().trim();

            Contact cntkt = new Contact(firstName,lastName,phoneNum,notes);
            return cntkt;
        }

    }

