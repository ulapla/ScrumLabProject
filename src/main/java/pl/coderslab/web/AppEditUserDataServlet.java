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

@WebServlet (urlPatterns = "/app.editUserData")
public class AppEditUserDataServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        getServletContext().getRequestDispatcher("/app.editUserData.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Admin admin = (Admin)session.getAttribute("admin");

        String name = req.getParameter("name");
        String lastName = req.getParameter("lastName");

        admin.setFirstName(name);
        admin.setLastName(lastName);
        AdminDao.update(admin);

        resp.sendRedirect("/app.dashboard");
    }
}
