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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yash
 */
public class Confirm extends HttpServlet {

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
            PreparedStatement pst = conn.prepareStatement("Select * from USERS where Name=? and password=?");
            pst.setString(1, user);
            pst.setString(2, pass);
            ResultSet rs = pst.executeQuery();
     //       out.println("here");
            if (rs.next()) {
                out.println("Correct login credentials");
                 RequestDispatcher myDispatch = request.getRequestDispatcher("ProjectDisp.jsp");
                 myDispatch.forward(request, response);
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