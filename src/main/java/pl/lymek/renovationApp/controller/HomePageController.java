package pl.lymek.renovationApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.lymek.renovationApp.repository.UserRepository;


@Controller
@RequestMapping
public class HomePageController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping
    public String showHomePage () {

        return "home";
    }



}
