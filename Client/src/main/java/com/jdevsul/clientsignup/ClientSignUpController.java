/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.clientsignup;

import com.jdevsul.DBclasses.Client;
import com.jdevsul.client.util.ClientUtil;
import com.jdevsul.clientimp.ClientImpl;
import com.jdevsul.helper.ClientHelper;
import com.jdevsul.interfaces.ServerAuthInt;
import com.jdevsul.interfaces.ServerManagerInt;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author sulta
 */
public class ClientSignUpController implements Initializable {

    @FXML
    private JFXTextField UserName;
//    private JFXPasswordField UserPassword;
    @FXML
    private JFXTextField Email;

    /*@FXML
    private JFXDatePicker Date_;
    @FXML
    private JFXComboBox<?> Gender;*/
    private ServerManagerInt serverManagerRef;
    private ServerAuthInt server;
    boolean signUpFlag;
    //private ClientInterface client;
    private Stage appStage;
    private double xoffset;
    private double yoffset;
    public static String name = "Noname";
    public static Image image = null;
    final FileChooser fileChooser = new FileChooser();
    public static URI imageURI;
    private Window window;
    @FXML
    private JFXPasswordField userPassword1;
    @FXML
    private JFXComboBox<String> gender;
    @FXML
    private Circle clientPhoto;
    @FXML
    private JFXPasswordField userPassword2;
    private Client currentClient;
    private ClientImpl clientImpl;
    private String genderselected;
    private String imagepath;
    @FXML
    private Label alreadyExist;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            xoffset = 0;
            yoffset = 0;
            Registry reg = LocateRegistry.getRegistry(7474);
            serverManagerRef = (ServerManagerInt) reg.lookup("ChatService");
            server = serverManagerRef.getServerAuthentication();
///
            ObservableList<String> genders = FXCollections.observableArrayList("male", "female");
            genderselected = "male";
            gender.setItems(genders);
            gender.setValue(genderselected);

            Email.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        if (Email.getText().isEmpty() || !Email.getText().matches("^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$")) {
                            Email.getStyleClass().clear();
                            Email.getStyleClass().add("text-error");
                        } else {
                            Email.getStyleClass().clear();
                            Email.getStyleClass().add("text-field");

                        }
                    }

                }
            });

            UserName.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        if (UserName.getText().isEmpty() || !UserName.getText().matches("^[a-zA-Z]+([ ][a-zA-Z]*)*$")) {
                            UserName.getStyleClass().clear();
                            UserName.getStyleClass().add("text-error");
                        } else {
                            UserName.getStyleClass().clear();
                            UserName.getStyleClass().add("text-field");
                        }
                    }
                }

            });

            userPassword2.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        if (!userPassword1.getText().equals(userPassword2.getText())) {
                            userPassword1.getStyleClass().clear();
                            userPassword1.getStyleClass().add("text-error");
                            userPassword2.getStyleClass().clear();
                            userPassword2.getStyleClass().add("text-error");
                        } else {
                            userPassword1.getStyleClass().clear();
                            userPassword1.getStyleClass().add("text-field");
                            userPassword2.getStyleClass().clear();
                            userPassword2.getStyleClass().add("text-field");
                        }
                    }
                }
            }
            );

        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ClientSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void HandleSignUpAction(ActionEvent event) {
        Client newClient;
        clientImpl = ClientImpl.getInstance();
        alreadyExist.setText("");
        // long millis;
        String uName = UserName.getText();
        String uPass = userPassword1.getText();
        String uEmail = Email.getText();
        if (!uName.isEmpty() && !uPass.isEmpty() && !uEmail.isEmpty()) {
            try {
                //check if user registered before or not
                appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                newClient = new Client();
                newClient.setClientName(uName);
                newClient.setClientPassword(uPass);
                newClient.setClientEmail(uEmail);
                newClient.setClientGender(genderselected);
                newClient.setClientStatus("Available");
                newClient.setClientImage(imagepath);
                newClient.setClientOnline(1);
                clientImpl.setCurrentClient(newClient);
                signUpFlag = server.signup(clientImpl);

                if (signUpFlag) {
                    serverManagerRef.register(clientImpl);
                    ClientUtil.loadWindow(getClass().getResource("/fxml/Main.fxml"), appStage, "Login");
                } else {
                    System.err.println("User exists!");
                    alreadyExist.setText("Already exist goto login page");
                }

            } catch (RemoteException ex) {
                Logger.getLogger(ClientSignUpController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            alreadyExist.setText("please check your data fields");
        }

    }

    @FXML
    private void HandleCloseAction(MouseEvent event) {
        ClientHelper.closeWindow(((Node) event.getSource()).getScene().getWindow());

    }

    @FXML
    private void HandleMinimizeAction(MouseEvent event) {
        ClientHelper.minimizeWindow(((Node) event.getSource()).getScene().getWindow());

    }

    @FXML
    private void HandleBackAction(MouseEvent event) {
        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ClientUtil.loadWindow(getClass().getResource("/fxml/ClientLogin.fxml"), appStage, "Login");
    }

    @FXML
    private void HandleOnCameraPressed(MouseEvent event) {
        window = ((Node) event.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(window);

        if (file != null) {
            imagepath = file.getPath();
            System.out.println("file not null");
            imageURI = file.toURI();
            image = new Image(file.toURI().toString());
            ImagePattern myimImagePattern = new ImagePattern(image);
            clientPhoto.setFill(myimImagePattern);
            clientPhoto.setVisible(true);
            System.out.println("image setted in  circle");

        } else {

            File file2 = new File("/images/logo2.png");
            imagepath = file2.getPath();
            imageURI = file2.toURI();
            image = new Image(file.toURI().toString());
            ImagePattern myimImagePattern = new ImagePattern(image);
            clientPhoto.setFill(myimImagePattern);
            clientPhoto.setVisible(true);
            System.out.println("image setted in  circle default photo ");

        }

    }

    @FXML
    private void HandleonMouseDragged(MouseEvent event) {

        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setX(event.getScreenX() + xoffset);
        appStage.setY(event.getScreenY() + yoffset);
    }

    @FXML
    private void HandleonMousePressed(MouseEvent event) {
        appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        xoffset = appStage.getX() - event.getScreenX();
        yoffset = appStage.getY() - event.getScreenY();
    }

    @FXML
    private void testFocus(MouseEvent event) {
        System.out.println("foucus lost from this field ");
    }

    @FXML
    private void handleOnGenderSelect(ActionEvent event) {

        genderselected = gender.getValue();
        System.out.println(genderselected);

    }

}
