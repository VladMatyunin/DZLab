package ru.dz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dz.entity.User;
import ru.dz.repository.UserRepository;

/**
 * Created by Vlad.M on 29.11.2016.
 */
@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public String getRegPage(ModelMap modelMap) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {

    /* The user is logged in :) */
            return "index";
        }
        return "registration";
    }

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public String registrate(@RequestParam("name") String name, @RequestParam("surname") String surname,
                             @RequestParam("email") String email, @RequestParam("password") String password,
                             @RequestParam("repassword") String confirm_password, ModelMap map) {
        if (name.length() < 2 || surname.length() < 2 || name.length() > 30 || surname.length() > 50) {
            map.addAttribute("message", "Заполните корректно имя и фамилию");
            return "registration";
        }
        if (password.length() < 5 || password.length() > 30) {
            map.addAttribute("message", "пароль должен быть не меньше 5 и не больше 30 символов");
            return "registration";
        }
        if (!password.equals(confirm_password)) {
            map.addAttribute("message", "Пароли не совпадают");
            return "registration";
        }
        userRepository.save(new User(name, email, password));
        return "index";
    }
}
