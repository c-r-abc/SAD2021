package com.mycompany.sadthings;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mycompany.sadthings.demo;
import java.sql.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * The UI contains the methods that allow for the user interaction to the database through java. 
 * 
 * @author Ciska Roodt & Bayley Lovett
 * @version 2.3
 */
public class UI {
    
    // variables for creating connection    
    static PreparedStatement p = null;
    static ResultSet r = null;
    static Connection c = null;
         
       
    /**
     * This method creates the connection to the Database
     */     
    public static void createConn() {
        // attempting to connect to the database through the driver
        try
        {
            Class.forName("org.sqlite.JDBC");
            System.out.println();
        }
        // catches error in connecting 
        catch (Exception e)
        {
            System.out.println(e);
        }
    
        // Using the driver to create the connection
        try
        {
            c = DriverManager.getConnection("jdbc:sqlite:SAD.db");
        }
        catch (Exception e) 
        {
             System.out.println(e);
        } 
    
    
    }
    
    /**
     * Creating a method that closes the connection to the database
     */
    public static void closeConn()
    {
        try 
        {
            //Closing the statements that are connected to the database 
            r.close();
            p.close();
            c.close();
        } 
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    //Creating the variables for the user input    
    static private final int CONTINUE = 0;
    static private final int TASKA = 1;
    static private final int TASKB = 2;
    static private final int TASKC = 3;
    static private final int TASKD = 4;
    static private final int TASKE = 5;
    static private final int TASKF = 6;
    static private final int TASKG = 7;
    static private final int EXIT = 8;
        
 
    
    public static void main(String[] args)
    {
        try
        {
            // Reader for user input 
            createConn();
            int option = CONTINUE;
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            
            // prints out 'main menu'
            while (option != EXIT) 
            {
                //The user will see the messages and it will tell them where to go
		System.out.println("\n WELCOME to the prototype ");
		System.out.println("\n Please select an option: ");
		System.out.println("\n [1] set a new Student ");
		System.out.println("\n [2] see Students in a class ");
		System.out.println("\n [3] insert student workbook details");
		System.out.println("\n [4] see student progress ");
                System.out.println("\n [5] mark class roll ");
                System.out.println("\n [6] Update the Schedule");
                System.out.println("\n [7] View Schedule");
		System.out.println("\n [8] Exit");
                option = Integer.parseInt(in.readLine());
		Scanner s = new Scanner(System.in);
                
                //Creates a switch to choose where the user wants to go in the code/app
		switch(option)
            	{
                    case TASKA:
                    demo.setNewStudent();
                    break;
                    
                    case TASKB:
                    demo.returnStudens();
                    break;
                    
                    case TASKC:
                    demo.InsertWorkbook();
                    break;
                    
                    case TASKD:
                    demo.confirmTest();
                    break;
                    
                    case TASKE:
                    demo.markRollBook();
                    break;
                    
                    case TASKF:
                    demo.updateSchedule();
                    break;
                    
                    case TASKG:
                    demo.viewSchedule();
                    break;
                    
                    case EXIT:
                    System.out.println("Bye Bye");
                    closeConn();
                    break;
                    
                    default:
                    System.out.println("Please enter a valid option [1-6].");
                    break;
		}
                                
                        
            }
        closeConn();
	}
	catch(IOException ioe)
	{
            ioe.printStackTrace();
	}

    }
    
}
