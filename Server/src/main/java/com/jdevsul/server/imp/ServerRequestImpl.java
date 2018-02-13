/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.server.imp;

import com.jdevsul.DBclasses.Contact;
import com.jdevsul.DBclasses.FriendRequest;
import com.jdevsul.interfaces.ServerRequestsInt;
import com.jdevsul.server.db.DatabaseHandler;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author szmoh
 */
public class ServerRequestImpl extends UnicastRemoteObject implements ServerRequestsInt, Serializable {

    ServerRequestImpl() throws RemoteException {
    }

    @Override
    public Contact acceptNewRequest(FriendRequest request) throws RemoteException {
        //mfrod a7oto fl clients vector wla la?
        Contact newFriend = new Contact();
        newFriend.setClientID(request.getClientID());
        newFriend.setContactID(request.getFriendID());
        DatabaseHandler.getInstance().addNewContact(newFriend);
        return newFriend;
    }

    @Override
    public void addNewRequest(FriendRequest request) throws RemoteException {
        DatabaseHandler.getInstance().addNewFriendRequest(request);

    }

    @Override
    public ArrayList<FriendRequest> getMyRequested(int myID) throws RemoteException {
        return DatabaseHandler.getInstance().getMyFriendRequests(myID);
    }

}
