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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;


/**
 *
 * @author Bhavesh
 */

public class ForgotPassOTP extends HttpServlet {

       
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                response.setContentType("text/html");
                response.setHeader("Cache-Control","no-cache");
                response.setHeader("Cache-Control","no-store");
                response.setHeader("Pragma","no-cache");
                response.setDateHeader("Expire", 0);
         PrintWriter out =response.getWriter();
         out.println("this is forgotpass-otp  servlet..........");
        String email=request.getParameter("Email");
        String userotp=request.getParameter("authcode");
        String passw=request.getParameter("password");
 
        
         try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conc =DriverManager.getConnection("jdbc:mysql://localhost:3306/test_db","root","admin");
            Statement st = conc.createStatement();
            String query = ("SELECT * FROM register WHERE Email='"+email+"' AND otp ='"+userotp+"';");
            ResultSet rs = st.executeQuery(query);
             while (rs.next()){         
                String client_id = rs.getString("client_id");
                String q="UPDATE register SET Password =?   WHERE client_id=?";
                PreparedStatement pstm= conc.prepareStatement(q);
                pstm.setString(1,passw);
                pstm.setString(2,client_id);
                pstm.executeUpdate();
                out.println("Password Updated Successfully!");
                out.println("<a href=\"Login.html\">Login</a>");
                }
             
   
   
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            out.println("Error2....");
        } catch (SQLException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
             out.println("Error SQL 2..........");
        }
    }
   }




























