package ru.job4j.htmlcssjs.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.htmlcssjs.datamodel.JsonUser;
import ru.job4j.htmlcssjs.persistent.MemoryStore;
import ru.job4j.htmlcssjs.persistent.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class JsonServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(JsonServlet.class.getName());
    private static Store storage = MemoryStore.getSingletonInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Users:");
        for (JsonUser jsonUser : storage.findAll().values()) {
            LOG.info(jsonUser);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            LOG.info("JSON : " + sb);
            ObjectMapper mapper = new ObjectMapper();
            JsonUser user = mapper.readValue(sb.toString(), JsonUser.class);
            storage.add(user);
            try (Writer writer = new PrintWriter(resp.getOutputStream())) {
                writer.write(mapper.writeValueAsString(user));
            }
        } catch (Exception ex) {
            LOG.error(ex);
        }
        doGet(req, resp);
    }
}
