/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.common;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Eman-PC
 */
public class MessageDB {

    int groupID;
    ArrayList<Integer> receiverID;

    public ArrayList<Integer> getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(ArrayList<Integer> receiverID) {
        this.receiverID = receiverID;
    }
    Date messageCreationDate;
    int clientID;

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public Date getMessageCreationDate() {
        return messageCreationDate;
    }

    public void setMessageCreationDate(Date messageCreationDate) {
        this.messageCreationDate = messageCreationDate;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

}
