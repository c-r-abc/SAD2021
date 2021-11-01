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
 * Creating the demo class for the app implementation
 */
public class demo {
   // Basic statements for getting user inputs for the SQL statements 
   static PreparedStatement p = null;
   static ResultSet r = null;
   
   
   
  
  
    /**
     * This method prints out the role for the specific class that the user chooses
     */
    public static void getAttendanceDemo()
    {
        //Selecting all the students from the Attendance table
        String sql = "SELECT * FROM Attendance";
        try 
        {
            p = configDB.c.prepareStatement(sql);
            p.clearParameters();
            r = p.executeQuery();
            int id;
            String type;
            
            //This will print out all the students with their id and type
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
    
    /**
     * This method create a new student
     */
    public static void setNewStudent()
    {   
        //This creates a scanner and then ask to input all the students details
        Scanner s = new Scanner(System.in);
        String[] details = new String[4];
        
        //This creates the students first name
        System.out.println("\nPlease enter your First Name");
        String firstName = s.nextLine();
        details[0] = firstName;
        String fn = firstName;
        
        //This creates the students last name
        System.out.println("\nPlease enter your Last Name");
        String lastName = s.nextLine();
        details[1] = lastName;
        String ln = lastName;        
        
        //This creates the students date of birth
        int[] DOB = new int[3];
        System.out.println("Please enter the day you were born in");
        DOB[0] = s.nextInt();
        
        //If the date isnt between 1-31 the program will throw an error
        while(DOB[0] > 31 || DOB[0] < 1){
            System.out.println("Please enter a number between 1-31");
            DOB[0] = s.nextInt();
        }
        //This enters the month, if it isnt between 1-12 the program will thrown and error
        System.out.println("Please enter the month you were born in");
        DOB[1] = s.nextInt();
        while(DOB[1] > 12 || DOB[0] < 1){
            System.out.println("Please enter a number between 1-12");
            DOB[1] = s.nextInt();
        }
        //This enters the year you were born in, the max value you can be is 100 years old
        System.out.println("Please enter the year you were born in");
        DOB[2] = s.nextInt();
        while(DOB[2] > 2021 || DOB[2] < 1921){
            System.out.println("Please enter a number between 2021-1921");
            DOB[2] = s.nextInt();
        }
        //This adds all the date of birth together so it is in a format of dd/mm/yyyy
        String dateOfBirth = DOB[0] + "/" + DOB[1] + "/" + DOB[2];
        details[2] = dateOfBirth;
        String dob = dateOfBirth;
        
        //This adds all the values of the student into the database
        String sql = "INSERT INTO Students VALUES (?,?,?,?,?) ";
        try {
            PreparedStatement PS = configDB.c.prepareStatement(sql);
            //This is getting the values from the user inputs and adding them into the database
            PS.setString(2, fn);
            PS.setString(3, ln);
            PS.setString(4, dob);
            PS.executeUpdate();
           
            System.out.println("Student Successfully added");
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }  
    }
    
    /**
     * This gets all the the students from a class and prints them out for the user to see
     */
    public static void returnStudens()
    {
        try 
        {
            //This is assing for the class number of the students you would like to print
            System.out.println(" Enter class number: ");
            Scanner s = new Scanner(System.in); 
            String group = s.nextLine();
            //Selects all the students from the class selected
            String sql = ("SELECT * FROM Students WHERE class="+group+" ");
            
            //Creates the statement for the SQL
            p = configDB.c.prepareStatement(sql);
            p.clearParameters();
            r = p.executeQuery();
            
            
            String Studentfn;
            String Studentln;
            
            //This will print all the students 
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
    
    /**
     * This method allows the students to enter their details when they finish a test etc.
     */
    public static void InsertWorkbook()
    {
        try 
        {
            //The app is asking for the students details and setting them in variables
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
        
            //The app then adds all the details that the student gave to the database
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
            System.out.println("updated student workbook");
        }
        catch (Exception e)
        {
            System.out.println(e);
        } 
    
    }
    
    /**
     * This method goes down a list of student names from a class and asks the 
     * teacher if the students are present or not and then updates the database
     * base on that information
     */
    public static void markRollBook()
    {
        try 
        {
            //This ask the user to enter the class ID of the role they would like to mark
            System.out.println(" Enter Class ID: ");
            Scanner s = new Scanner(System.in); 
            int classID = s.nextInt();
            
            //Once the class is entered then it will get all the students from that class
            String sql = ("Select s.first_name, s.last_name, s.student_ID From Students s Where s.class= " + classID + " ;");
        
            //Executing the statement
            p = configDB.c.prepareStatement(sql);
            p.clearParameters();
            r = p.executeQuery();
        
            //Initialising the variables to enter the students attendance
            String Studentfn;
            String Studentln;
            int StudentID;
            Date date = new Date();
            String sdate = date.toString();
            
            //This loops through the data that was received from the database and then asks the user if the student was present or not
            while( r.next() )
            {
                //Printing the students data
                Studentfn = r.getString(1);
                Studentln = r.getString(2);
                StudentID = r.getInt(3);
                System.out.println(" student: " + Studentfn + " " + Studentln + ", "); 
                
                //Asking the user if the student attended, was on leave or didnt show up
                System.out.println("attendance type: 1 = attended, 2 = leave, 3 = no show"); 
                int attID = s.nextInt();
                
                //This then enters the details of the user input into the database
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
    
    
    /**
     * This method updates the workbook and the student can edit the details of what tests they have completed
     */
    public static void UpdateWorkbook()
    {
        try 
        {
            //This asks for the students information
            System.out.println(" Enter Student ID: ");
            Scanner s = new Scanner(System.in); 
            int StudentID = s.nextInt();
            
            //This asks for what test the student completed
            System.out.println(" Enter test ID: ");
            int testID = s.nextInt();
            
            //This asks the student if the test is complete or in progress
            System.out.println("Progress: \n 1 = in progress \n 2 = complete");
            int progressID = s.nextInt();
  
            Date date = new Date();
            String sdate = date.toString();
            
            //This updates the database with the required infomation
            String sql = ("UPDATE Workbook SET dates = " + " \" date \" " 
                + ", test_ID = " + testID + ", progress_ID = " 
                + progressID + " WHERE student_ID = " + StudentID 
                + " AND test_ID = " + testID + ";");
        
            PreparedStatement PS = configDB.c.prepareStatement(sql);
           
          
            PS.setString(0, sdate);
            PS.setInt(3, testID);
            PS.setInt(4, progressID);
           
            PS.executeUpdate();    
                  
            p.clearParameters();
            System.out.println("updated student workbook");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        
        
    }
    
    /**
     * This method confirms if a student has finished a test and will give them a badge
     */
    public static void confirmTest()
            
    {
        //Enter the students details
        System.out.println("Enter Student ID  ");
        Scanner s = new Scanner(System.in); 
        int StudentID = s.nextInt();
        System.out.println("Enter badge ID: ");
        int badgeID = s.nextInt();
        
        
        try 
        {
            // This checks the mandatory parts of the test to see if the student has completed it 
            String sql = ("SELECT COUNT(w.test_ID) FROM Workbook w Where w.student_ID =" 
                    + StudentID + " AND badge_ID = " + badgeID + " AND w.test_ID BETWEEN 1 AND 10;");
            
            p = configDB.c.prepareStatement(sql);
            p.clearParameters();
            r = p.executeQuery();
            
            
            int completedm;
            
            //This checks to see if the student has completed the 9 mandatory tasks
            while( r.next() )
            {
                completedm = r.getInt(1);
                if (completedm > 9)
                    System.out.println("Student has completed mandatory tasks");
                else
                System.out.println("Student has NOT completed mandatory tasks");
                
            }
            
            // This checks to see if the student has completed the elective tasks
            String sql2 = ("SELECT COUNT(w.test_ID) FROM Workbook w Where w.student_ID =" 
                    + StudentID + " AND badge_ID =" + badgeID + " AND w.test_ID BETWEEN 11 AND 21;");
            
            p = configDB.c.prepareStatement(sql2);
            p.clearParameters();
            r = p.executeQuery();
            
            
            int completede;
            
            //This checks to see if the student has completed the elective tasks
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
    
    /**
     * This method creates a schedule that a teacher can choose when they want to do an exam
     */
    public static void updateSchedule() {
        
        try {
            //Enter the details of the test
            System.out.println(" Enter Test ID ");
        
            Scanner s = new Scanner(System.in); 
            int teacherID = s.nextInt();
        
            //Enter Teacher ID
            System.out.println(" Enter Teacher ID ");
            int testID = s.nextInt();
        
            //Enter when the exam needs to be done
            System.out.println(" Enter dates as Month day (Oct 1) ");
            String dates = s.next();

            //Add all the user inputs to the database
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
 
    /**
     * This method prints out the schedule for all the tests
     */
    public static void viewSchedule()
    {
        // This asks for the date that the user wants to check if any exams are on
        System.out.println("Enter date to view as month and day (nov 1) ");
        Scanner s = new Scanner(System.in); 
        String input = s.nextLine();
            
        try {
            //This gets the data from the user input
            String sql = ("select * From Schedule JOIN Teacher ON Schedule.teacher_ID = Teacher.teacher_ID WHERE dates like \"" + input + "%\" " );
                
            p = configDB.c.prepareStatement(sql);
            p.clearParameters();
            r = p.executeQuery();
                   
            //Initialises the variables to print the schedule
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
                //This prints out the data from the schedule
                System.out.println("Teacher ID: " + teacherID + " teacher: " + fn + " " + ln + " testID: " + testID);
                
            }
        }      
        catch (Exception e)
        {
            e.printStackTrace();
        }    
    } 
}
    
