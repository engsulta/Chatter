/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.client;


import com.jdevsul.common.ClientInterface;
import com.jdevsul.common.TheMessage;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javafx.application.Platform;

/**
 *
 */
public class ClientImpl extends UnicastRemoteObject implements ClientInterface {

    FXMLController mainFXMLController;

    public ClientImpl(FXMLController mainFXMLController) throws RemoteException {
        this.mainFXMLController = mainFXMLController;
    }

    @Override
    public void recieve(TheMessage message) throws RemoteException {
        
             //   mainFXMLController.render(message);
                
    }

   
}
