
// register and login of user

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class LaunchUser {
   public static void main(String[] args) throws Exception {
	   Scanner scan= new Scanner(System.in); 
	   System.out.println("1. Register");
	   System.out.println("2."
	   		+ " Login");
	   System.out.println("enter your choice");
	   int choice = scan.nextInt();
	   
	   
	  switch(choice) {
	  case 1:register();
	  break;
	   
	  case 2:login();
	  break;
	  
	  default:System.out.println("invalid choice");
	  break;
	 
	  }  
	   
   }

     static void register() throws Exception {
	    DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
	    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/june_2024","root","W7301@jqir*");
	    Scanner scan= new Scanner(System.in);
	    System.out.println("enter name");
	    String name = scan.next();
	    
	    System.out.println("enter username");
	    String username = scan.next();
	    
	    while(true) {
	    	
	    String s ="select * from user1 where username=?";
	    PreparedStatement pstmt=con.prepareStatement(s);
	    pstmt.setString(1,  username);
	    ResultSet res =pstmt.executeQuery();
	    
	    if(res.next()) {
	    	System.out.println("username already exists");
	    	username = scan.next();
	    }
	    else {
	    	break;
	    }
	  } 
	    
	    String password;
	    String confirmPassword;
	    
	    do {
	    	System.out.println("enter password");
		     password = scan.next();
		    
		    System.out.println("enter confirmPassword");
		     confirmPassword = scan.next();
	    }while(!password.equals(confirmPassword));
	    
	    System.out.println("enter email");
	    String email = scan.next();
	    
	    String s1="insert into user1 value(?,?,?,?)";
	    PreparedStatement pstmt1 = con.prepareStatement(s1);
	    pstmt1.setString(1, name);
	    pstmt1.setString(2, username);
	    pstmt1.setString(3, password);
	    pstmt1.setString(4, email);
	    pstmt1.executeUpdate();
	    
	    }
	 
       static void login() throws Exception {
    	  DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
   	      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/june_2024","root","W7301@jqir*");

    	 Scanner scan=new Scanner(System.in);
    	 System.out.println("enter username");
   	     String username = scan.next();
   	    
   	     System.out.println("enter password");
         String password = scan.next();
         
         String s ="select * from user1 where username=?";
         PreparedStatement pstmt = con.prepareStatement(s);
         pstmt.setString(1, username);
         ResultSet res = pstmt.executeQuery();
         if(res.next()) {
        	 if(password.equals(res.getString(3))) {
        		 System.out.println("login successfull");
        	 }
        	 else {
        		 System.out.println("invalid password");
        	 }
         } 
         else {
        		 System.out.println("invalid username");
        	 }
         }      
}

