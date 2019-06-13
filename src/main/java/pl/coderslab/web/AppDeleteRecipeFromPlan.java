package pl.coderslab.web;

import pl.coderslab.dao.RecipePlanDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/app.plan/details/deleteRecipe")
public class AppDeleteRecipeFromPlan extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("recipePlanId", req.getParameter("id"));

        getServletContext().getRequestDispatcher("/app.deleteRecipeFromPlan.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RecipePlanDao.delete(Integer.parseInt(req.getParameter("recipePlanId")));

        //Służy do redirect, recykluję metodę z przycisku powrót w szczegółach przepisu, która działa również tutaj.
        HttpSession session = req.getSession();
        resp.sendRedirect((String) session.getAttribute("recipeDetailBackButton"));
    }
}
