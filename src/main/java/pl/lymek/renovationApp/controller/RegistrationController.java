package pl.lymek.renovationApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.lymek.renovationApp.model.User;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/registration")
@Controller
public class RegistrationController {

    @RequestMapping
    public String showRegistrationForm (Model model) {
        model.addAttribute("user", new User());

        return "registrationForm";
    }

    @PostMapping
    @ResponseBody
    public String showFormResult (@ModelAttribute User user, HttpServletRequest request) {

        String companyName = request.getParameter("companyName");

        return user.getFirstName()+" "+companyName;
    }
}
