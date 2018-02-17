package com.jdevsul.main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.jdevsul.clientimp.ClientImpl;
import com.jdevsul.common.FileRMI;
import com.jdevsul.common.TheFile;
import com.jdevsul.common.TheMessage;
import com.jdevsul.interfaces.ServerManagerInt;
import com.jdevsul.interfaces.ServerSendInt;
import com.jdevsul.jaxb.MessageDetails;
import com.jdevsul.servicehandler.Connection;
import com.jdevsul.servicehandler.ServiceHandler;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXComboBox;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import static java.lang.Integer.toHexString;
import java.net.URI;
import java.net.URL;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sulta
 */
public class MainController implements Initializable {

    @FXML
    private Parent footer;
    @FXML
    private Parent header;
    @FXML
    private Parent list;
    @FXML
    private Footer footerController;
    @FXML
    private Header headerController;
    @FXML
    private ListController listController;

    @FXML
    private VBox Vbox;

    private ClientImpl clientImpl;
    private TextField msgTextField;
    private Stage appStage;
    private double xoffset;
    private double yoffset;
    private int myid;
    private int privid;
    private boolean firstMessge = true;

    public MainController() {
        clientImpl = ClientImpl.getInstance();
        clientImpl.setMainController(this);
        System.out.println("consturctor");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //clientImpl = ClientImpl.getInstance();
        //clientImpl.setMainController(this);
//        FXMLLoader loader = new FXMLLoader();
//        footer = loader.<Footer>getController();
//        header = loader.<Header>getController();
//        list = loader.<ListController>getController();
//        footerController.initMainController(this);
//        headerController.initMainController(this);
//        listController.initMainController(this);
        myid = clientImpl.getCurrentClient().getClientID();

//
//        try {
//            Registry reg = LocateRegistry.getRegistry(7474);
//            serverManagerRef = (ServerManagerInt) reg.lookup("ChatService");
//            serverSendRef = serverManagerRef.getServerSend();
//        } catch (RemoteException | NotBoundException ex) {
//            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    private void handleSendMessage(KeyEvent event) {
//        if (event.getCode() == KeyCode.ENTER) {
//
//            String msgStr = msgTextField.getText().trim();
//            int currentClientID = clientImpl.getCurrentClient().getClientID();
//
//            ArrayList<Integer> myList = new ArrayList<>();
//            myList.add(5);
//
////hena hakml el data lama el fxml y5ls
//            TheMessage msg = new TheMessage(currentClientID, myList, msgStr, null, null, null, LocalDateTime.now());
//            try {
//
//                serverSendRef.sendMsg(msg);
//                msgTextField.setText("");
//
//            } catch (RemoteException ex) {
//                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//    }
//    private void handleSendMessage(MouseEvent event) {
//        String msgStr = msgTextField.getText().trim();
//        int currentClientID = clientImpl.getCurrentClient().getClientID();
//
//        ArrayList<Integer> myList = new ArrayList<>();
//        myList.add(5);
//
////hena hakml el data lama el fxml y5ls
//        TheMessage msg = new TheMessage(currentClientID, myList, msgStr, null, null, null, LocalDateTime.now());
//        try {
//            serverSendRef.sendMsg(msg);
//            msgTextField.setText("");
//        } catch (RemoteException ex) {
//            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    }
//calback from server not from me i couldnot call this message now 

    public void renderMessage(TheMessage message) {
        System.out.println(message.getBody());
        HBox textBoxH = new HBox();
        Label sendername = new Label();
        if (message.getFromID() == ClientImpl.getInstance().getCurrentconn().getWtihID()) {

        } else {//or open new tab 
            Connection newConnection = new Connection(message.getFromID());
            newConnection.addChatMessage(message);
            clientImpl.getSession().addConnection(newConnection);
        }
//        sendername.setText();
//        sendername.setStyle("-fx-text-fill:Red;");
//        textBoxH.getChildren().add(sendername);
//        textBoxH.setAlignment(Pos.TOP_RIGHT);
//        textBoxH.setStyle("-fx-alignment:TOP_RIGHT;");
//        Vbox.getChildren().add(textBoxH);
//    
//                    Circle newCircle;
//
//                    HBox textBoxH = new HBox();
//                    if (privid != message.getId()) {
//                        privid = message.getId();
//                        //newCircle=new Circle(15);
//                        newCircle = new Circle(15);
//                        URI imageURI = message.getUserImage();
//                        Image senderimage = new Image(imageURI.toString());
//                        newCircle.setFill(new ImagePattern(senderimage));
//
//                    } else {
//                        ObservableList<Node> childrens = textBoxV.getChildren();
//
//                        HBox get = (HBox) childrens.get(childrens.size() - 1);
//                        newCircle = (Circle) get.getChildren().get(1);
//                        get.getChildren().get(0).setTranslateX(-30);
//                    }
//                    //other side message adding
//                    Label newmessageText = new Label(message.getMessage());
//
//                    newmessageText.setStyle("-fx-background-color:#E08283;-fx-background-radius:25 0 25 25;-fx-padding:8;-fx-font: normal  13px 'serif';");
//                    newmessageText.setLayoutX(-30);
//                    TextFlow myflow = new TextFlow(newmessageText);
//
//                    textBoxH.getChildren().addAll(myflow, newCircle);
//                    textBoxH.setAlignment(Pos.TOP_RIGHT);
//                    textBoxH.setSpacing(3);
//                    //textBoxH.setTranslateX(-30);
//                    textBoxH.setStyle("-fx-alignment:TOP_RIGHT;-fx-padding:5");
//                    textBoxV.getChildren().add(textBoxH);
//

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

    public void showMymessage(TheMessage message) {

        //  privid = myid;
        //   firstMessge = false;
        HBox textBoxH = new HBox();
        Label newmessageText = new Label(message.getBody());
        newmessageText.setStyle("-fx-background-color: #03C9A9;-fx-text-fill:white;-fx-background-radius:3 25 25 25;-fx-padding:8;-fx-font: normal  13px 'serif';");
        TextFlow myflow = new TextFlow(newmessageText);
        textBoxH.getChildren().add(myflow);
        textBoxH.setAlignment(Pos.TOP_LEFT);

        textBoxH.setStyle("-fx-alignment:TOP_LEFT;-fx-padding:5");

        Vbox.getChildren().add(textBoxH);

    }

    @FXML
    private void HandleOnFileSend(MouseEvent event) {

        try {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Choose file");
            File UploadFile = chooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());

            String clientpathfile = UploadFile.getAbsolutePath();
            byte[] mydata = new byte[(int) clientpathfile.length()];
            FileInputStream in = new FileInputStream(clientpathfile);
            System.out.println("uploading to server");
            in.read(mydata, 0, mydata.length);
            in.close();

            int currentClientID = clientImpl.getCurrentClient().getClientID();
            int receiverID = 2;
            TheFile newFile = new TheFile(currentClientID, receiverID, mydata, UploadFile.getName(), LocalTime.now());

            FileRMI fileRMI = new FileRMI();
            fileRMI.startFileRegistry();

            ServiceHandler.getserverSendInt().sendFile(newFile);

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void HandleOnSaveFile(MouseEvent event) {
    }

    @FXML
    private void HandleOnAddgroup(MouseEvent event) {
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
//         Platform.runLater(new Runnable() {
//
//            @Override
//            public void run() {
//                System.out.println(" render");
//                //System.out.println(message.getId()+message.getUserName()+message.getMessage());
//
//                if (myid == message.getId()) {
//                    HBox textBoxH = new HBox();
//                    privid = myid;
//                    //so iam this is my own message
//                    firstMessge = false;
//                    Label newmessageText = new Label(message.getMessage());
//                    newmessageText.setStyle("-fx-background-color: #03C9A9;-fx-text-fill:white;-fx-background-radius:3 25 25 25;-fx-padding:8;-fx-font: normal  13px 'serif';");
//                    TextFlow myflow = new TextFlow(newmessageText);
//                    textBoxH.getChildren().add(myflow);
//                    textBoxH.setAlignment(Pos.TOP_LEFT);
//
//                    textBoxH.setStyle("-fx-alignment:TOP_LEFT;-fx-padding:5");
//
//                    textBoxV.getChildren().add(textBoxH);
//
//                } else {
//                    //so  this is not my own message
//                    if (privid != message.getId()) {
//
//                        HBox textBoxH = new HBox();
//                        Label sendername = new Label();
//                        sendername.setText(message.getUserName());
//                        sendername.setStyle("-fx-text-fill:Red;");
//                        textBoxH.getChildren().add(sendername);
//                        textBoxH.setAlignment(Pos.TOP_RIGHT);
//                        textBoxH.setStyle("-fx-alignment:TOP_RIGHT;");
//                        textBoxV.getChildren().add(textBoxH);
//                    }
//                    Circle newCircle;
//
//                    HBox textBoxH = new HBox();
//                    if (privid != message.getId()) {
//                        privid = message.getId();
//                        //newCircle=new Circle(15);
//                        newCircle = new Circle(15);
//                        URI imageURI = message.getUserImage();
//                        Image senderimage = new Image(imageURI.toString());
//                        newCircle.setFill(new ImagePattern(senderimage));
//
//                    } else {
//                        ObservableList<Node> childrens = textBoxV.getChildren();
//
//                        HBox get = (HBox) childrens.get(childrens.size() - 1);
//                        newCircle = (Circle) get.getChildren().get(1);
//                        get.getChildren().get(0).setTranslateX(-30);
//                    }
//                    //other side message adding
//                    Label newmessageText = new Label(message.getMessage());
//
//                    newmessageText.setStyle("-fx-background-color:#E08283;-fx-background-radius:25 0 25 25;-fx-padding:8;-fx-font: normal  13px 'serif';");
//                    newmessageText.setLayoutX(-30);
//                    TextFlow myflow = new TextFlow(newmessageText);
//
//                    textBoxH.getChildren().addAll(myflow, newCircle);
//                    textBoxH.setAlignment(Pos.TOP_RIGHT);
//                    textBoxH.setSpacing(3);
//                    //textBoxH.setTranslateX(-30);
//                    textBoxH.setStyle("-fx-alignment:TOP_RIGHT;-fx-padding:5");
//                    textBoxV.getChildren().add(textBoxH);
//
//                }
//            }
//
//        });
//}
}
