package com.medinet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MedicineSuggestionServlet")

public class MedicineSuggestionServlet
extends HttpServlet {

@Override

protected void doPost(
HttpServletRequest request,
HttpServletResponse response)

throws ServletException, IOException {

String symptoms=
request.getParameter("symptoms");

symptoms=
symptoms.toLowerCase();

String medicine="";
String advice="";

if(symptoms.contains("fever")){

medicine=
"Paracetamol";

advice=
"Take rest and drink more water.";
}

else if(symptoms.contains("cold")){

medicine=
"Cetirizine";

advice=
"Avoid cold drinks and take steam.";
}

else if(symptoms.contains("cough")){

medicine=
"Benadryl Syrup";

advice=
"Drink warm water regularly.";
}

else if(symptoms.contains("headache")){

medicine=
"Dolo 650";

advice=
"Take proper sleep and reduce stress.";
}

else if(symptoms.contains("stomach pain")){

medicine=
"Antacid";

advice=
"Avoid spicy foods.";
}

else{

medicine=
"Consult Doctor";

advice=
"Symptoms not identified properly.";
}

request.setAttribute(
"symptoms",
symptoms);

request.setAttribute(
"medicine",
medicine);

request.setAttribute(
"advice",
advice);

request.getRequestDispatcher(
"medicine-suggestions.jsp")
.forward(request,response);
}
}
