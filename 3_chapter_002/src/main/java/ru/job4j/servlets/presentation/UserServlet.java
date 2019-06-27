package ru.job4j.servlets.presentation;

import ru.job4j.servlets.datamodel.User;
import ru.job4j.servlets.logic.Validate;
import ru.job4j.servlets.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Presentation layout.
 *
 * Необходимо создать сервлет UserServlet и определить там методы doGet doPost. Это будет слой Presentation.
 *
 * Метод doGet - должен отдавать список всех пользователей в системе.
 *
 * Метод doPost - должен  уметь делать три вещи, создавать пользователя, обновлять поля пользователя, удалять пользователя.
 *
 * Подумайте. как тут можно применить шаблон - https://github.com/peterarsentev/code_quality_principles#2-dispatch-pattern-instead-of-multiple-if-statements-and-switch-anti-pattern
 *
 * Давайте посмотрим пример post-запроса.
 *
 * Создание нового пользователя.
 *
 * action=add - ключ указывает какое действие выполнить. у нас будут три ключа add, update, delete. их мы должно обработать в doPost.
 *
 * name=petr - ключ указывает имя пользователя.
 *
 * на стороне сервера мы извлекаем эти данные с помощью метода request.getParameter("action")
 *
 * Обновление пользователя.
 *
 * action=update
 *
 * id=1 - первичный ключ - генерируется один раз. по нему мы будем искать пользователя в коллекции.
 *
 * name=ivan - новое имя.
 *
 * Удаление пользователя.
 *
 * action=delete
 *
 * id=1 - по ключу мы удаляем пользователя.
 *
 *
 *
 * запрос примерно будет такой
 *
 * curl -d "action=add&name=Petr" -X POST http://localhost:8080/users
 * Тестирование сервлета осуществлять через Test RESTFull service
 */

public class UserServlet extends HttpServlet {
    private final Validate logic = ValidateService.getSingletonInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
        printWriter.append("users = " + this.logic.findAll());
        printWriter.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String action = req.getParameter("action");
        String country = req.getParameter("country");
        String city = req.getParameter("city");
        if ("add".equals(action)) {
            this.logic.add(
                    new User(req.getParameter("name"),
                            req.getParameter("login"),
                            req.getParameter("email"),
                            req.getParameter("password"),
                            req.getParameter("role"),
                            country == null || country.equals("") ? 0 : Integer.parseInt(country),
                            city == null ||  city.equals("") ? 0 : Integer.parseInt(city)
                    )
            );
        }
        if ("update".equals(action)) {
            this.logic.update(
                    Integer.valueOf(req.getParameter("id")),
                    new User(req.getParameter("name"),
                            req.getParameter("login"),
                            req.getParameter("email"),
                            req.getParameter("password"),
                            req.getParameter("role"),
                            country == null || country.equals("") ? 0 : Integer.parseInt(country),
                            city == null ||  city.equals("") ? 0 : Integer.parseInt(city)
                    )
            );
        }
        if ("delete".equals(action)) {
            this.logic.delete(Integer.valueOf(req.getParameter("id")));
        }
        doGet(req, resp);
    }
}
