package pl.lymek.renovationApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.lymek.renovationApp.components.CurrentUser;
import pl.lymek.renovationApp.model.Address;
import pl.lymek.renovationApp.model.Commission;
import pl.lymek.renovationApp.model.Company;
import pl.lymek.renovationApp.model.User;
import pl.lymek.renovationApp.repository.AddressRepository;
import pl.lymek.renovationApp.repository.CompanyRepository;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RequestMapping("/company")
@Controller
public class CompanyController {

    private Logger logger = LoggerFactory.getLogger("logger");

    private final CompanyRepository companyRepository;
    private final AddressRepository addressRepository;
    private final CurrentUser currentUser1;

    public CompanyController(CompanyRepository companyRepository, AddressRepository addressRepository, CurrentUser currentUser1) {
        this.companyRepository = companyRepository;
        this.addressRepository = addressRepository;
        this.currentUser1 = currentUser1;
    }

    //------------------------------------------------------------------------------------------------------

    @GetMapping
    public String showCompanyPage(Model model) {

        List<Commission> commissions =currentUser1.getCurrentUser().getCompany().getCommissions();
        if(!commissions.isEmpty()) {
            Collections.sort(commissions);
            model.addAttribute("primeComplete",commissions.get(0));
        }

        return "company";
    }

    @GetMapping("/companyDetails")
    public String showCompanyDetails(Model model) {

        User currentUser = currentUser1.getCurrentUser();

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

        Company company = currentUser1.getCurrentUserCompanyById(id);

        model.addAttribute("companyToEdit", company);

        return "companyForm";
    }


    @PostMapping("/edit/{id}")
    public String getEditedUserFromForm (@ModelAttribute("companyToEdit") @Valid Company companyAfterEdition, BindingResult result) {


        if (result.hasErrors()) {

            return "companyForm";
        } else {

            companyRepository.save(companyAfterEdition);

            Address address =companyAfterEdition.getAddress();
            if (address==null) {

                return "companyAddressAnnotation";
            }

            return "redirect:/company/companyDetails";
        }
    }

    @GetMapping ("/companyAddressEdit/{id}")
    public  String showCompanyAddressForm (@PathVariable long id, Model model) {

        User user = currentUser1.getCurrentUser();
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

            addressRepository.save(addressAfterEdition);
            Company company = currentUser1.getCurrentUserCompanyById(id);
            company.setAddress(addressAfterEdition);
            companyRepository.save(company);
        }

        return "redirect:/company/companyDetails";
    }

}
