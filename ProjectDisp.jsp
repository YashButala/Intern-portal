<%-- 
    Document   : ProjectDisp
    Created on : 7 Apr, 2019, 1:58:46 AM
    Author     : yash
--%>

<!DOCTYPE html>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %> 
<html>
<head>
<title>display data from the table using jsp</title>
</head>
<body>
<h2>List of Projects</h2>
<%
try {
/* Create string of connection url within specified format with machine
name, port number and database name. Here machine name id localhost and 
database name is student. */
String connectionURL = "jdbc:mysql://localhost:3306/Loginfo";
// declare a connection by using Connection interface
Connection connection = null;
/* declare object of Statement interface that is used for executing sql 
statements. */
Statement statement = null;
// declare a resultset that uses as a table for output data from tha table.
ResultSet rs = null;
// Load JBBC driver "com.mysql.jdbc.Driver"
Class.forName("com.mysql.jdbc.Driver").newInstance();
/* Create a connection by using getConnection() method that takes parameters 
of string type connection url, user name and password to connect to database.*/
connection = DriverManager.getConnection(connectionURL, "root", "software");
/* createStatement() is used for create statement object that is used for 
sending sql statements to the specified database. */
statement = connection.createStatement();
// sql query to retrieve values from the secified table.
String QueryString = "SELECT * from Project_info";



rs = statement.executeQuery(QueryString);
%>

  
   
  <TABLE cellpadding="15" border="1" style="background-color: #ffffcc;">

`   <%
    while (rs.next()) {
    %> 

    <TR>
    <TD><%=rs.getInt(1)%></TD>
    <TD><%=rs.getString(2)%></TD>
    <TD><%=rs.getString(3)%></TD>
    <TD><%=rs.getString(4)%></TD>
    
</TR>
<% } %>
  
<%
// close all the connections.
rs.close();
statement.close();
connection.close();
} catch (Exception ex) {
%>

<%
out.println("Unable to connect to database.");
}
%>
</TABLE>
<label><br><br>Enter the ID of projects in oreder of precedence.<br>YOu can apply for upto 3 projects.</label>
<div class="userinput">
    <br>
   <label> First Preference   :</label>
   <input type=int name="pref1" value="" />
  </div>
<div class="userinput">
    <br>
   <label> Second Preference &nbsp &nbsp &nbsp : </label>
   <input type=int name="pref2" value="" />
  </div>
<div class="userinput">
    <br>
   <label> Third Preference&nbsp &nbsp &nbsp :</label>
   <input type=int name="pref3" value="" />
  </div>


<TABLE>

<br>    
<a href="index1.html" >
<button type="submit"><--return</button>
</a>
</TABLE>

</font>
</body>
</html>