package com.example.lostinthesauce;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class dbClass {

    //method to create table and database
    /*
    public static void createDatabaseAndTableForPlayer() {
        String dbFilePath = ".//AccountInfo.accdb";
        String databaseURL = "jdbc:ucanaccess://" + dbFilePath;
        File dbFile = new File(dbFilePath);
        if (!dbFile.exists()) {
            try {
                Database db = DatabaseBuilder.create(Database.FileFormat.V2010, new File(dbFilePath));

                try {
                    System.out.println("The database file has been created.");
                } catch (Throwable var8) {
                    if (db != null) {
                        try {
                            db.close();
                        } catch (Throwable var7) {
                            var8.addSuppressed(var7);
                        }
                    }

                    throw var8;
                }

                if (db != null) {
                    db.close();
                }
            } catch (IOException var9) {
                var9.printStackTrace(System.err);
            }
        }

        try {
            Connection conn = DriverManager.getConnection(databaseURL);
            String sql = "CREATE TABLE AccountInfo (username nvarchar(255), password nvarchar(255))";
            Statement createTableStatement = conn.createStatement();
            createTableStatement.execute(sql);
            conn.commit();
        } catch (SQLException var6) {
            var6.printStackTrace();
        }

    }

     */


    public static void createAccount(Connection conn, String username, String password){
        String sql = "INSERT INTO AccountInfo (username, password) VALUES (?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
        } catch (SQLException var8) {
            throw new RuntimeException(var8);
        }
    }

    public static void printAccountInfo(Connection conn) {
        try {
            String tableName = "AccountInfo";
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("select * from " + tableName);
            while (result.next()) {
                String username = result.getString("username");
                String password = result.getString("password");
                System.out.printf("%s %s \n", username, password);
            }
        } catch (SQLException except) {
            except.printStackTrace();
        }
    }
}
