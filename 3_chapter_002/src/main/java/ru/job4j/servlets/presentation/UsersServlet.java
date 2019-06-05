package ru.job4j.servlets.presentation;

import ru.job4j.servlets.logic.Validate;
import ru.job4j.servlets.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsersServlet extends HttpServlet {
    private final Validate logic = ValidateService.getSingletonInstance();
    /**
     * - doGet URL  /list - открывает таблицу со всеми пользователями.
     * В каждой строку должна быть колонка с кнопками (редактировать, удалить)
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", this.logic.findAll().values());
        req.getRequestDispatcher("/WEB-INF/views/users.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        String action = req.getParameter("action");
        if (action.equals("delete")) {
            this.logic.delete(id);
        }
        doGet(req, resp);
    }
}
