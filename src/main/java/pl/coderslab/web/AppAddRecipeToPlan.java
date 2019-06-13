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
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/app.recipe/plan/add")
public class AppAddRecipeToPlan extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Admin admin = (Admin) session.getAttribute("admin"); //zczytany admin z sesji

        List<Plan> listPlan = PlanDao.findAllByAdminID(admin.getId()); //zczytujemy wszystkie plany danego admin
        req.setAttribute("listPlan", listPlan);
        session.setAttribute("listPlan", listPlan); //przekazujemy obiekt do sesji, żeby potem zczytać jego ID

        List<Recipe> listRecipe = RecipeDao.findAllByAdminId(admin.getId()); //zczytujemy wszystkie przepisy danego admina
        req.setAttribute("listRecipe", listRecipe);
        session.setAttribute("listRecipe", listRecipe);

        List<DayName> listDay = DayNameDao.findAll(); //zczytujemy wszystkie dni
        req.setAttribute("listDay", listDay);
        session.setAttribute("listDay", listDay);

        getServletContext().getRequestDispatcher("/app.addRecipeToPlan.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (req.getParameter("recipie").equals("") ||
        req.getParameter("name").equals("") ||
        req.getParameter("number").equals("") ||
        req.getParameter("day").equals("") ||
        req.getParameter("choosePlan").equals("")) {

            doGet(req, resp);

        } else {
            String recipeParam = req.getParameter("recipie");
            String nameRecipeParam = req.getParameter("name");
            String numberRecipeParam = req.getParameter("number");
            String dayParam = req.getParameter("day");
            String choosePlanParam = req.getParameter("choosePlan");

            int displayOrder = Integer.parseInt(numberRecipeParam);

            int idPlan = 0;
            List<Plan> listPlan = (ArrayList<Plan>) session.getAttribute("listPlan");

            for (Plan plan : listPlan) {
                if ((plan.getName()).equals(choosePlanParam)) {
                    idPlan = plan.getId();
                    System.out.println(idPlan);
                }
            }

            int idRecipe = 0;
            List<Recipe> listRecipe = (ArrayList<Recipe>) session.getAttribute("listRecipe");

            for (Recipe recipe : listRecipe) {
                if (recipe.getName().equals(recipeParam)) {
                    idRecipe = recipe.getId();
                }
            }

            int idDay = 0;
            List<DayName> listDay = (ArrayList<DayName>) session.getAttribute("listDay");

            for (DayName dayName : listDay) {
                if (dayName.getName().equals(dayParam)) {
                    idDay = dayName.getId();
                }
            }

            PlanDao.AddRecipeToPlan(idRecipe, nameRecipeParam, displayOrder, idDay, idPlan);

            resp.sendRedirect("/app.recipe/plan/add");
        }

    }
}
