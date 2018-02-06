/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.server;


import com.jdevsul.common.ClientInterface;
import com.jdevsul.common.ServerInterface;
import com.jdevsul.common.TheMessage;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.image.Image;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {

    private static Vector<ClientInterface> clients = new Vector<>();
   // private static HashMap<Integer,Image> client_images = new LinkedHashMap<Integer, Image>();

    public ServerImpl() throws RemoteException {
    }

    @Override
    public void tellOthers(TheMessage message) throws RemoteException {

                for (ClientInterface client : clients) {
                    try {
                        client.recieve(message);
                        } 
                    catch (RemoteException ex) {
                        Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                 }
            }
        
                
         
    
    @Override
    public void register(ClientInterface client) throws RemoteException {
        clients.add(client);
    }

    @Override
    public void unregister(ClientInterface client) throws RemoteException {
        clients.remove(client);
    }

}
