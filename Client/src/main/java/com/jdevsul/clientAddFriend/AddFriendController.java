///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.jdevsul.clientAddFriend;
//
//
//import com.jfoenix.controls.JFXButton;
//import com.jfoenix.controls.JFXTextField;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.ResourceBundle;
//import javafx.collections.FXCollections;
//import javafx.event.ActionEvent;
//import javafx.event.Event;
//import javafx.event.EventHandler;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.TextField;
//import javafx.scene.input.DragEvent;
//import javafx.scene.input.MouseDragEvent;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.text.Text;
//import com.jdevsul.DBclasses.Client;
//import com.jdevsul.DBclasses.FriendRequest;
//import com.jdevsul.DBclasses.Contact;
//
//
///**
// * FXML Controller class
// *
// * @author Eman-PC
// */
//public class AddFriendController implements Initializable {
//
//    DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
//
//    @FXML
//    private TextField friendEmail;
//    @FXML
//    private Text errorMessage;
//    @FXML
//    private AnchorPane addFriend;
//    @FXML
//    private JFXButton add;
//
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//    }
//
//    public void addNewFriend(int clientID, String email) {
//        //first check that this email exists
//        //then check that this email has not already existed in contacts of user
//        //then check that this email has not already had a friend request from user
//
//        Client client = databaseHandler.getClientByEmail(email);
//
//        //first check that this email exists
//        if (client == null) {
//            errorMessage.setText("Please enter a valid email");
//        } else {
//            //check that this email has not already existed in contacts of user
//            Contact contact = new Contact();
//            contact.setClientID(clientID);
//            contact.setContactID(client.getClientID());
//            boolean isContact = databaseHandler.isContact(contact);
//            if (isContact) {
//                errorMessage.setText("You and this user are already friends");
//            } else {
//                //check that this email has not already had a friend request from user
//                FriendRequest friendRequest = new FriendRequest();
//                friendRequest.setClientID(client.getClientID());
//                friendRequest.setFriendID(clientID);
//                boolean isFriendRequestExist = databaseHandler.isFriendRequestExist(friendRequest);
//                if (isFriendRequestExist) {
//                    errorMessage.setText("You had already sent a friend request before to this person");
//                } else {
//                    //send friend request
//                    errorMessage.setText("Friend request has been sent sucessfully");
//                    databaseHandler.addNewFriendRequest(friendRequest);
//
//                }
//            }
//
//        }
//    }
//
//    @FXML
//    private void onEnter(ActionEvent event) {
//        //addNewFriend(1, friendEmail.getText().trim());
//    }
//
//    @FXML
//    private void HandleOnBackPressed(MouseEvent event) {
//    }
//
//    @FXML
//    private void SendRequest(MouseDragEvent event) {
//         addNewFriend(1, friendEmail.getText().trim());
//    }
//
//    @FXML
//    private void send(DragEvent event) {
//        addNewFriend(1, friendEmail.getText().trim());
//    }
//
//}
