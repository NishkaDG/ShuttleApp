/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.edu.ashoka.TransportApp;
import java.sql.*;

/**
 *
 * @author Mayukh Nair
 */
public class DBScribe {
    
    static final String databasepath = "jdbc:mysql://localhost:3306/mysql";
    
    static final String username = "shuttler";
    static final String password = "LittleBobbyTables";
    
    Connection conn,con = null;
    Statement stat,st = null;
    ResultSet ras;

//    public void checkDB(){
//        Thread DBCheckThread = new Thread(new Runnable() {
//            public void run() {
//                boolean k = true;
//                try {
//                    Class.forName("com.mysql.jdbc.driver");
//                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ShuttleDatabase", "shuttler", "LittleBobbyTables");
//                    st = con.createStatement();
//                } catch (ClassNotFoundException | SQLException ex) {
//                    System.out.println("Error connecting to database for checks: " + ex.getMessage() + " Aborting.\n");
//                    ex.printStackTrace();
//                    k = false;
//                }
//
//                if (k) {
//                    System.out.println("Successfully connected to MySQL Server\nSearching for Database...\n");
//                    try {
//                        st.executeUpdate("CREATE DATABASE IF NOT EXISTS ShuttleDatabase");
//                        String query = "SELECT table_name FROM INFORMATION_SCHEMA.tables WHERE table_name='confirmed_bookings';";
//                        ras = st.executeQuery(query);
//                        if (ras.first()) {
//                            System.out.println("Confirmed records Found. Connecting...Connected Succcessfully.\n");
//                        } else {
//                            st.executeUpdate("create table confirmed_bookings(name varchar(50) PRIMARY KEY, destination, datetime)");
//                        }
//                        String wlquery = "SELECT table_name FROM INFORMATION_SCHEMA.tables WHERE table_name='waitlisted_bookings';";
//                        ras = st.executeQuery(wlquery);
//                        if (ras.first()) {
//                            System.out.println("Waitlist records Found. Connecting...Connected Succcessfully.\n");
//                        } else {
//                            st.executeUpdate("create table waitlisted_bookings(name varchar(50) PRIMARY KEY, destination, datetime)");
//                        }
//
//                    } catch (SQLException e) {
//                        System.out.println("Error in generating database: " + e.getMessage());
//                    }
//
//                }
//            }
//        }
//        );
//        DBCheckThread.start();
//    }
    
    public void addToConfirmedList(String list){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Attempting connection....");
            conn = DriverManager.getConnection(databasepath,username,password);
            System.out.println("Connected to database!");
            stat=conn.createStatement();
            System.out.println("Preparing query.....");
//            String n="";
//            String[] lines = list.split("\r\n|\r|\n",-1);
//            System.out.println("Block:"+lines[1]);
//            String name="";
//            for(int i=0;i<lines.length;i++){
//                name=lines[i];
//                System.out.println(name);
//                String query = "INSERT INTO testtable VALUES ('"+name+"')";
//                System.out.println("Query prepared, executing....");
//                stat.executeUpdate(query);
//                System.out.println("Query execution successful!");
//            }
                String name=list;
                System.out.println(name);
                stat.executeUpdate("CREATE DATABASE IF NOT EXISTS ShuttleDatabase");

                String query = "INSERT INTO testtable VALUES ('"+name+"')";
                System.out.println("Query prepared, executing....");
//                stat.executeUpdate(query);
                System.out.println("Query execution successful!");
        }
        
        catch(Exception e){
            e.printStackTrace();
        }
        
        finally{
            try{
               if(stat!=null){
                   conn.close();
                   System.out.println("Connection closed.");
               } 
               if(conn!=null){
                   conn.close();
                   System.out.println("Connection closed.");
               }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    
}
