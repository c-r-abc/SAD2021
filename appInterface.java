/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SAD;

/**
 *
 * @author Bayley
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class appInterface {
    static int answer;
    
    Scanner s = new Scanner(System.in);
    private final static int CONTINUE = 10;
    private final static int A = 1;
    private final static int B = 2; 
    private final static int C = 3;
    private final static int D = 4; 
    private final static int EXIT = 0; 
    
    public static void start(){
        int answer = CONTINUE;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        Scanner s = new Scanner(System.in);
        try{        
            while(answer!=EXIT){
                System.out.println("\nPlease choose who you are\n"
                    + "1 = Student \n"
                    + "2 = Teacher \n"
                    + "0 = Quit APP");
            
                answer = s.nextInt();           
        
                switch(answer){
            
                    case A:
                        System.out.println("\nPlease login with your details");
                        String[] studentDetails = createStudent();
                        Student s1 = new Student(studentDetails[0], studentDetails[1], studentDetails[2], studentDetails[3]);
                        s1.options();
                        break;
                    
                    //case B:
                    
                    
                    case EXIT:
                        System.out.println("\nQuiting APP");
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
            start();
        }
    }
        
    
    
    public static String[] createStudent(){
        Scanner s = new Scanner(System.in);
        String[] details = new String[4];
        
        System.out.println("\nPlease enter your First Name");
        String firstName = s.nextLine();
        details[0] = firstName;
        
        System.out.println("\nPlease enter your Last Name");
        String lastName = s.nextLine();
        details[1] = lastName;
                
        
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
        
        System.out.println("\nPlease enter your group name");
        String groupName = s.nextLine();
        details[3] = groupName;
        
        
        return details;
    }
    
    public void connectDB(){
        
    }
    
    public static void main(String[] args) {
        start();
    }
}
