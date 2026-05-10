package com.medinet;
import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AppointmentServlet")

public class AppointmentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String patient =
        request.getParameter("patient_name");

        String doctor =
        request.getParameter("doctor_name");

        String date =
        request.getParameter("appointment_date");

        String time =
        request.getParameter("appointment_time");

        String symptoms =
        request.getParameter("symptoms");

        try {

            Connection con =
            DBconnection.getConnection();

            /* CHECK SLOT */

            String checkQuery =
            "SELECT * FROM appointments WHERE doctor_name=? AND appointment_date=? AND appointment_time=?";

            PreparedStatement check =
            con.prepareStatement(checkQuery);

            check.setString(1, doctor);
            check.setString(2, date);
            check.setString(3, time);

            ResultSet rs =
            check.executeQuery();

            /* IF ALREADY BOOKED */

            if(rs.next()) {

                response.getWriter().println(

                "<html>" +

                "<body style='font-family:Arial;text-align:center;padding-top:100px;background:#f4f6f9;'>" +

                "<div style='width:400px;margin:auto;background:white;padding:40px;border-radius:20px;box-shadow:0 10px 30px rgba(0,0,0,0.1);'>" +

                "<h2 style='color:red;'>Doctor Busy ❌</h2>" +

                "<p>Choose Another Time</p>" +

                "<br>" +

                "<a href='appointments.html'>" +

                "<button style='padding:12px 25px;background:#2f6fed;color:white;border:none;border-radius:10px;'>Back</button>" +

                "</a>" +

                "</div>" +

                "</body>" +

                "</html>"

                );

            }

            else {

                /* INSERT APPOINTMENT */

                String insertQuery =
                "INSERT INTO appointments(patient_name,doctor_name,appointment_date,appointment_time,symptoms,status) VALUES(?,?,?,?,?,?)";

                PreparedStatement pst =
                con.prepareStatement(insertQuery);

                pst.setString(1, patient);
                pst.setString(2, doctor);
                pst.setString(3, date);
                pst.setString(4, time);
                pst.setString(5, symptoms);
                pst.setString(6, "Booked");

                pst.executeUpdate();

                /* SUCCESS PAGE */

                response.getWriter().println(

                "<html>" +

                "<body style='font-family:Arial;text-align:center;padding-top:100px;background:#f4f6f9;'>" +

                "<div style='width:450px;margin:auto;background:white;padding:40px;border-radius:20px;box-shadow:0 10px 30px rgba(0,0,0,0.1);'>" +

                "<div style='font-size:80px;color:green;'>✔</div>" +

                "<h2 style='color:green;'>Appointment Confirmed</h2>" +

                "<p>Your appointment has been booked successfully.</p>" +

                "<br>" +

                "<p><b>Doctor:</b> " + doctor + "</p>" +

                "<p><b>Date:</b> " + date + "</p>" +

                "<p><b>Time:</b> " + time + "</p>" +

                "<br>" +

                "<a href='patients.html'>" +

                "<button style='padding:12px 30px;background:#2f6fed;color:white;border:none;border-radius:10px;font-size:16px;'>Done</button>" +

                "</a>" +

                "</div>" +

                "</body>" +

                "</html>"

                );

            }

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}
