/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.server.imp;

import com.jdevsul.common.Notification;
import com.jdevsul.common.TheFile;
import com.jdevsul.common.TheMessage;
import com.jdevsul.interfaces.ClientInterface;
import com.jdevsul.interfaces.ServerSendInt;
import com.jdevsul.common.FileRMI;
import java.io.Serializable;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author szmoh
 */
public class ServerSendImpl extends UnicastRemoteObject implements ServerSendInt, Serializable {

    private static Vector<ClientInterface> clientsImplRef;

    public ServerSendImpl(Vector<ClientInterface> clients) throws RemoteException {
        this.clientsImplRef = clients;
    }

 
    @Override
    public boolean sendFile(TheFile file) throws RemoteException { 
//        try { 
//            //upload
//            Registry myreg = LocateRegistry.getRegistry("127.0.0.1", 7070);
//            FileRMI fileRMI = (FileRMI)myreg.lookup("remoteObject");
//
//            String serverpathfile = "/Users/gehad/ServerStorage/" +file.getName();
//            fileRMI.uploadFileToServer(file.getData(), serverpathfile);
//            
//            for (ClientInterface clientRef : clientsImplRef) {
//                if (clientRef.getCurrentClient().getClientID() == file.getToID()) {
//                    clientRef.recieveFile(file);
//                    return true;
//                }
//            }
//
//        } catch (AccessException ex) {
//            Logger.getLogger(ServerSendImpl.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (NotBoundException ex) {
//            Logger.getLogger(ServerSendImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return false;
    }

    @Override
    public void sendMsg(TheMessage message) throws RemoteException {
        for (ClientInterface clientRef : clientsImplRef) {
            for (int i = 0; i < message.getToID().size(); i++) {

                if (clientRef.getCurrentClient().getClientID() == message.getToID().get(i)) {
                    clientRef.recieveMsg(message);
                }
            }

        }
    }

    @Override
    public void sendNotification(Notification notification) throws RemoteException {
        for (ClientInterface clientRef : clientsImplRef) {
            clientRef.recieveNotification(notification);
        }
    }

}
