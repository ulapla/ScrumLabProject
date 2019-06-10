package pl.coderslab.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class FilterLogin implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    }

    @Override
    public void destroy() {}

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
}
