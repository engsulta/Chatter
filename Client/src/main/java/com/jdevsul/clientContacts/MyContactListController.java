///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.jdevsul.clientContacts;
//
//import com.jdevsul.DBclasses.Client;
//import com.jdevsul.DBclasses.Group;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.ResourceBundle;
//import java.util.Vector;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.geometry.Pos;
//import javafx.scene.control.Button;
//import javafx.scene.control.ListCell;
//import javafx.scene.control.ListView;
//import javafx.scene.control.SelectionMode;
//import javafx.scene.control.TextField;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.ImagePattern;
//import javafx.scene.shape.Circle;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
//import javafx.scene.text.Text;
//import javafx.scene.text.TextAlignment;
//import javafx.util.Callback;
//
///**
// * FXML Controller class
// *
// * @author Eman-PC
// */
//public class MyContactListController implements Initializable {
//
//    @FXML
//    private ListView<Client> myContactList;
//
//
//    public MyContactListController() {
//
//    }
//
//public void updateMyContacts(ArrayList<Client> contacts) {
//        ObservableList<Client> myContacts = FXCollections.observableArrayList(contacts);
//
//        //add the contacts to the listView
//        myContactList.setItems(myContacts);
//        Vector<String> ids = new Vector();
//        //make custom render --> updateItem
//        myContactList.setCellFactory(new Callback<ListView<Client>, ListCell<Client>>() {
//            @Override
//            public ListCell<Client> call(ListView<Client> param) {
//                return new ListCell<Client>() {
//                    @Override
//                    protected void updateItem(Client myContact, boolean empty) {
//                        super.updateItem(myContact, empty);
//
//                        //containerVBox include all boxes
//                        VBox containerVBox = new VBox();
//
//                        //info include images, name
//                        HBox info = null;
//
//                        //status include away/available/busy
//                        HBox status = null;
//
//                        //images include image, online/offline
//                        HBox images = null;
//
//                        //name include client name
//                        HBox name = null;
//
//                        Image image = null;
//                        ImagePattern myimImagePattern = null;
//                        Circle contactImage = null;
//
//                        ImageView contactOnline = null;
//
//                        Text contactName = null;
//                        //available, away, busy
//                        Text contactStatus = null;
//
//                        if (myContact != null && !empty) {
//
//                            info = new HBox();
//                            status = new HBox();
//                            images = new HBox();
//                            name = new HBox();
//
//                            //all client images are on folder on server
//                            //getClientImage returns the path of the image
//                            image = new Image(myContact.getClientImage(), 50, 50, false, false);
//                            myimImagePattern = new ImagePattern(image);
//                            contactImage = new Circle();
//                            contactImage.setFill(myimImagePattern);
//                            contactImage.setRadius(25);
//                            contactImage.setVisible(true);
//
//                            if (myContact.getClientOnline() == 0) {
//                                contactOnline = new ImageView("clientjavafx2rmi/online.png");
//                            } else {
//                                contactOnline = new ImageView("clientjavafx2rmi/offline.png");
//                            }
//                            contactName = new Text(myContact.getClientName());
//
//                            //when the user click on the vbox anywhere
//                            /* containerVBox.setOnMousePressed(new EventHandler<MouseEvent>() {
//
//                                @Override
//                                public void handle(MouseEvent event) {
//
//                                 //   test1.setText(String.valueOf(myContact.getClientID()));
//                                }
//                            });*/
//                            //when the user press done after choosing his/her group
//                            done.setOnAction(new EventHandler<ActionEvent>() {
//                                @Override
//                                public void handle(ActionEvent event) {
//
//                                    ObservableList<Client> selectedItems = myContactList.getSelectionModel().getSelectedItems();
//                                    Group group = new Group();
//                                    group.setClientID(myContact.getClientID());
//
//                                    ArrayList<Client> selectedClients = new ArrayList<>();
//                                    selectedItems.forEach((client) -> {
//                                        selectedClients.add(client);
//                                    });
//
////                                    String test = "";
////                                    for (Client c : selectedItems) {
////                                        test += String.valueOf(c.getClientID());
////                                    }
////                                    Alert alert = new Alert(AlertType.INFORMATION);
////                                    alert.setTitle("Information Dialog");
////                                    alert.setHeaderText("I have a great message for you!");
////                                    alert.setContentText(String.valueOf(test));
////
////                                    alert.showAndWait();
//                                }
//                            });
//
//                            contactStatus = new Text(myContact.getClientStatus());
//
//                            contactStatus.setTextAlignment(TextAlignment.CENTER);
//                            contactStatus.setFont(Font.font("Agency FB", FontWeight.BOLD, 17));
//
//                            contactName.setTextAlignment(TextAlignment.CENTER);
//                            contactName.setFont(Font.font("Agency FB", FontWeight.BOLD, 25));
//
//                            contactOnline.setFitWidth(15);
//                            contactOnline.setFitHeight(15);
//
//                            status.getChildren().add(contactStatus);
//                            status.setAlignment(Pos.BASELINE_CENTER);
//
//                            images.getChildren().addAll(contactImage, contactOnline);
//                            images.setAlignment(Pos.BASELINE_LEFT);
//                            images.setSpacing(-10);
//
//                            name.getChildren().add(contactName);
//                            name.setAlignment(Pos.BASELINE_CENTER);
//
//                            info.getChildren().addAll(images, name);
//                            info.setSpacing(40);
//
//                            containerVBox.getChildren().addAll(info, status);
//
//                            setGraphic(containerVBox);
//                        } else {
//                            setGraphic(null);
//                        }
//                    }
//                };
//            }
//        });
//    }
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//        myContactList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//
//    }

//
//}
