package web.servlet;

import service.ProductService;
import service.SecurityService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.sql.Date.valueOf;

public class DeleteProductServlet extends HttpServlet {
    private final ProductService productService;
    private final SecurityService securityService;

    public DeleteProductServlet(ProductService productService, SecurityService securityService) {
        this.productService = productService;
        this.securityService = securityService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies = request.getCookies();

        boolean isAuthorized = securityService.checkCookies(cookies);
        if (isAuthorized) {
            response.sendRedirect("/login");
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            productService.deleteById(id);
            response.sendRedirect("/products");
        }
    }
}
