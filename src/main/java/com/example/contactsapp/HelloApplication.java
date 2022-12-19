package com.example.contactsapp;

import com.example.contactsapp.datamodel.ContactData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.contactsapp.datamodel.ContactData;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 240);
        stage.setTitle("My Contacts");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() throws Exception{
        System.out.println("Init happened");
    }

    @Override
    public void stop() throws Exception{
        try{
            ContactData.getInstance().storeContanctsData();
        } catch (Exception e) {
              e.printStackTrace();
            System.out.println("file was not saved");
            }
    }
}

