package com.medinet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/NotificationServlet")

public class NotificationServlet
extends HttpServlet {

@Override

protected void doGet(
HttpServletRequest request,
HttpServletResponse response)

throws ServletException, IOException {

response.sendRedirect(
"notifications.jsp");
}
}
