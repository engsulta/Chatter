/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.server.mainui;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author sulta
 */
public class MainFXMLController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private JFXTabPane mainTabPane;
    @FXML
    private Tab bookIssueTab;
    @FXML
    private HBox book_info;
    @FXML
    private JFXTextField bookIDInput;
    @FXML
    private StackPane bookInfoContainer;
    @FXML
    private Text bookName;
    @FXML
    private Text bookAuthor;
    @FXML
    private Text bookStatus;
    @FXML
    private Tab renewTab;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loadBookInfo(ActionEvent event) {
    }
    
}
