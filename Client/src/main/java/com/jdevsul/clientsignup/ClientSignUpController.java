/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.clientsignup;

import com.jdevsul.DBclasses.Client;
import com.jdevsul.clientimp.ClientImpl;
import com.jdevsul.interfaces.ClientInterface;
import com.jdevsul.interfaces.ServerAuthInt;
import com.jdevsul.interfaces.ServerManagerInt;
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
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author sulta
 */
public class ClientSignUpController implements Initializable {

    @FXML
    private JFXTextField UserName;
    @FXML
    private JFXPasswordField UserPassword;
    @FXML
    private JFXTextField Email;
    /*@FXML
    private JFXDatePicker Date_;
    @FXML
    private JFXComboBox<?> Gender;*/
    
    private ServerManagerInt serverManagerRef;
    private ServerAuthInt server;
    boolean signUpFlag;
    private ClientInterface client;
   // private Stage appStage;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            Registry reg = LocateRegistry.getRegistry(7474);
            serverManagerRef = (ServerManagerInt) reg.lookup("ChatService");
            server = serverManagerRef.getServerAuthentication();
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ClientSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void HandleSignUpAction(ActionEvent event) {
        Client newClient;
        client= ClientImpl.getInstance();
       // long millis;
        String uName = UserName.getText();
        String uPass = UserPassword.getText();
        String uEmail = Email.getText();
      //  Date date = (Date) Date.from(Date_.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

      //  String uGender = Gender.getValue().toString();
        if (!uName.isEmpty() && !uPass.isEmpty() && !uEmail.isEmpty()) {
            try {
                //check if user registered before or not
              //  appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                newClient = new Client();
                newClient.setClientID(1);
                newClient.setClientName(uName);
                newClient.setClientPassword(uPass);
                newClient.setClientEmail(uEmail);
                // newClient.setClientBirthdate(" ");
                newClient.setClientGender("female");
                

                /* millis=System.currentTimeMillis();  
                 java.sql.Date date=new java.sql.Date(millis);  
                newClient.setClientBirthdate(date);  */

                //kda na2s set image
                newClient.setClientStatus("Available");
                //newClient.setClientCreationDate(date);
                newClient.setClientImage("kkk");
                newClient.setClientOnline(1);
                 client.setCurrentClient(newClient);
                 signUpFlag=server.signup(client);

                if(signUpFlag)
                {
                    serverManagerRef.register(client);
                    //ClientUtil.loadWindow(getClass().getResource("/fxml/Main.fxml"), appStage, "Login");
                }
                
                else
                    System.err.println("User exists!");
                
            } catch (RemoteException ex) {
                Logger.getLogger(ClientSignUpController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            //mfrod a create label fl UI 3nd el component elly fady
            
        }

    }

    @FXML
    private void HandleCloseAction(MouseEvent event) {
    }

    @FXML
    private void HandleMinimizeAction(MouseEvent event) {
    }

    @FXML
    private void HandleBackAction(MouseEvent event) {
    }

   


    @FXML
    private void HandleOnCameraPressed(MouseEvent event) {
    }

    @FXML
    private void HandleonMouseDragged(MouseEvent event) {
    }

    @FXML
    private void HandleonMousePressed(MouseEvent event) {
    }
    
}
