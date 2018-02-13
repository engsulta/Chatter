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
import java.util.Vector;

/**
 *
 * @author szmoh
 */
public class ServerAuthImpl extends UnicastRemoteObject implements ServerAuthInt, Serializable {

    private static Vector<ClientInterface> clients;

    ServerAuthImpl(Vector<ClientInterface> clients) throws RemoteException {
        this.clients = clients;
    }

    @Override
    public boolean signup(ClientInterface clientRef) throws RemoteException {
        // not tested yet
        Client currentClient = clientRef.getCurrentClient();
        boolean signup_flag = true;
        ArrayList<Client> allClients = DatabaseHandler.getInstance().getAllClients();
        for (int i = 0; i < allClients.size(); i++) {
            if (allClients.get(i).getClientEmail().equals(currentClient.getClientEmail())) {
                signup_flag = false;
            }

        }
        clients.add(clientRef);
        DatabaseHandler.getInstance().addNewClient(clientRef.getCurrentClient());
        return signup_flag;
    }

    @Override
    public Client login(String clientEmail, String clientPassword, ClientInterface clientRef) throws RemoteException {

        Client client = DatabaseHandler.getInstance().getClientByEmail(clientEmail);

        if (client != null) {
            String password = client.getClientPassword();
            if (clientPassword.equals(password)) {
                return client;
            }
        }

        return client;

        // not tested yet
//        ArrayList<Client> allClients = DatabaseHandler.getInstance().getAllClients();
//        Client currentClient = null;
//        for (int i = 0; i < allClients.size() && currentClient == null; i++) {
//
//            if (allClients.get(i).getClientEmail().equals(clientEmail)) {
//                if (allClients.get(i).getClientPassword().equals(clientPassword)) {
//                    currentClient = allClients.get(i);
//                    clientRef.setCurrentClient(allClients.get(i));
//                    clients.add(clientRef);
//                } else {
//                    // l7d ma yekon feh design
//                    System.out.println("Password incorrect");
//                }
//            }
//        }
//        return currentClient;
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
