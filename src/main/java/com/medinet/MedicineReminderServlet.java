// MedicineReminderServlet.java
package com.medinet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MedicineReminderServlet")

public class MedicineReminderServlet
extends HttpServlet {

    @Override

    protected void doPost(

    HttpServletRequest request,
    HttpServletResponse response)

    throws ServletException, IOException {

        /* GET FORM VALUES */

        String medicineName =
        request.getParameter("medicineName");

        String reminderDate =
        request.getParameter("reminderDate");

        String reminderTime =
        request.getParameter("reminderTime");

        String ampm =
        request.getParameter("ampm");

        String tabletType =
        request.getParameter("tabletType");

        String dosage =
        request.getParameter("dosage");

        String notes =
        request.getParameter("notes");

        try {

            /* MYSQL DRIVER */

            Class.forName(
            "com.mysql.cj.jdbc.Driver");

            /* DATABASE CONNECTION */

            Connection con =
            DriverManager.getConnection(

            "jdbc:mysql://localhost:3306/medinet_hms",
            "root",
            "Priya@123"

            );

            /* INSERT QUERY */

            String query =

            "INSERT INTO medicine_reminders " +

            "(medicine_name, reminder_date, reminder_time, ampm, tablet_type, dosage, notes) " +

            "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps =
            con.prepareStatement(query);

            /* SET VALUES */

            ps.setString(1, medicineName);

            ps.setString(2, reminderDate);

            ps.setString(3, reminderTime);

            ps.setString(4, ampm);

            ps.setString(5, tabletType);

            ps.setString(6, dosage);

            ps.setString(7, notes);

            /* EXECUTE */

            int result =
            ps.executeUpdate();

            if(result > 0){

                response.sendRedirect(
                "patients.html");

            } else {

                response.getWriter().println(
                "Reminder Failed");
            }

            /* CLOSE */

            ps.close();
            con.close();

        } catch(Exception e){

            e.printStackTrace();

            response.getWriter().println(

            "Database Error : " +
            e.getMessage()

            );
        }
    }
}
