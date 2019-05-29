package ru.job4j.servlets.presentation;

import ru.job4j.servlets.datamodel.User;
import ru.job4j.servlets.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserUpdateServlet extends HttpServlet {
    private final ValidateService logic = ValidateService.getSingletonInstance();

    /**
     * - doGet URL /edit?id={userId} - открывает форму для редактирования с заполенными полями.
     *
     * - doPost - / - сохраняет пользователя.
     */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
        int id = Integer.parseInt(req.getParameter("id"));
        User user = logic.findById(id);

        StringBuilder users = new StringBuilder("<table>");
        users.append("List of available users:");
        for (User userValue : this.logic.findAll().values()) {
            users.append("<tr><td>");
            users.append(userValue.toString());
            users.append("</tr></td>");
        }
        users.append("</table>");

        printWriter.print("<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title></title>" +
                "</head>" +
                "<body>" +
                "<form action='" + req.getContextPath() + "/edit?id=" + user.getId() + "' method='post'>" +
                "ID: <input type='text' name='id' value="+user.getId()+" size='10'/>" +
                "Name: <input type='text' name='name' value="+user.getName()+" size='25'/>" +
                "Login: <input type='text' name='login' value="+user.getLogin()+" size='25'/>" +
                "Email: <input type='text' name='email' value="+user.getEmail()+" size='25'/>" +
                "<input type='submit'>" +
                "</form>" +
                "</body>" +
                "</html>");
        printWriter.flush();
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
