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

@WebServlet(urlPatterns = "/app.plan/edit")
public class AppEditPlan extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        req.setAttribute("admin",(Admin)session.getAttribute("admin"));
        int planId = Integer.parseInt(req.getParameter("id"));
        Plan plan = PlanDao.read(planId);
        req.setAttribute("plan",plan);

        getServletContext().getRequestDispatcher("/app.editSchedule.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int planId = Integer.parseInt(req.getParameter("planId"));
        Plan plan = PlanDao.read(planId);
        plan.setName(req.getParameter("name"));
        plan.setDescription(req.getParameter("description"));
        PlanDao.update(plan);


        resp.sendRedirect("/app.plan/list");
    }
}
