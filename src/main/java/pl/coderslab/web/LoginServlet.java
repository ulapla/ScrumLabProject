package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String emailParam = req.getParameter("email");
        String passwordParam = req.getParameter("password");
        boolean check = true;

        if (AdminDao.checkPassword(emailParam, passwordParam) == null ) {
            check = false;
            req.setAttribute("check", check);
            getServletContext().getRequestDispatcher("/login.jsp").forward(req,resp);
        } else {
            Admin admin = AdminDao.checkPassword(emailParam, passwordParam); //tworzymy obiekt admin, aby łatwiej wyciągać dane
            HttpSession session = req.getSession();
            session.setAttribute("admin", admin);
            getServletContext().getRequestDispatcher("/app.dashboard.jsp").forward(req,resp);
        }
    }
}
