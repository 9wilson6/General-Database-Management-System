
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sam
 */
public class Dbconfig {
   static Connection conn=null;
    
    public static Connection getCon(){
       String   url="JDBC:sqlite:logIn.sqlite";
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
            return conn;
            
        } catch (SQLException| ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
            
        }
    
    }
}
