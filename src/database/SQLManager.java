package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import tnotif.Tool;

/*
 *
 * @author Takylo
 *
 *
*/
public class SQLManager {

	private static Connection Connection;

	public static final void setUpConnexion() {
		try {

			String url = "jdbc:mysql://localhost:3306/tnotif";
			String user = "root";
			String passwd = "sebseb";
			Connection = DriverManager.getConnection(url,user,passwd); // on set la co a la db

		} catch (Exception e) {
           Tool.Alert("Error","Error with code B96DFE",2); // ne doit surtout pas arriver
         }
       }
	// fonction pour faire des query a la db
       public static ResultSet executeQuery(String query) throws SQLException{

        Connection DB;
        DB = Connection;

        Statement stat = DB.createStatement();
        ResultSet RS = stat.executeQuery(query);

        return RS;
      }
             public static int executeUpdate(String query) throws SQLException{

        Connection DB;
        DB = Connection;

        Statement stat = DB.createStatement();
        int RS = stat.executeUpdate(query);

        return RS;
      }
    }