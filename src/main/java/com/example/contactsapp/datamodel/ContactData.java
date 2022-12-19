package com.example.contactsapp.datamodel;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class ContactData {

    private static ContactData instance = new ContactData();
    private static String filename = "ContactList.txt";
    private ObservableList<Contact> contactsList;

    public static ContactData getInstance(){
        return instance;
    }

    public List<Contact> getContactsData(){
        return this.contactsList;
    }

    public void addContact(Contact addCntkt){
        this.contactsList.add(addCntkt);
    }

    public void setContactsList(ObservableList<Contact> cntktList){
        this.contactsList = cntktList;
    }

    public void loadContactsData() throws IOException{

    }
    public void storeContanctsData() throws IOException{
        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try{
            Iterator<Contact> iter = contactsList.iterator();
            while (iter.hasNext()){
                Contact cntkt = iter.next();
                bw.write(String.format("%s\t%s\t%s\t%s",
                                        cntkt.getFirstName(),
                                        cntkt.getLastName(),
                                        cntkt.getPhoneNum(),
                                        cntkt.getNotes()));
                bw.newLine();
            }
        } finally {
            if(bw != null){
                bw.close();
            }
        }
    }
}
