package ru.job4j.servlets.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ru.job4j.servlets.datamodel.City;
import ru.job4j.servlets.datamodel.Country;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class JsonServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(JsonServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String param = req.getParameter("countryId");
        Integer countryId = param.equals("") ? null : Integer.parseInt(param);
        @SuppressWarnings("unchecked")
        ConcurrentHashMap<Integer, Country> countries =
                (ConcurrentHashMap<Integer, Country>) getServletContext().getAttribute("countriesMap");
        CopyOnWriteArrayList<City> cityList;
        if (countryId == null) {
            cityList = new CopyOnWriteArrayList<>(); //returns an empty CopyOnWriteArrayList for creating an empty select
        } else {
            Country country = countries.get(countryId);
            cityList = new CopyOnWriteArrayList<>(country.getCities());
        }
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(cityList);
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(json);
        writer.flush();
    }
}