/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.clientFriendRequest;

import com.jdevsul.DBclasses.Client;
import com.jdevsul.DBclasses.FriendRequest;
import com.jdevsul.clientimp.ClientImpl;
import com.jdevsul.interfaces.ServerManagerInt;
import com.jdevsul.interfaces.ServerRequestsInt;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
public class FriendRequestController implements Initializable {

    @FXML
    private ListView<Client> friendRequestList;

    private ServerManagerInt serverManagerRef;
    private ServerRequestsInt serverRequestsInt;
    private ClientImpl clientImpl;

    ArrayList<Client> requests;

    //DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Registry reg = LocateRegistry.getRegistry(7474);
            serverManagerRef = (ServerManagerInt) reg.lookup("ChatService");
            serverRequestsInt = serverManagerRef.getServerRequests();

            ClientImpl clientImpl = ClientImpl.getInstance();
            int currentClientID = clientImpl.getCurrentClient().getClientID();

            ArrayList<Client> friendReqs = serverRequestsInt.getMyRequested(currentClientID);
            updateMyFriendRequests(currentClientID, friendReqs);

        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(FriendRequestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateMyFriendRequests(final int clientID, ArrayList<Client> firendRequest) {

        requests = firendRequest;
        ObservableList<Client> myContacts = FXCollections.observableArrayList(firendRequest);

        //add the contacts to the listView
        friendRequestList.setItems(myContacts);

        //make custom render --> updateItem
        friendRequestList.setCellFactory(new Callback<ListView<Client>, ListCell<Client>>() {
            @Override
            public ListCell<Client> call(ListView<Client> param) {
                return new ListCell<Client>() {
                    @Override
                    protected void updateItem(final Client myFriend, boolean empty) {
                        try {
                            super.updateItem(myFriend, empty);
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("customFriendRequest.fxml"));
                            Pane parent = loader.load();
                            Circle contactImage = (Circle) parent.getChildren().get(0);
                            Label contactName = (Label) parent.getChildren().get(1);

                            JFXButton confirmBtn = (JFXButton) parent.getChildren().get(2);
                            JFXButton ignoreBtn = (JFXButton) parent.getChildren().get(3);

                            Image image = null;
                            ImagePattern myimImagePattern = null;

                            if (myFriend != null && !empty) {

                                //all client images are on folder on server
                                //getClientImage returns the path of the image
                                image = new Image(myFriend.getClientImage(), 50, 50, false, false);
                                myimImagePattern = new ImagePattern(image);
                                contactImage = new Circle();
                                contactImage.setFill(myimImagePattern);
                                contactImage.setRadius(25);
                                contactImage.setVisible(true);

                                contactName.setText(myFriend.getClientName());

                                confirmBtn.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        FriendRequest friendRequest = new FriendRequest();
                                        friendRequest.setClientID(clientID);
                                        friendRequest.setFriendID(myFriend.getClientID());
                                        //0 mean confirmed request so this request will be added to the contacts
                                        try {
                                            serverRequestsInt.removeFriendRequest(friendRequest, 0);
                                            ArrayList<Client> updatedRequests = serverRequestsInt.getMyRequested(clientID);
                                            friendRequestList.setItems(FXCollections.observableArrayList(updatedRequests));
                                        } catch (RemoteException ex) {
                                            Logger.getLogger(FriendRequestController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                });

                                ignoreBtn.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        FriendRequest friendRequest = new FriendRequest();
                                        friendRequest.setClientID(clientID);
                                        friendRequest.setFriendID(myFriend.getClientID());
                                        try {
                                            //1 mean rejected request
                                            serverRequestsInt.removeFriendRequest(friendRequest, 1);
                                            ArrayList<Client> updatedRequests = serverRequestsInt.getMyRequested(clientID);
                                            friendRequestList.setItems(FXCollections.observableArrayList(updatedRequests));
                                        } catch (RemoteException ex) {
                                            Logger.getLogger(FriendRequestController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                });

                                setGraphic(parent);
                            } else {
                                setGraphic(null);
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(FriendRequestController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                };
            }
        });
    }

}
