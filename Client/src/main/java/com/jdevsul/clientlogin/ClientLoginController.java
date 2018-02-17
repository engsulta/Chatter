/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.clientlogin;

import com.jdevsul.DBclasses.Client;
import com.jdevsul.client.util.ClientUtil;
import com.jdevsul.clientimp.ClientImpl;
import com.jdevsul.interfaces.*;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sulta
 */
public class ClientLoginController implements Initializable {

    private ServerManagerInt serverManagerRef;
    private ServerAuthInt serverAuthRef;
    private Client currentClient;
    private ClientImpl clientImpl;
    private Stage appStage;

    @FXML
    private JFXTextField UserName;
    @FXML
    private JFXPasswordField userPassword;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Registry reg = LocateRegistry.getRegistry(7474);
            serverManagerRef = (ServerManagerInt) reg.lookup("ChatService");
            serverAuthRef = serverManagerRef.getServerAuthentication();
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ClientLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
////////////

    @FXML
    private void HandleLoginAction(ActionEvent event) {

        try {
            appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            clientImpl = ClientImpl.getInstance();
            currentClient = serverAuthRef.login(UserName.getText().trim(), userPassword.getText().trim());

            if (currentClient != null) {
                clientImpl.setCurrentClient(currentClient);
                serverManagerRef.register(clientImpl);
                ClientUtil.loadWindow(getClass().getResource("/fxml/list.fxml"), appStage, "Login");
            } else {
                userPassword.getStyleClass().add("text-error");
                userPassword.getStyleClass().add("text-error");
                //7aga mo2ktn
                System.err.println("Invalid username or password");
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ClientLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void HandleForgetPasswordAction(MouseEvent event) {
    }

    @FXML
    private void HandleCloseAction(MouseEvent event) {
    }

    @FXML
    private void HandleMinimizeAction(MouseEvent event) {
    }

    @FXML
    private void HandleRegisterAction(MouseEvent event) {
        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        ClientUtil.loadWindow(getClass().getResource("/fxml/ClientSignUp.fxml"), appStage, "Login");

    }

    @FXML
    private void HandleFBAction(MouseEvent event) {
    }

    @FXML
    private void HandleonMouseDrage(MouseEvent event) {
    }

    @FXML
    private void HandleonMousePressed(MouseEvent event) {
    }

}
