/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.main;

import com.jdevsul.DBclasses.Client;
import com.jdevsul.clientimp.ClientImpl;
import com.jdevsul.servicehandler.Connection;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 *
 * @author sulta
 */
public class Header implements Initializable {
    
    @FXML
    private AnchorPane header;
    @FXML
    private Circle headerPohto;
    @FXML
    private Label headerName;
    @FXML
    private Label headerStatus;
    @FXML
    private MaterialDesignIconView headerOnline;
    private MainController main;
    private int myid;
    private int connectionid;
    private Connection thisconn;
    private Client connClient = null;
    
    public void initMainController(MainController main) {
        this.main = main;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connClient = ClientImpl.getInstance().getCurrentClient();
        thisconn = ClientImpl.getInstance().getCurrentconn();

//there is no connection opened yet
        headerName.setText("Welcome " + connClient.getClientName());
//        Image imgProfile = new Image();
//        ImagePattern imgPattern = new ImagePattern(imgProfile);
//        headerPohto.setFill(imgPattern);
        headerOnline.setVisible(false);
        headerStatus.setVisible(false);
    }
    
    @FXML
    private void HandleOnSaveFile(MouseEvent event) {
        thisconn.getChatMessages();
    }
    
    @FXML
    private void HandleOnAddgroup(MouseEvent event) {
    }
    
    @FXML
    private void HandleCloseAction(MouseEvent event) {
    }
    
    @FXML
    private void HandleMinimizeAction(MouseEvent event) {
    }
    
    @FXML
    private void HandleOnCall(MouseEvent event) {
    }
    
    public void updateHeader() {
        headerName.setText(connClient.getClientName());
//        Image imgProfile = new Image();
//        ImagePattern imgPattern = new ImagePattern(imgProfile);
//        headerPohto.setFill(imgPattern);
        headerOnline.setVisible(true);
        headerOnline.setText("");
        headerStatus.setVisible(true);
        headerStatus.setText("");
    }
    
}
