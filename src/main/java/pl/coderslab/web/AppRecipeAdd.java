package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/*
Zabezpieczyłem się przed wystąpieniem potencjalnego wyjątku wynikającego z pustego pola w formularzu poprzez dodanie if.
 */
@WebServlet(urlPatterns = "/app.recipe/add")
public class AppRecipeAdd extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/app.RecipeAdd.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Recipe recipe = new Recipe();

        /*Sprawdzam pola formularza, czy wszystkie zostały wypełnione. Odsyłam korzystając z doGet, które inicjuje wszystko od początku.
         */
        if      (req.getParameter("name").equals("") ||
                req.getParameter("description").equals("") ||
                req.getParameter("preparationTime").equals("") ||
                req.getParameter("preparation").equals("") ||
                req.getParameter("ingredients").equals("") ) {

            doGet(req, resp);

        } else {
            //Wczytywanie danych z formularza
            recipe.setName(req.getParameter("name"));
            recipe.setDescription(req.getParameter("description"));
            recipe.setPreparationTime(Integer.parseInt(req.getParameter("preparationTime")));
            recipe.setPreparation(req.getParameter("preparation"));
            recipe.setIngredients(req.getParameter("ingredients"));

            //Wczytywanie daty i czasu utworzenia
            LocalDateTime localDateTime =  LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
            String formattedDate = localDateTime.format(formatter);
            recipe.setCreated(formattedDate);
            recipe.setUpdated(formattedDate);

            //Wczytywanie admina z sesji
            HttpSession session = req.getSession();
            Admin admin = (Admin) session.getAttribute("admin");
            recipe.setAdminId(admin.getId());

            RecipeDao.create(recipe);

            resp.sendRedirect("/app.recipes/list");
        }


    }
}
