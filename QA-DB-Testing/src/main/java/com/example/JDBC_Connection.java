package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class JDBC_Connection {

    public static void main(String[] args) throws SQLException {

        // Database server details
        String host = "localhost";  // Where database is running
        String port = "3306";       // MySQL default port
        String database = "qa_dbt";  // Database name
        String username = "";   // Database username
        String password = "";  // Database password
        
        // Build connection URL
        String dbUrl = "jdbc:mysql://" + host + ":" + port + "/" + database;
        
        // Connect to database with username and password
        Connection con = DriverManager.getConnection(dbUrl, username, password);
        
        // Create statement to run SQL commands
        Statement s = con.createStatement();
        
        // Run query to get Info about users
        ResultSet rs = s.executeQuery("select * from credit_card_users where scenario = 'Login'");

        // Move to the first record in the results
        
        while (rs.next()) {

            // Process the results of the query & print username and password
            System.out.println(rs.getString("username"));
            System.out.println(rs.getString("password"));
          
        // Initialize WebDriver (Make sure to set the path to your chromedriver)
        WebDriver driver = new ChromeDriver();
        // Selenium code to login to the application with username and password from database
        driver.get("https://rahulshettyacademy.com/client");

        // Fill in username and password from database
		driver.findElement(By.id("userEmail")).sendKeys(rs.getString("username"));
		driver.findElement(By.id("userPassword")).sendKeys(rs.getString("password"));
		driver.findElement(By.id("login")).click();
    };
    
}
}
