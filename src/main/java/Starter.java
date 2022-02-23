import dao.jdbc.ConnectionFactory;
import dao.jdbc.JdbcProductDao;
import dao.jdbc.JdbcUserDao;
import org.flywaydb.core.Flyway;
import service.ProductService;
import conf.PropertiesReader;
import service.SecurityService;
import service.UserService;
import web.servlet.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.Properties;

public class Starter {
    public static void main(String[] args) throws Exception {
        //configuration
        PropertiesReader propertiesReader = new PropertiesReader("src/main/resources/application.properties");
        Properties properties = propertiesReader.getProperties();
        ConnectionFactory connectionFactory = new ConnectionFactory(properties.getProperty("db.url"),
                properties.getProperty("db.username"), properties.getProperty("db.password"));

        //flyway configuration
        Flyway flyway = Flyway.configure().dataSource(properties.getProperty("db.url"),
                properties.getProperty("db.username"), properties.getProperty("db.password")).load();
        flyway.migrate();

        //dao
        JdbcProductDao productDao = new JdbcProductDao(connectionFactory);
        JdbcUserDao userDao = new JdbcUserDao(connectionFactory);

        //services
        ProductService productService = new ProductService(productDao);
        UserService userService = new UserService(userDao);
        SecurityService securityService = new SecurityService(userService);

        //servlets
        AllProductsServlet allProductsServlet = new AllProductsServlet(productService);
        AddProductServlet addProductServlet = new AddProductServlet(productService, securityService);
        DeleteProductServlet deleteProductServlet = new DeleteProductServlet(productService, securityService);
        EditProductServlet editProductServlet = new EditProductServlet(productService, securityService);
        LoginServlet loginServlet = new LoginServlet(securityService);

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);

        contextHandler.addServlet(new ServletHolder(allProductsServlet), "/products");
        contextHandler.addServlet(new ServletHolder(addProductServlet), "/products/add");
        contextHandler.addServlet(new ServletHolder(deleteProductServlet), "/products/delete");
        contextHandler.addServlet(new ServletHolder(editProductServlet), "/products/edit");
        contextHandler.addServlet(new ServletHolder(loginServlet), "/login");

        Server server = new Server(8080);
        server.setHandler(contextHandler);
        server.start();
    }
}