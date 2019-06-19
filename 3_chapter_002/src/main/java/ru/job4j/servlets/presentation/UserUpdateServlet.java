package ru.job4j.servlets.presentation;

import ru.job4j.servlets.datamodel.User;
import ru.job4j.servlets.logic.Validate;
import ru.job4j.servlets.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserUpdateServlet extends HttpServlet {
    private final Validate logic = ValidateService.getSingletonInstance();

    /**
     * - doGet URL /edit?id={userId} - открывает форму для редактирования с заполенными полями.
     *
     * - doPost - / - сохраняет пользователя.
     */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User user = ValidateService.getSingletonInstance().findById(id);
        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
/*        System.out.println("id in edit - " + Integer.valueOf(req.getParameter("id")));
        System.out.println("name in edit - " + req.getParameter("name"));
        System.out.println("login in edit - " + req.getParameter("login"));
        System.out.println("email in edit - " + req.getParameter("email"));
        System.out.println("password in edit - " + req.getParameter("password"));
        System.out.println("role in edit - " + req.getParameter("role"));*/
        this.logic.update(
                Integer.valueOf(req.getParameter("id")),
                new User(
                        req.getParameter("name"),
                        req.getParameter("login"),
                        req.getParameter("email"),
                        req.getParameter("password"),
                        req.getParameter("role")
                )
        );
        doGet(req, resp);
    }

}