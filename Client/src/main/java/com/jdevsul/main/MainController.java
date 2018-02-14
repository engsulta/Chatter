package com.jdevsul.main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.jdevsul.clientimp.ClientImpl;
import com.jdevsul.common.TheMessage;
import com.jdevsul.interfaces.ServerManagerInt;
import com.jdevsul.interfaces.ServerSendInt;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.net.URL;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author sulta
 */
public class MainController implements Initializable {

    private ClientImpl clientImpl;
    private ServerManagerInt serverManagerRef;
    private ServerSendInt serverSendRef;

    @FXML
    private TextField msgTextField;
    @FXML
    private MaterialDesignIconView sendButton;

    public MainController() {
        clientImpl = ClientImpl.getInstance();
        clientImpl.setMainController(this);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Registry reg = LocateRegistry.getRegistry(7474);
            serverManagerRef = (ServerManagerInt) reg.lookup("ChatService");
            serverSendRef = serverManagerRef.getServerSend();
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleSendMessage(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {

            String msgStr = msgTextField.getText().trim();
            int currentClientID = clientImpl.getCurrentClient().getClientID();

            ArrayList<Integer> myList = new ArrayList<>();
            myList.add(5);

//hena hakml el data lama el fxml y5ls
            TheMessage msg = new TheMessage(currentClientID, myList, msgStr, null, null, null, LocalDateTime.now());
            try {

                serverSendRef.sendMsg(msg);
                msgTextField.setText("");

            } catch (RemoteException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void handleSendMessage(MouseEvent event) {
        String msgStr = msgTextField.getText().trim();
        int currentClientID = clientImpl.getCurrentClient().getClientID();

        ArrayList<Integer> myList = new ArrayList<>();
        myList.add(5);

//hena hakml el data lama el fxml y5ls
        TheMessage msg = new TheMessage(currentClientID, myList, msgStr, null, null, null, LocalDateTime.now());
        try {
            serverSendRef.sendMsg(msg);
            msgTextField.setText("");
        } catch (RemoteException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void renderMessage(TheMessage message) {
        System.out.println(message.getBody());
    }

}
