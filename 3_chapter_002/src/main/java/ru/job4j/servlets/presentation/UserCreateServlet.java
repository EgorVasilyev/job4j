package ru.job4j.servlets.presentation;

import ru.job4j.servlets.datamodel.Country;
import ru.job4j.servlets.datamodel.User;
import ru.job4j.servlets.logic.Validate;
import ru.job4j.servlets.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserCreateServlet extends HttpServlet {
    private final Validate logic = ValidateService.getSingletonInstance();
    /**
     * - doGet URL /create - Открывает форму для создания нового пользователя.
     *
     * - doPost - / - сохраняет пользователя.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        @SuppressWarnings("unchecked")
        Map<Integer, Country> countriesMap = (HashMap<Integer, Country>) getServletContext().getAttribute("countriesMap");
        List<Country> countriesList = new ArrayList<>(countriesMap.values());
        req.setAttribute("countries", countriesList);
        req.getRequestDispatcher("/WEB-INF/views/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String country = req.getParameter("country");
        String city = req.getParameter("city");
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
        resp.sendRedirect(String.format("%s/create", req.getContextPath()));
    }
}
