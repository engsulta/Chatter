/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.servicehandler;

import com.jdevsul.interfaces.ServerAuthInt;
import com.jdevsul.interfaces.ServerContactInt;
import com.jdevsul.interfaces.ServerGroupsInt;
import com.jdevsul.interfaces.ServerManagerInt;
import com.jdevsul.interfaces.ServerRequestsInt;
import com.jdevsul.interfaces.ServerSendInt;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sulta
 */
public class ServiceHandler {
//ServiceHandler.getserverManager() ServerGroupImpl serverAuthRef = serverManagerRef.getServerAuthentication();

    private static ServerManagerInt serverManagerRef = null;
    private static ServerAuthInt serverAuthRef = null;
    private static ServerContactInt ServerContactRef = null;
    private static ServerGroupsInt ServerGroupsRef = null;
    private static ServerSendInt ServerSendRef = null;
    private static ServerRequestsInt ServerRequestsRef = null;
    private static int port;
    
    private ServiceHandler() {

    }

    public static ServerManagerInt getserverManager() {
        if (serverManagerRef == null) {
            Registry reg;
            try {
                reg = LocateRegistry.getRegistry(7474);
                serverManagerRef = (ServerManagerInt) reg.lookup("ChatService");
                serverAuthRef = serverManagerRef.getServerAuthentication();
                ServerContactRef = serverManagerRef.getServerContact();
                ServerGroupsRef = serverManagerRef.getServerGroups();
                ServerSendRef = serverManagerRef.getServerSend();
                ServerRequestsRef = serverManagerRef.getServerRequests();

            } catch (RemoteException ex) {
                Logger.getLogger(ServiceHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NotBoundException ex) {
                Logger.getLogger(ServiceHandler.class.getName()).log(Level.SEVERE, null, ex);

            }

        }
        return serverManagerRef;
    }

    public static ServerAuthInt getserverAuth() {
        if (serverAuthRef == null) {
            try {
                Registry reg;
                reg = LocateRegistry.getRegistry(7474);
                serverManagerRef = (ServerManagerInt) reg.lookup("ChatService");
                serverAuthRef = serverManagerRef.getServerAuthentication();
            } catch (RemoteException ex) {
                Logger.getLogger(ServiceHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NotBoundException ex) {
                Logger.getLogger(ServiceHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return serverAuthRef;

    }

    public static ServerContactInt getserverContactInt() {
        if (ServerContactRef == null) {
            try {
                Registry reg;
                reg = LocateRegistry.getRegistry(7474);
                serverManagerRef = (ServerManagerInt) reg.lookup("ChatService");
                ServerContactRef = serverManagerRef.getServerContact();
            } catch (RemoteException ex) {
                Logger.getLogger(ServiceHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NotBoundException ex) {
                Logger.getLogger(ServiceHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ServerContactRef;

    }

    public static ServerGroupsInt getserverGroupsInt() {
        if (ServerGroupsRef == null) {
            try {
                Registry reg;
                reg = LocateRegistry.getRegistry(7474);
                serverManagerRef = (ServerManagerInt) reg.lookup("ChatService");
                ServerGroupsRef = serverManagerRef.getServerGroups();
            } catch (RemoteException ex) {
                Logger.getLogger(ServiceHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NotBoundException ex) {
                Logger.getLogger(ServiceHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ServerGroupsRef;

    }

    public static ServerSendInt getserverSendInt() {
        if (ServerSendRef == null) {
            try {
                Registry reg;
                reg = LocateRegistry.getRegistry(7474);
                serverManagerRef = (ServerManagerInt) reg.lookup("ChatService");
                ServerSendRef = serverManagerRef.getServerSend();
            } catch (RemoteException ex) {
                Logger.getLogger(ServiceHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NotBoundException ex) {
                Logger.getLogger(ServiceHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ServerSendRef;

    }

    public static ServerRequestsInt getserverRequestsInt() {
        if (ServerRequestsRef == null) {
            try {
                Registry reg;
                reg = LocateRegistry.getRegistry(7474);
                serverManagerRef = (ServerManagerInt) reg.lookup("ChatService");
                ServerRequestsRef = serverManagerRef.getServerRequests();
            } catch (RemoteException ex) {
                Logger.getLogger(ServiceHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NotBoundException ex) {
                Logger.getLogger(ServiceHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ServerRequestsRef;

    }

    public static int getPort() {
        return port;
    }
    public static void setPort(int port) {
        ServiceHandler.port = port;
    }
}
