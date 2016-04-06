/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;

import java.sql.*;
import javax.swing.JOptionPane;

public class Project3 {
private Connection connection = null;
    public void establishConnection()
    {
        if (connection != null)
            return;
        String url = "Jdbc:postgresql://localhost:5432/";
        try
        {
           Class.forName("org.postgresql.Driver");
          
           
           connection = DriverManager.getConnection(url, "postgres", "postgres");
           
           if (connection != null) {
               System.out.println("Connecting to database...");
           }
        } catch(Exception e){
            System.out.println("Problem when connecting to the database 1");
        }
    }
          public ResultSet giveName()
    {
        ResultSet rs = null;
        Statement s = null;
        try
        {
            s = connection.createStatement();
            
            rs = s.executeQuery("SELECT * FROM library");
        }catch(Exception e)
        {
            System.out.println("Problem in searching the database 1");
        }
        return rs;
    }
public void closeConnection()
    {
        try
        {
            connection.close();
        }catch(Exception e)
        {
            System.out.println("Problem to close the connection to the database");
        }
    }
    public static void main(String[] args)  throws SQLException {
        {
        Project3 x = new Project3();
        ResultSet rs = null;
        String string = "";

        x.establishConnection();
        rs = x.giveName();
        
        try {
        while(rs.next())
        {
            
            string += rs.getString(1) + "," + rs.getString(2) + "," + rs.getString(3) + "," + rs.getString(4) + "," + rs.getString(5) + "\n";


        }

        JOptionPane.showMessageDialog(null, string, "authors", 1);
        } catch(Exception e)
        {
            System.out.println("Problem when printing the database.");
        }
        x.closeConnection();
    }

}
}