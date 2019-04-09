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
public class Prof_Register extends HttpServlet {

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
        String area = request.getParameter("field");
        String con = request.getParameter("contact");
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
                PreparedStatement pst1 = conn.prepareStatement("SELECT COUNT(*) FROM PROF_INFO");
                ResultSet rs1 = pst1.executeQuery();
                int count = 0;
                while(rs1.next()){
                    count = rs1.getInt("count(*)");
                }
                out.println(count);
                
                
                PreparedStatement pst = conn.prepareStatement("INSERT INTO PROF_INFO VALUES (?, ?, ?, ?, ?, ? ,?)");
                pst.setInt(1, count+1);
                pst.setString(2, name);
                pst.setString(3, email);
                pst.setString(4, pass);
                pst.setString(5, area);
                pst.setString(6, con);
                pst.setString(7, address);
                
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