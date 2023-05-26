package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
        public static Connection getConnection()
                throws ClassNotFoundException, SQLException {
            
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection conn ;
            
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3307/pokemon",
                    "root",
                    ""
            );
            
            System.out.println("Conexão bem sucedida!");
            return conn;
        }
}
