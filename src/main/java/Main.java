/**
 * Created by alexey on 21.02.14.
 */
import frontend.Frontend;
import org.eclipse.jetty.rewrite.handler.RedirectRegexRule;
import org.eclipse.jetty.rewrite.handler.RewriteHandler;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    public static void main(String[] args) throws Exception {
        Frontend frontend = new Frontend();

        Server server = new Server(8081);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        //context.addServlet(new ServletHolder(frontend), "/index");
        context.addServlet(new ServletHolder(frontend), "/userId");

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setResourceBase("static");

        //RewriteHandler rewriteHandler = new RewriteHandler();
        //rewriteHandler.setRewriteRequestURI(true);
        //rewriteHandler.setRewritePathInfo(true);
        //rewriteHandler.setOriginalPathAttribute("requestedPath");
        //RedirectRegexRule rule = new RedirectRegexRule();
        //rule.setRegex("/");
        //rule.setReplacement("/index");
        //rewriteHandler.addRule(rule);

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});
        server.setHandler(handlers);

        server.start();
        server.join();
        }
    }
