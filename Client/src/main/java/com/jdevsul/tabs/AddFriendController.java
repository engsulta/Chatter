package com.jdevsul.tabs;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.jdevsul.DBclasses.Client;
import com.jdevsul.DBclasses.FriendRequest;
import com.jdevsul.clientFriendRequest.FriendRequestController;
import com.jdevsul.clientimp.ClientImpl;
import com.jdevsul.interfaces.ServerAuthInt;
import com.jdevsul.interfaces.ServerManagerInt;
import com.jdevsul.interfaces.ServerRequestsInt;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author sulta
 */
public class AddFriendController implements Initializable {

    @FXML
    private AnchorPane addFriend;
    @FXML
    private JFXTextField UserName;
    @FXML
    private TextField friendEmail;
    @FXML
    private Text errorMessage;

    private ServerManagerInt serverManagerRef;
    private ServerAuthInt serverAuthInt;
    private ServerRequestsInt serverRequestsInt;
    int currentClientID;
    ClientImpl clientImpl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            Registry reg = LocateRegistry.getRegistry(7474);
            serverManagerRef = (ServerManagerInt) reg.lookup("ChatService");
            serverAuthInt = serverManagerRef.getServerAuthentication();
            serverRequestsInt = serverManagerRef.getServerRequests();

            clientImpl = ClientImpl.getInstance();
            currentClientID = clientImpl.getCurrentClient().getClientID();

        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(FriendRequestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addNewFriend(int clientID, String email) {
        //first check that this email exists
        //then check that this email has not already existed in contacts of user
        //then check that this email has not already had a friend request from user

        if (!email.trim().isEmpty()) {
            try {
                Client client = serverAuthInt.searchForClient(email);

                //first check that this email exists
                if (client == null) {
                    errorMessage.setText("Please enter a valid email");
                } else {
                    boolean contactExist = false;
                    boolean friendRequestExist = false;

                    //check that this email has not already existed in contacts of user
                    for (Client c : clientImpl.getMyContacts()) {
                        if (c.getClientEmail().equals(email)) {
                            errorMessage.setText("You and this user are already friends");
                            contactExist = true;
                        }

                    }

                    if (!contactExist) {
                        //check that this user has not already sent request to this email
                        for (Client c : clientImpl.getMySentfriendrequests()) {
                            if (c.getClientEmail().equals(email)) {
                                errorMessage.setText("You had already sent a friend request before to this person");
                                friendRequestExist = true;
                            }
                        }
                    }
                    if (!friendRequestExist) {
                        errorMessage.setText("Friend request has been sent sucessfully");
                        FriendRequest friendRequest = new FriendRequest();
                        friendRequest.setClientID(client.getClientID());
                        friendRequest.setFriendID(clientID);
                        serverRequestsInt.addNewRequest(friendRequest);
                    }

                }
            } catch (RemoteException ex) {
                Logger.getLogger(com.jdevsul.clientAddFriend.AddFriendController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void HandleOnBackPressed(MouseEvent event) {
    }

    @FXML
    private void HandleOnSendRequest2(MouseEvent event) {
        addNewFriend(currentClientID, friendEmail.getText().trim());
    }

}
