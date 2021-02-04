package pl.lymek.renovationApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.lymek.renovationApp.components.CurrentUser;
import pl.lymek.renovationApp.model.Address;
import pl.lymek.renovationApp.model.User;
import pl.lymek.renovationApp.repository.AddressRepository;
import pl.lymek.renovationApp.repository.UserRepository;
import javax.validation.Valid;


@RequestMapping("/user")
@Controller
public class UserController {

    private Logger logger = LoggerFactory.getLogger("logger");

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final CurrentUser currentUser;

    public UserController(UserRepository userRepository, AddressRepository addressRepository, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.currentUser = currentUser;
    }

    //------------------------------------------------------------------------------------------------------------

    @GetMapping
    public String showUserZone (){

        return "user";
    }

    @GetMapping("/userDetails")
    public String goOnCurrentUserProfile (Model model){

        User user = currentUser.getCurrentUser();
        model.addAttribute("user",user);

        if(user.getAddress() !=null) {
            model.addAttribute("address",user.getAddress());
        }

        return "userDetails";
    }

    @GetMapping("/edit/{id}")
    public String loadUserEditForm (@PathVariable long id,Model model) {

        User user = currentUser.getCurrentUserById(id);
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

        User user = currentUser.getCurrentUserById(id);

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

               User user = currentUser.getCurrentUserById(id);
               addressRepository.save(addressAfterEdition);
               user.setAddress(addressAfterEdition);
               userRepository.save(user);
        }

        return "redirect:/user/userDetails";
    }

}
