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
public class Prof_LoginServ extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
       // out.println("Here, you'll see the result\n");
        String user = request.getParameter("email");
        String pass = request.getParameter("pass");
       // out.println("Here, you'll see the result\n");
        try {
            //out.println("here1\n");
            Class.forName("com.mysql.jdbc.Driver");
            //out.println("here2\n");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Loginfo", "root", "software");
       //     out.println("here3\n");
            PreparedStatement pst = conn.prepareStatement("Select * from PROF_INFO where Email=? and Password=?");
            pst.setString(1, user);
            pst.setString(2, pass);
            ResultSet rs = pst.executeQuery();
     //       out.println("here");
            if (rs.next()) {
                out.println("Correct login credentials");
             //  RequestDispatcher myDispatch = request.getRequestDispatcher("ProjectDisp.jsp");
             //   myDispatch.forward(request, response);
            } 
            else {
                out.println("Incorrect login credentials");
            }
            
        } 
        catch (ClassNotFoundException | SQLException e) {
            out.println("error");
            e.printStackTrace();
        }
    }
}