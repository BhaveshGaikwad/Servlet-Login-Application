package com.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.*;
import javax.servlet.http.*;


/**
 *
 * @author Bhavesh
 */

public class RegisterServlet extends HttpServlet{

    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
         response.setContentType("text/html");
         PrintWriter out =response.getWriter();
           /*Generating OTP */
         int length = 5; 
         String numbers = "0123456789"; 
  
        // Using random method 
        Random rndm_method = new Random(); 
  
        char[] otp = new char[length]; 
        
        for (int i = 0; i < length; i++) 
        { 
            // Use of charAt() method : to get character value 
            // Use of nextInt() as it is scanning the value as int 
            otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length())); 
       
        } 
        String str =String.valueOf(otp);
        
         /*Saving data in strings */
         String fname=request.getParameter("First_Name");
         String mname=request.getParameter("Middle_Name");
         String lname=request.getParameter("Last_Name");
         String email=request.getParameter("Email_ID");
         String mnumb=request.getParameter("Mobile_Number");
         String dob=request.getParameter("Birth_Date");
         String cn=request.getParameter("City_Name");
         String sn=request.getParameter("State_Name");
         String cnn=request.getParameter("Country_Name");

         
             /*Saving data in MYSQL Database */
        try {
         
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement pstmt;
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_db","root","admin");
                 String q="insert into register(Fname,Mname,Lname,Email,Mobile,DOB,City,State,Country,otp) values(?,?,?,?,?,?,?,?,?,?)";
                 pstmt = con.prepareStatement(q);
                 pstmt.setString(1,fname);
                 pstmt.setString(2,mname);
                 pstmt.setString(3,lname);
                 pstmt.setString(4,email);
                 pstmt.setString(5,mnumb);
                 pstmt.setString(6,dob);
                 pstmt.setString(7,cn);
                 pstmt.setString(8,sn);
                 pstmt.setString(9,cnn);
                 pstmt.setString(10,str);
                 pstmt.executeUpdate();
                 
           
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            out.println("Error SSQL Database....");
        } catch (SQLException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
             out.println("User Already Registered with this Email Please ..........");
        RequestDispatcher rdl=request.getRequestDispatcher("ErrorPages/RegistrationError.html");
        rdl.forward(request, response);
        return;
        }
        
         /*Sending Email */
          String to = email;

        // Sender's email ID needs to be mentioned
        String from = "localhostsendotp@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("localhostsendotp@gmail.com", "Always@1614");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("User Email Verification!");
             

            // Now set the actual message
            
            message.setText("Registration Successfully! Please verify your account using this OTP code: "+str);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
                          
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        RequestDispatcher rd=request.getRequestDispatcher("otp.html");
        rd.forward(request, response);

    }
}
































































































































