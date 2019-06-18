package pl.coderslab.web;

import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.regex.Pattern;

//Do sprawdzenia: domyślnie ustawiam adres przekierowania na "/login". W razie problemów sprawdzić poprawność przekierowania.

// W celu udostępnienia możliwości wykorzystywania filtra do pom.xml dociągnąłem dependency javax-servlet-api 4.0.1

/* W celu sprawdzenia, czy użytkownik jest zalogowany, sprawdzam w sesji czy zalogował się z wykorzystaniem atrybutu admin.
W razie problemów należy sprawdzić, czy nazwy atrybutów w sesji się pokrywają.
 */

/*Działanie filtra: Jeżeli chcemy użyć filtru logowania na wybranej przez nas stronie, Servlet strony musi zaczynać się od "/app.".
Wtedy filtr będzie wyłapywał taką stronę i sprawdzał, czy użytkownik jest zalogowany. W przeciwnym razie przekieruje na wybraną
przez nas stronę.
 */

@WebFilter(urlPatterns = "/*")
public class FilterLogin extends HttpFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //Musiałem dokonać poniższej transformacji z ServletRequest request na HttpServletRequest req, aby uzyskać dostęp do sesji. Nawias po prawnej stronie to rzutowanie.
        HttpServletRequest req = (HttpServletRequest) request;

        //Tej samej transformacji dokonałem przy response aby odblokować redirect.
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();

        //Sprawdzenie czy servlet spełnia wymóg filtrowania
        if (Pattern.matches("^\\/app\\..+$", req.getRequestURI()) ) {

            //Sprawdzenie, czy użytkownik jest zalogowany
            Admin admin = (Admin)session.getAttribute("admin");
            if (admin!= null && admin.getEnable() == 1) {
                chain.doFilter(req, resp);
            } else {
                // Tutaj ustawiany jest adres przekierowania, którym ma być strona z logowaniem. Na potrzeby testów ustawić adres przekierowania na testowy servlet
                resp.sendRedirect("/login");
            }

        } else {
            chain.doFilter(req, resp);
        }


    }
}
