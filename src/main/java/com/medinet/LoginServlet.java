import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(
    HttpServletRequest request,
    HttpServletResponse response)

    throws ServletException, IOException {

        String email =
        request.getParameter("email");

        String password =
        request.getParameter("password");

        String role =
        request.getParameter("role");

        try {

            Connection con =
            DBconnection.getConnection();

            PreparedStatement ps = null;

            // DOCTOR LOGIN

            if(role.equalsIgnoreCase("doctor")) {

                String query =

                "SELECT * FROM doctor_profile WHERE email=? AND password=?";

                ps = con.prepareStatement(query);

                ps.setString(1,email);
                ps.setString(2,password);

                ResultSet rs =
                ps.executeQuery();

                if(rs.next()) {

                    HttpSession session =
                    request.getSession();

                    session.setAttribute(
                    "email",
                    email);

                    session.setAttribute(
                    "doctorName",
                    rs.getString("doctor_name"));

                    session.setAttribute(
                    "role",
                    role);

                    response.sendRedirect(
                    "doctor-dashboard.jsp");
                }

                else {

                    response.getWriter().println(

                    "<h2>Invalid Doctor Login</h2>"

                    );
                }
            }

            // PATIENT LOGIN

            else if(role.equalsIgnoreCase("patient")) {

                String query =

                "SELECT * FROM users WHERE email=? AND password=?";

                ps = con.prepareStatement(query);

                ps.setString(1,email);
                ps.setString(2,password);

                ResultSet rs =
                ps.executeQuery();

                if(rs.next()) {

                    HttpSession session =
                    request.getSession();

                    session.setAttribute(
                    "email",
                    email);

                    session.setAttribute(
                    "patientName",
                    rs.getString("name"));

                    session.setAttribute(
                    "role",
                    role);

                    response.sendRedirect(
                    "patients.jsp");
                }

                else {

                    response.getWriter().println(

                    "<h2>Invalid Patient Login</h2>"

                    );
                }
            }

        }

        catch(Exception e) {

            e.printStackTrace();
        }
    }
}