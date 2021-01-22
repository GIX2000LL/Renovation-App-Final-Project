package pl.lymek.renovationApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.lymek.renovationApp.model.User;
import pl.lymek.renovationApp.repository.UserRepository;

import java.util.Optional;

@Controller
@RequestMapping
public class HomePageController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping
    public String showHomePage () {

        return "home";
    }

    @RequestMapping("/testJpa")
    @ResponseBody
    public String showEntity () {

        Optional<User> user = userRepository.findById(1L);

       User result = user.get();

        return result.toString();
    }



}
