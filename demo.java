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
import java.util.concurrent.TimeUnit;

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
       
          
        //System.out.print("Enter Employee ID: ");
  
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
        
        //System.out.println("\nPlease enter your group ID");
        //nt group = s.nextInt();
        //details[3] = group;
        //int groupID = group;
        
       String sql = "INSERT INTO Students VALUES (?,?,?,?,?) ";
       try {
           PreparedStatement PS = configDB.c.prepareStatement(sql);
           
           PS.setString(2, fn);
           PS.setString(3, ln);
           PS.setString(4, dob);
           //PS.setString(5, groupID);
           
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
        
        System.out.println("Grade if avaliable");
        int grade = s.nextInt();
  
        Date date = new Date();
        String sdate = date.toString();
        
        //String sql = ("UPDATE Workbook SET dates = " + " \" date \" " + ", test_ID = " + testID + ", progress_ID = " 
        //        + progressID + " WHERE student_ID = " + StudentID + ";");
        
        String sql = ("INSERT INTO Workbook (Student_ID, dates, "
                + "test_ID, progress_ID, Grade) VALUES (?,?,?,?,?)");
        
        PreparedStatement PS = configDB.c.prepareStatement(sql);
           
           PS.setInt(1, StudentID);
           PS.setString(2, sdate);
           PS.setInt(3, testID);
           PS.setInt(4, progressID);
           PS.setInt(5, grade);
           
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
    
    public static void markRollBook()
    
    {
            
     try 
        {
        System.out.println(" Enter Class ID: ");
        Scanner s = new Scanner(System.in); 
        int classID = s.nextInt();
        
        String sql = ("Select s.first_name, s.last_name, s.student_ID From Students s Where s.class= " + classID + " ;");
        
            p = configDB.c.prepareStatement(sql);
            p.clearParameters();
            r = p.executeQuery();
        
            String Studentfn;
            String Studentln;
            int StudentID;
             Date date = new Date();
            String sdate = date.toString();
            
            while( r.next() )
            {
                Studentfn = r.getString(1);
                Studentln = r.getString(2);
                StudentID = r.getInt(3);
                System.out.println(" student: " + Studentfn + " " + Studentln + ", "); 
                    
                System.out.println("attendance type: 1 = attended, 2 = leave, 3 = no show"); 
                int attID = s.nextInt();
                
                String sql2 = ("INSERT INTO Rollbook (Date, group_ID, student_ID, Attendence_ID) VALUES (?, ?, ?, ?)");
                PreparedStatement PS = configDB.c.prepareStatement(sql2);
           
                PS.setString(1, sdate);
                PS.setInt(2, classID);
                PS.setInt(3, StudentID);
                PS.setInt(4, attID);
                PS.addBatch();
                PS.executeBatch();
                
                
                
            }   
        
            
            
            
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
   
    public static void updateSchedule() {
        
        try {
            System.out.println(" Enter Test ID ");
        
            Scanner s = new Scanner(System.in); 
            int teacherID = s.nextInt();
        
            System.out.println(" Enter Teacher ID ");
            int testID = s.nextInt();
        
            System.out.println(" Enter dates as Month day (Oct 1) ");
            String dates = s.next();
        
        
        
        String sql = ("INSERT INTO Schedule (teacher_ID, test_ID, dates) VALUES (?,?,?)");
        
        PreparedStatement PS = configDB.c.prepareStatement(sql);
           
           PS.setInt(1, teacherID);
           PS.setInt(2, testID);
           PS.setString(3, dates);
           
           TimeUnit.SECONDS.sleep(10);   
           
           PS.executeUpdate();
                
        }
        catch (Exception e)
                {
                    e.printStackTrace();
                }
        
}
 
    public static void viewSchedule()
    {
        System.out.println("Enter date to view as month and day (nov 1) ");
            Scanner s = new Scanner(System.in); 
            String input = s.nextLine();
            
            
        
            
            try { 
                
                String sql = ("select * From Schedule JOIN Teacher ON Schedule.teacher_ID = Teacher.teacher_ID WHERE dates like \"" + input + "%\" " );
                
                p = configDB.c.prepareStatement(sql);
                p.clearParameters();
                r = p.executeQuery();
            
            
            
            int teacherID;
            int testID;
            String date;
            String fn;
            String ln;
            
            System.out.println("Tests scheduled for: " + input + ": ");
            
            
            while( r.next() )
            {
                teacherID = r.getInt(1);
                testID = r.getInt(2);
                date = r.getString(3);
                fn = r.getString(5);
                ln = r.getString(6);
                
                System.out.println("Teacher ID: " + teacherID + " teacher: " + fn + " " + ln + " testID: " + testID);
                
            }
            } 
            
            catch (Exception e)
            {
            }
            
    
    
    } 
    
    
    }
    
