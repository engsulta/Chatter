/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.servicehandler;

import com.jdevsul.common.TheMessage;
import java.util.ArrayList;

/**
 *
 * @author sulta
 */
public class Connection {
    int connID;
    int wtihID;
    ArrayList<TheMessage> chatMessages;

    public int getConnID() {
        return connID;
    }

    public void setConnID(int connID) {
        this.connID = connID;
    }

    
    public Connection(int wtihID) {
        this.wtihID = wtihID;
    }

    public int getWtihID() {
        return wtihID;
    }

    public void setWtihID(int wtihID) {
        this.wtihID = wtihID;
    }

    public ArrayList<TheMessage> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(ArrayList<TheMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }
    public void addChatMessage(TheMessage message){
    this.chatMessages.add(message);
    }
    public TheMessage getLastChatMessage(){
    return this.chatMessages.get(chatMessages.size()-1);
    
    }
}
