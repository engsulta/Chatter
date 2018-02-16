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
import com.jfoenix.controls.JFXComboBox;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.File;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sulta
 */
public class MainController implements Initializable {

    private ClientImpl clientImpl;
    private ServerManagerInt serverManagerRef;
    private ServerSendInt serverSendRef;

    private TextField msgTextField;
    @FXML
    private Circle headerPohto;
    @FXML
    private Label headerName;
    @FXML
    private Label headerStatus;
    @FXML
    private MaterialDesignIconView headerOnline;
    @FXML
    private TextField textMessage;
    @FXML
    private VBox Vbox;
    @FXML
    private AnchorPane messageFormat;
    @FXML
    private JFXComboBox<?> fontFamily;
    @FXML
    private JFXComboBox<?> fontColor;
    @FXML
    private JFXComboBox<?> fontSize;
    
    @FXML
    private MaterialDesignIconView attachFile;
    
    private Stage chooserStage;

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

    @FXML
    private void HandleOnSaveFile(MouseEvent event) {
    }

    @FXML
    private void HandleOnAddgroup(MouseEvent event) {
    }

    @FXML
    private void HandleOnFileSend(MouseEvent event) {
   
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose file");
        File file = chooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
        // newFileUpload.setFile(file);

        int currentClientID = clientImpl.getCurrentClient().getClientID();
        ArrayList<Integer> myList = new ArrayList<>();
        myList.add(2);
    //    TheFile newFile = new TheFile(currentClientID, myList, data, name, LocalTime.MIN, currentClientID);
            
    }

    @FXML
    private void HandleOnSendMessage(MouseEvent event) {
    }

    @FXML
    private void HandleOnMessageDetails(MouseEvent event) {
    }

    @FXML
    private void HandleBold(ActionEvent event) {
    }

    @FXML
    private void HandleUnderline(ActionEvent event) {
    }

    @FXML
    private void handleItalicFont(ActionEvent event) {
    }

}
