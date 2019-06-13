package pl.coderslab.web;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.DayName;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;
import pl.coderslab.model.RecipePlan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = "/app.plan/details")
public class AppScheduleDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int planId = Integer.parseInt(req.getParameter("id"));
        Plan plan = PlanDao.read(planId);
        req.setAttribute("plan", plan);

        //Ściągnięcie tylko tych dni, które są zaplanowane przez admina
        List<DayName> listDayName = DayNameDao.readByPlanId(planId);
        req.setAttribute("listDayName", listDayName);

        //Ściągnięcie wszelkich rekordów recipe_plan powiązanych z danym planem
        List<RecipePlan> listRecipePlan = RecipePlanDao.readByPlanId(planId);
        req.setAttribute("listRecipePlan", listRecipePlan);

        /*Ściągnięcie wszelkich receptur wykorzystywanych w danym planie. Wykorzystuję hashset, ponieważ receptury mogą
        się powtarzać, co nie jest potrzebne tutaj.
         */
        Iterator<RecipePlan> iteratorRecipePlan = listRecipePlan.iterator();
        Set<Recipe> hashSetRecipe = new HashSet<>();
        while (iteratorRecipePlan.hasNext()) {
            RecipePlan tempRecipePlan = iteratorRecipePlan.next();
            hashSetRecipe.add(RecipeDao.read(tempRecipePlan.getRecipeId()));
        }
        req.setAttribute("hashSetRecipe", hashSetRecipe);

        /*Ten fragment pozwala na zapisanie w sesji skąd przechodzi się do szczegółów przepisu, co jest potem wykorzystywane
        przy przycisku "Powrót"
         */
        HttpSession session = req.getSession();
        String originURL = req.getRequestURI() + "?id=" + planId;
        session.setAttribute("recipeDetailBackButton", originURL);


        getServletContext().getRequestDispatcher("/app.scheduleDetails.jsp").forward(req,resp);
    }
}
