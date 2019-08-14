package ru.job4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.job4j.entity.ad.Ad;
import ru.job4j.entity.user.User;
import ru.job4j.service.ad.AdService;
import ru.job4j.service.user.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService USER_SERVICE;
    private final AdService AD_SERVICE;

    User activeUser;//=====временно, заменю в задании security===========

    @Autowired
    public UsersController(UserService USER_SERVICE, AdService AD_SERVICE) {
        this.USER_SERVICE = USER_SERVICE;
        this.AD_SERVICE = AD_SERVICE;
    }

    @GetMapping("/show")
    public String show(ModelMap modelMap) {

        activeUser = USER_SERVICE.getUserById(2);//=====временно, заменю в задании security===========
        modelMap.addAttribute("activeUser", activeUser);//=====временно, заменю в задании security===========

        modelMap.addAttribute("users", USER_SERVICE.getUsers());

        return "users";
    }
    @PostMapping("/updateUser")
    public ModelAndView updateUser(@ModelAttribute User user) {
        ModelAndView modelAndView = new ModelAndView("updateUser");

        modelAndView.addObject("activeUser", activeUser);//=====временно, заменю в задании security===========

        return modelAndView;
    }
    @PostMapping("/delete")
    public String delete(
            @RequestParam(value = "action") String action,
            @RequestParam(value = "id") String userId,
            ModelMap modelMap) {

        modelMap.addAttribute("activeUser", activeUser);//=====временно, заменю в задании security===========

        int id = Integer.valueOf(userId);
        List<Ad> userAds = AD_SERVICE.getAdsByUserId(id);
        for (int i = 0; i < userAds.size(); i++) {
            AD_SERVICE.delete(userAds.get(i));
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
    public ModelAndView update(@ModelAttribute User user) {
        USER_SERVICE.update(user);
        ModelAndView model = new ModelAndView("updateUser");

        model.addObject("activeUser", activeUser);//=====временно, заменю в задании security===========

        return model;
    }
}
