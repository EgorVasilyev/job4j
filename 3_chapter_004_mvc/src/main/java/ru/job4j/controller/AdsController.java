package ru.job4j.controller;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.entity.ad.Ad;
import ru.job4j.entity.car.*;
import ru.job4j.entity.user.User;
import ru.job4j.service.ad.AdService;
import ru.job4j.service.car.*;
import ru.job4j.service.user.UserService;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ads")
public class AdsController {
    private final AdService AD_SERVICE;
    private final EngineService ENGINE_SERVICE;
    private final BodyService BODY_SERVICE;
    private final ColorService COLOR_SERVICE;
    private final YearService YEAR_SERVICE;
    private final CarService CAR_SERVICE;
    private final UserService USER_SERVICE;

    private User activeUser;

    @Autowired
    public AdsController(AdService AD_SERVICE,
                         EngineService ENGINE_SERVICE, BodyService BODY_SERVICE,
                         ColorService COLOR_SERVICE, YearService YEAR_SERVICE,
                         CarService CAR_SERVICE, UserService USER_SERVICE) {
        this.AD_SERVICE = AD_SERVICE;
        this.ENGINE_SERVICE = ENGINE_SERVICE;
        this.BODY_SERVICE = BODY_SERVICE;
        this.COLOR_SERVICE = COLOR_SERVICE;
        this.YEAR_SERVICE = YEAR_SERVICE;
        this.CAR_SERVICE = CAR_SERVICE;
        this.USER_SERVICE = USER_SERVICE;
    }


