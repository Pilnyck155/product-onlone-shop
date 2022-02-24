package web.servlet;

import entity.Product;
import service.ProductService;
import service.SecurityService;
import web.PageGenerator;
import web.ProductManager;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EditProductServlet extends HttpServlet {
    private final ProductService productService;

    public EditProductServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.getProductById(id);
        if (product == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.sendRedirect("/products");
            return;
        }

        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("product", product);
        String products = PageGenerator.getPage("edit_product.html", pageVariables);

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(products);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Product productFromRequest = ProductManager.getProductFromRequest(request);

        productService.editProductById(productFromRequest);
        response.sendRedirect("/products");
    }
}
