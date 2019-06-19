/*
package ru.job4j.servlets.presentation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.servlets.datamodel.User;
import ru.job4j.servlets.logic.Validate;
import ru.job4j.servlets.logic.ValidateService;
import ru.job4j.servlets.logic.ValidateStub;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UsersServletTest {
    private Validate validate = new ValidateStub();
    private HttpServletRequest req = mock(HttpServletRequest.class);
    private HttpServletResponse resp = mock(HttpServletResponse.class);
    {
        PowerMockito.mockStatic(ValidateService.class);
        Mockito.when(ValidateService.getSingletonInstance()).thenReturn(validate);
    }
    @Before
    public void createUser() throws ServletException, IOException {
        UserCreateServlet userCreateServlet = new UserCreateServlet();

        when(req.getParameter("name")).thenReturn("testName");
        when(req.getParameter("login")).thenReturn("testLogin");
        when(req.getParameter("email")).thenReturn("testEmail");
        when(req.getParameter("password")).thenReturn("testPass");
        when(req.getParameter("role")).thenReturn("user");

        userCreateServlet.doPost(req, resp);
    }
    @Test
    public void deleteTest() throws ServletException, IOException {
        UsersServlet usersServlet = new UsersServlet();
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(req.getParameter("id")).thenReturn("1");
        when(req.getParameter("action")).thenReturn("delete");
        when(req.getRequestDispatcher("/WEB-INF/views/users.jsp")).thenReturn(dispatcher);

        ArrayList<User> list = new ArrayList<>(validate.findAll().values());
        assertThat(list.size(), is(1));

        usersServlet.doPost(req, resp);

        list = new ArrayList<>(validate.findAll().values());
        assertThat(list.size(), is(0));
    }
}
*/
