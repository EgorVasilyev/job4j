package ru.job4j.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.models.Hall;
import ru.job4j.models.Order;
import ru.job4j.persistence.DbStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class HallServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(HallServlet.class.getName());
    private static DbStore persistence = DbStore.getSingletonInstance();

    static {
        persistence.clearAllBlockedPlaces();
        persistence.cleanOrders();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action").equals("loadHall")) {
            int number = Integer.valueOf(req.getParameter("number"));
            Hall hall = persistence.findByNumber(number);
            resp.setContentType("text/json");
            ObjectMapper mapper = new ObjectMapper();
            try (Writer writer = new PrintWriter(resp.getOutputStream())) {
                writer.write(mapper.writeValueAsString(hall));
            } catch (Exception ex) {
                LOG.error(ex);
            }
        }
        if (req.getParameter("action").equals("showHalls")) {
            CopyOnWriteArrayList<Hall> halls = new CopyOnWriteArrayList<>(persistence.findAll().values());
            resp.setContentType("text/json");
            ObjectMapper mapper = new ObjectMapper();
            try (Writer writer = new PrintWriter(resp.getOutputStream())) {
                writer.write(mapper.writeValueAsString(halls));
            } catch (Exception ex) {
                LOG.error(ex);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        String fio = req.getParameter("fio");
        String phone = req.getParameter("phone");
        int hallNumber = Integer.valueOf(req.getParameter("hallNumber"));
        int price = Integer.valueOf(req.getParameter("finishPrice"));
        String blockedPlaces = req.getParameter("blockedPlaces");
        ArrayList<Integer> places = Arrays.stream(blockedPlaces
                .replace("\"", "")
                .replace("[", "")
                .replace("]", "")
                .split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toCollection(ArrayList::new));
        persistence.addBlockedPlaces(hallNumber, places);
        persistence.addOrder(new Order(fio, phone, hallNumber, places, price));
    }
}
