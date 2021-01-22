package pl.lymek.renovationApp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
public class LogoutController {

    @RequestMapping("/logout-success")
    public String showLogoutPage() {

        return "logout";
    }
}
