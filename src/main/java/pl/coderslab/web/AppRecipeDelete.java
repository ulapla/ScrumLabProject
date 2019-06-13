package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/app.recipe/delete")
public class AppRecipeDelete extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/app.recipesDelete.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        int id = Integer.parseInt(idParam);
        int check = 0;

        if (RecipePlanDao.findAllPlanRecipeByRecipeId(id).isEmpty()) {
            RecipeDao.delete(id);
            check = 1;
            resp.sendRedirect("/app.recipes/list");
        } else {
            req.setAttribute("check", check); //przekazujemy do jsp, że recipeId jest w jakimś planie
            resp.sendRedirect("/app.recipes/list");
        }
    }
}
