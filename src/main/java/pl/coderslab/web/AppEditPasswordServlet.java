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

@WebServlet (urlPatterns = "/app.editPassword")
public class AppEditPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        req.setAttribute("admin",(Admin)session.getAttribute("admin"));

        getServletContext().getRequestDispatcher("/app.editPassword.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameter("password"));
        System.out.println(req.getParameter("repeatPassword"));
        boolean checkPassword;
        if (req.getParameter("password").equals(req.getParameter("repeatPassword"))) {
            HttpSession session = req.getSession();
            Admin admin = (Admin) session.getAttribute("admin");
            admin.setPassword(req.getParameter("password"));
            AdminDao.update(admin);
            checkPassword = true;
            req.setAttribute("checkPassword", checkPassword);
            getServletContext().getRequestDispatcher("/app.editPassword.jsp").forward(req,resp);
        } else {
            checkPassword = false;
            req.setAttribute("checkPassword", checkPassword);
            getServletContext().getRequestDispatcher("/app.editPassword.jsp").forward(req,resp);
        }
    }
}
