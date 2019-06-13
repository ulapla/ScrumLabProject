package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet (urlPatterns = "/app.recipe/edit")
public class AppEditRecipe extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.getAttribute("admin");

        int recipeId = Integer.parseInt(req.getParameter("id"));
        Recipe recipe = RecipeDao.read(recipeId);
        req.setAttribute("recipe", recipe);

        getServletContext().getRequestDispatcher("/app.editRecipe.jsp").forward(req, resp);
    }
}
