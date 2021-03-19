package com.servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

public class ForgotPass extends HttpServlet {

       
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
         PrintWriter out =response.getWriter();
         out.println("this is forgot-pass servlet..........");
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
        
        String email=request.getParameter("Email");
        String dob=request.getParameter("Birth_Date");
         try {
         
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conc =DriverManager.getConnection("jdbc:mysql://localhost:3306/test_db","root","admin");
            Statement st = conc.createStatement();
            String query = ("SELECT * FROM register WHERE Email='"+email+"' AND DOB ='"+dob+"';");
            ResultSet rs = st.executeQuery(query);
             while (rs.next()){         
                String client_id = rs.getString("client_id");
                String q="UPDATE register SET otp =?   WHERE client_id=?";
                PreparedStatement pstm= conc.prepareStatement(q);
                pstm.setString(1,str);
                pstm.setString(2,client_id);
                pstm.executeUpdate();
                }
             
   
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            out.println("Error Forgot Pass....");
        } catch (SQLException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
             out.println("Not Registered with this mail.........");
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
            message.setSubject("User Email Verification to Update Password!");
             

            // Now set the actual message
            
            message.setText("Please verify your account using this OTP code: "+str+" to Update Password!");

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
                          
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        RequestDispatcher rd=request.getRequestDispatcher("ForgotPassOTP.html");
        rd.forward(request, response);
    }
   }































