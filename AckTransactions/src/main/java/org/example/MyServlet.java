package org.example;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/wordapp")
public class MyServlet extends HttpServlet {

    @Inject
    private WordService wordService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String word = req.getParameter("word");

        try {
            wordService.send(word);
        }
        catch (RuntimeException e) {

        }

        resp.getWriter().println("<h1>Word Received</h1>");
    }
}
