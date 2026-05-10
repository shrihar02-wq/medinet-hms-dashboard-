package com.medinet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BookAppointmentServlet")

public class BookAppointmentServlet
extends HttpServlet {

@Override

protected void doPost(
HttpServletRequest request,
HttpServletResponse response)

throws ServletException, IOException {

String patientName=
request.getParameter("patient_name");

String doctorName=
request.getParameter("doctor_name");

String appointmentDate=
request.getParameter("appointment_date");

String appointmentTime=
request.getParameter("appointment_time");

String symptoms=
request.getParameter("symptoms");

try{

Connection con=
DBconnection.getConnection();

String query=

"insert into appointments(patient_name,doctor_name,appointment_date,appointment_time,symptoms,status) values(?,?,?,?,?,?)";

PreparedStatement ps=
con.prepareStatement(query);

ps.setString(1,patientName);
ps.setString(2,doctorName);
ps.setString(3,appointmentDate);
ps.setString(4,appointmentTime);
ps.setString(5,symptoms);
ps.setString(6,"Pending");

int i=ps.executeUpdate();

if(i>0){

response.sendRedirect(
"patients.jsp?success=1");
}

else{

response.sendRedirect(
"patients.jsp?error=1");
}

}catch(Exception e){

e.printStackTrace();
}
}
}
