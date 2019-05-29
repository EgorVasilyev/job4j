package ru.job4j.servlets.presentation;

import ru.job4j.servlets.datamodel.User;
import ru.job4j.servlets.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserCreateServlet extends HttpServlet {
    private final ValidateService logic = ValidateService.getSingletonInstance();
    /**
     * - doGet URL /create - Открывает форму для создания нового пользователя.
     *
     * - doPost - / - сохраняет пользователя.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
        printWriter.append("<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title></title>" +
                "</head>" +
                "<body>" +
                "<form action='" + req.getContextPath() + "/create' method='post'>" +
                "Name: <input type='text' name='name'/>" +
                "Login: <input type='text' name='login'/>" +
                "Email: <input type='text' name='email'/>" +
                "<input type='submit'>" +
                "</form>" +
                "</body>" +
                "</html>");
        printWriter.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        this.logic.add(
                new User(req.getParameter("name"),
                        req.getParameter("login"),
                        req.getParameter("email")
                )
        );
        doGet(req, resp);
    }
}
