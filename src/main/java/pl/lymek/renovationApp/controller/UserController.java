package pl.lymek.renovationApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.lymek.renovationApp.model.User;
import pl.lymek.renovationApp.repository.AddressRepository;
import pl.lymek.renovationApp.repository.UserRepository;
import pl.lymek.renovationApp.security.PrincipalDetails;

import java.util.ArrayList;
import java.util.List;
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
    public String showUserAndAddressForm (@ModelAttribute("userToEdit") User userAfterEdition) {


        return "redirect:/user/userDetails";
    }

//---------------------------------------------------------------------------------------------------------------------------------------
    public void reloadPrincipal () {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
        updatedAuthorities.add(new SimpleGrantedAuthority("ROLE_OWNER"));
        updatedAuthorities.add(new SimpleGrantedAuthority("ROLE_SUPER-ADMIN"));

        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);

        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }

}
