import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

@WebServlet("/TodayAppointmentServlet")

public class TodayAppointmentServlet
extends HttpServlet {

@Override

protected void doGet(
HttpServletRequest request,
HttpServletResponse response)

throws ServletException, IOException {

try{

HttpSession session=
request.getSession(false);

if(session==null){

response.sendRedirect("login.html");
return;
}

String doctorName=

(String)session.getAttribute(
"doctorName");

if(doctorName==null){

response.getWriter().println(
"Doctor Session Missing");

return;
}

Connection con=
DBconnection.getConnection();

String query=

"SELECT * FROM appointments WHERE doctor_name=? AND appointment_date=CURDATE()";

PreparedStatement ps=
con.prepareStatement(query);

ps.setString(1,doctorName);

ResultSet rs=
ps.executeQuery();

ArrayList<String[]> list=
new ArrayList<>();

while(rs.next()){

String data[]={

rs.getString("id"),

rs.getString("patient_name"),

rs.getString("appointment_date"),

rs.getString("appointment_time"),

rs.getString("symptoms"),

rs.getString("status")
};

list.add(data);
}

request.setAttribute(
"appointments",
list);

request.getRequestDispatcher(
"today-appointments.jsp")
.forward(request,response);

}

catch(Exception e){

e.printStackTrace();
}
}
}