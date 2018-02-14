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

/**
 *
 */
public class ClientImpl extends UnicastRemoteObject implements ClientInterface {

    private static ClientImpl myInstance = null;
    private Client currentClient = null;
    private MainController mainController;
    private ArrayList<Contact> myContacts;

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
    public void recieveNotification(Notification notification) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
