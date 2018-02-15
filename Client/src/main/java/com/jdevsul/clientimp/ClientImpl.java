/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.clientimp;

import com.jdevsul.DBclasses.Client;
import com.jdevsul.DBclasses.Contact;
import com.jdevsul.common.Notification;
import com.jdevsul.common.ServerAdsense;
import com.jdevsul.common.TheFile;
import com.jdevsul.interfaces.ClientInterface;
import com.jdevsul.common.TheMessage;
import com.jdevsul.main.MainController;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 */
public class ClientImpl extends UnicastRemoteObject implements ClientInterface {

    private static ClientImpl myInstance = null;
    private Client currentClient = null;
    private MainController mainController;
    private ArrayList<Client> myContacts;

    private ClientImpl() throws RemoteException {
    }

    public static ClientImpl getInstance() {
        if (myInstance == null) {
            try {
                myInstance = new ClientImpl();
            } catch (RemoteException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return myInstance;
    }

    @Override
    public void recieveMsg(TheMessage message) throws RemoteException {

        mainController.renderMessage(message);
    }

    @Override
    public void recieveFile(TheFile file) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void recieveContact(Contact contact) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void recieveNotification(final Notification noti) throws RemoteException {
        //System.out.println(notification.getTitle() + " " + notification.getContent());

        new Thread(new Runnable() {
            @Override
            public void run() {
                final Notifications notificationBuilder = Notifications.create();
                notificationBuilder.title(noti.getTitle());
                notificationBuilder.text(noti.getContent());
                //notificationBuilder.graphic(new ImageView("/javafx/notification/tick_green.png"));
                notificationBuilder.hideAfter(Duration.seconds(3));
                notificationBuilder.position(Pos.BOTTOM_RIGHT);

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        notificationBuilder.show();
                    }
                });
            }
        }).start();

    }

    @Override
    public void recieveAdsense(ServerAdsense adsense) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Client getCurrentClient() {
        return currentClient;
    }

    @Override
    public void setCurrentClient(Client client) {
        this.currentClient = client;
    }

    public void setMainController(MainController controller) {
        this.mainController = controller;
    }
}
