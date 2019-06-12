package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet (urlPatterns = "/app.dashboard")
public class AppDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Admin admin = (Admin)session.getAttribute("admin");
        req.setAttribute("admin",admin);
        int recipeCounter = RecipeDao.usersRecipesCounter(admin.getId());
        req.setAttribute("recipes",recipeCounter);
        int planCounter = PlanDao.userPlansQuantityCounter(admin.getId());
        req.setAttribute("plans",planCounter);
        List<String[]> planDetalis = PlanDao.findLastPlan(admin);
        /* do testów
        List<String[]> planDetalis = new ArrayList<>();
        String[] s1= {"poniedziałek","śniadanie","owsianka","z mailinami"};
        String[] s2= {"poniedziałek","kolacja","omlet","z jabłakami"};
        String[] s3= {"wtorek","sniadanie","omlet","z jabłakami"};
        planDetalis.add(s1);
        planDetalis.add(s2);
        planDetalis.add(s3);
        */


        String planName;
        if(PlanDao.findUserPlans(admin).size() == 0 ) {
            planName = "nie masz jeszcze planu";
        }else {
            planName = PlanDao.findUserPlans(admin).get(0).getName();
            req.setAttribute("planDetails", planDetalis);
        }
        req.setAttribute("planName", planName);

        getServletContext().getRequestDispatcher("/app.dashboard.jsp").forward(req,resp);

    }
}
