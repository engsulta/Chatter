/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.server.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gehad
 */
public class FileRMI extends UnicastRemoteObject implements Serializable {

    File serverStorageFile;
    public FileRMI() throws RemoteException {
        serverStorageFile = new File("/Users/gehad/ServerStorage");
        serverStorageFile.mkdir();
    }

    public void setFile(File serverStorageFile)
    {
        this.serverStorageFile = serverStorageFile;
    }
    public void uploadFileToServer(byte[] mydata, int length) throws RemoteException {

        try (FileOutputStream out = new FileOutputStream(serverStorageFile)) {
            byte[] data = mydata;

            out.write(data);
            out.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileRMI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileRMI.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Done writing data...");

    }

    public byte[] downloadFileFromServer(String serverpath) throws RemoteException {

        byte[] mydata;

        File serverpathfile = new File(serverpath);
        mydata = new byte[(int) serverpathfile.length()];
        FileInputStream in;
        try {
            in = new FileInputStream(serverpathfile);
            try {
                in.read(mydata, 0, mydata.length);
            } catch (IOException e) {

                e.printStackTrace();
            }
            try {
                in.close();
            } catch (IOException e) {

                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }

        return mydata;

    }

}
