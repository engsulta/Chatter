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
 * @author sulta
 */
public interface DataBaseHandlerInt extends Remote {
      public void createConnection()throws RemoteException;
     public  void setupChatTables()throws RemoteException ;
      public boolean insertClient( ) throws RemoteException ;
      
      //test again eman
// insert delete client update client getclient by email get client by status or online so on
}
