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
import java.util.List;

@WebServlet(urlPatterns = "/app.superAdminUsers")
public class AppSuperAdminUsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        req.setAttribute("admin",(Admin)session.getAttribute("admin"));

        List<Admin> admins = AdminDao.findAll();
        req.setAttribute("admins",admins);


        getServletContext().getRequestDispatcher("/app.superAdminUsers.jsp").forward(req,resp);
    }
}
