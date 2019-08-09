package ru.job4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.entity.ad.Ad;
import ru.job4j.entity.car.Car;
import ru.job4j.entity.user.User;
import ru.job4j.service.ad.AdService;
import ru.job4j.service.car.*;
import ru.job4j.service.user.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UserService USER_SERVICE;
    @Autowired
    private AdService AD_SERVICE;
    @Autowired
    private CarService CAR_SERVICE;


    User activeUser;//=======================================================================


    @GetMapping("/show")
    public String show(
            ModelMap modelMap) {

        activeUser = USER_SERVICE.getUserById(2);//=======================================================================
        modelMap.addAttribute("activeUser", activeUser);//=======================================================================

        modelMap.addAttribute("users", USER_SERVICE.getUsers());

        return "users";
    }
    @GetMapping("/updateUser")
    public String updateUser(
            @RequestParam(value = "id") String id,
            ModelMap modelMap) {

        activeUser = USER_SERVICE.getUserById(2);//=======================================================================
        modelMap.addAttribute("activeUser", activeUser);//=======================================================================

        int userId = Integer.valueOf(id);
        User user = USER_SERVICE.getUserById(userId);
        modelMap.addAttribute("user", user);

        return "updateUser";
    }
    @PostMapping("/delete")
    public String delete(
            @RequestParam(value = "action") String action,
            @RequestParam(value = "id") String userId,
            ModelMap modelMap) {

        modelMap.addAttribute("activeUser", activeUser);//=======================================================================

        int id = Integer.valueOf(userId);
        List<Ad> userAds = AD_SERVICE.getAdsByUserId(id);
        for (int i = 0; i < userAds.size(); i++) {
            AD_SERVICE.delete(userAds.get(i));
        }

        List<Car> userCars = CAR_SERVICE.getCarsByUserId(id);
        for (int i = 0; i < userCars.size(); i++) {
            CAR_SERVICE.delete(userCars.get(i));
        }

        User user = USER_SERVICE.getUserById(id);
        USER_SERVICE.delete(user);
        if (action.equals("deleteFromAll")) {
            return "redirect:show";
        }

        if (action.equals("deleteFromProfile")) {
            return "login";
        }
        return "";
    }
    @PostMapping("/update")
    public String update(
            @RequestParam(value = "password") String password,
            @RequestParam(value = "phone") String phone,
            @RequestParam(value = "role") String role,
            @RequestParam(value = "id") String id,
            ModelMap modelMap) {

        modelMap.addAttribute("activeUser", activeUser);//=======================================================================

        int userId = Integer.valueOf(id);
        User user = USER_SERVICE.getUserById(userId);
        user.setPassword(password);
        user.setPhone(phone);
        user.setRole(role);

        USER_SERVICE.update(user);

        return "redirect:updateUser?id=" + userId;
    }
}
