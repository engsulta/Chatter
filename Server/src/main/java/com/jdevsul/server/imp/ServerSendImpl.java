/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.server.imp;

import com.jdevsul.common.TheFile;
import com.jdevsul.common.TheMessage;
import com.jdevsul.interfaces.ClientInterface;
import com.jdevsul.interfaces.ServerSendInt;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

/**
 *
 * @author szmoh
 */
public class ServerSendImpl extends UnicastRemoteObject implements ServerSendInt, Serializable {

    private static Vector<ClientInterface> clients;

    public ServerSendImpl(Vector<ClientInterface> clients) throws RemoteException {
        this.clients = clients;
    }

    @Override
    public boolean sendFile(TheFile file) throws RemoteException {
        for (ClientInterface client : clients) {
            if (client.getCurrentClient().getClientID() == file.getToID()) {
                client.recieveFile(file);
                return true;
            }
        }

        return false;
    }

    @Override
    public void sendMsg(TheMessage message) throws RemoteException {
        for (ClientInterface client : clients) {
            for (int i = 0; i < message.getToID().size(); i++) {
                if (client.getCurrentClient().getClientID() == message.getToID().get(i)) {
                    client.recieveMsg(message);
                }
            }

        }
    }
}
