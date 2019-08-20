package ru.job4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.entity.user.User;
import ru.job4j.service.user.UserService;

@Controller
public class HomeController {
    private final UserService USER_SERVICE;

    @Autowired
    public HomeController(UserService user_service) {
        USER_SERVICE = user_service;
    }

    @GetMapping(value="/login")
    public String login() {
        return "login";
    }
    @PostMapping("/registration")
    public String update(@ModelAttribute User user, ModelMap modelMap) {

        if (USER_SERVICE.getUserByLogin(user.getLogin()) != null) {
            modelMap.addAttribute("msg", "This login already exists!");
            return "redirect:login";
        } else {
            user.setRole("user");
            user.setId(USER_SERVICE.save(user));

            UserDetails userDetails = org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
                    .username(user.getLogin())
                    .password(user.getPassword())
                    .roles(user.getRole())
                    .build();
            Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);

            modelMap.addAttribute("activeUser", user);
            return "redirect:ads/show";
        }
    }
}    