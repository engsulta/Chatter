package com.jdevsul.common;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.driver.OracleDriver;


public final class DatabaseHandler implements DataBaseHandlerInt{

    private static DatabaseHandler handler = null;
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs;
    private String queryString;
    private PreparedStatement insert;
    private PreparedStatement update;
    private PreparedStatement delete;
    private DriverManager mydiDriverManager;
   private DatabaseHandler(){
   createConnection();
   }

    public static DatabaseHandler getInstance() {
        if (handler == null) {
            handler = new DatabaseHandler();
        }
        return handler;
    }

    @Override
   public  void createConnection() {
        try {
            mydiDriverManager.registerDriver(new OracleDriver());
            con =DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","hr","hr");
            stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE );
            delete=con.prepareStatement("Delete from myEmployees where Client_Email=?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            insert=con.prepareStatement("INSERT INTO myEmployees VALUES (?,'?','?','?','?')",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE );
            update=con.prepareStatement("update myEmployees set first_name='?', last_name='?',  email='?', phone_number='?' where Employee_id=?",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE );
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @Override
    public void setupChatTables() {
        String TABLE_NAME = "Client";
        try {
            if (rs.next()) {
                System.out.println("Table " + TABLE_NAME + "already exists. Ready for go!");
            } else {
                stmt.execute("CREATE TABLE " + TABLE_NAME + "("
                        + "	id varchar(200) primary key,\n"
                        + "	username varchar(200),\n"
                        + "	email varchar(200),\n"
                        + "	password varchar(100),\n"
                        + "	isAvail boolean default true"
                        + " )");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage() + " --- setupDatabase");
        } finally {
        }
    }

    @Override
    public boolean insertClient() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
  
   
  
}
