package org.example.view;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.clinicalapp.CheckEligibilityService;
import org.example.clinicalapp.model.Patient;

import java.io.IOException;

@WebServlet(urlPatterns = "/jms-test")
public class ViewServlet extends HttpServlet {

    @Inject
    private CheckEligibilityService eligibilityService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer age = Integer.valueOf(req.getParameter("age"));
        Double salary = Double.valueOf(req.getParameter("salary"));
        String name = req.getParameter("name");

        Patient patient = Patient.builder().age(age).salary(salary).name(name).build();

        eligibilityService.checkPatient(patient);

        resp.getWriter().println("<h1>Your request was queued...</h1>");
        resp.getWriter().close();
    }
}
