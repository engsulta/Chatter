package com.jdevsul.passwordrecover;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jdevsul.client.util.ClientUtil;
import com.jdevsul.helper.ClientHelper;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author sulta
 */
public class ForgetPasswordController implements Initializable {

    @FXML
    private JFXTextField recoveremail;
    private Stage appStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void HandleBackAction(MouseEvent event) {
        
        //ClientHelper.closeWindow(((Node) event.getSource()).getScene().getWindow());
         appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ClientUtil.loadWindow(getClass().getResource("/fxml/ClientLogin.fxml"), appStage, "Login");

    }

    @FXML
    private void HandleSendRecoverRequest(ActionEvent event) {
        
        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ClientUtil.loadWindow(getClass().getResource("/fxml/ClientLogin.fxml"), appStage, "Login");
        
    }
    
}
