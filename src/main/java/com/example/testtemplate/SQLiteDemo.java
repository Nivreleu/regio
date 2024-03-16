package com.example.testtemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDemo {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:testConn";

        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS personen (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "alter INTEGER)";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("Verbindung zur SQLite-Datenbank hergestellt!");
                // Hier könntest du Tabellen erstellen oder Abfragen ausführen

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("CONNECTION FAILED");
        }
    }
}
