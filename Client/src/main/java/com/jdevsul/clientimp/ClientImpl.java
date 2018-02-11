/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.clientimp;

import com.jdevsul.DBclasses.Client;
import com.jdevsul.DBclasses.Contact;
import com.jdevsul.main.MainController;
import com.jdevsul.common.Notification;
import com.jdevsul.common.ServerAdsense;
import com.jdevsul.common.TheFile;
import com.jdevsul.interfaces.ClientInterface;
import com.jdevsul.common.TheMessage;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javafx.application.Platform;

/**
 *
 */
public class ClientImpl extends UnicastRemoteObject implements ClientInterface {

    MainController mainFXMLController;
    Client currentClient;

    public ClientImpl(MainController mainFXMLController) throws RemoteException {
        this.mainFXMLController = mainFXMLController;
    }

    @Override
    public void recieveMsg(TheMessage message) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
}
