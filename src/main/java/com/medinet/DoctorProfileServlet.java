import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class DoctorProfileServlet extends HttpServlet {

    @Override
    protected void doPost(
    HttpServletRequest request,
    HttpServletResponse response)

    throws ServletException, IOException {

        String email =
        (String) request.getSession()
        .getAttribute("email");

        String name =
        request.getParameter("name");

        String phone =
        request.getParameter("phone");

        String specialization =
        request.getParameter("specialization");

        String availability =
        request.getParameter("availability");

        String password =
        request.getParameter("password");

        Connection con = null;
        PreparedStatement ps = null;

        try{

            Class.forName(
            "com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/medinet_hms",
            "root",
            "Priya@123");

            // IF PASSWORD FIELD EMPTY

            if(password == null ||
               password.trim().equals("")){

                ps = con.prepareStatement(

                "update doctor_profile set doctor_name=?, phone=?, specialization=?, availability=? where email=?"

                );

                ps.setString(1,name);
                ps.setString(2,phone);
                ps.setString(3,specialization);
                ps.setString(4,availability);
                ps.setString(5,email);

            }

            // IF PASSWORD ENTERED

            else{

                ps = con.prepareStatement(

                "update doctor_profile set doctor_name=?, phone=?, specialization=?, availability=?, password=? where email=?"

                );

                ps.setString(1,name);
                ps.setString(2,phone);
                ps.setString(3,specialization);
                ps.setString(4,availability);
                ps.setString(5,password);
                ps.setString(6,email);
            }

            int i = ps.executeUpdate();

            if(i > 0){

                response.sendRedirect(
                "doctor-profile.jsp?success=1");

            }
            else{

                response.sendRedirect(
                "doctor-profile.jsp?error=1");
            }

        }
        catch(Exception e){

            PrintWriter out =
            response.getWriter();

            out.println(e);
        }

        finally{

            try{

                if(ps != null)
                ps.close();

                if(con != null)
                con.close();

            }catch(Exception e){}
        }
    }
}