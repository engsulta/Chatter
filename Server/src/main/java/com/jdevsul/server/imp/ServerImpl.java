package com.jdevsul.server.imp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import com.jdevsul.DBclasses.ClientDB;
import com.jdevsul.DBclasses.Contact;
import com.jdevsul.DBclasses.Group;
import com.jdevsul.DBclasses.Request;
import com.jdevsul.common.TheFile;
import com.jdevsul.interfaces.ClientInterface;
import com.jdevsul.interfaces.ServerInterface;
import com.jdevsul.common.TheMessage;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
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

//    @Override
//    public void tellOthers(TheMessage message) throws RemoteException {
//
//                for (ClientInterface client : clients) {
//                    try {
//                        client.recieve(message);
//                        } 
//                    catch (RemoteException ex) {
//                        Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                 }
//            }
//        
//                
//         
//    
//    @Override
//    public void register(ClientInterface client) throws RemoteException {
//        clients.add(client);
//    }
//
//    @Override
//    public void unregister(ClientInterface client) throws RemoteException {
//        clients.remove(client);
//    }

    @Override
    public void sendMsg(TheMessage message) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean signup(ClientInterface client) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean login(int clientID) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean logout(int clientID) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean sendFile(TheFile file) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void creatNewGroup(Group group) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeGroup(Group group) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Contact acceptNewRequest(Request request) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeContact(Contact contact) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ClientDB> getMyContacts(int myID) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Group> getMyGroups(int myID) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Request> getMyRequested(int myID) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addNewRequest(Request request) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateMe(ClientInterface client) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateMyGroup(ClientInterface client) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}


