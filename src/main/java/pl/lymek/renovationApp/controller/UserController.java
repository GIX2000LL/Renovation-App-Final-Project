package pl.lymek.renovationApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {

    @RequestMapping
    public String showUserZone () {

        return "user";
    }
}
