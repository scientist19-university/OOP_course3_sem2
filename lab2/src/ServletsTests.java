import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;


public class ServletsTests {
    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private RequestDispatcher requestDispatcher;

    @Before
    public void setUp() throws Exception {
    	
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void doGetNoSuchLogin() throws Exception {
    	
        when(request.getParameter("login")).thenReturn("abcd");
        when(request.getParameter("password")).thenReturn("jerichorej");
        
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        
        when(request.getRequestDispatcher("index.html")).thenReturn(requestDispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        new LoginServlet().doGet(request, response);
        assertTrue(stringWriter.toString().contains("no such login registered"));
    }
    
    @Test
    public void doGetIncorrectPassword() throws Exception {
    	
        when(request.getParameter("login")).thenReturn("jericho");
        when(request.getParameter("password")).thenReturn("abcd");
        
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        
        when(request.getRequestDispatcher("index.html")).thenReturn(requestDispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        new LoginServlet().doGet(request, response);
        assertTrue(stringWriter.toString().contains("incorrect password"));
    }
    
    @Test
    public void doGetAdminIncorrectPassword() throws Exception {
    	
        when(request.getParameter("login")).thenReturn("admin1");
        when(request.getParameter("password")).thenReturn("incorrectPassword");
        when(request.getParameter("adminIndex")).thenReturn("incorrectIndex");
        
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        
        when(request.getRequestDispatcher("adminLogin.jsp")).thenReturn(requestDispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        new AdminLoginServlet().doGet(request, response);
        assertTrue(stringWriter.toString().contains("incorrect password or admin index"));
    }
    
    @Test
    public void doGetAdminNotRegistered() throws Exception {
    	
        when(request.getParameter("login")).thenReturn("incorrectAdmin");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("adminIndex")).thenReturn("index");
        
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        
        when(request.getRequestDispatcher("adminLogin.jsp")).thenReturn(requestDispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        new AdminLoginServlet().doGet(request, response);
        assertTrue(stringWriter.toString().contains("no such admin name"));
    }
    
    @Test
    public void doGetRegistrationFailed() throws Exception {
    	
        when(request.getParameter("loginReg")).thenReturn("jericho");
        when(request.getParameter("passwordReg")).thenReturn("password");
        when(request.getParameter("firstName")).thenReturn("maiia");
        when(request.getParameter("lastName")).thenReturn("chudinova");
        when(request.getParameter("birthDate")).thenReturn("07-07-2000");
        when(request.getParameter("sex")).thenReturn("female");
        
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        
        when(request.getRequestDispatcher("registration.jsp")).thenReturn(requestDispatcher);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        new RegisterServlet().doGet(request, response);
        assertTrue(stringWriter.toString().contains("User with this login is already registered."));
    }
}