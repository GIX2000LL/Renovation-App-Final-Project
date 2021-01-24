package pl.lymek.renovationApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.lymek.renovationApp.model.Company;
import pl.lymek.renovationApp.model.User;
import pl.lymek.renovationApp.repository.CompanyRepository;
import pl.lymek.renovationApp.repository.UserRepository;
import pl.lymek.renovationApp.security.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequestMapping("/registration")
@Controller
public class RegistrationController{

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public RegistrationController(UserRepository userRepository, CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository=companyRepository;
    }

    @RequestMapping
    public String showRegistrationForm (Model model) {
        model.addAttribute("user", new User());

        return "registrationForm";
    }

    @PostMapping
    public String showFormResult (@ModelAttribute @Valid User user, BindingResult result, HttpServletRequest request) {

        if(result.hasErrors()){

            return "registrationForm";
        } else {

            user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt()));
            user.setSecurityRole("ROLE_OWNER");
//            user.setActive(true);

            Company newCompany = new Company(request.getParameter("companyName"));
            companyRepository.save(newCompany);
            user.setCompany(newCompany);
            newCompany.setOwner(user);
            userRepository.save(user);

            return "redirect:/login";
        }
    }
}
