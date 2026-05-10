package com.medinet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PatientServlet")

public class PatientServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String gender = request.getParameter("gender");
        String contact = request.getParameter("contact");

        try {

            Connection con = DBconnection.getConnection();

            String sql = "INSERT INTO patients(name,age,gender,contact) VALUES(?,?,?,?)";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, name);
            pst.setInt(2, Integer.parseInt(age));
            pst.setString(3, gender);
            pst.setString(4, contact);

            pst.executeUpdate();

            response.getWriter().println(

            "<html>" +

            "<body style='font-family:Arial;background:#f4f6f9;padding:40px;'>"+

            "<div style='width:400px;margin:auto;background:white;padding:30px;border-radius:15px;'>"+

            "<h2 style='color:green;'>Patient Added Successfully</h2>"+

            "<p><b>Name:</b> " + name + "</p>" +

            "<p><b>Age:</b> " + age + "</p>" +

            "<p><b>Gender:</b> " + gender + "</p>" +

            "<p><b>Contact:</b> " + contact + "</p>" +

            "<br><a href='patients.html'>Back</a>" +

            "</div>" +

            "</body>" +

            "</html>"

            );

            con.close();

        } catch(Exception e) {

            response.getWriter().println(
                "<h2>Error : " + e.getMessage() + "</h2>"
            );
        }
    }
}
