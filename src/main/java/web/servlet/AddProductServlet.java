package web.servlet;

import entity.Product;
import service.ProductService;
import web.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import static java.sql.Date.*;

public class AddProductServlet extends HttpServlet {
    private  final ProductService productService;
    PageGenerator pageGenerator = PageGenerator.instance();

    public AddProductServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        String products = pageGenerator.getPage("add_product.html", pageVariables);

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(products);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String productName = request.getParameter("productName");
        int price = Integer.parseInt(request.getParameter("price"));
        String date = request.getParameter("creationDate");
        Date creationDate = valueOf(date);
        Product product = new Product(productName, price, creationDate);
        productService.saveProduct(product);

        response.setContentType("text/html;charset=utf-8");
        response.sendRedirect("/products/add");
    }
}
