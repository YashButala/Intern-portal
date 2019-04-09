/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
/**
 *
 * @author yash
 */
public class Stud_Register extends HttpServlet {

    int y,d,g;
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //out.println("Here, you'll see the result\n");
        String name = request.getParameter("Name");
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        String confpass = request.getParameter("confpassword");
        String[] deg = request.getParameterValues("degree");
         // Get null if the parameter is missing from query string.
        
         if (deg == null || deg.length == 0) {
            out.println("<p>Degree: NONE</p>");
         } else {
             int tmp=0;
             while(tmp==0)
             {
                 tmp=0;
                 for (String deg1 : deg) 
                 {
                    if (deg1.equals("Bach")) {
                        tmp+=1;
                        d=1;
                    } else if (deg1.equals("Mast")) {
                        tmp+=1;
                        d=2;
                    } else if (deg1.equals("Doc")) {
                        tmp+=1;
                        d=3;
                    }
                 }   
                 if(tmp==1)
                 {
                     break;
                 }
                 else
                 {
                     out.println("Select 1 field");
                 }
            }
            out.println("</p>Done");
         }
         String[] year = request.getParameterValues("year");
    //     int y;
         // Get null if the parameter is missing from query string.
         if (deg == null || deg.length == 0) {
            out.println("<p>Year: NONE</p>");
         } else {
             int tmp=0;
             while(tmp==0)
             {
                 tmp=0;
                 for (String year1 : year) 
                 {
                    if (year1.equals("1st")) {
                        tmp+=1;
                        y=1;
                    } else if (year1.equals("2nd")) {
                        tmp+=1;
                        y=2;
                    } else if (year1.equals("3rd")) {
                        tmp+=1;
                        y=3;
                    } else if (year1.equals("4th")) {
                        tmp+=1;
                        y=4;
                    } else if (year1.equals("5th")) {
                        tmp+=1;
                        y=5;
                    }
                 }   
                 if(tmp==1)
                 {
                     break;
                 }
                 else
                 {
                     out.println("Select 1 field");
                 }
            }
            out.println("</p>Done");
         }
         
         String dis = request.getParameter("discipline");
         String insti = request.getParameter("institute");
         String dob= request.getParameter("dateOfBirth");
         String gender = request.getParameter("gender");
         if(gender=="m")
             g=1;
         else
             g=2;
         String contactNumber= request.getParameter("contactNumber");
         String address= request.getParameter("comments");
         
        if(!pass.equals(confpass)){
            out.println("Error : Passwords do not match");
        }else{
            try {
                out.println("here1\n");
                Class.forName("com.mysql.jdbc.Driver");
                out.println("here2\n");

                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Loginfo", "root", "software");
                out.println("here3\n");
                PreparedStatement pst1 = conn.prepareStatement("SELECT COUNT(*) FROM USERS");
                ResultSet rs1 = pst1.executeQuery();
                int count = 0;
                while(rs1.next()){
                    count = rs1.getInt("count(*)");
                }
                out.println(count);
                
                
                PreparedStatement pst = conn.prepareStatement("INSERT INTO USERS VALUES (?, ?, ?, ?, ?, ? ,?, ?, ?, ?, ?, ?)");
                pst.setInt(1, count+1);
                pst.setString(2, name);
                pst.setString(3, email);
                pst.setString(4, pass);
                pst.setInt(5, d);
                pst.setInt(6, y);
                pst.setString(7, dis);
                pst.setString(8, insti);
                pst.setString(9, dob);
                pst.setInt(10, g);
                pst.setString(11, contactNumber);
                pst.setString(12, address);
                
                int i = pst.executeUpdate();
                out.println("Account successfully created !");
             //   response.sendRedirect("http://localhost:8080/Login/");
                /*
                if (rs.next()) {
                    out.println("Correct login credentials");
                    response.sendRedirect("home.jsp");
                } 
                else {
                    out.println("Incorrect login credentials");
                }*/
            
            }               
            catch (ClassNotFoundException | SQLException e) {
                out.println("error");
                e.printStackTrace();
            }
        }
        
    }
}