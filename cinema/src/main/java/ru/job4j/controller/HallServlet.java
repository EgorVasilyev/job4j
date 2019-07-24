package ru.job4j.controller;

import com.fasterxml.jackson.core.type.TypeReference;
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

public class HallServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(HallServlet.class.getName());
    private static final DbStore PERSISTENCE = DbStore.getSingletonInstance();

    static {
        PERSISTENCE.clearAllBlockedPlaces();
        PERSISTENCE.cleanOrders();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action").equals("loadHall")) {
            int number = Integer.valueOf(req.getParameter("number"));
            Hall hall = PERSISTENCE.findByNumber(number);
            resp.setContentType("text/json");
            ObjectMapper mapper = new ObjectMapper();
            try (Writer writer = new PrintWriter(resp.getOutputStream())) {
                writer.write(mapper.writeValueAsString(hall));
            } catch (Exception ex) {
                LOG.error(ex.getMessage(), ex);
            }
        }
        if (req.getParameter("action").equals("showHalls")) {
            ArrayList<Hall> halls = new ArrayList<>(PERSISTENCE.findAll().values());
            resp.setContentType("text/json");
            ObjectMapper mapper = new ObjectMapper();
            try (Writer writer = new PrintWriter(resp.getOutputStream())) {
                writer.write(mapper.writeValueAsString(halls));
            } catch (Exception ex) {
                LOG.error(ex.getMessage(), ex);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fio = req.getParameter("fio");
        String phone = req.getParameter("phone");
        int hallNumber = Integer.valueOf(req.getParameter("hallNumber"));
        int price = Integer.valueOf(req.getParameter("finishPrice"));
        String blockedPlaces = req.getParameter("blockedPlaces");
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Integer> places = mapper.readValue(blockedPlaces, new TypeReference<ArrayList<Integer>>() { });

        PERSISTENCE.addBlockedPlaces(hallNumber, places);
        PERSISTENCE.addOrder(new Order(fio, phone, hallNumber, places, price));
    }
}
