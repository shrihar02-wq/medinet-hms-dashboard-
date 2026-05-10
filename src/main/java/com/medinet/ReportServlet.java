import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ReportServlet")

public class ReportServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String patient =
        request.getParameter("patient");

        String bp =
        request.getParameter("bp");

        String sugar =
        request.getParameter("sugar");

        String weight =
        request.getParameter("weight");

        try {

            Connection con =
            DBconnection.getConnection();

            String sql =
            "INSERT INTO reports(patient_name,bp,sugar,weight,report_date) VALUES(?,?,?,?,CURDATE())";

            PreparedStatement pst =
            con.prepareStatement(sql);

            pst.setString(1, patient);
            pst.setString(2, bp);
            pst.setString(3, sugar);
            pst.setString(4, weight);

            pst.executeUpdate();

            response.getWriter().println(
            "<h2>Report Added Successfully</h2>"
            );

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}