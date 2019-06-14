package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/recipes/search")
public class RecipesSearch extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/recipesSearch.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nameParam = req.getParameter("name");
        List <Recipe> listRecipe = RecipeDao.findAll();
        List<Recipe> resultList = new ArrayList<>();

        for (Recipe recipe : listRecipe) {
            if ((recipe.getName().toLowerCase()).contains(nameParam.toLowerCase())){
                resultList.add(recipe);
            }
        }
        req.setAttribute("list", resultList);
        getServletContext().getRequestDispatcher("/recipesSearch.jsp").forward(req, resp);
    }
}
