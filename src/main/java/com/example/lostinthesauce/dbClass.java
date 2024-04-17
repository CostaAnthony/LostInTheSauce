package com.example.lostinthesauce;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class dbClass {
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
