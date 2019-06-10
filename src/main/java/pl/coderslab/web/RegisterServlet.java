package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameParam = req.getParameter("name");
        String surnameParam = req.getParameter("surname");
        String emailParam = req.getParameter("email");

        String[] checkEqualsPassword = req.getParameterValues("password");
        if (checkEqualsPassword[0].equals(checkEqualsPassword[1])) { //sprawdzamy czy hasła są takie same
            Admin newAdmin = new Admin();
            newAdmin.setFirstName(nameParam);
            newAdmin.setLastName(surnameParam);
            newAdmin.setEmail(emailParam);
            newAdmin.setPassword(checkEqualsPassword[0]);

            AdminDao adminDao = new AdminDao();
            adminDao.create(newAdmin); //zapisujemy do bazy admina, który się zarejestrował
            resp.sendRedirect("/login"); //kierujemy na login

        } else {
            req.setAttribute("msg", "1");//dowolna wartość obiektu, aby nie był null, na registration jsp jest if do tego
            getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);
        }
    }
}
