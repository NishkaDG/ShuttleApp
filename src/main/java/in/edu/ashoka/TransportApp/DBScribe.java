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
    
    static final String jdbcdriver = "java.sql.Driver";
    static final String databasepath = "jdbc:mysql://localhost:3306/ShuttleDatabase";
    
    static final String username = "shuttler";
    static final String password = "LittleBobbyTables";
    
    Connection conn = null;
    Statement stat = null;
    
    public void addToConfirmedList(String list){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Attempting connection....");
            conn = DriverManager.getConnection(databasepath,username,password);
            System.out.println("Connected to database!");
            stat=conn.createStatement();
            System.out.println("Preparing query.....");
            String n="";
            String[] lines = list.split("\r\n|\r|\n",-1);
            System.out.println("Block:"+lines[1]);
            String name="";
            for(int i=0;i<lines.length;i++){
                name=lines[i];
                System.out.println(name);
                String query = "INSERT INTO testtable VALUES ('"+name+"')";
                System.out.println("Query prepared, executing....");
                stat.executeUpdate(query);
                System.out.println("Query execution successful!");
            }
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
