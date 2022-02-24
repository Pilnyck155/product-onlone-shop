package web.servlet;

import entity.User;
import freemarker.log.SLF4JLoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.SecurityService;
import service.UserService;
import web.PageGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(getClass());

    private final SecurityService securityService;

    public LoginServlet(SecurityService service) {
        this.securityService = service;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        String products = PageGenerator.getPage("login.html", pageVariables);

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(products);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        logger.info("Current email {}", email);
        logger.info("Current password {}", password);

        if (email != null && password != null) {
            boolean validateUser = securityService.validateUser(email, password);
            if (validateUser) {
                Cookie cookie = securityService.generateCookie();
                response.addCookie(cookie);
                response.sendRedirect("/products");
            } else {
                response.sendRedirect("/login");
            }
        }
    }
}
