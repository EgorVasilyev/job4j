package ru.job4j.servlets.presentation;

import ru.job4j.servlets.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserUpdateServlet extends HttpServlet {
    private final ValidateService logic = ValidateService.getSingletonInstance();

    /**
     * - doGet URL /edit?id={userId} - открывает форму для редактирования с заполенными полями.
     *
     * - doPost - / - сохраняет пользователя.
     */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        this.logic.update(
                Integer.valueOf(req.getParameter("id"))
                , req.getParameter("name")
                , req.getParameter("login")
                , req.getParameter("email")
        );
        doGet(req, resp);
    }

}
