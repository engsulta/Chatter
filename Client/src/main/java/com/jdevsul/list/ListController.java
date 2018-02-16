/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.list;

import com.jdevsul.client.util.ClientUtil;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author sulta
 */
public class ListController implements Initializable {

    @FXML
    private AnchorPane contactList;
    @FXML
    private TextField searchFriend;
    @FXML
    private ListView<?> myContactList1;
    @FXML
    private Circle headerPohto;
    @FXML
    private Circle headerPohto2;
    @FXML
    private AnchorPane addFriend;
    @FXML
    private JFXTextField UserName1;
    @FXML
    private TextField friendEmail;
    @FXML
    private Text errorMessage;
    @FXML
    private AnchorPane profileSettings;
    @FXML
    private Circle profilePohto;
    @FXML
    private JFXTextField UserName;
    @FXML
    private JFXTextField status;
    @FXML
    private AnchorPane notification;
    @FXML
    private ListView<?> friendRequestList;
    @FXML
    private Circle headerPohto11;
    @FXML
    private Tab homeTab;
    @FXML
    private Tab profileTab;
    @FXML
    private Tab settingsTab;
    @FXML
    private Tab notificationTab;
    @FXML
    private JFXTabPane tabName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }


    @FXML 
    private void HandleOnCamera(MouseEvent event) {
    }

    @FXML
    private void HandleOnEditName(MouseEvent event) {
    }

    @FXML
    private void HandleOnEditStatus(MouseEvent event) {
    }

    @FXML
    private void HandleOnhomeSelect(Event event) {
    }

    @FXML
    private void HandleOnprofileSelect(Event event) {
    }

    @FXML
    private void HandleOnSettingsSelect(Event event) {
    }

    @FXML
    private void HandleOnNotificationSelect(Event event) {
    }

    
}
