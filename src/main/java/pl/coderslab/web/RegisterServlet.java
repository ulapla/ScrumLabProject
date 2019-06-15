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


        if (!(checkEqualsPassword[0].equals(checkEqualsPassword[1]))) { //sprawdzamy czy hasła są takie same
            req.setAttribute("checkPassword", false);
            getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);

        } else if (!(emailParam.matches("^[\\w0-9+_.-]+@[\\w0-9.-]+$"))) { //sprawdzanie czy maila ma poprawny format
            req.setAttribute("regexMail", false);
            getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);


        } else if (!((nameParam.matches("[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ].*")) && (surnameParam.matches("[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ].*")))) { //imię musi nie mieć cyfr ani znaków specjalnych
            req.setAttribute("regexName", false);
            getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);

        } else if (!(checkEqualsPassword[0].matches("[a-zA-Z\\d\\D\\W\\w]{8,}"))) { //hasło musi mieć conajmniej 8 znaków
            req.setAttribute("regexPassword", false);
            getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);

        } else {
            Admin newAdmin = new Admin();
            newAdmin.setFirstName(nameParam);
            newAdmin.setLastName(surnameParam);
            newAdmin.setEmail(emailParam);
            newAdmin.setPassword(checkEqualsPassword[0]);

            Admin admin = AdminDao.create(newAdmin); //zapisujemy do bazy admina, który się zarejestrował
            if (admin == null) {//Ula: jesli nie udało się stworzyć admina znaczy że taki email jest już w bazie
                req.setAttribute("email", true);
                //resp.sendRedirect("/register");
                getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);
            }
        }
        resp.sendRedirect("/login"); //kierujemy na login
    }
}

