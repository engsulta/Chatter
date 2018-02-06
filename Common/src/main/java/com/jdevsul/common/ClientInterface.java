package com.jdevsul.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote{
   // public static final HashMap<Integer,Image> client_images = new HashMap<Integer, Image>();
    public void recieve(TheMessage message)throws RemoteException;
    
}
