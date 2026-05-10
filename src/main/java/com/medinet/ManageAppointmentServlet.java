import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ManageAppointmentServlet")

public class ManageAppointmentServlet
extends HttpServlet {

@Override

protected void doPost(
HttpServletRequest request,
HttpServletResponse response)

throws ServletException, IOException {

String id =
request.getParameter("id");

String status =
request.getParameter("status");

try{

Connection con =
DBconnection.getConnection();

String query =

"update appointments set status=? where id=?";

PreparedStatement ps =
con.prepareStatement(query);

ps.setString(1,status);

ps.setString(2,id);

int i =
ps.executeUpdate();

if(i>0){

response.sendRedirect(
"manage-appointments.jsp");
}

else{

response.getWriter().println(
"Status Update Failed");
}

}

catch(Exception e){

e.printStackTrace();
}
}
}