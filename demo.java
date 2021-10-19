package com.mycompany.sadthings;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.sadthings.demo;
import java.io.DataInputStream;
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;

/**
 *
 * @author Ciska
 */
public class demo {
    
   static PreparedStatement p = null;
   static ResultSet r = null;
   
   
   
  
   //static Connection c = null; 
    
    public static void getAttendanceDemo()
    {
    String sql = "SELECT * FROM Attendance";
        try 
        {
            p = configDB.c.prepareStatement(sql);
            p.clearParameters();
            
            r = p.executeQuery();
            
            int id;
            String type;
            
            while( r.next() )
            {
                id = r.getInt(1);
                type = r.getString("type");
                
                System.out.println("id = " + id + "type = " + type);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    
    public static void setNewStudent()
    {
       
          
        System.out.print("Enter Employee ID: ");
  
        Scanner s = new Scanner(System.in);
        String[] details = new String[4];
        
        System.out.println("\nPlease enter your First Name");
        String firstName = s.nextLine();
        details[0] = firstName;
        String fn = firstName;
        
        System.out.println("\nPlease enter your Last Name");
        String lastName = s.nextLine();
        details[1] = lastName;
        String ln = lastName;        
        
        int[] DOB = new int[3];
        System.out.println("Please enter the day you were born in");
        DOB[0] = s.nextInt();
        
        while(DOB[0] > 31 || DOB[0] < 1){
            System.out.println("Please enter a number between 1-31");
            DOB[0] = s.nextInt();
        }
        System.out.println("Please enter the month you were born in");
        DOB[1] = s.nextInt();
        while(DOB[1] > 12 || DOB[0] < 1){
            System.out.println("Please enter a number between 1-12");
            DOB[1] = s.nextInt();
        }
        System.out.println("Please enter the year you were born in");
        DOB[2] = s.nextInt();
        while(DOB[2] > 2021 || DOB[2] < 1921){
            System.out.println("Please enter a number between 2021-1921");
            DOB[2] = s.nextInt();
        }
        String dateOfBirth = DOB[0] + "/" + DOB[1] + "/" + DOB[2];
        details[2] = dateOfBirth;
        String dob = dateOfBirth;
        
        System.out.println("\nPlease enter your group name");
        String groupName = s.nextLine();
        details[3] = groupName;
        String group = groupName;
        
       String sql = "INSERT INTO Students VALUES (?,?,?,?,?) ";
       try {
           PreparedStatement PS = configDB.c.prepareStatement(sql);
           
           PS.setString(2, fn);
           PS.setString(3, ln);
           PS.setString(4, dob);
           PS.setString(5, group);
           
           PS.executeUpdate();
           
           System.out.println("Student Successfully added");
       } catch (SQLException ex) {
           
       }  
    }
    
    public static void returnStudens()
    {
        

        //String sql = ("SELECT * FROM Students WHERE class="+group+" ");
        
        try 
        {
            System.out.println(" Enter class number: ");
            Scanner s = new Scanner(System.in); 
            String group = s.nextLine();
            String sql = ("SELECT * FROM Students WHERE class="+group+" ");
            
            p = configDB.c.prepareStatement(sql);
            p.clearParameters();
            r = p.executeQuery();
            
            
            String Studentfn;
            String Studentln;
            
            while( r.next() )
            {
                Studentfn = r.getString(2);
                Studentln = r.getString(3);
                System.out.println(" Class: "+ group + " student: " 
                        + Studentfn + " " + Studentln + ", "); 
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }
    
   

    public static void InsertWorkbook()
    {
         try 
        {
        System.out.println(" Enter Student ID: ");
        Scanner s = new Scanner(System.in); 
        int StudentID = s.nextInt();
        
        System.out.println(" Enter test ID: ");
        int testID = s.nextInt();
        
        System.out.println("Progress: \n 1 = in progress \n 2 = complete");
        int progressID = s.nextInt();
  
        Date date = new Date();
        String sdate = date.toString();
        
        //String sql = ("UPDATE Workbook SET date = " + " \" date \" " + ", test_ID = " + testID + ", progress_ID = " 
        //        + progressID + " WHERE student_ID = " + StudentID + ";");
        
        String sql = ("INSERT INTO Workbook (Student_ID, date, "
                + "test_ID, progress_ID) VALUES (?,?,?,?)");
        
        PreparedStatement PS = configDB.c.prepareStatement(sql);
           
           PS.setInt(1, StudentID);
           PS.setString(2, sdate);
           PS.setInt(3, testID);
           PS.setInt(4, progressID);
           
           PS.executeUpdate();
        
        
            p = configDB.c.prepareStatement(sql);
            p.clearParameters();
            //r = p.executeQuery();

            System.out.println("updated student workbook");
        }
        catch (Exception e)
        {
            System.out.println(e);
        } 
    
    }
    
    
    
    
    
    
    
    public static void UpdateWorkbook()
    {
        try 
        {
        System.out.println(" Enter Student ID: ");
        Scanner s = new Scanner(System.in); 
        int StudentID = s.nextInt();
        
        System.out.println(" Enter test ID: ");
        int testID = s.nextInt();
        
        System.out.println("Progress: \n 1 = in progress \n 2 = complete");
        int progressID = s.nextInt();
  
        Date date = new Date();
        String sdate = date.toString();
        
        String sql = ("UPDATE Workbook SET dates = " + " \" date \" " 
                + ", test_ID = " + testID + ", progress_ID = " 
                + progressID + " WHERE student_ID = " + StudentID 
                + " AND test_ID = " + testID + ";");
        
        PreparedStatement PS = configDB.c.prepareStatement(sql);
           
          
           PS.setString(0, sdate);
           PS.setInt(3, testID);
           PS.setInt(4, progressID);
           
           PS.executeUpdate();    
        
            //p = configDB.c.prepareStatement(sql);
            
            p.clearParameters();
            //r = p.executeQuery();

            System.out.println("updated student workbook");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        
        
    }
    
    public static void confirmTest()
            
    {
        System.out.println("Enter Student ID  ");
            Scanner s = new Scanner(System.in); 
            int StudentID = s.nextInt();
        System.out.println("Enter badge ID: ");
            int badgeID = s.nextInt();
        
        
    try 
        {
            // mandatory 
            String sql = ("SELECT COUNT(w.test_ID) FROM Workbook w Where w.student_ID =" 
                    + StudentID + " AND badge_ID = " + badgeID + " AND w.test_ID BETWEEN 1 AND 10;");
            
            p = configDB.c.prepareStatement(sql);
            p.clearParameters();
            r = p.executeQuery();
            
            
            int completedm;
 
            while( r.next() )
            {
                completedm = r.getInt(1);
                
                
                if (completedm > 9)
                    System.out.println("Student has completed mandatory tasks");
                else
                System.out.println("Student has NOT completed mandatory tasks");
                
            }
            
            // elective
            String sql2 = ("SELECT COUNT(w.test_ID) FROM Workbook w Where w.student_ID =" 
                    + StudentID + " AND badge_ID =" + badgeID + " AND w.test_ID BETWEEN 11 AND 21;");
            
            p = configDB.c.prepareStatement(sql2);
            p.clearParameters();
            r = p.executeQuery();
            
            
            int completede;
 
            while( r.next() )
            {
                completede = r.getInt(1);
                
                
                if (completede > 2)
                    System.out.println("Student has completed all elective tasks");
                else
                System.out.println("Student has NOT completed elective tasks");
                
            }
            
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
      
    }    
   
    }
    
    

