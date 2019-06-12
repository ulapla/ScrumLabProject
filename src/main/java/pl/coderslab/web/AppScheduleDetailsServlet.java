package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.DayName;
import pl.coderslab.model.Plan;
import pl.coderslab.utils.AppScheduleDetailsUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/app.plan/details")
public class AppScheduleDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int planId = Integer.parseInt(req.getParameter("id"));
        Plan plan = PlanDao.read(planId);
        req.setAttribute("plan", plan);

        List<DayName> list = AppScheduleDetailsUtils.getUserDays(planId);
        req.setAttribute("listDayName", list);

        getServletContext().getRequestDispatcher("/app.scheduleDetails.jsp").forward(req,resp);
    }
}
