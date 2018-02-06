/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author IbrahimDesouky
 */
public interface ServerInterface extends Remote{
    public void tellOthers(TheMessage message)throws RemoteException;
    public void register(ClientInterface client)throws RemoteException;
    public void unregister(ClientInterface client)throws RemoteException;
}
