package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/app.delete/plan")
public class AppPlanDelete extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/app.planDelete.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        int id = Integer.parseInt(idParam);
        int check = 0;

        if (RecipePlanDao.findAllPlanRecipeByPlanId(id).isEmpty()) {
            PlanDao.delete(id);
            check = 1;
            resp.sendRedirect("/app.plan/list");
        } else {
            req.setAttribute("check", check);
            getServletContext().getRequestDispatcher("/app.planDelete.jsp").forward(req, resp);
        }
    }
}
