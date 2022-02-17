package servlet;

import entity.Product;
import service.ProductService;
import util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import static java.sql.Date.valueOf;

public class EditProductServlet extends HttpServlet {
    ProductService productService;
    PageGenerator pageGenerator = PageGenerator.instance();

    public EditProductServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.getProductById(id);

        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("product", product);
        String products = pageGenerator.getPage("edit_product.html", pageVariables);

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(products);
        //response.sendRedirect("/products/a");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String productName = request.getParameter("productName");
        int price = Integer.parseInt(request.getParameter("price"));
        String date = request.getParameter("creationDate");
        Date creationDate = valueOf(date);
        Product product = new Product(id, productName, price, creationDate);
        productService.editProductById(product);
        response.sendRedirect("/products/edit");
    }
}
