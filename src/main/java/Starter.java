import entity.servlet.AllProductsServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Starter {
    public static void main(String[] args) throws Exception {
        AllProductsServlet allProductsServlet = new AllProductsServlet();
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);

        contextHandler.addServlet(new ServletHolder(allProductsServlet), "/products");

        Server server = new Server(8080);
        server.setHandler(contextHandler);

        server.start();
    }
}
