package com.servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.servlets.FirstLogin;
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

public class FirstLogin extends HttpServlet {

       
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
         PrintWriter out =response.getWriter();
         out.println("this is password servlet..........");
        String email=request.getParameter("email");
        String passw=request.getParameter("password");
         try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conc =DriverManager.getConnection("jdbc:mysql://localhost:3306/test_db","root","admin");
            Statement st = conc.createStatement();
            String query = ("SELECT * FROM register ;");
            ResultSet rs = st.executeQuery(query);
             if (rs.next()){
                String q="UPDATE register SET Password =? WHERE Password is NULL AND Verified='Verified' AND Email=? ;";
                PreparedStatement pstm= conc.prepareStatement(q);
                pstm.setString(1,passw);
                pstm.setString(2,email);
                pstm.executeUpdate();
                out.println("Password Added! ");
                out.println("Please Login");
                out.println("<a href=\"Login.html\">Login</a>");
               
             }
   
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            out.println("Error Password wala....");
        } catch (SQLException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
             out.println("Error! Password already set..........");
        }
    }
   }










































