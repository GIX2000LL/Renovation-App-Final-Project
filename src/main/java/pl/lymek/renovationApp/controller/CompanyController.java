package pl.lymek.renovationApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.lymek.renovationApp.model.Company;
import pl.lymek.renovationApp.model.User;
import pl.lymek.renovationApp.repository.AddressRepository;
import pl.lymek.renovationApp.repository.CompanyRepository;
import pl.lymek.renovationApp.security.PrincipalDetails;

import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequestMapping("/company")
@Controller
public class CompanyController {

    private Logger logger = LoggerFactory.getLogger("logger");

    private CompanyRepository companyRepository;
    private AddressRepository addressRepository;

    public CompanyController(CompanyRepository companyRepository, AddressRepository addressRepository) {
        this.companyRepository = companyRepository;
        this.addressRepository = addressRepository;
    }

    //------------------------------------------------------------------------------------------------------
    @ModelAttribute(name = "currentUser")
    protected User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails principalDetails = (PrincipalDetails) auth.getPrincipal();

        return principalDetails.getUser();
    }

//----------------------------------------------------------------------------------------------------

    @GetMapping
    public String showCompanyPage() {

        return "company";
    }

    @GetMapping("/companyDetails")
    public String showCompanyDetails() {

        return "companyDetails";
    }

    @PreAuthorize("hasAnyRole('OWNER','SUPER-ADMIN')")
    @GetMapping("/edit/{id}")
    public String loadCompanyEditForm(@PathVariable long id, Model model) {

        Optional<Company> companyOptional = companyRepository.findById(id);

        Company company = companyOptional.stream()
                .findAny()
                .orElseThrow(NoSuchElementException::new);

        model.addAttribute("companyToEdit", company);

        return "companyForm";
    }


    @PostMapping("/edit/{id}")
    public String getEditedUserFromForm (@ModelAttribute("companyToEdit") @Valid Company companyAfterEdition, BindingResult result) {


        if (result.hasErrors()) {

            return "companyForm";
        } else {

            addressRepository.save(companyAfterEdition.getAddress());
            companyRepository.save(companyAfterEdition);

            logger.info(companyAfterEdition.toString());
            logger.info(companyAfterEdition.getAddress().toString());

            return "redirect:/login";
        }
    }

}
