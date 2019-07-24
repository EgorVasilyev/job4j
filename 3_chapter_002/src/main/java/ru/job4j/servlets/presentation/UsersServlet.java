package ru.job4j.servlets.presentation;

import ru.job4j.servlets.datamodel.City;
import ru.job4j.servlets.datamodel.Country;
import ru.job4j.servlets.logic.Validate;
import ru.job4j.servlets.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UsersServlet extends HttpServlet {
    private final Validate logic = ValidateService.getSingletonInstance();

    /**
     * - doGet URL  /list - открывает таблицу со всеми пользователями.
     * В каждой строку должна быть колонка с кнопками (редактировать, удалить)
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        @SuppressWarnings("unchecked")
        Map<Integer, Country> countriesMap = (Map<Integer, Country>) getServletContext()
                .getAttribute("countriesMap");
        List<Country> countriesList = new ArrayList<>(countriesMap.values());
        @SuppressWarnings("unchecked")
        List<City> citiesList = (ArrayList<City>) getServletContext()
                .getAttribute("citiesList");
        req.setAttribute("countries", countriesList);
        req.setAttribute("cities", citiesList);
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

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
