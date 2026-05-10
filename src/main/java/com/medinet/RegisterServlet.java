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

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.getWriter().println("RegisterServlet Working");
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String ageStr = request.getParameter("age");
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");
        String bloodgroup = request.getParameter("bloodgroup");
        String password = request.getParameter("password");

        try {

            Connection con = DBconnection.getConnection();

            // CHECK EMAIL EXISTS
            String checkEmail = "SELECT 1 FROM users WHERE email=?";
            PreparedStatement ps1 = con.prepareStatement(checkEmail);
            ps1.setString(1, email);
            ResultSet rs1 = ps1.executeQuery();

            if (rs1.next()) {
                response.sendRedirect("register.html?error=email");
                return;
            }

            // CHECK PHONE EXISTS
            String checkPhone = "SELECT 1 FROM users WHERE phone=?";
            PreparedStatement ps2 = con.prepareStatement(checkPhone);
            ps2.setString(1, phone);
            ResultSet rs2 = ps2.executeQuery();

            if (rs2.next()) {
                response.sendRedirect("register.html?error=phone");
                return;
            }

            int age = Integer.parseInt(ageStr);

            // INSERT DATA
            String query =
            "INSERT INTO users(name,email,age,gender,phone,bloodgroup,password) VALUES(?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setInt(3, age);
            ps.setString(4, gender);
            ps.setString(5, phone);
            ps.setString(6, bloodgroup);
            ps.setString(7, password);

            ps.executeUpdate();

            ps.close();
            ps1.close();
            ps2.close();
            con.close();

            response.sendRedirect("login.html");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
