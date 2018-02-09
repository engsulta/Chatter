/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.server.db;

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

    public void addGroup(GroupDB newGroup) {
        try {
            ArrayList<Integer> receiverIDs = newGroup.getReceiverID();

            for (int count = 0; count < receiverIDs.size(); count++) {

                //Statement to be executed
                PreparedStatement myStatement = con.prepareStatement("insert into groupChat"
                        + "(groupID,receiverID,groupCreationDate,clientID)"
                        + "values(?,?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

                myStatement.setInt(1, newGroup.getGroupID());
                myStatement.setInt(2, receiverIDs.get(count));
                myStatement.setDate(3, newGroup.getGroupCreationDate());
                myStatement.setInt(4, newGroup.getClientID());
                myStatement.executeUpdate();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in adding new group");
        }
    }

    public int getNumberOfOnlineClients() {
        int onlineClients = 0;
        try {

            Statement myStatement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //if clientOnlie =0 then online 
            //if clientOnlie=1 then offline
            String query = "select count(*) from client where clientOnline = 0";

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

    public int getNumberOfOfflineClients() {
        int onlineClients = 0;
        try {

            Statement myStatement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //if clientOnlie =0 then online 
            //if clientOnlie=1 then offline
            String query = "select count(*) from client where clientOnline <> 0";

            ResultSet resultSet = myStatement.executeQuery(query);

            while (resultSet.next()) {
                onlineClients = resultSet.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in getting number of offline clients");
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

    public int getNumberOfMaleClients() {
        int maleClients = 0;
        try {

            Statement myStatement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String query = "select count(*) from client where lower(clientGender)=lower('male')";

            ResultSet resultSet = myStatement.executeQuery(query);

            while (resultSet.next()) {
                maleClients = resultSet.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in getting number of male clients");
        }
        return maleClients;
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
//        ClientDB c = new ClientDB();
//        Date date= new Date(2018,2,9);
//        c.setClientBirthdate(date);
//        c.setClientCreationDate(date);
//        c.setClientEmail("eman@yaho.com");
//        c.setClientGender("female");
//        c.setClientID(1);
//        c.setClientImage("c:/hdjd/hdjd");
//        c.setClientName("eman");
//        c.setClientOnline(0);
//        c.setClientPassword("1234");
//        c.setClientStatus("away");
//        ClientDB c = new ClientDB();
//        Date date = new Date(2018, 2, 10);
//        c.setClientBirthdate(date);
//        c.setClientCreationDate(date);
//        c.setClientEmail("is@yaho.com");
//        c.setClientGender("male");
//        c.setClientID(2);
//        c.setClientImage("d:/hdjd/hdjd");
//        c.setClientName("is");
//        c.setClientOnline(1);
//        c.setClientPassword("1234");
//        c.setClientStatus("busy");

        ClientDB c = new ClientDB();
        Date date = new Date(2018, 2, 11);
        c.setClientBirthdate(date);
        c.setClientCreationDate(date);
        c.setClientEmail("gh@yaho.com");
        c.setClientGender("male");
        c.setClientID(3);
        c.setClientImage("d:/hdjd/hdjd");
        c.setClientName("gh");
        c.setClientOnline(0);
        c.setClientPassword("1234");
        c.setClientStatus("available");
//
//        ClientDB c1 = new ClientDB();
//        Date date1 = new Date(2018, 2, 12);
//        c1.setClientBirthdate(date1);
//        c1.setClientCreationDate(date1);
//        c1.setClientEmail("gh6@yaho.com");
//        c1.setClientGender("female");
//        c1.setClientID(4);
//        c1.setClientImage("d:/hdjd/hdjd");
//        c1.setClientName("gh");
//        c1.setClientOnline(1);
//        c1.setClientPassword("1234");
//        c1.setClientStatus("away");

//        ClientDB c1 = new ClientDB();
//        Date date1 = new Date(2018, 2, 13);
//        c1.setClientBirthdate(date1);
//        c1.setClientCreationDate(date1);
//        c1.setClientEmail("gh6@yahor.com");
//        c1.setClientGender("male");
//        c1.setClientID(5);
//        c1.setClientImage("d:/hdjd/hdjd");
//        c1.setClientName("gh");
//        c1.setClientOnline(1);
//        c1.setClientPassword("1234");
//        c1.setClientStatus("away");
        DatabaseHandler test = DatabaseHandler.getInstance();
//        test.addFriendRequest(c, c1, date1);
//        test.addClient(c1);
//        GroupDB m = new GroupDB();
//        m.setClientID(1);
//        m.setGroupID(1);
//        Date d1= new Date(2018,1,1);
//        m.setMessageCreationDate(d1);
//        ArrayList<Integer> i = new ArrayList<>();
//        i.add(1);
//        i.add(2);
//        m.setReceiverID(i);
//        
//        test.addMessage(m);


        ArrayList<Integer> m = test.getFriendRequests(c);
        for(int i=0;i<m.size();i++)
            System.out.println(m.get(i));
    }

}
