package com.example.testtemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class TestTemplateApplication {

    public static void main(String[] args) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.CreatePerson(2,"someName", 43);
        Person person = databaseConnection.GetPersonById(2);
        System.out.println("1 PERSON");
        System.out.println(person.getId() + person.getName() + person.getAge());
        List<Person> personList =   databaseConnection.GetAllPersons();
        System.out.println("LIST:");
        for (Person person1 : personList) {
            System.out.println(person1.getId()+person1.getName()+person1.getAge());
        }

    }

}
