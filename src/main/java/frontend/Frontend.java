package frontend;

/**
 * Created by alexey on 21.02.14.
 */

        import templater.PageGenerator;
        import javax.servlet.ServletException;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;
        import java.util.HashMap;
        import java.util.Map;


        import javax.servlet.http.HttpSession;
        import java.text.DateFormat;
        import java.text.SimpleDateFormat;
        import java.util.Date;
        import java.util.concurrent.atomic.AtomicLong;


public class Frontend extends HttpServlet {

    private String login = "";
    private String password = "";
    private AtomicLong userIdGenerator = new AtomicLong();

    private static final DateFormat formatter = new SimpleDateFormat("HH.mm.ss");
    public static String getTime() {


        return formatter.format(new Date());
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        login = request.getParameter("login");
        password = request.getParameter("password");
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        Map<String, Object> pageVariables = new HashMap<>();
        if(login.equals("test") && password.equals(("test")))
        {
            HttpSession session = request.getSession();
            Long userId = (Long) session.getAttribute("userId");
            if (userId == null) {
                userId = userIdGenerator.getAndIncrement();
                session.setAttribute("userId", userId);
            }
            pageVariables.put("refreshPeriod", "1000");
            pageVariables.put("serverTime", getTime());
            pageVariables.put("userId", userId);
        }
        response.getWriter().println(PageGenerator.getPage("userId.tml", pageVariables));
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        login = request.getParameter("login");
        password = request.getParameter("password");
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        Map<String, Object> pageVariables = new HashMap<>();
        if(login.equals("test") && password.equals(("test")))
        {
            HttpSession session = request.getSession();
            Long userId = (Long) session.getAttribute("userId");
            if (userId == null) {
                userId = userIdGenerator.getAndIncrement();
                session.setAttribute("userId", userId);
            }
            pageVariables.put("refreshPeriod", "1000");
            pageVariables.put("serverTime", getTime());
            pageVariables.put("userId", userId);
            response.getWriter().println(PageGenerator.getPage("userId.tml", pageVariables));
        }
    }
}
