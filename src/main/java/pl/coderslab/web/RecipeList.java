package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
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

@WebServlet(urlPatterns = "/app.recipe/list")
public class RecipeList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Admin admin = (Admin) session.getAttribute("admin"); //zczytany admin z sesji
        int id = admin.getId(); //zczytane admin id
        List<Recipe> listRecipe = RecipeDao.findAllByAdminId(id); //metoda zczytująca przepisy po id admina

        req.setAttribute("listRecipe", listRecipe); //przekazane do jsp, w jsp pętla for each

        getServletContext().getRequestDispatcher("/recipe_list.jsp").forward(req, resp);
    }
}
