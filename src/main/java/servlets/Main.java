package servlets;

import frontend.Frontend;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import Jetty.JettyServer;
/**
 * Created by alexey on 21.02.14.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8083);
        server.setHandler(new JettyServer());

        server.start();
        server.join();
    }
}
