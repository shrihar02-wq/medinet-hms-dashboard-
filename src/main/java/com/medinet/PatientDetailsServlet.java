package com.medinet;
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

@WebServlet("/PatientDetailsServlet")

public class PatientDetailsServlet
extends HttpServlet {

@Override

protected void doGet(
HttpServletRequest request,
HttpServletResponse response)

throws ServletException, IOException {

try{

Connection con=
DBconnection.getConnection();

String query=

"SELECT * FROM appointments ORDER BY appointment_date DESC";

PreparedStatement ps=
con.prepareStatement(query);

ResultSet rs=
ps.executeQuery();

ArrayList<String[]> list=
new ArrayList<>();

while(rs.next()){

String data[]={

rs.getString("id"),

rs.getString("patient_name"),

rs.getString("doctor_name"),

rs.getString("appointment_date"),

rs.getString("appointment_time"),

rs.getString("symptoms"),

rs.getString("status")
};

list.add(data);
}

request.setAttribute(
"patients",
list);

request.getRequestDispatcher(
"Patient-details.jsp")
.forward(request,response);

}

catch(Exception e){

response.getWriter().println(e);
}
}
}
