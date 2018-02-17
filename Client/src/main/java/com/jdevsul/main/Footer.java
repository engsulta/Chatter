/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.main;

import com.jdevsul.clientimp.ClientImpl;
import static com.jdevsul.clientsignup.ClientSignUpController.image;
import static com.jdevsul.clientsignup.ClientSignUpController.imageURI;
import com.jdevsul.common.TheFile;
import com.jdevsul.common.TheMessage;
import com.jdevsul.servicehandler.ServiceHandler;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXComboBox;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Integer.toHexString;
import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 *
 * @author sulta
 */
public class Footer implements Initializable {

    @FXML
    private AnchorPane footer;
    @FXML
    private AnchorPane messageFormat;
    @FXML
    private JFXComboBox<String> fontNameComboBox;
    @FXML
    private JFXComboBox<String> fontSizeComboBox;
    @FXML
    private JFXColorPicker colorPicker;
    @FXML
    private TextField textMessage;

    private MainController main;
    private boolean firstbold = false;
    private boolean firstitalic = false;
    private boolean firstunderline = false;
    private int myid;
    private int toid;
    String selectedColor;
    String selectedFontFamily;
    String selectedFontSize;
    Window window;
    final FileChooser fileChooser = new FileChooser();
    @FXML
    private Label sending;

    public void initMainController(MainController main) {
        this.main = main;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initMessageFormat();
        myid = ClientImpl.getInstance().getCurrentClient().getClientID();

    }

    @FXML
    private void handleOnfontNameComboBox(ActionEvent event) {
        selectedFontFamily = fontNameComboBox.getValue();
        System.out.println(selectedFontFamily);
        //-fx-font-family
        textMessage.setStyle("-fx-font-family:" + selectedFontFamily);

    }

    @FXML
    private void handleOnfontSizeComboBox(ActionEvent event) {
        selectedFontSize = fontSizeComboBox.getValue();
        System.out.println(selectedFontSize);
        //-fx-font-size
        textMessage.setStyle("-fx-font-size:" + Integer.parseInt(selectedFontSize));

    }
//  newmessageText.setStyle("-fx-background-color: #03C9A9;-fx-text-fill:white;-fx-background-radius:3 25 25 25;-fx-padding:8;-fx-font: normal  13px 'serif';");

    @FXML
    private void HandleBold(MouseEvent event) {
        // textMessage.getStyleClass().add("-fx-font-weight:bold;");
        //textMessage.getStyleClass().clear();
        if (firstbold == false) {
            textMessage.setStyle("-fx-font-weight:bold;");
            firstbold = true;
        } else {
            textMessage.setStyle("-fx-font-weight:Regular;");
            firstbold = false;

        }

    }

    @FXML
    private void HandleBold(ActionEvent event) {
        if (firstbold == false) {
            textMessage.setStyle("-fx-font-style:bold;");
            firstbold = true;
        } else {
            textMessage.setStyle("-fx-font-Style:Regular;");
            firstbold = false;

        }
    }

    @FXML
    private void HandleUnderline(ActionEvent event) {
        if (firstunderline == false) {
            textMessage.setStyle("-fx-underline:true;");
            firstunderline = true;
        } else {
            textMessage.setStyle("-fx-underline:true;");
            firstunderline = false;

        }
    }

    @FXML
    private void handleItalicFont(ActionEvent event) {
        if (firstitalic == false) {
            textMessage.setStyle("-fx-font-style:bold;");
            firstitalic = true;
        } else {
            textMessage.setStyle("-fx-font-style:Regular;");
            firstitalic = false;

        }

    }

    @FXML
    private void handleOnColorPicker(ActionEvent event) {
        //hashCode() --> construct the 32bit integer representation of the color
        //toHexString() return #+6 symbols representation of the color
        selectedColor = toHexString(colorPicker.getValue().hashCode());
        System.out.println(selectedColor);
    }

    @FXML
    private void HandleOnFileSend(final MouseEvent event) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                FileInputStream fis = null;

                window = ((Node) event.getSource()).getScene().getWindow();
                File file = fileChooser.showOpenDialog(window);

                if (file != null) {
                    try {
                        fis = new FileInputStream(file);
                        BufferedInputStream in = new BufferedInputStream(fis);
                        int length = (int) file.length();
                        int Mega = 1024 * 1024;
                        byte[] bytesArray = new byte[Mega];
                        int len = 0;
                        int i = 0;
                        int counter = 0;
                        int myid = ClientImpl.getInstance().getCurrentClient().getClientID();
                        int toid = ClientImpl.getInstance().getCurrentconn().getWtihID();
                        while ((len = in.read(bytesArray)) != -1) {
                            System.out.println("send" + counter++);

                            TheFile fileto = new TheFile(myid, toid, bytesArray, file.getName(), LocalTime.MIN);
                            ServiceHandler.getserverSendInt().sendFile(fileto);

                            i += Mega;

                        }
                        fis.close();

                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Footer.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Footer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
        sending.setText("sending...");

    }

    @FXML

    private void HandleOnSendMessage(MouseEvent event) {
        toid = ClientImpl.getInstance().getCurrentconn().getWtihID();
        ArrayList<Integer> tolist = new ArrayList<>();
        tolist.add(toid);
        TheMessage message = new TheMessage(myid, tolist, textMessage.getText(), selectedColor, selectedFontFamily, selectedFontSize, LocalDateTime.MIN);
        ClientImpl.getInstance().getCurrentconn().addChatMessage(message);
        try {
            ServiceHandler.getserverSendInt().sendMsg(message);
            textMessage.setText("");

        } catch (RemoteException ex) {
            Logger.getLogger(Footer.class.getName()).log(Level.SEVERE, null, ex);
        }

        //show message on vbox now from me i we dont need call back method here 
        main.showMymessage(message);

    }

    @FXML
    private void HandleOnMessageDetails(MouseEvent event) {

    }

    private void initMessageFormat() {

        colorPicker.setValue(Color.BLACK);
        colorPicker.setStyle("-fx-color-label-visible: false");
        ObservableList<String> fontFamilies = FXCollections.observableArrayList(Font.getFamilies());

        fontNameComboBox.setItems(fontFamilies);
        fontNameComboBox.setValue("Arial");
        ObservableList<String> fontSizes = FXCollections.observableArrayList("8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28");
        fontSizeComboBox.setItems(fontSizes);
        fontSizeComboBox.setValue("12");

    }

}
//    public String toHexString(int pickedColor) {
//        String color = "#" + Integer.toHexString(pickedColor).substring(0, 6);
//        return color;
//    }

