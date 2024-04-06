package com.example.lostinthesauce;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class dbClass {

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
}
