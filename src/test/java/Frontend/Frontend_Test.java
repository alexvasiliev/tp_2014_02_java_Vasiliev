package Frontend;

import Database.*;
import  frontend.*;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import java.util.Random;
/**
 * Created by alexey on 15.03.14.
 */
public class Frontend_Test {



    final private static HttpServletRequest REQUEST = mock(HttpServletRequest.class);
    final private static HttpServletResponse RESPONSE = mock(HttpServletResponse.class);
    final private static HttpSession HTTPSESSION = mock(HttpSession.class);

    private static StringWriter stringWriter = new StringWriter();
    private static PrintWriter writer = new PrintWriter(stringWriter);

    final private static String MAIN_PAGE = "/index";
    final private static String USERID_PAGE = "/userId";
    final private static String POST_LOGIN = "/PersonalPage";
    final private static String POST_REGISTER = "/createUser";
    private static Frontend frontend = new Frontend();


    private void prepare(String path) throws IOException
    {
        when(RESPONSE.getWriter()).thenReturn(writer);
        when(REQUEST.getSession()).thenReturn(HTTPSESSION);
        when(REQUEST.getPathInfo()).thenReturn(path);
    }

    @Test
    public void testDoGetUserIdPageSuccess() throws ServletException, IOException
    {
        prepare(USERID_PAGE);
        frontend.doGet(REQUEST, RESPONSE);
        Assert.assertTrue(stringWriter.toString().contains("<p>userId: "));
    }

    @Test
    public void testDoGetMainPageSuccess() throws ServletException, IOException
    {
        prepare(MAIN_PAGE);
        frontend.doGet(REQUEST, RESPONSE);
        Assert.assertTrue(stringWriter.toString().contains("<a href=\"/createUser.html\">Registration</a>"));
    }

    @Test
    public void testDoGetNonExistentPageFailure() throws ServletException, IOException
    {
        prepare("/There_is_no_page");
        frontend.doGet(REQUEST, RESPONSE);
        Assert.assertTrue(stringWriter.toString().equals(""));
    }

    @Test
    public void testDoPostLoginSuccess() throws ServletException, IOException
    {
        prepare(POST_LOGIN);
        when(REQUEST.getParameter("login")).thenReturn("1");
        when(REQUEST.getParameter("password")).thenReturn("1");

        ORM orm = new ORM();
        frontend.doPost(REQUEST, RESPONSE);
        verify(RESPONSE, atLeastOnce()).sendRedirect("/userId");
    }

    @Test
    public void testDoPostLoginFailure() throws ServletException, IOException
    {
        prepare(POST_LOGIN);
        when(REQUEST.getParameter("login")).thenReturn("1");
        when(REQUEST.getParameter("password")).thenReturn("2");

        ORM orm = new ORM();
        frontend.doPost(REQUEST, RESPONSE);
        verify(RESPONSE, atLeastOnce()).sendRedirect("/");
    }

    @Test
    public void testDoPostRegisterSuccess() throws ServletException, IOException
    {
        Random rnd = new Random();
        String name = "Random_user" + rnd.nextLong();

        prepare(POST_REGISTER);
        when(REQUEST.getParameter("login")).thenReturn(name);
        when(REQUEST.getParameter("password")).thenReturn(name);

        ORM orm = new ORM();
        frontend.doPost(REQUEST, RESPONSE);
    }

    @Test
    public void testDoPostRegisterFailure() throws ServletException, IOException
    {
        prepare(POST_REGISTER);
        when(REQUEST.getParameter("login")).thenReturn("1");
        when(REQUEST.getParameter("password")).thenReturn("");

        ORM orm = new ORM();
        frontend.doPost(REQUEST, RESPONSE);
        verify(RESPONSE, atLeastOnce()).sendRedirect("/Error.html");
    }
}
