/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.clientContacts;

import com.jdevsul.DBclasses.Client;
import com.jdevsul.DBclasses.Group;
import com.jdevsul.clientFriendRequest.FriendRequestController;
import com.jdevsul.clientimp.ClientImpl;
import com.jdevsul.interfaces.ServerManagerInt;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Eman-PC
 */
public class MyContactListController implements Initializable {

    @FXML
    private ListView<Client> myContactList;
    int currentClientID;
    ClientImpl clientImpl;

    public void updateMyContacts(ArrayList<Client> contacts) {

        Collections.sort(contacts, new Comparator<Client>() {
            @Override
            public int compare(Client c1, Client c2) {
                return c1.getClientOnline() - c2.getClientOnline();
            }
        });
        ObservableList<Client> myContacts = FXCollections.observableArrayList(contacts);
        //add the contacts to the listView
        myContactList.setItems(myContacts);
        //make custom render --> updateItem
        myContactList.setCellFactory(new Callback<ListView<Client>, ListCell<Client>>() {
            @Override
            public ListCell<Client> call(ListView<Client> param) {
                return new ListCell<Client>() {
                    @Override
                    protected void updateItem(Client myContact, boolean empty) {
                        try {
                            super.updateItem(myContact, empty);
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomList.fxml"));

                            Pane parent = loader.load();
                            Circle contactImage = (Circle) parent.getChildren().get(0);
                            Circle contactOnline = (Circle) parent.getChildren().get(1);

                            Label contactStatus = (Label) parent.getChildren().get(3);
                            Label contactName = (Label) parent.getChildren().get(2);

                            Image image = null;
                            ImagePattern myimImagePattern = null;
                            if (myContact != null && !empty) {

                                //all client images are on folder on server
                                //getClientImage returns the path of the image
                                image = new Image(myContact.getClientImage(), 50, 50, false, false);
                        //          image = new Image("clientjavafx2rmi/offline.png", 50, 50, false, false);
                                myimImagePattern = new ImagePattern(image);

                                contactImage.setFill(myimImagePattern);
                                contactImage.setRadius(25);
                                contactImage.setVisible(true);

                                if (myContact.getClientOnline() == 0) {
                                    contactOnline.setFill(Color.GREEN);
                                } else {
                                    contactOnline.setFill(Color.RED);
                                }
                                contactName.setText(myContact.getClientName());

                                //when the user click on the vbox anywhere
                                /* containerVBox.setOnMousePressed(new EventHandler<MouseEvent>() {
    
    @Override
    public void handle(MouseEvent event) {
    
    //   test1.setText(String.valueOf(myContact.getClientID()));
    }
    });*/
                                //when the user press done after choosing his/her group
//                                done.setOnAction(new EventHandler<ActionEvent>() {
//                                    @Override
//                                    public void handle(ActionEvent event) {
//
//                                        ObservableList<Client> selectedItems = myContactList.getSelectionModel().getSelectedItems();
//                                        Group group = new Group();
//                                        group.setClientID(myContact.getClientID());
//
//                                        ArrayList<Client> selectedClients = new ArrayList<>();
//                                        selectedItems.forEach((client) -> {
//                                            selectedClients.add(client);
//                                        });
//
//                                        String test = "";
//                                        for (Client c : selectedItems) {
//                                            test += String.valueOf(c.getClientID());
//                                        }
//                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                                        alert.setTitle("Information Dialog");
//                                        alert.setHeaderText("I have a great message for you!");
//                                        alert.setContentText(String.valueOf(test));
//
//                                        alert.showAndWait();
//                                    }
//                                });
                                contactStatus.setText(myContact.getClientStatus());
                                setGraphic(parent);
                            } else {
                                setGraphic(null);
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(MyContactListController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                };
            }
        });
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        clientImpl = ClientImpl.getInstance();
        //  myContactList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        currentClientID = clientImpl.getCurrentClient().getClientID();

    }

}
