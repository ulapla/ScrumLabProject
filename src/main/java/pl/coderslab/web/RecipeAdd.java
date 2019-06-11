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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(urlPatterns = "/app.recipe/add")
public class RecipeAdd extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/RecipeAdd.jsp").forward(req,resp);
    }

    //Nie ma w klasie Recipe atrybutu Preparation
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Recipe recipe = new Recipe();

        //Wczytywanie danych z formularza
        recipe.setName(req.getParameter("name"));
        recipe.setDescription(req.getParameter("description"));
        recipe.setPreparationTime(Integer.parseInt(req.getParameter("preparationTime")));
        recipe.setIngredients(req.getParameter("ingredients"));

        //Wczytywanie daty i czasu utworzenia
        LocalDateTime localDateTime =  LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        String formattedDate = localDateTime.format(formatter);
        recipe.setCreated(formattedDate);

        //Wczytywanie admina z sesji
        HttpSession session = req.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        recipe.setAdminId(admin.getId());

        RecipeDao.create(recipe);
    }
}
