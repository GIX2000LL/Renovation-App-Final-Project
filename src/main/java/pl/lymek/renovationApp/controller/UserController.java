package pl.lymek.renovationApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.lymek.renovationApp.model.User;
import pl.lymek.renovationApp.repository.AddressRepository;
import pl.lymek.renovationApp.repository.UserRepository;
import pl.lymek.renovationApp.security.PrincipalDetails;

import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.Optional;


@RequestMapping("/user")
@Controller
public class UserController {

    private Logger logger = LoggerFactory.getLogger("logger");

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public UserController(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }
//----------------------------------------------------------------------------------------------------

    @ModelAttribute(name = "currentUser")
    protected User getCurrentUser () {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails principalDetails=(PrincipalDetails) auth.getPrincipal();

        return principalDetails.getUser();
    }
//----------------------------------------------------------------------------------------------------

    @GetMapping
    public String showUserZone (){

        return "user";
    }

    @GetMapping("/userDetails")
    public String goOnCurrentUserProfile (){

        return "userDetails";
    }


//---------------------------------------------------------------------------------------------------------

    @PreAuthorize("hasAnyRole('OWNER','SUPER-ADMIN')")
    @GetMapping("/edit/{id}")
    public String loadUserEditForm (@PathVariable long id,Model model) {

        Optional <User> userOptional=userRepository.findById(id);

        User user = userOptional.stream()
                .findAny()
                .orElseThrow(NoSuchElementException::new);

        model.addAttribute("userToEdit",user);

        return "userForm";
    }


    @PostMapping("/edit/{id}")
    public String getEditedUserFromForm (@ModelAttribute("userToEdit") @Valid User userAfterEdition, BindingResult result) {


        if (result.hasErrors()) {

            return "userForm";
        } else {

            addressRepository.save(userAfterEdition.getAddress());

            userRepository.save(userAfterEdition);

            logger.info(userAfterEdition.toString());
            logger.info(userAfterEdition.getAddress().toString());

            return "redirect:/login";
        }
    }

//----------------------------------------------------------------------------------------------------------------------


}
