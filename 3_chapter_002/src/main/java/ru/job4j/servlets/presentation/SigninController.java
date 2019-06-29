package ru.job4j.servlets.presentation;

import ru.job4j.servlets.datamodel.User;
import ru.job4j.servlets.logic.Validate;
import ru.job4j.servlets.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SigninController extends HttpServlet {
    private final Validate logic = ValidateService.getSingletonInstance();
    {
        logic.add(new User("root", "root", "root", "root", "admin", 1, 1));
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        int id = ValidateService.getSingletonInstance().isCredential(login, password);
        if (id > 0) {
            HttpSession httpSession = req.getSession();
            synchronized (httpSession) {
                httpSession.setAttribute("activeUser", this.logic.findById(id));
            }

            resp.sendRedirect(String.format("%s/users", req.getContextPath()));
        } else {
            req.setAttribute("error", "Invalid user");
            doGet(req, resp);
        }
    }
}
