package com.medinet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AIServlet")

public class AIServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String symptom =
        request.getParameter("symptom");

        String result = "";

        symptom = symptom.toLowerCase();

        if(symptom.contains("fever")
                && symptom.contains("cough")) {

            result = "Possible Flu or Viral Fever";

        }

        else if(symptom.contains("headache")) {

            result = "Possible Migraine";

        }

        else {

            result = "Please consult doctor";
        }

        response.getWriter().println(

        "<h2>AI Result</h2>" +

        "<p>" + result + "</p>"

        );
    }
}
