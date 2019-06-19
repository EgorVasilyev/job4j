package ru.job4j.servlets.presentation;

import ru.job4j.servlets.datamodel.User;
import ru.job4j.servlets.logic.Validate;
import ru.job4j.servlets.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserCreateServlet extends HttpServlet {
    private final Validate logic = ValidateService.getSingletonInstance();
    /**
     * - doGet URL /create - Открывает форму для создания нового пользователя.
     *
     * - doPost - / - сохраняет пользователя.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        /*System.out.println("name in add - " + req.getParameter("name"));
        System.out.println("login in add - " + req.getParameter("login"));
        System.out.println("email in add - " + req.getParameter("email"));
        System.out.println("password in add - " + req.getParameter("password"));
        System.out.println("role in add - " + req.getParameter("role"));*/
        this.logic.add(
                new User(req.getParameter("name"),
                        req.getParameter("login"),
                        req.getParameter("email"),
                        req.getParameter("password"),
                        req.getParameter("role")
                )
        );
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}