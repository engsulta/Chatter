package com.jdevsul.tabs;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.jdevsul.clientFriendRequest.FriendRequestController;
import com.jdevsul.clientimp.ClientImpl;
import com.jdevsul.interfaces.ServerAuthInt;
import com.jdevsul.interfaces.ServerManagerInt;
import com.jdevsul.interfaces.ServerRequestsInt;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * FXML Controller class
 *
 * @author Eman
 */
public class ProfileSettingsController implements Initializable {

    @FXML
    private Circle headerPohto1;
    @FXML
    private JFXTextField UserName;
    @FXML
    private AnchorPane profileSettings;
    @FXML
    private MaterialDesignIconView photoUpload;
    @FXML
    private MaterialDesignIconView nameUpdate;
    @FXML
    private JFXComboBox<String> statusCombobox;

    private ServerManagerInt serverManagerRef;
    private ServerAuthInt serverAuthInt;
    int currentClientID;
    ClientImpl clientImpl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {

            Registry reg = LocateRegistry.getRegistry(7474);
            serverManagerRef = (ServerManagerInt) reg.lookup("ChatService");
            serverAuthInt = serverManagerRef.getServerAuthentication();
            clientImpl = ClientImpl.getInstance();
            currentClientID = clientImpl.getCurrentClient().getClientID();

        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(FriendRequestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<String> status = FXCollections.observableArrayList("Available", "Away", "Busy");
        statusCombobox.setItems(status);
        statusCombobox.setValue("Available");
        statusCombobox.setOnAction(new EventHandler() {
            public void handle(Event t) {

                try {
                    String selectedstatus = statusCombobox.getValue();
                    clientImpl.getCurrentClient().setClientStatus(selectedstatus);
                    serverAuthInt.updateMe(clientImpl);
                } catch (RemoteException ex) {
                    Logger.getLogger(ProfileSettingsController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    @FXML
    private void HandleOnBackPressed(MouseEvent event) {
    }

    @FXML
    private void HandleOnCamera(MouseEvent event) {
        showOpenFileDialog();
    }

    @FXML
    private void HandleOnNameChange(MouseEvent event) {
    }

    private void showOpenFileDialog() {
        try {
            FileChooser fileChooser = new FileChooser();

            List<String> imagesType = new ArrayList<>();

            imagesType.add("*.jpg");
            imagesType.add("*.png");
            imagesType.add("*.jpeg");
            FileChooser.ExtensionFilter imagesFilter
                    = new FileChooser.ExtensionFilter("All images", imagesType);
            fileChooser.getExtensionFilters()
                    .addAll(imagesFilter);
            fileChooser.setTitle("Choose image for your profile");
            //Show open file dialog
            File file = fileChooser.showOpenDialog(null);
            System.out.println(file.getAbsolutePath());
            BufferedImage bufferedImage = ImageIO.read(file);
            Image myImage = SwingFXUtils.toFXImage(bufferedImage, null);
            ImagePattern myimImagePattern = new ImagePattern(myImage);

            headerPohto1.setFill(myimImagePattern);

            headerPohto1.setVisible(true);

            String imageString = ImageToBase64(myImage);
            //send this array of bytes to server
            //byte[] imageBytes = imageString.getBytes();
        } catch (IOException ex) {
            Logger.getLogger(ProfileSettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Image Base64ToImage(String image) {

        byte[] imageBytes = Base64.decode(image);
        ByteArrayInputStream is = new ByteArrayInputStream(imageBytes);

        Image newImage = new Image(is);
        return newImage;

    }

    public String ImageToBase64(Image img) throws IOException {

        BufferedImage bImage = SwingFXUtils.fromFXImage(img, null);
        byte[] res;
        try (ByteArrayOutputStream s = new ByteArrayOutputStream()) {
            ImageIO.write(bImage, "png", s);
            res = s.toByteArray();
        }
        String myImageS = Base64.encode(res);
        return myImageS;
    }

}
