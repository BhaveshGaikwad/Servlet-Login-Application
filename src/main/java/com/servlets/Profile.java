/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.*;
import javax.servlet.http.*;

/**
*

- @author Bhavesh
*/
public class Profile extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    response.setContentType("text/html");
    response.setHeader("Cache-Control","no-cache");
    response.setHeader("Cache-Control","no-store");
    response.setHeader("Pragma","no-cache");
    response.setDateHeader("Expire", 0);

    PrintWriter out =response.getWriter();
    String id=request.getParameter("id");

    try {

    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection conc =DriverManager.getConnection("jdbc:mysql://localhost:3306/test_db","root","admin");
    Statement st = conc.createStatement();
    String query = ("SELECT * FROM student WHERE Id= '"+id+"' ;");
    ResultSet rs = st.executeQuery(query);
    while (rs.next()){
    id = rs.getString("Id");

    String name = rs.getString("Name");

    String branch=rs.getString("Branch");
             out.println("<h1>Login Success</h1>");

             out.println("<h2>Name: "+ name+"</h2>");

           out.println("<h2>Branch: "+branch+"</h2>");

             out.println("<h2>Id: "+id+"</h2>");

             out.println("<a href=\"abc.html\">Logout</a>");

            if(id==null){
                out.println("ID Failed! ID is incorrect!");
            }
          }

     } catch (ClassNotFoundException ex) {
         Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
         out.println("Error LoginPage....");
     } catch (SQLException ex) {
         Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
          out.println("Error SQL!.........");
     }

    }
    }

