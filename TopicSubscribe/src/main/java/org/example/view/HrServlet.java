package org.example.view;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.HrService;
import org.example.model.Employee;

import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(urlPatterns = "/hrapp")
public class HrServlet extends HttpServlet {

    Logger LOG = Logger.getLogger(HrServlet.class.getName());

    @Inject
    HrService hrService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        int age = Integer.valueOf(req.getParameter("age"));
        double salary = Double.valueOf(req.getParameter("salary"));
        boolean gender = Boolean.valueOf(req.getParameter("gender"));

        Employee employee = Employee.builder().age(age).name(name).salary(salary).gender(gender).build();

        LOG.info("employee received...");

        hrService.hireEmployee(employee);

        resp.getWriter().println("<h1>Employee hiring process has started...</h1>");
    }
}
