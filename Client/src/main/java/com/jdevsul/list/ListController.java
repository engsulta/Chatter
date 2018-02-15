/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.list;

import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 *
 * @author szmoh
 */
public class ListController {

    @FXML
    private AnchorPane contactList;
    @FXML
    private TextField searchFriend;
    @FXML
    private JFXTabPane tabName;
    @FXML
    private Tab homeTab;
    @FXML
    private ListView<?> myContactList1;
    @FXML
    private Circle headerPohto;
    @FXML
    private Circle headerPohto2;
    @FXML
    private Tab profileTab;
    @FXML
    private AnchorPane addFriend;
    @FXML
    private JFXTextField UserName1;
    @FXML
    private TextField friendEmail;
    @FXML
    private Text errorMessage;
    @FXML
    private Tab settingsTab;
    @FXML
    private AnchorPane profileSettings;
    @FXML
    private Circle profilePohto;
    @FXML
    private JFXTextField UserName;
    @FXML
    private JFXTextField status;
    @FXML
    private Tab notificationTab;
    @FXML
    private AnchorPane notification;
    @FXML
    private ListView<?> friendRequestList;
    @FXML
    private Circle headerPohto11;

    @FXML
    private void HandleOnhomeSelect(Event event) {
    }

    @FXML
    private void HandleOnprofileSelect(Event event) {
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
    private void HandleOnSettingsSelect(Event event) {
    }

    @FXML
    private void HandleOnNotificationSelect(Event event) {
    }
    
}
