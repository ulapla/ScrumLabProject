package pl.coderslab.web;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.DayName;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/app/recipe/plan/add")
public class AppAddRecipeToPlan extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Admin admin = (Admin) session.getAttribute("admin"); //zczytany admin z sesji

        List<Plan> listPlan = PlanDao.findAllByAdminID(admin.getId()); //zczytujemy wszystkie plany danego admin
        req.setAttribute("listPlan", listPlan);

        List <Recipe> listRecipe = RecipeDao.findAllByAdminId(admin.getId()); //zczytujemy wszystkie przepisy danego admina
        req.setAttribute("listRecipe", listRecipe);

        List<DayName> listDay = DayNameDao.findAll(); //zczytujemy wszystkie dni
        req.setAttribute("listDay",listDay);

        getServletContext().getRequestDispatcher("/app.addRecipeToPlan.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
