/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.clientFriendRequest;

import com.jdevsul.DBclasses.Client;
import com.jdevsul.DBclasses.FriendRequest;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
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
    @FXML
    private TextField friendText;


    /**
     * Initializes the controller class.
     */
    
    DatabaseHandler databaseHandler = new DatabaseHandler();
  
    public void updateMyFriendRequests(final int clientID, ArrayList<Client> firendRequest) {

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
                        super.updateItem(myFriend, empty);

                        //containerVBox include all boxes
                        HBox containerVBox = null;

                        //requestInfo include name, buttons
                        VBox requestInfo = null;
                        //confirmDelete include confirm,delete
                        HBox confirmDelete = null;

                        //images include image
                        VBox images = null;

                        //name include client name
                        HBox name = null;

                        Image image = null;
                        ImagePattern myimImagePattern = null;
                        Circle contactImage = null;

                        Button confirmBtn = null;
                        Button deleteBtn = null;

                        Text contactName = null;

                        if (myFriend != null && !empty) {
                            containerVBox = new HBox();
                            requestInfo = new VBox();
                            confirmDelete = new HBox();
                            confirmBtn = new Button("Confirm");
                            deleteBtn = new Button("Delete");
                            images = new VBox();
                            name = new HBox();

                            //all client images are on folder on server
                            //getClientImage returns the path of the image
                            image = new Image(myFriend.getClientImage(), 50, 50, false, false);
                            myimImagePattern = new ImagePattern(image);
                            contactImage = new Circle();
                            contactImage.setFill(myimImagePattern);
                            contactImage.setRadius(25);
                            contactImage.setVisible(true);

                            contactName = new Text(myFriend.getClientName());

                            //when the user click on the vbox anywhere
                            containerVBox.setOnMousePressed(new EventHandler<MouseEvent>() {

                                @Override
                                public void handle(MouseEvent event) {

                                    friendText.setText(String.valueOf(myFriend.getClientID()));

                                }
                            });

                            confirmBtn.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    FriendRequest friendRequest = new FriendRequest();
                                    friendRequest.setClientID(clientID);
                                    friendRequest.setFriendID(myFriend.getClientID());
                                    //0 mean confirmed request so this request will be added to the contacts
                                    databaseHandler.removeFriendRequest(friendRequest, 0);

                                    ArrayList<Client> updatedRequests = databaseHandler.getMyFriendRequests(clientID);
                                    friendRequestList.setItems(FXCollections.observableArrayList(updatedRequests));
                                }
                            });

                            deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    FriendRequest friendRequest = new FriendRequest();
                                    friendRequest.setClientID(clientID);
                                    friendRequest.setFriendID(myFriend.getClientID());
                                    //1 mean rejected request
                                    databaseHandler.removeFriendRequest(friendRequest, 1);

                                    ArrayList<Client> updatedRequests = databaseHandler.getMyFriendRequests(clientID);
                                    friendRequestList.setItems(FXCollections.observableArrayList(updatedRequests));
                                }
                            });

                            contactName.setTextAlignment(TextAlignment.CENTER);
                            contactName.setFont(Font.font("Agency FB", FontWeight.BOLD, 25));

                            confirmBtn.setFont(Font.font("Agency FB", FontWeight.BOLD, 18));
                            deleteBtn.setFont(Font.font("Agency FB", FontWeight.BOLD, 18));

                            images.getChildren().add(contactImage);
                            images.setAlignment(Pos.BASELINE_LEFT);
                            images.setId("imagehbox");

                            name.getChildren().add(contactName);
                            name.setAlignment(Pos.BASELINE_LEFT);

                            confirmDelete.setId("confirmDeleteBtns");
                            confirmDelete.getChildren().addAll(confirmBtn, deleteBtn);

                            requestInfo.setSpacing(10);
                            requestInfo.getChildren().addAll(name, confirmDelete);

                            containerVBox.setId("contactBox");
                            containerVBox.setSpacing(15);
                            containerVBox.getChildren().addAll(images, requestInfo);

                            setGraphic(containerVBox);
                        } else {
                            setGraphic(null);
                        }
                    }
                };
            }
        });
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
