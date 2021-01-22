package pl.lymek.renovationApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.lymek.renovationApp.model.User;
import pl.lymek.renovationApp.repository.UserRepository;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;


@RequestMapping("/user")
@Controller
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String showUserZone (Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = auth.getName();

        Optional<User> userOpt =userRepository.findByEmail(currentUserEmail);

        String userFirstName = userOpt.stream()
                .map(user -> user.getFirstName())
                .collect(Collectors.joining());

        model.addAttribute("currentUserName",userFirstName);


        return "user";
    }
}
