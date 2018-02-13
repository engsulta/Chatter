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
import com.jdevsul.main.MainController;
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

    @FXML
    private JFXTextField UserName;

    private ServerManagerInt serverManagerRef;
    private ServerAuthInt authRef;
    private Client currentClient;
    ClientImpl clientRef;
    Stage appStage;
    @FXML
    private JFXPasswordField userPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Registry reg = LocateRegistry.getRegistry(7474);
            serverManagerRef = (ServerManagerInt) reg.lookup("ChatService");
            authRef = serverManagerRef.getServerAuthentication();
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ClientLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
////////////

    @FXML
    private void HandleLoginAction(ActionEvent event) {

        try {
            appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            clientRef = new ClientImpl(new MainController());
            currentClient = authRef.login(UserName.getText().trim(), userPassword.getText().trim(), clientRef);

            if (currentClient != null) {

                ClientUtil.loadWindow(getClass().getResource("/fxml/Main.fxml"), appStage, "Login");
            } else {
                userPassword.getStyleClass().add("text-error");
                userPassword.getStyleClass().add("text-error");
                //7aga mo2ktn
                System.out.println("Invalid username or password");
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
