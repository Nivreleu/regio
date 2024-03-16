package com.example.testtemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    public final String DATABASE_URL = "jdbc:sqlite:testConn";

    public void CreatePerson(String firstName, String lastName, int age) {
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
            Statement statement = conn.createStatement()) {

            System.out.println("CONNECTION SUCCESS");
            //statement.execute("INSERT INTO ");
        } catch (SQLException e) {
            System.out.println("CONNECTION FAILED: " + e.getMessage());
        }
    }

    public void CreateDatabase() {
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
            Statement statement = conn.createStatement()) {

            System.out.println("Connection success");

            //statement.execute("CREATE DATABASE IF NOT EXISTS arc;");

            System.out.println("Database in use");
            statement.execute("CREATE TABLE IF NOT EXISTS person (\n" +
                    "id INTEGER PRIMARY KEY,\n" +
                    "name TEXT NOT NULL,\n" +
                    "age INTEGER\n" +
                    ");");

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void CreatePerson(int id, String name, int age) {
        String str = String.format("INSERT INTO person(id, name, age) VALUES (%s, \"%s\", %s)", id, name, age);
        System.out.println(str);
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
        Statement statement = conn.createStatement()) {

            System.out.println("CONNECTION SUCCESSFULL");
            String sql = "INSERT INTO person(id, name, age) VALUES (?, ?)";

            statement.execute(str);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Person GetPersonById(int id) {
        String sql = String.format("SELECT * FROM person WHERE id = %s", id);
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
        Statement statement = connection.createStatement();
        ) {
            ResultSet rs = statement.executeQuery(sql);

            Person person = new Person(rs.getInt("id"), rs.getString("name"), rs.getInt("age"));
            System.out.println("CONNECTION SUCCESSFULL");
            return person;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new Person(1, "FAILED", 400);
        }
    }

    public List<Person> GetAllPersons() {
        String sql = String.format("SELECT * FROM person;");
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
        Statement statement = conn.createStatement()){
            List<Person> persons = new ArrayList<>();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                persons.add(new Person(rs.getInt("id"), rs.getString("name"), rs.getInt("age")));
            }
            return persons;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            List<Person> persons = new ArrayList<>();
            persons.add(new Person(400, "FAIL", 400));
            return persons;
        }
    }

    public static void main(String[] args) {
        String url = "jdbc:sqlite:testConn";
        DatabaseConnection databaseConnection = new DatabaseConnection();
        List<Person> persons = databaseConnection.GetAllPersons();

        for (Person person : persons) {
            System.out.println(person.getId()+person.getName()+ person.getAge());
        }

    }
}
