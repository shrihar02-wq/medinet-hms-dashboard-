import java.io.File;
import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet("/UploadPrescriptionServlet")

@MultipartConfig

public class UploadPrescriptionServlet
extends HttpServlet {

@Override

protected void doPost(
HttpServletRequest request,
HttpServletResponse response)

throws ServletException, IOException {

String patientName=
request.getParameter("patient_name");

String medicines=
request.getParameter("medicines");

String dosage=
request.getParameter("dosage");

String notes=
request.getParameter("notes");

HttpSession session=
request.getSession();

String doctorName=
(String)session.getAttribute("doctorName");

Part filePart=
request.getPart("prescription_file");

String fileName=
filePart.getSubmittedFileName();

String uploadPath=

getServletContext().getRealPath("")
+ File.separator
+ "uploads";

File uploadDir=
new File(uploadPath);

if(!uploadDir.exists()){

uploadDir.mkdir();
}

filePart.write(
uploadPath + File.separator + fileName);

try{

Connection con=
DBconnection.getConnection();

String query=

"insert into prescriptions(patient_name,doctor_name,medicines,dosage,notes,file_name) values(?,?,?,?,?,?)";

PreparedStatement ps=
con.prepareStatement(query);

ps.setString(1,patientName);

ps.setString(2,doctorName);

ps.setString(3,medicines);

ps.setString(4,dosage);

ps.setString(5,notes);

ps.setString(6,fileName);

int i=
ps.executeUpdate();

if(i>0){

response.sendRedirect(
"upload-prescription.jsp?success=1");
}

else{

response.getWriter().println(
"Upload Failed");
}

}

catch(Exception e){

e.printStackTrace();
}
}
}