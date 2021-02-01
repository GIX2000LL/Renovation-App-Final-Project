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
import pl.lymek.renovationApp.model.Address;
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

    @GetMapping
    public String showCompanyPage() {

        return "company";
    }

    @GetMapping("/companyDetails")
    public String showCompanyDetails(Model model) {
        User currentUser = getCurrentUser();

        if(currentUser.getCompany().getAddress()!=null) {

            model.addAttribute("address",currentUser.getCompany().getAddress());
        }

        if(currentUser.getCompany().getEmail()!=null || currentUser.getCompany().getPhoneNumber()!=null) {

            model.addAttribute("email",currentUser.getCompany().getEmail());
            model.addAttribute("phoneNumber",currentUser.getCompany().getPhoneNumber());
        }

        return "companyDetails";
    }

    @GetMapping("/edit/{id}")
    public String loadCompanyEditForm(@PathVariable long id, Model model) {

        Company company = getCurrentUserCompanyById(id);

        model.addAttribute("companyToEdit", company);

        return "companyForm";
    }


    @PostMapping("/edit/{id}")
    public String getEditedUserFromForm (@ModelAttribute("companyToEdit") @Valid Company companyAfterEdition, BindingResult result) {


        if (result.hasErrors()) {

            return "companyForm";
        } else {

//            logger.info(companyAfterEdition.toString());

            companyRepository.save(companyAfterEdition);

            Address address =companyAfterEdition.getAddress();
            if (address==null) {

                return "companyAddressAnnotation";
            }

            logger.info(companyAfterEdition.toString());

            return "redirect:/company/companyDetails";
        }
    }

    @GetMapping ("/companyAddressEdit/{id}")
    public  String showCompanyAddressForm (@PathVariable long id, Model model) {

        User user = getCurrentUser();
        if (user.getCompany().getAddress()==null) {
            model.addAttribute("address",new Address());
            model.addAttribute("companyId",user.getCompany().getId());
        } else {
            model.addAttribute("address",user.getCompany().getAddress());
            model.addAttribute("companyId",user.getCompany().getId());
        }

        return "companyAddressForm";

    }

    @PostMapping("/companyAddressEdit/{id}")
    public String createCompanyAddressFromForm (@PathVariable long id,@ModelAttribute("address") @Valid Address addressAfterEdition,
                                                BindingResult result) {

        if(result.hasErrors()) {

            return "companyAddressForm";
        } else {

            logger.info(addressAfterEdition.toString());
            addressRepository.save(addressAfterEdition);
            Company company = getCurrentUserCompanyById(id);
            logger.info(company.getName());
            company.setAddress(addressAfterEdition);
            companyRepository.save(company);
        }

        return "redirect:/company/companyDetails";
    }


    //--------------------------------------------------------------------------------------------------------------

    private User getCurrentUser () {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails principalDetails = (PrincipalDetails) auth.getPrincipal();

        return principalDetails.getUser();
    }

    private Company getCurrentUserCompanyById (long id) {

        Optional<Company> companyOptional = companyRepository.findById(id);

        Company company = companyOptional.stream()
                .findAny()
                .orElseThrow(NoSuchElementException::new);

        return company;
    }

}
