package pl.lymek.renovationApp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String showLoginForm () {

        return "login";
    }

//    @PostMapping("/login")
//    public String goToUserPage () {
//
//        return "redirect:/user";
//    }

}
