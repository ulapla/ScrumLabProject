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
        boolean checkPassword;
//        if (!(emailParam.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$"))) { //trzeba dopracować
//            req.setAttribute("msg1", "1");
//            getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);
//        }

        String[] checkEqualsPassword = req.getParameterValues("password");
        if (checkEqualsPassword[0].equals(checkEqualsPassword[1])) { //sprawdzamy czy hasła są takie same
            Admin newAdmin = new Admin();
            newAdmin.setFirstName(nameParam);
            newAdmin.setLastName(surnameParam);
            newAdmin.setEmail(emailParam);
            newAdmin.setPassword(checkEqualsPassword[0]);

            Admin admin = AdminDao.create(newAdmin); //zapisujemy do bazy admina, który się zarejestrował
            if (admin == null){//Ula: jesli nie udało się stworzyć admina znaczy że taki email jest już w bazie
                req.setAttribute("email",true);
                //resp.sendRedirect("/register");
                getServletContext().getRequestDispatcher("/registration.jsp").forward(req,resp);
            }
            resp.sendRedirect("/login"); //kierujemy na login

        } else {
            checkPassword = false;
            req.setAttribute("checkPassword", checkPassword);
            getServletContext().getRequestDispatcher("/registration.jsp").forward(req,resp);

        }
    }
}
