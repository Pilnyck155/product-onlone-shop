import dao.jdbc.ConnectionFactory;
import dao.jdbc.JdbcProductDao;
import service.ProductService;
import conf.PropertiesReader;
import web.servlet.AddProductServlet;
import web.servlet.AllProductsServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import web.servlet.DeleteProductServlet;
import web.servlet.EditProductServlet;

import java.util.Properties;

public class Starter {
    public static void main(String[] args) throws Exception {
        //configuration
        PropertiesReader propertiesReader = new PropertiesReader("src/main/resources/application.properties");
        Properties properties = propertiesReader.getProperties();
        ConnectionFactory connectionFactory = new ConnectionFactory(properties.getProperty("db.url"),
                properties.getProperty("db.username"), properties.getProperty("db.password"));

        //dao
        JdbcProductDao productDao = new JdbcProductDao(connectionFactory);

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