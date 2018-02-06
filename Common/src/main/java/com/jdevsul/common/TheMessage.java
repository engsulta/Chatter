/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.common;

import java.io.File;
import java.io.Serializable;
import java.net.URI;

/**
 *
 * @author sulta
 */
public class TheMessage implements Serializable{
    String message;
    int id;
    String userName;
    URI userImage;

    public TheMessage(String message, int id,String username,URI userImage) {
        this.message = message;
        this.id = id;
        this.userName=username;
        this.userImage=userImage;
    }

    public URI getUserImage() {
        return userImage;
    }

    public void setUserImage(URI userImage) {
        this.userImage = userImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}