package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(urlPatterns = "/app.plan/add")
public class AppPlanAdd extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/app.planadd.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Plan plan = new Plan();

        plan.setName(req.getParameter("planName"));
        plan.setDescription(req.getParameter("planDescription"));

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        String formattedDate = localDateTime.format(dateTimeFormatter);
        plan.setCreated(formattedDate);

        HttpSession session = req.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        plan.setAdminId(admin.getId());
        PlanDao.create(plan);

        resp.sendRedirect("/app.plan/list");


    }
}
