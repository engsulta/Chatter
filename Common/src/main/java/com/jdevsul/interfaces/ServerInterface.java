/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.interfaces;

import com.jdevsul.interfaces.ClientInterface;
import com.jdevsul.DBclasses.ClientDB;
import com.jdevsul.DBclasses.Request;
import com.jdevsul.DBclasses.Contact;
import com.jdevsul.DBclasses.Group;
import com.jdevsul.common.TheFile;
import com.jdevsul.common.TheMessage;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 */
public interface ServerInterface extends Remote{
    public void sendMsg(TheMessage message)throws RemoteException;
    public boolean signup(ClientInterface client)throws RemoteException;
    public boolean login(int clientID)throws RemoteException;
    public boolean logout(int clientID)throws RemoteException;
    public boolean sendFile(TheFile file)throws RemoteException;
    public void creatNewGroup(Group group)throws RemoteException;
    public boolean removeGroup(Group group)throws RemoteException;   
    public Contact acceptNewRequest(Request request)throws RemoteException;//and the server will add the contact to you and your friend implecit
    public boolean removeContact(Contact contact)throws RemoteException;
    public ArrayList<ClientDB> getMyContacts(int myID)throws RemoteException;
    public ArrayList<Group> getMyGroups(int myID)throws RemoteException;
    public ArrayList<Request> getMyRequested(int myID)throws RemoteException;
    public void addNewRequest(Request request)throws RemoteException;   
    public void updateMe(ClientInterface client)throws RemoteException;
    public void updateMyGroup(ClientInterface client)throws RemoteException;
    



}
