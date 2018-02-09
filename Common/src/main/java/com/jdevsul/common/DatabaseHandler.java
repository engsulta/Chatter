/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.common;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import oracle.jdbc.OracleDriver;

/**
 *
 * @author Eman-PC
 */
//Singleton class
public class DatabaseHandler {

    Connection con;
    private static DatabaseHandler myInstance = null;

    private DatabaseHandler() {
        connectToDB();
    }

    public static DatabaseHandler getInstance() {
        if (myInstance == null) {
            myInstance = new DatabaseHandler();
        }

        return myInstance;
    }

    private void connectToDB() {
        try {
            //open connection with database
            DriverManager.registerDriver(new OracleDriver());
            con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "chat", "chat");

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in openning connection");
        }

    }

    public void closeConnectionDB() {
        try {
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in closing connection");
        }

    }

    public void addClient(ClientDB newClient) {
        try {
            //Statement to be executed
            PreparedStatement myStatement = con.prepareStatement("insert into client"
                    + "(clientID,clientEmail,clientName,clientPassword,clientStatus,clientCreationDate"
                    + ",clientImage,clientGender,clientBirthdate,clientOnline)"
                    + "values(?,?,?,?,?,?,?,?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            myStatement.setInt(1, newClient.getClientID());
            myStatement.setString(2, newClient.getClientEmail());
            myStatement.setString(3, newClient.getClientName());
            myStatement.setString(4, newClient.getClientPassword());
            myStatement.setString(5, newClient.getClientStatus());
            myStatement.setDate(6, newClient.getClientCreationDate());
            myStatement.setString(7, newClient.getClientImage());
            myStatement.setString(8, newClient.getClientGender());
            myStatement.setDate(9, newClient.getClientBirthdate());
            myStatement.setInt(10, newClient.getClientOnline());
            myStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in adding new client");
        }
    }

    public void addFriendRequest(ClientDB currentClient, ClientDB friendClient, Date requestDate) {
        try {
            //Statement to be executed
            PreparedStatement myStatement = con.prepareStatement("insert into friendRequest"
                    + "(clientID,friendID,requestDate)"
                    + "values(?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            myStatement.setInt(1, currentClient.getClientID());
            myStatement.setInt(2, friendClient.getClientID());
            myStatement.setDate(3, requestDate);
            myStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in adding new friend");
        }
    }

    public void deleteFriendRequest(ClientDB currentClient, ClientDB friendClient, int type) {
        try {
            //type=0 --> accept request so add to contacts
            //anything else --> reject request
            //in both cases the request will be deleted from the friendRequest table

            //Statement to be executed
            PreparedStatement myStatement = con.prepareStatement("delete from friendRequest "
                    + "where friendID=?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            myStatement.setInt(1, friendClient.getClientID());
            myStatement.executeUpdate();

            if (type == 0) {
                addContact(currentClient, friendClient);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in deleting friend request");
        }
    }

    public void addContact(ClientDB currentClient, ClientDB contactClient) {
        try {
            //Statement to be executed
            PreparedStatement myStatement = con.prepareStatement("insert into contact"
                    + "(clientID,contactID)"
                    + "values(?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            myStatement.setInt(1, currentClient.getClientID());
            myStatement.setInt(2, contactClient.getClientID());
            myStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in adding new contact");
        }
    }

    public void addMessage(MessageDB newMessage) {
        try {
            ArrayList<Integer> receiverIDs = newMessage.getReceiverID();

            for (int count = 0; count < receiverIDs.size(); count++) {

                //Statement to be executed
                PreparedStatement myStatement = con.prepareStatement("insert into message"
                        + "(groupID,receiverID,messageCreationDate,clientID)"
                        + "values(?,?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

                myStatement.setInt(1, newMessage.getGroupID());
                myStatement.setInt(2, receiverIDs.get(count));
                myStatement.setDate(3, newMessage.getMessageCreationDate());
                myStatement.setInt(4, newMessage.getClientID());
                myStatement.executeUpdate();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in adding new message");
        }
    }

    public int getNumberOfOnlineClients() {
        int onlineClients = 0;
        try {

            Statement myStatement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //if clientOnlie =0 then online 
            //if clientOnlie=1 then offline
            String query = "select count(*) from client where clientOnlie = 0";

            ResultSet resultSet = myStatement.executeQuery(query);

            while (resultSet.next()) {
                onlineClients = resultSet.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in getting number of online clients");
        }
        return onlineClients;
    }

    public int getNumberOfFemaleClients() {
        int femaleClients = 0;
        try {

            Statement myStatement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String query = "select count(*) from client where lower(clientGender)=lower('female')";

            ResultSet resultSet = myStatement.executeQuery(query);

            while (resultSet.next()) {
                femaleClients = resultSet.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in getting number of female clients");
        }
        return femaleClients;
    }

    public int getNumberOfAwayClients() {
        int awayClients = 0;
        try {

            Statement myStatement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String query = "select count(*) from client where lower(clientStatus)=lower('away')";

            ResultSet resultSet = myStatement.executeQuery(query);

            while (resultSet.next()) {
                awayClients = resultSet.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in getting number of away clients");
        }
        return awayClients;
    }

    public int getNumberOfBusyClients() {
        int busyClients = 0;
        try {

            Statement myStatement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String query = "select count(*) from client where lower(clientStatus)=lower('busy')";

            ResultSet resultSet = myStatement.executeQuery(query);

            while (resultSet.next()) {
                busyClients = resultSet.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in getting number of busy clients");
        }
        return busyClients;
    }

    public int getNumberOfAvailableClients() {
        int availableClients = 0;
        try {

            Statement myStatement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String query = "select count(*) from client where lower(clientStatus)=lower('available')";

            ResultSet resultSet = myStatement.executeQuery(query);

            while (resultSet.next()) {
                availableClients = resultSet.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in getting number of available clients");
        }
        return availableClients;
    }

    public ArrayList<Integer> getContacts(ClientDB currentClient) {
        ArrayList<Integer> contacts = new ArrayList<>();
        try {
            //Statement to be executed
            PreparedStatement myStatement = con.prepareStatement("select contactID from contact "
                    + "where clientID = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            myStatement.setInt(1, currentClient.getClientID());
            ResultSet resultSet = myStatement.executeQuery();
            while (resultSet.next()) {
                contacts.add(resultSet.getInt(1));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in getting contacts");
        }
        return contacts;
    }

    public ArrayList<Integer> getFriendRequests(ClientDB currentClient) {
        ArrayList<Integer> friendRequests = new ArrayList<>();
        try {
            //Statement to be executed
            PreparedStatement myStatement = con.prepareStatement("select friendID from friendRequest "
                    + "where clientID = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            myStatement.setInt(1, currentClient.getClientID());
            ResultSet resultSet = myStatement.executeQuery();
            while (resultSet.next()) {
                friendRequests.add(resultSet.getInt(1));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in getting friend requests");
        }
        return friendRequests;
    }

    public static void main(String[] args) {
        // TODO code application logic here
    }

}
