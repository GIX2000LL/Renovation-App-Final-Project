package pl.lymek.renovationApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.lymek.renovationApp.model.Address;
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

//------------------------------------------------------------------------------------------------------------

    @GetMapping
    public String showUserZone (){

        return "user";
    }

    @GetMapping("/userDetails")
    public String goOnCurrentUserProfile (Model model){

        User user = getCurrentUser();
        model.addAttribute("user",user);

        if(user.getAddress() !=null) {
            model.addAttribute("address",user.getAddress());
        }

        return "userDetails";
    }

    //---------------------------------------------------------------------
    @GetMapping("/edit/{id}")
    public String loadUserEditForm (@PathVariable long id,Model model) {

        User user = getCurrentUserById(id);
        model.addAttribute("userToEdit",user);

        return "userForm";
    }

    @PostMapping("/edit/{id}")
    public String editUserDetails (@ModelAttribute("userToEdit") @Valid User userAfterEdition, BindingResult result) {

        if(result.hasErrors()) {

            return "userForm";
        } else {

            userRepository.save(userAfterEdition);

            Address address =userAfterEdition.getAddress();
            if (address == null) {

                return "addressAnnotation";
            }
        }

        return "redirect:/user/userDetails";
    }

    @GetMapping ("/addressEdition/{id}")
    public String showUserAddressEditionForm (@PathVariable long id, Model model) {

        User user = getCurrentUserById(id);

        if(user.getAddress()==null) {
            model.addAttribute("address",new Address());
        } else {
            model.addAttribute("address",user.getAddress());
        }

        return "userAddressEditionForm";
    }

    @PostMapping("/addressEdition/{id}")
    public String editUserAddressDetails (@PathVariable long id,@ModelAttribute("address") @Valid Address addressAfterEdition,
                                          BindingResult result) {

        if(result.hasErrors()) {

            return "userAddressEditionForm";
        } else {

               User user = getCurrentUserById(id);
               addressRepository.save(addressAfterEdition);
               user.setAddress(addressAfterEdition);
               userRepository.save(user);
        }

        return "redirect:/user/userDetails";
    }

//---------------------------------------------------------------------------------------------------------------------------------------

    private User getCurrentUserById (long id) {

        Optional <User> userOptional=userRepository.findById(id);

        User user = userOptional.stream()
                .findAny()
                .orElseThrow(NoSuchElementException::new);

        return user;
    }

//-------------------------------------------------------------------------------------------------------------------
protected User getCurrentUser () {

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    PrincipalDetails principalDetails = (PrincipalDetails) auth.getPrincipal();

    return principalDetails.getUser();
}


}
