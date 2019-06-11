package pl.coderslab.web;

import com.sun.tools.corba.se.idl.StringGen;
import pl.coderslab.dao.AdminDao;

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
            HttpSession session = req.getSession();
            req.setAttribute("userEmail", emailParam);
            req.setAttribute("userPassword", passwordParam);
            getServletContext().getRequestDispatcher("/home.jsp").forward(req,resp);
        }
    }
}
