/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.server.imp;

import com.jdevsul.DBclasses.Client;
import com.jdevsul.interfaces.ClientInterface;
import com.jdevsul.interfaces.ServerAuthInt;
import com.jdevsul.server.db.DatabaseHandler;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author szmoh
 */
public class ServerAuthImpl extends UnicastRemoteObject implements ServerAuthInt, Serializable {

    ServerAuthImpl() throws RemoteException {
    }

    @Override
    public boolean signup(ClientInterface clientRef) throws RemoteException {
        // not tested yet
        Client currentClient = clientRef.getCurrentClient();
        boolean userExist = false;
        boolean userAdded=false;
        ArrayList<Client> allClients = DatabaseHandler.getInstance().getAllClients();
        for (int i = 0; i < allClients.size(); i++) {
            if (allClients.get(i).getClientEmail().equals(currentClient.getClientEmail())) {
                userExist = true;
            }

        }
        if(!userExist)
            userAdded=DatabaseHandler.getInstance().addNewClient(clientRef.getCurrentClient());
           
        
        return userAdded;
    }

    @Override
    public Client login(String clientEmail, String clientPassword) throws RemoteException {

        Client client = DatabaseHandler.getInstance().getClientByEmail(clientEmail);
        boolean invalidLogin;
        if (client != null) {
            if(!clientEmail.equals(client.getClientEmail()) || !clientPassword.equals(client.getClientPassword()))
            {
                client=null;
                invalidLogin=true;
            }
            
        }

        return client;
    }

    @Override
    public boolean logout(ClientInterface clientRef) throws RemoteException {
        return DatabaseHandler.getInstance().removeClient(clientRef.getCurrentClient().getClientID());
    }

    @Override
    public void updateMe(ClientInterface client) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

}
