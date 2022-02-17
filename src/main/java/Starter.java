import dao.ProductDao;
import service.ProductService;
import servlet.AddProductServlet;
import servlet.AllProductsServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlet.DeleteProductServlet;
import servlet.EditProductServlet;

public class Starter {
    public static void main(String[] args) throws Exception {
        //dao
        ProductDao productDao = new ProductDao();

        //services
        ProductService productService = new ProductService(productDao);

        //servlets
        AllProductsServlet allProductsServlet = new AllProductsServlet(productService);
        AddProductServlet addProductServlet = new AddProductServlet(productService);
        DeleteProductServlet deleteProductServlet = new DeleteProductServlet(productService);
        EditProductServlet editProductServlet = new EditProductServlet(productService);

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);

        contextHandler.addServlet(new ServletHolder(allProductsServlet), "/products");
        contextHandler.addServlet(new ServletHolder(addProductServlet), "/products/add");
        contextHandler.addServlet(new ServletHolder(deleteProductServlet), "/products/delete");
        contextHandler.addServlet(new ServletHolder(editProductServlet), "/products/edit");

        Server server = new Server(8080);
        server.setHandler(contextHandler);

        server.start();
    }
}