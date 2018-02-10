/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.server.db;

import com.jdevsul.common.Client;
import com.jdevsul.common.Contact;
import com.jdevsul.common.FriendRequest;
import com.jdevsul.common.Group;
import java.util.ArrayList;

/**
 *
 * @author Eman-PC
 */
public interface DatabaseHandlerInt {

    /*------------------------- Client ----------------------------*/
    public void addClient(Client client);

    public void updateClient(Client client);

    public boolean removeClient(int clientID);

    public Client getClient(int clientID);

    public ArrayList<Client> getAllClients();

    /*------------------------- Group ----------------------------*/
    public void addNewGroup(Group newGroup);

    public int updateGroup(Group client);

    public boolean removeGroup(int groupID);

    //return the groups of specific client
    public ArrayList<Group> getMyGroups(int clientID);

    /*------------------------- Contact ----------------------------*/
    public void addNewContact(Contact contact);

    //return the contacts of specific id
    public ArrayList<Client> getMyContacts(int clientID);

    public boolean removeContact(int contactID);

    /*---------------------- FriendRequest --------------------------*/
    public void addFriendRequest(FriendRequest request);

    //type=0 --> accept request so add to contacts
    //anything else --> reject request
    //in both cases the request will be deleted from the friendRequest table
    public void removeFriendRequest(FriendRequest request, int type);

    //return the friend requests of specific id
    public ArrayList<FriendRequest> getMyFriendRequests(int clientID);

    /*------------------------ Statistics ----------------------------*/
    public int getNumberOfAllClients();

    public int getNumberOfOnlineClients();

    public int getNumberOfOfflineClients();

    public int getNumberOfFemaleClients();

    public int getNumberOfMaleClients();

    public int getNumberOfAwayClients();

    public int getNumberOfBusyClients();

    public int getNumberOfAvailableClients();

}
