package com.jdevsul.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
//Pull the data out of the ResultSet and put it into a serializable Collection class (HashMap, ArrayList, etc.) and send the Collection instead.
public interface ClientInterface extends Remote{
   // public static final HashMap<Integer,Image> client_images = new HashMap<Integer, Image>();
    public void recieveMsg(TheMessage message)throws RemoteException;
    public void recieveFile(TheFile file)throws RemoteException;
    public void signinResult(boolean signin_Result)throws RemoteException;
    public void signoutResult(boolean signout_Result)throws RemoteException;
    public void showNotification(User requested,boolean notificationType)throws RemoteException;//
    public void showAdsense(ServerAdsense adsense)throws RemoteException;
    public void addFriendResult(boolean request_Result)throws RemoteException;
    
}
