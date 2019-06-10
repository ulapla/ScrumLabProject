package pl.coderslab.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;


// W celu udostępnienia możliwości wykorzystywania filtra do pom.xml dociągnąłem dependency javax-servlet-api 4.0.1

/* Filter potrzebuje ustawienia mapowania, aby wiedział które odnośniki ma sprawdzać czy użytkownik jest zalogowany.
W tym celu stworzyłem w webapp/META-INF web.xml, gdzie nadaję ustawienia dla filtera. Potencjalne miejsce konfliktów.
Dokładniej - w
 */

/* W celu sprawdzenia, czy użytkownik jest zalogowany, sprawdzam w sesji czy użytkownik jest zalogowany. To oznacza, że
formularz logowania też musi zapisywać w sesji, czy użytkownik jest zalogowany.
UWAGA!!! Nazwa atrybutu, w którym jest ta informacja zapisywana musi być ta sama. Także Filter będzie potrzebował
dodatkowego sprawdzenia, czy jest dobrze zintegrowany z modułem logowania.
 */

@WebFilter
public class FilterLogin implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //Musiałem dokonać poniższej transformacji z ServletRequest request na HttpServletRequest req, aby uzyskać dostęp do sesji. Nawias po prawnej stronie to rzutowanie.
        HttpServletRequest req = (HttpServletRequest) request;

        //Tej samej transformacji dokonałem przy response aby odblokować redirect.
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();

        if (session.getAttribute("loginStatus") != null) {
            chain.doFilter(req, resp);
        } else {
            // Domyślnie przeniesienie niezalogowanego użytkownika ustawiam na formularz logowania. Do testów proponuję stworzyć dowolny servlet.
            resp.sendRedirect("/test2");
        }


    }

    @Override
    public void destroy() {}

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}
