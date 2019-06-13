package pl.coderslab.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

@WebServlet(urlPatterns = "/")
public class HomePage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        for(String param: parameterMap.keySet()){
            if(param.equals("logout")){
                req.getSession().removeAttribute("admin");
            }
        }
        req.getSession().removeAttribute("admin");

        getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}
