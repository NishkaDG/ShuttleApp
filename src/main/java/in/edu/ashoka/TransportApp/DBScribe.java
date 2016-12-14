package in.edu.ashoka.TransportApp;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**Manages the databases.
 *
 * @author Mayukh Nair
 */
public class DBScribe {
    
    static final String databasepath = "jdbc:mysql://localhost:3306/mysql";
    
    static final String username = "shuttler";
    static final String password = "LittleBobbyTables";
    
    private Connection con = null;
    private Statement st = null;
    private ResultSet ras,ras2;

    boolean k = true;

    // Initializes link to database; if database doesn't exist it creates the database with
    public void initDBConn(){
        Thread DBInitThread = new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.print("Connecting...");
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "shuttler", "LittleBobbyTables");
                    st = con.createStatement();
                    System.out.print("Connected!");
                } catch (ClassNotFoundException | SQLException ex) {
                    System.out.println("Error connecting to database for checks: " + ex.getMessage() + " Aborting.\n");
                    ex.printStackTrace();
                    k = false;
                }
                if (k) {
                    try {
                        System.out.println("Checking for database");
                        String query = "SELECT table_name FROM INFORMATION_SCHEMA.tables WHERE table_name='confirmed_bookings'";
                        ras = st.executeQuery(query);
                        if (ras.first()) {
                            System.out.println("Confirmed records Found. Connecting...Connected Succcessfully.\n");
                        } else {
                            st.executeUpdate("create database ShuttleDatabase");
                            st.executeUpdate("use ShuttleDatabase");
                            st.executeUpdate("create table confirmed_bookings(name varchar(50) PRIMARY KEY, destination VARCHAR(50) , datetime VARCHAR(50), booking_datetime VARCHAR(50) )");
                        }
                        String wlquery = "SELECT table_name FROM INFORMATION_SCHEMA.tables WHERE table_name='waitlisted_bookings'";
                        ras = st.executeQuery(wlquery);
                        if (ras.first()) {
                            System.out.println("Waitlist records Found. Connecting...Connected Succcessfully.\n");
                        } else {
                            st.executeUpdate("use ShuttleDatabase");
                            st.executeUpdate("create table waitlisted_bookings(name varchar(50) PRIMARY KEY, destination VARCHAR(50) , datetime VARCHAR(50), booking_datetime VARCHAR(50))");
                        }
                        String canquery = "SELECT table_name FROM INFORMATION_SCHEMA.tables WHERE table_name='cancelled_bookings'";
                        ras = st.executeQuery(canquery);
                        if(ras.first()){
                            System.out.println("Cancellation records Found. Connecting...Connected Succcessfully.\n");
                        } else {
                            st.executeUpdate("use ShuttleDatabase");
                            st.executeUpdate("create table cancelled_bookings(name varchar(50) PRIMARY KEY, destination VARCHAR(50) , datetime VARCHAR(50), booking_datetime VARCHAR(50))");
                        }
                    } catch (SQLException e) {
                        System.out.println("Error in generating database: " + e.getMessage());
                    }
                    finally{
                        try{
                            if(st!=null){
                                con.close();
                                System.out.println("Connection closed.");
                            }
                            if(con!=null){
                                con.close();
                                System.out.println("Connection closed.");
                            }
                        }
                        catch(SQLException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
            });
        DBInitThread.start();
    }

    /*Adds names of confirmed bookings to database; creates a database if it does not already exist.
     *
     * @param list The list of names in String form
     */

    public void addToList(String status, String name, String destination, String datetime){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date;
        try{
            System.out.print("Connecting...");
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ShuttleDatabase", "shuttler", "LittleBobbyTables");
            st = con.createStatement();
            switch(status){
                case "confirmed":
                                date = new Date();
                                String confquery = "INSERT INTO confirmed_bookings VALUES ('"+name+"','"+destination+"','"+datetime+"','"+dateFormat.format(date)+"')";
                                System.out.println(confquery);
                                System.out.println("Query prepared for recording confirmation, executing....");
                                st.executeUpdate(confquery);
                                System.out.println("Query execution successful!");
                                break;
                case "waitlist":
                                date = new Date();
                                String waitquery = "INSERT INTO waitlisted_bookings VALUES ('"+name+"','"+destination+"','"+datetime+"','"+dateFormat.format(date)+"')";
                                System.out.println("Query prepared for recording waitlisting, executing....");
                                st.executeUpdate(waitquery);
                                System.out.println("Query execution successful!");
                                break;
                case "cancellation":
                                date = new Date();
                                String cancquery = "INSERT INTO cancelled_bookings VALUES ('"+name+"','"+destination+"','"+datetime+"','"+dateFormat.format(date)+"')";
                                System.out.println("Query prepared for recording cancellation, executing....");
                                st.executeUpdate(cancquery);
                                System.out.println("Query execution successful!");
                                break;
            }
        }

        catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        finally{
            try{
               if(st!=null){
                   con.close();
                   System.out.println("Connection closed.");
               }
               if(con!=null){
                   con.close();
                   System.out.println("Connection closed.");
               }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void restoreBackups(){
        System.out.print("Connecting...");
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ShuttleDatabase", "shuttler", "LittleBobbyTables");
            st = con.createStatement();
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            Date currentdate = new Date();
            Date recorddate;
            ras = st.executeQuery("SELECT * FROM confirmed_bookings");
            while(ras.next()){
                recorddate = dateFormat.parse(ras.getString(3));
                if(recorddate.compareTo(currentdate)>0){
                    ras2 = st.executeQuery("SELECT name,destination,datetime FROM confirmed_bookings where datetime = '"+recorddate+"'");
                    while(ras2.next()){
                        MainApplication.manager(0,ras2.getString(1),ras2.getString(2),ras2.getString(3));
                    }
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                if(st!=null){
                    con.close();
                    System.out.println("Connection closed.");
                }
                if(con!=null){
                    con.close();
                    System.out.println("Connection closed.");
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }

    }



}
