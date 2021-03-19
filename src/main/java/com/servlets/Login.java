/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlets;

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
import javax.servlet.ServletException;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Bhavesh
 */
public class Login extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                response.setContentType("text/html");
                response.setHeader("Cache-Control","no-cache");
                response.setHeader("Cache-Control","no-store");
                response.setHeader("Pragma","no-cache");
                response.setDateHeader("Expire", 0);
         PrintWriter out =response.getWriter();
         out.println("this is login servlet..........");
        String email=request.getParameter("email");
        String passw=request.getParameter("password");
         try {
            String id=null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conc =DriverManager.getConnection("jdbc:mysql://localhost:3306/test_db","root","admin");
            Statement st = conc.createStatement();
            String query = ("SELECT * FROM register WHERE Email='"+email+"' AND Password ='"+passw+"';");
            ResultSet rs = st.executeQuery(query);
             while (rs.next()){         
                id = rs.getString("client_id");
                String fname = rs.getString("Fname");
                String mname = rs.getString("Mname");
                String lname = rs.getString("Lname");
                String ogemail = rs.getString("Email");
                String mnumb = rs.getString("Mobile");
                String dob = rs.getString("DOB");
                String cn = rs.getString("City");
                String sn = rs.getString("State");
                String coun = rs.getString("Country");
                String veri = rs.getString("Verified");
                String lc = rs.getString("LoginCount");
                int i; 
                i = Integer.parseInt(lc);
                i=i+1;
                String lcount=String.valueOf(i);
                out.println("<h1>Login Success</h1>");
                
                out.println("<h2>First Name: "+ fname+"</h2>");
                out.println("<h2>Middle Name: "+ mname+"</h2>");
                out.println("<h2>Last Name: "+ lname+"</h2>");
                out.println("<h2>Email ID: "+ ogemail+"</h2>");
                out.println("<h2>Mobile Number: "+ mnumb+"</h2>");
                out.println("<h2>Date Of Birth: "+ dob+"</h2>");
                out.println("<h2>City Name: "+ cn+"</h2>");
                out.println("<h2>State Name: "+ sn+"</h2>");
                out.println("<h2>Country Name: "+ coun+"</h2>");
                out.println("<h2>Email is: "+veri +"</h2>");
                out.println("<a href=\"Login.html\">Logout</a>");
                
                String q="UPDATE register SET LoginCount =?   WHERE client_id=?";
                PreparedStatement pstm= conc.prepareStatement(q);
                pstm.setString(1,lcount);
                pstm.setString(2,id);
                pstm.executeUpdate();
               if(id==null){
                   out.println("Login Failed! Email or Password Incorrect");
               }
             }
   
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            out.println("Error Login wala....");
        } catch (SQLException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
             out.println("Error! Email or Password Wrong..........");
        }
    }
}






























