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
        Admin admin = (Admin)session.getAttribute("admin");
        req.setAttribute("admin",admin);

        if(admin.getSuperadmin() != 1){
            getServletContext().getRequestDispatcher("/app.dashboard").forward(req,resp);resp.sendRedirect("/app.dashboard");
        }

        List<Admin> admins = AdminDao.findAll();
        req.setAttribute("admins",admins);

        if(req.getParameter("id") != null){
            Admin adminToChange = AdminDao.read(Integer.parseInt(req.getParameter("id")));
            if(adminToChange.getEnable()==1){
                adminToChange.setEnable(0);
            }
            else{
                adminToChange.setEnable(1);
            }

            AdminDao.update(adminToChange);
        }



        getServletContext().getRequestDispatcher("/app.superAdminUsers.jsp").forward(req,resp);
    }
}