    @GetMapping("/show")
    public String ads(
            @RequestParam(value = "actual", required = false, defaultValue = "") String actual,
            @RequestParam(value = "currentDay", required = false, defaultValue = "") String currentDay,
            @RequestParam(value = "byName", required = false, defaultValue = "") String byName,
            @RequestParam(value = "withPhoto", required = false, defaultValue = "") String withPhoto,
            ModelMap modelMap) {

        activeUser = USER_SERVICE.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        modelMap.addAttribute("activeUser", activeUser);

        Map<String, String> filter = new HashMap<>();
        if (!actual.equals("")) {
            filter.put("actual", null);
            modelMap.addAttribute("checkedActual", true);
        } else {
            modelMap.addAttribute("checkedActual", false);
        }
        if (!currentDay.equals("")) {
            filter.put("currentDay", null);
            modelMap.addAttribute("checkedCurrentDay", true);
        } else {
            modelMap.addAttribute("checkedCurrentDay", false);
        }
        if (!byName.equals("")) {
            filter.put("byName", byName);
            modelMap.addAttribute("model", byName);
        }
        if (!withPhoto.equals("")) {
            filter.put("withPhoto", null);
            modelMap.addAttribute("checkedWithPhoto", true);
        } else {
            modelMap.addAttribute("checkedWithPhoto", false);
        }
        List<Ad> ads = AD_SERVICE.getAdsByFilter(filter);
        HashMap<Integer, String> picturesBase64 = new HashMap<>(ads.size());
        ads.forEach(ad -> picturesBase64.put(ad.getId(), Base64.encode(ad.getPicture())));
        modelMap.addAttribute("ads", ads);
        modelMap.addAttribute("pictures", picturesBase64);
        return "ads";
    }
    @GetMapping("/update")
    public String editAd(
            @RequestParam(value = "id") String adId,
            ModelMap modelMap) {

        modelMap.addAttribute("activeUser", activeUser);

        int id = Integer.valueOf(adId);
        Ad ad = AD_SERVICE.getAdById(id);
        String base64String = Base64.encode(ad.getPicture());
        modelMap.addAttribute("picture", base64String);
        modelMap.addAttribute("ad", ad);
        modelMap.addAttribute("bodies", BODY_SERVICE.getBodies());
        modelMap.addAttribute("colors", COLOR_SERVICE.getColors());
        modelMap.addAttribute("engines", ENGINE_SERVICE.getEngines());
        modelMap.addAttribute("years", YEAR_SERVICE.getYears());
        return "updateAd";
    }
    @GetMapping("/create")
    public String createAd(ModelMap modelMap) {

        modelMap.addAttribute("activeUser", activeUser);

        modelMap.addAttribute("bodies", BODY_SERVICE.getBodies());
        modelMap.addAttribute("colors", COLOR_SERVICE.getColors());
        modelMap.addAttribute("engines", ENGINE_SERVICE.getEngines());
        modelMap.addAttribute("years", YEAR_SERVICE.getYears());
        return "createAd";
    }
    @GetMapping("/userAds")
    public String userAds(
            @RequestParam(value = "id") String userId,
            ModelMap modelMap) {

        modelMap.addAttribute("activeUser", activeUser);

        int id = Integer.valueOf(userId);
        List<Ad> ads = AD_SERVICE.getAdsByUserId(id);
        HashMap<Integer, String> picturesBase64 = new HashMap<>(ads.size());
        ads.forEach(ad -> picturesBase64.put(ad.getId(), Base64.encode(ad.getPicture())));
        modelMap.addAttribute("ads", ads);
        modelMap.addAttribute("pictures", picturesBase64);
        return "userAds";
    }
    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate(
            @RequestParam(value = "action", required = false) String action,
            @RequestParam(value = "model", required = false) String model,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "yearAddit", required = false) String yearAddit,
            @RequestParam(value = "body", required = false) String body,
            @RequestParam(value = "bodyAddit", required = false) String bodyAddit,
            @RequestParam(value = "color", required = false) String color,
            @RequestParam(value = "colorAddit", required = false) String colorAddit,
            @RequestParam(value = "engine", required = false) String engine,
            @RequestParam(value = "engineAddit", required = false) String engineAddit,
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "adId", required = false) String adId,
            @RequestParam(value = "file", required = false) MultipartFile file,
            ModelMap modelMap) {

        modelMap.addAttribute("activeUser", activeUser);

        int userId = Integer.valueOf(id);
        User user = USER_SERVICE.getUserById(userId);

        int valueYear = Integer.valueOf(year.equals("other...")
                ? yearAddit : year);
        Year currentYear;
        if (!YEAR_SERVICE.contains(valueYear)) {
            currentYear = new Year();
            currentYear.setValue(valueYear);
            currentYear.setId(YEAR_SERVICE.save(currentYear));
        } else {
            currentYear = YEAR_SERVICE.getByValue(valueYear);
        }

        String nameBody = body.equals("other...")
                ? bodyAddit : body;
        Body currentBody;
        if (!BODY_SERVICE.contains(nameBody)) {
            currentBody = new Body();
            currentBody.setName(nameBody);
            currentBody.setId(BODY_SERVICE.save(currentBody));
        } else {
            currentBody = BODY_SERVICE.getByName(nameBody);
        }

        String nameColor =color.equals("other...")
                ? colorAddit : color;
        Color currentColor;
        if (!COLOR_SERVICE.contains(nameColor)) {
            currentColor = new Color();
            currentColor.setName(nameColor);
            currentColor.setId(COLOR_SERVICE.save(currentColor));
        } else {
            currentColor = COLOR_SERVICE.getByName(nameColor);
        }

        String nameEngine = engine.equals("other...")
                ? engineAddit : engine;
        Engine currentEngine;
        if (!ENGINE_SERVICE.contains(nameEngine)) {
            currentEngine = new Engine();
            currentEngine.setName(nameEngine);
            currentEngine.setId(ENGINE_SERVICE.save(currentEngine));
        } else {
            currentEngine = ENGINE_SERVICE.getByName(nameEngine);
        }

        byte[] picture = getPicture(file);
        if (action.equals("update")) {
            boolean closed = Boolean.valueOf(status);
            int idOfAd = Integer.valueOf(adId);
            Ad ad = AD_SERVICE.getAdById(idOfAd);
            ad.setDescription(description);

            Car car = ad.getCar();
            car.setName(model);
            car.setBody(currentBody);
            car.setColor(currentColor);
            car.setEngine(currentEngine);
            car.setYear(currentYear);
            car.setUser(user);
            CAR_SERVICE.update(car);

            ad.setCar(car);
            if (picture.length != 0) {
                ad.setPicture(picture);
            }
            ad.setClosed(closed);
            AD_SERVICE.update(ad);

            return "redirect:update?id=" + idOfAd;
        }

        if (action.equals("save")) {
            Ad ad = new Ad();
            ad.setDescription(description);
            ad.setUser(user);

            Car car = new Car();
            car.setName(model);
            car.setBody(currentBody);
            car.setColor(currentColor);
            car.setEngine(currentEngine);
            car.setYear(currentYear);
            car.setUser(user);

            car.setId(CAR_SERVICE.save(car));

            ad.setCar(car);
            if (picture.length != 0) {
                ad.setPicture(picture);
            }
            AD_SERVICE.save(ad);

            return "redirect:create";
        }
        return "redirect:show";
    }
    private byte[] getPicture(MultipartFile file) {
        byte[] buffer = new byte[0];
        if (file != null) {
            try (InputStream fileContent = file.getInputStream();
                 BufferedInputStream fin = new BufferedInputStream(fileContent)) {
                buffer = new byte[fin.available()];
                fin.read(buffer, 0, buffer.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer;
    }
    @PostMapping("/delete")
    public String delete(
            @RequestParam(value = "action") String action,
            @RequestParam(value = "id") String adId,
            ModelMap modelMap) {

        modelMap.addAttribute("activeUser", activeUser);

        int id = Integer.valueOf(adId);
        Ad ad = AD_SERVICE.getAdById(id);
        AD_SERVICE.delete(ad);
        if (action.equals("deleteFromAll")) {
            return "redirect:show";
        }

        if (action.equals("deleteFromMine")) {
            int userId = ad.getUser().getId();
            return "redirect:userAds?id=" + userId;
    }
        return "ads";
    }
}
