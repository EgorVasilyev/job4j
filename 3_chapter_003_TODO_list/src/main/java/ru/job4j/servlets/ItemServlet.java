package ru.job4j.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.entity.Item;
import ru.job4j.service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class ItemServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(ItemServlet.class.getName());
    private static final ItemService ITEM_SERVICE = ItemService.getInstance();

    private static final Map<String, Function<ItemService, List<Item>>> ITEMS = new HashMap<>();
    private static final Map<String, Consumer<HttpServletRequest>> SERVICE = new HashMap<>();

    @Override
    public void init() throws ServletException {
        super.init();
        this.loadFunction("showItems", showItems());
        this.loadFunction("showNotDoneItems", showNotDoneItems());
        this.loadConsumer("sendNewItem", saveItem());
        this.loadConsumer("updateItem", updateItem());
        this.loadConsumer("deleteItem", deleteItem());
    }

    public Function<ItemService, List<Item>> showItems() {
        return ItemService::getItems;
    }

    public Function<ItemService, List<Item>> showNotDoneItems() {
        return ItemService::getNotDoneItems;
    }

    public void loadFunction(String action, Function<ItemService, List<Item>> handle) {
        ITEMS.put(action, handle);
    }

    public Consumer<HttpServletRequest> saveItem() {
        return req -> {
            String description = req.getParameter("description");
            Item item = new Item();
            item.setDescription(description);
            ITEM_SERVICE.save(item);
        };
    }

    public Consumer<HttpServletRequest> updateItem() {
        return req -> {
            int id = Integer.valueOf(req.getParameter("id"));
            boolean done = Boolean.valueOf(req.getParameter("done"));
            Item item = ITEM_SERVICE.getById(id);
            item.setDone(done);
            ITEM_SERVICE.update(item);
        };
    }
    public Consumer<HttpServletRequest> deleteItem() {
        return req -> {
            int id = Integer.valueOf(req.getParameter("id"));
            Item item = ITEM_SERVICE.getById(id);
            ITEM_SERVICE.delete(item);
        };
    }
    public void loadConsumer(String action, Consumer<HttpServletRequest> handle) {
        SERVICE.put(action, handle);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/json");
        ObjectMapper mapper = new ObjectMapper();
        try (Writer writer = new PrintWriter(resp.getOutputStream())) {
            writer.write(mapper.writeValueAsString(ITEMS.get(req.getParameter("action")).apply(ITEM_SERVICE)));
        } catch (Exception ex) {
            LOG.error("Error", ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/json");
        SERVICE.get(req.getParameter("action")).accept(req);
    }
}
