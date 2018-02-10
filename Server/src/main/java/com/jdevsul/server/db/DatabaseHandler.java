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
import java.util.logging.Level;
import java.util.logging.Logger;
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

    /*------------------------- Client ----------------------------*/
    public void addClient(Client client) {
        try {
            //Statement to be executed
            PreparedStatement myStatement = con.prepareStatement("insert into client"
                    + "(clientID,clientEmail,clientName,clientPassword,clientStatus,clientCreationDate"
                    + ",clientImage,clientGender,clientBirthdate,clientOnline)"
                    + "values(?,?,?,?,?,?,?,?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            myStatement.setInt(1, client.getClientID());
            myStatement.setString(2, client.getClientEmail());
            myStatement.setString(3, client.getClientName());
            myStatement.setString(4, client.getClientPassword());
            myStatement.setString(5, client.getClientStatus());
            myStatement.setDate(6, client.getClientCreationDate());
            myStatement.setString(7, client.getClientImage());
            myStatement.setString(8, client.getClientGender());
            myStatement.setDate(9, client.getClientBirthdate());
            myStatement.setInt(10, client.getClientOnline());
            myStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in adding new client");
        }
    }

    public void updateClient(Client client) {
        try {
            //Statement to be executed
            PreparedStatement myStatement = con.prepareStatement("update client"
                    + "set clientID=?,clientEmail=?,clientName=?,clientPassword=?,"
                    + "clientStatus=?,clientCreationDate=?,clientImage=?,clientGender=?,"
                    + "clientBirthdate=?,clientOnline=? where clientID=?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            myStatement.setInt(1, client.getClientID());
            myStatement.setString(2, client.getClientEmail());
            myStatement.setString(3, client.getClientName());
            myStatement.setString(4, client.getClientPassword());
            myStatement.setString(5, client.getClientStatus());
            myStatement.setDate(6, client.getClientCreationDate());
            myStatement.setString(7, client.getClientImage());
            myStatement.setString(8, client.getClientGender());
            myStatement.setDate(9, client.getClientBirthdate());
            myStatement.setInt(10, client.getClientOnline());
            myStatement.setInt(11, client.getClientID());
            myStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in adding new client");
        }
    }

    public boolean removeClient(int clientID) {
        int affectedRows = 0;
        try {
            //Statement to be executed
            PreparedStatement myStatement = con.prepareStatement("delete from client"
                    + " where clientID=?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            myStatement.setInt(1, clientID);
            affectedRows = myStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in removing client");
        }
        //if affectedRows !=0 return true else return false
        return affectedRows != 0;
    }

    public Client getClient(int clientID) {
        Client client = null;
        try {

            //Statement to be executed
            PreparedStatement myStatement = con.prepareStatement("select * from client where id=?",
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            myStatement.setInt(1, clientID);
            ResultSet resultSet = myStatement.executeQuery();
            while (resultSet.next()) {
                client = new Client();
                client.setClientBirthdate(resultSet.getDate("clientBirthdate"));
                client.setClientCreationDate(resultSet.getDate("clientCreationDate"));
                client.setClientEmail(resultSet.getString("clientEmail"));
                client.setClientGender(resultSet.getString("clientGender"));
                client.setClientID(resultSet.getInt("clientID"));
                client.setClientImage(resultSet.getString("clientImage"));
                client.setClientName(resultSet.getString("clientName"));
                client.setClientOnline(resultSet.getInt("clientOnline"));
                client.setClientPassword(resultSet.getString("clientPassword"));
                client.setClientStatus(resultSet.getString("clientStatus"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in adding new client");
        }
        return client;
    }

    public ArrayList<Client> getAllClients() {
        ArrayList<Client> clients = new ArrayList<>();
        try {

            Statement myStatement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String query = "select clientID from client";

            ResultSet resultSet = myStatement.executeQuery(query);

            while (resultSet.next()) {
                clients.add(getClient(resultSet.getInt("clientID")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clients;
    }

    /*------------------------- Group ----------------------------*/
    public void addNewGroup(Group newGroup) {
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

//    public int updateGroup(Group client) {
//    }
    public boolean removeGroup(int groupID) {
        int affectedRows = 0;
        try {
            //Statement to be executed
            PreparedStatement myStatement = con.prepareStatement("delete from groupChat"
                    + " where groupID=?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            myStatement.setInt(1, groupID);
            affectedRows = myStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in removing group");
        }
        //if affectedRows !=0 return true else return false
        return affectedRows != 0;
    }

//    public ArrayList<Group> getMyGroups(int clientID) {
//
//    }

    /*------------------------- Contact ----------------------------*/
    public void addNewContact(Contact contact) {
        try {
            //Statement to be executed
            PreparedStatement myStatement = con.prepareStatement("insert into contact"
                    + "(clientID,contactID) values(?,?)", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            myStatement.setInt(1, contact.getClientID());
            myStatement.setInt(2, contact.getContactID());
            myStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in adding new contact");
        }
    }

    public ArrayList<Client> getMyContacts(int clientID) {
        ArrayList<Client> contacts = new ArrayList<>();
        try {
            //Statement to be executed
            PreparedStatement myStatement = con.prepareStatement("select contactID from contact "
                    + "where clientID = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            myStatement.setInt(1, clientID);
            ResultSet resultSet = myStatement.executeQuery();
            while (resultSet.next()) {
                contacts.add(getClient(resultSet.getInt("contactID")));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in getting contacts");
        }
        return contacts;
    }

    public boolean removeContact(int contactID) {
        int affectedRows = 0;
        try {
            //Statement to be executed
            PreparedStatement myStatement = con.prepareStatement("delete from contact"
                    + " where contactID=?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            myStatement.setInt(1, contactID);
            affectedRows = myStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in removing contact");
        }//if affectedRows !=0 return true else return false
        return affectedRows != 0;

    }

    /*---------------------- FriendRequest --------------------------*/
    public void addFriendRequest(FriendRequest request) {
        try {
            //Statement to be executed
            PreparedStatement myStatement = con.prepareStatement("insert into friendRequest"
                    + "(clientID,friendID,requestDate)"
                    + "values(?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            myStatement.setInt(1, request.getClientID());
            myStatement.setInt(2, request.getFriendID());
            myStatement.setDate(3, request.getRequestDate());
            myStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in adding new friend");
        }
    }

    public void removeFriendRequest(FriendRequest request, int type) {
        try {
            //type=0 --> accept request so add to contacts
            //anything else --> reject request
            //in both cases the request will be deleted from the friendRequest table

            //Statement to be executed
            PreparedStatement myStatement = con.prepareStatement("delete from friendRequest "
                    + "where friendID=? and clientID=?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            myStatement.setInt(1, request.getFriendID());
            myStatement.setInt(2, request.getClientID());
            myStatement.executeUpdate();

            if (type == 0) {
                Contact contact = new Contact();
                contact.setClientID(request.getClientID());
                contact.setContactID(request.getFriendID());
                addNewContact(contact);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in deleting friend request");
        }
    }

    public ArrayList<FriendRequest> getMyFriendRequests(int clientID) {
        ArrayList<FriendRequest> friendRequests = new ArrayList<>();
        FriendRequest request = null;
        try {
            //Statement to be executed
            PreparedStatement myStatement = con.prepareStatement("select * from friendRequest "
                    + "where clientID = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            myStatement.setInt(1, clientID);
            ResultSet resultSet = myStatement.executeQuery();
            while (resultSet.next()) {
                request.setClientID(resultSet.getInt("clientID"));
                request.setFriendID(resultSet.getInt("friendID"));
                friendRequests.add(request);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in getting friend requests");
        }
        return friendRequests;
    }

    /*------------------------ Statistics ----------------------------*/
    public int getNumberOfAllClients() {
        int clients = 0;
        try {

            Statement myStatement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String query = "select count(*) from client";

            ResultSet resultSet = myStatement.executeQuery(query);

            while (resultSet.next()) {
                clients = resultSet.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in getting number of all clients");
        }
        return clients;
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

    public static void main(String[] args) {
        // TODO code application logic here
//        Client c = new Client();
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
//        Client c = new Client();
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

        Client c = new Client();
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
//        Client c1 = new Client();
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

//        Client c1 = new Client();
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
//        Group m = new Group();
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

    }

}
