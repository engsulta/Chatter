/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.clientlogin;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author sulta
 */
public class ClientLoginController implements Initializable {

    @FXML
    private JFXTextField UserName;
    @FXML
    private JFXPasswordField AdminPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void HandleLoginAction(ActionEvent event) {
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
