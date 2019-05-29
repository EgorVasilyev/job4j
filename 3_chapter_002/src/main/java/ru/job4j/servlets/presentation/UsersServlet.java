package ru.job4j.servlets.presentation;

import ru.job4j.servlets.datamodel.User;
import ru.job4j.servlets.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UsersServlet extends HttpServlet {
    private final ValidateService logic = ValidateService.getSingletonInstance();
    /**
     * - doGet URL  /list - открывает таблицу со всеми пользователями.
     * В каждой строку должна быть колонка с кнопками (редактировать, удалить)
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
        StringBuilder users = new StringBuilder("<table>");
        users.append("List of available users:");
        for (User userValue : this.logic.findAll().values()) {
            users.append("<tr><td>ID: " + userValue.getId() + "</tr></td>");
            users.append("<tr><td>Name: " + userValue.getName() + "</tr></td>");
            users.append("<tr><td>Login: " + userValue.getLogin() + "</tr></td>");
            users.append("<tr><td>Email: " + userValue.getEmail() + "</tr></td>");

            users.append("<tr><td><form action='" + req.getContextPath() + "/edit?id=" + userValue.getId() + "' method='post'>");
            users.append("<input type='submit' value ='Edit'></form></td>");

            users.append("<td><form action='" + req.getContextPath() + "/users' method='post'>");
            users.append("<input type='hidden' name='action' value='delete'>");
            users.append("<input type='hidden' name='id' value='" + userValue.getId() + "'/>");
            users.append("<input type='submit' value ='Delete'>");
            users.append("</form></tr></td>");
        }
        users.append("</table>");

        printWriter.print("<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title></title>" +
                "</head>" +
                "<body>" +
                users.toString() +
                "</body>" +
                "</html>");
        printWriter.flush();
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
