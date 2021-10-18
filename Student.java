/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SAD;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Scanner;

/**
 *
 * @author Bayley
 */




public class Student {
    
    private final int CONTINUE = 10;
    private final int A = 1;
    private final int B = 2; 
    private final int C = 3;
    private final int D = 4; 
    private final int E = 5;
    private final int EXIT = 0;
    
    
    
    String firstName;
    String lastName;
    String DOB;
    String group;
    int studentId;
    
    public Student(String firstName, String lastName, String DOB, String group){
        this.firstName=firstName;
        this.lastName=lastName;
        this.DOB=DOB;
        this.group=group;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDOB() {
        return DOB;
    }

    public String getGroup() {
        return group;
    }

    public int getStudentId() {
        return studentId;
    }

    @Override
    public String toString() {
        return "\n-First name = " + this.getFirstName() 
                + "\n-Last Name = " + this.getLastName() 
                + "\n-DOB = " + this.getDOB() 
                + "\n-Group = " + this.getGroup();
    }
    
    
    
    public void options(){
        int answer = CONTINUE;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        Scanner s = new Scanner(System.in);
        try{        
            while(answer!=EXIT){
                System.out.println("\nWhat would you like to do? \n"
                    + "1 = Edit student details \n"
                    + "2 = Add student to database \n"
                    + "3 = Record attendance for student \n"
                    + "4 = Seach for a student \n"
                    + "0 = Return to home");
            
                answer = s.nextInt();           
        
                switch(answer){
            
                    case A:
                        this.editDetails();
                        break;
                    
                    case B:
                        this.addToDB();
                        break;
                    
                    case C:
                        this.recordAttendance();
                        break;
                        
                    case D:
                        this.searchStudent();
                        break;
                    
                    case EXIT:
                        System.out.println("\nReturned to home");
                        break;
                
                    default:
                        System.out.println("\nPlease enter a correct value");
                        break;
                }
            }
        }
        catch(InputMismatchException e){
            System.out.println("\n-------------------------------------------------"
                    + "\nThis is the wrong input please try again");
            options();
        }
    }
    
    public void editDetails(){
        int answer = CONTINUE;
        String input = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        Scanner s = new Scanner(System.in);
        try{
            while(answer!=EXIT){
                System.out.println("\nThis is your current details:" + toString());
                System.out.println("\nPlease choose what you would like to edit : \n"
                    + "1 = First Name \n"
                    + "2 = Last Name \n"
                    + "3 = Date Of Birth \n"
                    + "4 = Group \n"
                    + "0 = Stop Editing Details");
            
                answer = s.nextInt();           
        
               switch(answer){
            
                    case A:
                        System.out.println("\nPlease enter the first name you would like");
                        Scanner ss = new Scanner(System.in);
                        String input_1 = ss.nextLine();
                        setFirstName(input_1);    
                        break;
                    case B:
                        System.out.println("\nPlease enter the last name you would like");
                        Scanner sss = new Scanner(System.in);
                        String input_2 = sss.nextLine();
                        setLastName(input_2);
                        break;
                    case C:
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
                        String input_3 = dateOfBirth;
                        setDOB(input_3); 
                        break;
                        
                    case D:
                        System.out.println("\nPlease enter the group you would like");
                        Scanner sssss = new Scanner(System.in);
                        String input_4 = sssss.nextLine();
                        setGroup(input_4); 
                        break;
                        
                    case EXIT:
                        System.out.println("\nFinished Editing Details");
                        break;
                
                    default:
                        System.out.println("\nPlease enter a correct value");
                        break;
                }
            }
        }
        catch(InputMismatchException e){
            System.out.println("\n-------------------------------------------------"
                    + "\nThis is the wrong input please try again");
            editDetails();
        }
    }
    
    
    
    public void addToDB(){
        System.out.println("\nThis is your current details:" + toString());
        int answer = CONTINUE;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        Scanner s = new Scanner(System.in);
        
        try{
            while(answer!=EXIT){
            System.out.println("\nWould you like to add this student to the database? \n"
                + "1 = Yes \n"
                + "0 = No");
            
            answer = s.nextInt();           
            switch(answer){
            
                case A:
                    //insert conenction and add student with this data.
                    
                    System.out.println("\nThis student has been added to the database");
                    answer = EXIT;
                    break;
                    
                case EXIT:
                    System.out.println("\nYou have been returned to the student screen");
                    break;
                
                default:
                    System.out.println("\nPlease enter a correct value");
                    break;
                }
            }
        }
        catch(InputMismatchException e){
            System.out.println("\n-------------------------------------------------"
                    + "\nThis is the wrong input please try again");
            addToDB();
        }
    }
    
    public void recordWeeklyMeeting(){
    
    }
    
    public void recordWeeklyIntensive(){
        
    }
    
    public void recordPart(){
        
    }
    
    public void recordTopic(){
        
    }
    
    public void recordTest(){
        
    }
    
    public void recordAttendance(){
        System.out.println("\nThis is your current details:" + toString());
        System.out.println("\nPlease ensure your details are correct");
        int answer = CONTINUE; 
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        Scanner s = new Scanner(System.in);
        
        try{
            while(answer!=EXIT){
            System.out.println("\nWhat would you like to record \n"
                + "1 = Weekly meeting \n"
                + "2 = Weekly intensive \n"
                + "3 = Part Completed \n"
                + "4 = Topic completed \n"
                + "5 = Test completed \n"
                + "0 = Return to student screen");
            
            answer = s.nextInt();           
            switch(answer){
            
                case A:
                    recordWeeklyMeeting();                    
                    break;
                    
                case B:
                    recordWeeklyIntensive();
                    break;
                    
                case C:
                    recordPart();
                    break;
                    
                case D:
                    recordTopic();
                    break;
                    
                case E:
                    recordTest();
                    break;
                    
                case EXIT:
                    System.out.println("\nYou have been returned to the student screen");
                    break;
                
                default:
                    System.out.println("\nPlease enter a correct value");
                    break;
                }
            }
        }
        catch(InputMismatchException e){
            System.out.println("\n-------------------------------------------------"
                    + "\nThis is the wrong input please try again");
            recordAttendance();
        }        
    }
    
    public void searchStudent(){
        Scanner s = new Scanner(System.in);
        System.out.println("\nPlease enter the details of the student you want to search");

        System.out.println("\nPlease enter your First Name");
        String searchFirstName = s.nextLine();
        
        System.out.println("\nPlease enter your Last Name");
        String searchLastName = s.nextLine();
        
        
        
    }
}
