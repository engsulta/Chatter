package com.jdevsul.tabs;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author sulta
 */
public class ProfileSettingsController implements Initializable {

    @FXML
    private AnchorPane home;
    @FXML
    private Circle headerPohto1;
    @FXML
    private JFXTextField UserName;
    @FXML
    private JFXTextField status;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void HandleOnBackPressed(MouseEvent event) {
    }

    @FXML
    private void HandleOnCamera(MouseEvent event) {
    }

    @FXML
    private void HandleOnToggleAction(ActionEvent event) {
    }
    
}
