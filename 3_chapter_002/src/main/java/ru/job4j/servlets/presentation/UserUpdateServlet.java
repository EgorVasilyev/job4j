package ru.job4j.servlets.presentation;

import ru.job4j.servlets.datamodel.City;
import ru.job4j.servlets.datamodel.Country;
import ru.job4j.servlets.datamodel.User;
import ru.job4j.servlets.logic.Validate;
import ru.job4j.servlets.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

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
        @SuppressWarnings("unchecked")
        ConcurrentHashMap<Integer, Country> countriesMap =
                (ConcurrentHashMap<Integer, Country>) getServletContext().getAttribute("countriesMap");
        CopyOnWriteArrayList<Country> countriesList = new CopyOnWriteArrayList<>(countriesMap.values());
        CopyOnWriteArrayList<City> cities =
                (CopyOnWriteArrayList<City>) countriesMap.get(user.getCountryId()).getCities();
        req.setAttribute("user", user);
        req.setAttribute("countries", countriesList);
        req.setAttribute("cities", cities);
        req.getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String id = req.getParameter("id");
        String country = req.getParameter("country");
        String city = req.getParameter("city");
        this.logic.update(
                id == null || id.equals("") ? 0 : Integer.parseInt(id),
                new User(
                        req.getParameter("name"),
                        req.getParameter("login"),
                        req.getParameter("email"),
                        req.getParameter("password"),
                        req.getParameter("role"),
                        country == null || country.equals("") ? 0 : Integer.parseInt(country),
                        city == null ||  city.equals("") ? 0 : Integer.parseInt(city)
                )
        );
        doGet(req, resp);
    }

}
