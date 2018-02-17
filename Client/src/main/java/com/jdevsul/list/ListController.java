/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.list;

import com.jdevsul.DBclasses.Client;
import com.jdevsul.DBclasses.FriendRequest;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.jdevsul.clientimp.ClientImpl;
import com.jdevsul.clientlogin.ClientLoginController;
import com.jdevsul.interfaces.ServerAuthInt;
import com.jdevsul.interfaces.ServerContactInt;
import com.jdevsul.interfaces.ServerManagerInt;
import com.jdevsul.interfaces.ServerRequestsInt;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javax.imageio.ImageIO;

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
    private JFXTabPane tabName;
    @FXML
    private Tab homeTab;
    @FXML
    private ListView<Client> myContactList;
    @FXML
    private Tab profileTab;
    @FXML
    private AnchorPane addFriend;
    @FXML
    private JFXTextField UserName1;
    @FXML
    private TextField friendEmail;
    @FXML
    private JFXButton submit;
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
    private JFXComboBox<String> statusCombobox;
    @FXML
    private Tab notificationTab;
    @FXML
    private AnchorPane notification;
    @FXML
    private ListView<Client> friendRequestList2;

    private ServerManagerInt serverManagerRef;
    private ServerAuthInt serverAuthRef;
    private Client currentClient;
    private ClientImpl clientImpl;
    private ServerRequestsInt serverRequestsInt;
    private ServerContactInt serverContactInt;
    int currentClientID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Registry reg = LocateRegistry.getRegistry(7474);
            serverManagerRef = (ServerManagerInt) reg.lookup("ChatService");
            serverAuthRef = serverManagerRef.getServerAuthentication();
            clientImpl = ClientImpl.getInstance();
            currentClientID = clientImpl.getCurrentClient().getClientID();

            serverRequestsInt = serverManagerRef.getServerRequests();
            serverContactInt = serverManagerRef.getServerContact();

            // ArrayList<Client> friendReqs = serverRequestsInt.getMyRequested(currentClientID);
            //   updateMyFriendRequests(currentClientID, friendReqs);
            ArrayList<Client> test = new ArrayList<>();
            Client c = new Client();
            c.setClientEmail("shdsdhjsd");
            c.setClientID(40);
            c.setClientName("hhhh");
            test.add(c);
                    updateMyContacts(test);

            ObservableList<String> status = FXCollections.observableArrayList("Available", "Away", "Busy");
            statusCombobox.setItems(status);
            statusCombobox.setValue("Available");
            statusCombobox.setOnAction(new EventHandler() {
                @Override
                public void handle(Event t) {

                    String selectedstatus = statusCombobox.getValue();
                    clientImpl.getCurrentClient().setClientStatus(selectedstatus);
                    //     serverAuthRef.updateMe(clientImpl);
                }
            });

        } catch (RemoteException | NotBoundException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void HandleOnContactsSelect(Event event) {

    }

    @FXML
    private void handleOnSubmit(MouseEvent event) {

    }

    @FXML
    private void HandleOnAddFriendSelect(Event event) {

    }

    @FXML
    private void HandleOnCamera(MouseEvent event) {
        // showOpenFileDialog();
    }

    @FXML
    private void HandleOnEditName(MouseEvent event) {
    }

    @FXML
    private void HandleOnProfileSelect(Event event) {

    }

    @FXML
    private void HandleOnFriendRequestsSelect(Event event) {

    }
    
    
    
    public void updateMyContacts(ArrayList<Client> contacts) {

        Collections.sort(contacts, new Comparator<Client>() {
            @Override
            public int compare(Client c1, Client c2) {
                return c1.getClientOnline() - c2.getClientOnline();
            }
        });
        ObservableList<Client> myContacts = FXCollections.observableArrayList(contacts);
        //add the contacts to the listView
        myContactList.setItems(myContacts);
        //make custom render --> updateItem
        myContactList.setCellFactory(new Callback<ListView<Client>, ListCell<Client>>() {
            @Override
            public ListCell<Client> call(ListView<Client> param) {
                return new ListCell<Client>() {
                    @Override
                    protected void updateItem(Client myContact, boolean empty) {
                        super.updateItem(myContact, empty);

                        //containerVBox include all boxes
                        VBox containerVBox = new VBox();

                        //info include images, name
                        HBox info = null;

                        //status include away/available/busy
                        HBox status = null;

                        //images include image, online/offline
                        HBox images = null;

                        //name include client name
                        HBox name = null;

                        Image image = null;
                        ImagePattern myimImagePattern = null;
                        Circle contactImage = null;

                        ImageView contactOnline = null;

                        Text contactName = null;
                        //available, away, busy
                        Text contactStatus = null;

                        if (myContact != null && !empty) {

                            info = new HBox();
                            status = new HBox();
                            images = new HBox();
                            name = new HBox();

                            //all client images are on folder on server
                            //getClientImage returns the path of the image
                            image = new Image("C:/Users/Eman-PC/Desktop/1.png", 50, 50, false, false);
                            myimImagePattern = new ImagePattern(image);
                            contactImage = new Circle();
                            contactImage.setFill(myimImagePattern);
                            contactImage.setRadius(25);
                            contactImage.setVisible(true);

                            if (myContact.getClientOnline() == 0) {
                                contactOnline = new ImageView("C:/Users/Eman-PC/Desktop/online.png");
                            } else {
                                contactOnline = new ImageView("C:/Users/Eman-PC/Desktop/offline.png");
                            }
                            contactName = new Text(myContact.getClientName());

                            //when the user click on the vbox anywhere
                            /* containerVBox.setOnMousePressed(new EventHandler<MouseEvent>() {

                                @Override
                                public void handle(MouseEvent event) {

                                 //   test1.setText(String.valueOf(myContact.getClientID()));
                                }
                            });*/
                            //when the user press done after choosing his/her group
                            

                            contactStatus = new Text(myContact.getClientStatus());

                            contactStatus.setTextAlignment(TextAlignment.CENTER);
                            contactStatus.setFont(Font.font("Agency FB", FontWeight.BOLD, 17));

                            contactName.setTextAlignment(TextAlignment.CENTER);
                            contactName.setFont(Font.font("Agency FB", FontWeight.BOLD, 25));

                            contactOnline.setFitWidth(15);
                            contactOnline.setFitHeight(15);

                            status.getChildren().add(contactStatus);
                            status.setAlignment(Pos.BASELINE_CENTER);

                            images.getChildren().addAll(contactImage, contactOnline);
                            images.setAlignment(Pos.BASELINE_LEFT);
                            images.setSpacing(-10);

                            name.getChildren().add(contactName);
                            name.setAlignment(Pos.BASELINE_CENTER);

                            info.getChildren().addAll(images, name);
                            info.setSpacing(40);

                            containerVBox.getChildren().addAll(info, status);

                            setGraphic(containerVBox);
                        } else {
                            setGraphic(null);
                        }
                    }
                };
            }
        });
    }

    

    public void updateMyContacts2(ArrayList<Client> contacts) {

        Collections.sort(contacts, new Comparator<Client>() {
            @Override
            public int compare(Client c1, Client c2) {
                return c1.getClientOnline() - c2.getClientOnline();
            }
        });
        ObservableList<Client> myContacts = FXCollections.observableArrayList(contacts);
        //add the contacts to the listView
        myContactList.setItems(myContacts);
        //make custom render --> updateItem
        myContactList.setCellFactory(new Callback<ListView<Client>, ListCell<Client>>() {
            @Override
            public ListCell<Client> call(ListView<Client> param) {
                return new ListCell<Client>() {
                    @Override
                    protected void updateItem(Client myContact, boolean empty) {
                        try {
                            super.updateItem(myContact, empty);
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomList.fxml"));

                            Pane parent = loader.load();
                            Circle contactImage = (Circle) parent.getChildren().get(0);
                            Circle contactOnline = (Circle) parent.getChildren().get(1);

                            Label contactStatus = (Label) parent.getChildren().get(3);
                            Label contactName = (Label) parent.getChildren().get(2);

                            Image image = null;
                            ImagePattern myimImagePattern = null;
                            if (myContact != null && !empty) {

                                //all client images are on folder on server
                                //getClientImage returns the path of the image
                                image = new Image("C:/Users/Eman-PC/Desktop/1.png", 50, 50, false, false);
                                myimImagePattern = new ImagePattern(image);

                                contactImage.setFill(myimImagePattern);
                                contactImage.setRadius(25);
                                contactImage.setVisible(true);

                                if (myContact.getClientOnline() == 0) {
                                    contactOnline.setFill(Color.GREEN);
                                } else {
                                    contactOnline.setFill(Color.RED);
                                }
                                contactName.setText(myContact.getClientName());

                                contactStatus.setText(myContact.getClientStatus());
                                setGraphic(parent);
                            } else {
                                setGraphic(null);
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                };
            }
        });
    }
//
//    public void updateMyFriendRequests(final int clientID, ArrayList<Client> firendRequest) {
//
//        ObservableList<Client> myContacts = FXCollections.observableArrayList(firendRequest);
//
//        //add the contacts to the listView
//        friendRequestList2.setItems(myContacts);
//
//        //make custom render --> updateItem
//        friendRequestList2.setCellFactory(new Callback<ListView<Client>, ListCell<Client>>() {
//            @Override
//            public ListCell<Client> call(ListView<Client> param) {
//                return new ListCell<Client>() {
//                    @Override
//                    protected void updateItem(final Client myFriend, boolean empty) {
//                        try {
//                            super.updateItem(myFriend, empty);
//                            FXMLLoader loader = new FXMLLoader(getClass().getResource("customFriendRequest.fxml"));
//                            Pane parent = loader.load();
//                            Circle contactImage = (Circle) parent.getChildren().get(0);
//                            Label contactName = (Label) parent.getChildren().get(1);
//                            JFXButton confirmBtn = (JFXButton) parent.getChildren().get(2);
//                            JFXButton ignoreBtn = (JFXButton) parent.getChildren().get(3);
//                            Image image = null;
//                            ImagePattern myimImagePattern = null;
//                            if (myFriend != null && !empty) {
//
//                                //all client images are on folder on server
//                                //getClientImage returns the path of the image
//                                image = new Image(myFriend.getClientImage(), 50, 50, false, false);
//                                myimImagePattern = new ImagePattern(image);
//                                contactImage = new Circle();
//                                contactImage.setFill(myimImagePattern);
//                                contactImage.setRadius(25);
//                                contactImage.setVisible(true);
//
//                                contactName.setText(myFriend.getClientName());
//
//                                confirmBtn.setOnAction(new EventHandler<ActionEvent>() {
//                                    @Override
//                                    public void handle(ActionEvent event) {
//                                        FriendRequest friendRequest = new FriendRequest();
//                                        friendRequest.setClientID(clientID);
//                                        friendRequest.setFriendID(myFriend.getClientID());
//                                        //0 mean confirmed request so this request will be added to the contacts
//                                        try {
//                                            serverRequestsInt.removeFriendRequest(friendRequest, 0);
//                                            ArrayList<Client> updatedRequests = serverRequestsInt.getMyRequested(clientID);
//                                            friendRequestList2.setItems(FXCollections.observableArrayList(updatedRequests));
//                                        } catch (RemoteException ex) {
//                                            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
//                                        }
//                                    }
//                                });
//
//                                ignoreBtn.setOnAction(new EventHandler<ActionEvent>() {
//                                    @Override
//                                    public void handle(ActionEvent event) {
//                                        FriendRequest friendRequest = new FriendRequest();
//                                        friendRequest.setClientID(clientID);
//                                        friendRequest.setFriendID(myFriend.getClientID());
//                                        try {
//                                            //1 mean rejected request
//                                            serverRequestsInt.removeFriendRequest(friendRequest, 1);
//                                            ArrayList<Client> updatedRequests = serverRequestsInt.getMyRequested(clientID);
//                                            friendRequestList2.setItems(FXCollections.observableArrayList(updatedRequests));
//                                        } catch (RemoteException ex) {
//                                            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
//                                        }
//                                    }
//                                });
//
//                                setGraphic(parent);
//                            } else {
//                                setGraphic(null);
//                            }
//                        } catch (IOException ex) {
//                            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                    }
//                };
//            }
//        });
//    }
//
//    public void addNewFriend(int clientID, String email) {
//        //first check that this email exists
//        //then check that this email has not already existed in contacts of user
//        //then check that this email has not already had a friend request from user
//
//        if (!email.trim().isEmpty()) {
//            try {
//                Client client = serverAuthRef.searchForClient(email);
//
//                //first check that this email exists
//                if (client == null) {
//                    errorMessage.setText("Please enter a valid email");
//                } else {
//                    boolean contactExist = false;
//                    boolean friendRequestExist = false;
//
//                    //check that this email has not already existed in contacts of user
//                    for (Client c : clientImpl.getMyContacts()) {
//                        if (c.getClientEmail().equals(email)) {
//                            errorMessage.setText("You and this user are already friends");
//                            contactExist = true;
//                        }
//
//                    }
//
//                    if (!contactExist) {
//                        //check that this user has not already sent request to this email
//                        for (Client c : clientImpl.getMySentfriendrequests()) {
//                            if (c.getClientEmail().equals(email)) {
//                                errorMessage.setText("You had already sent a friend request before to this person");
//                                friendRequestExist = true;
//                            }
//                        }
//                    }
//                    if (!friendRequestExist) {
//                        errorMessage.setText("Friend request has been sent sucessfully");
//                        FriendRequest friendRequest = new FriendRequest();
//                        friendRequest.setClientID(client.getClientID());
//                        friendRequest.setFriendID(clientID);
//                        serverRequestsInt.addNewRequest(friendRequest);
//                    }
//
//                }
//            } catch (RemoteException ex) {
//                Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
//
//    private void showOpenFileDialog() {
//        try {
//            FileChooser fileChooser = new FileChooser();
//
//            List<String> imagesType = new ArrayList<>();
//
//            imagesType.add("*.jpg");
//            imagesType.add("*.png");
//            imagesType.add("*.jpeg");
//            FileChooser.ExtensionFilter imagesFilter
//                    = new FileChooser.ExtensionFilter("All images", imagesType);
//            fileChooser.getExtensionFilters()
//                    .addAll(imagesFilter);
//            fileChooser.setTitle("Choose image for your profile");
//            //Show open file dialog
//            File file = fileChooser.showOpenDialog(null);
//            System.out.println(file.getAbsolutePath());
//            BufferedImage bufferedImage = ImageIO.read(file);
//            Image myImage = SwingFXUtils.toFXImage(bufferedImage, null);
//            ImagePattern myimImagePattern = new ImagePattern(myImage);
//
//            profilePohto.setFill(myimImagePattern);
//
//            profilePohto.setVisible(true);
//
//            String imageString = ImageToBase64(myImage);
//            //send this array of bytes to server
//            //byte[] imageBytes = imageString.getBytes();
//        } catch (IOException ex) {
//            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public Image Base64ToImage(String image) {
//
//        byte[] imageBytes = Base64.decode(image);
//        ByteArrayInputStream is = new ByteArrayInputStream(imageBytes);
//
//        Image newImage = new Image(is);
//        return newImage;
//
//    }
//
//    public String ImageToBase64(Image img) throws IOException {
//
//        BufferedImage bImage = SwingFXUtils.fromFXImage(img, null);
//        byte[] res;
//        try (ByteArrayOutputStream s = new ByteArrayOutputStream()) {
//            ImageIO.write(bImage, "png", s);
//            res = s.toByteArray();
//        }
//        String myImageS = Base64.encode(res);
//        return myImageS;
//    }

}
