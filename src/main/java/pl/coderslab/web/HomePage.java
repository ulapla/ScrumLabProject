package pl.coderslab.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

@WebServlet(urlPatterns = "/home")
public class HomePage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getParameter("logout").equals((String)"1")) {
            System.out.println("działa");
            System.out.println(req.getParameter("logout"));
            req.getSession().removeAttribute("admin");
        }




        getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}
