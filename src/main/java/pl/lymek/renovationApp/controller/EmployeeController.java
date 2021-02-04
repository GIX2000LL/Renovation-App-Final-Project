package pl.lymek.renovationApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.lymek.renovationApp.model.Address;
import pl.lymek.renovationApp.model.Employee;
import pl.lymek.renovationApp.model.Skill;
import pl.lymek.renovationApp.model.User;
import pl.lymek.renovationApp.repository.AddressRepository;
import pl.lymek.renovationApp.repository.EmployeeRepository;
import pl.lymek.renovationApp.repository.SkillRepository;
import pl.lymek.renovationApp.security.PrincipalDetails;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final Logger logger = LoggerFactory.getLogger("logger");

    private final EmployeeRepository employeeRepository;
    private final SkillRepository skillRepository;
    private  final AddressRepository addressRepository;

    public EmployeeController(EmployeeRepository employeeRepository, SkillRepository skillRepository, AddressRepository addressRepository) {
        this.employeeRepository = employeeRepository;
        this.skillRepository = skillRepository;
        this.addressRepository = addressRepository;
    }


    //------------------------------------------------------------------------------------------------

    @GetMapping
    public String showAllEmployees (@AuthenticationPrincipal PrincipalDetails principal,Model model) {

        User user = principal.getUser();

        if(user.getCompany().getEmployees().isEmpty()) {
            return "noEmployees";
        } else {

            model.addAttribute("employees",user.getCompany().getEmployees());
        }

        return "employees";
    }

    @GetMapping("/add")
    public String showNewEmployeeForm (Model model) {

        model.addAttribute("employee",new Employee());
        return "employeeForm";
    }

    @PostMapping("/add")
    public String createNewEmployeeFromForm (@AuthenticationPrincipal PrincipalDetails principal,
                                     @ModelAttribute("employee") @Valid Employee newEmployee, BindingResult result) {

        if(result.hasErrors()) {
            return "employeeForm";
        } else {

            User currentUser=principal.getUser();
            newEmployee.setCompany(currentUser.getCompany());
            employeeRepository.save(newEmployee);

            return "redirect:/employees";
        }
    }

    @GetMapping("/details/{id}")
    public String showEmployeeDetails (@PathVariable long id, Model model) {

        Employee employee = getEmployee(id);

        model.addAttribute("employee",employee);

        if(employee.getAddress()!=null) {
            model.addAttribute("address",getEmployee(id).getAddress());
        }

        return "employeeDetails";
    }

    @GetMapping("/edit/{id}")
    public String showEmployeeEditForm (@PathVariable long id,Model model) {

        Employee employee =getEmployee(id);
        model.addAttribute("employee",employee);

        return "employeeEdit";
    }

    @PostMapping("/edit/{id}")
    public String editEmployee (@ModelAttribute @Valid Employee employee, BindingResult result) {

        if(result.hasErrors()) {

            return "employeeEdit";
        } else {

            employeeRepository.save(employee);
            return "redirect:/employees";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee (@PathVariable long id, Model model) {

        Employee employee = getEmployee(id);
        model.addAttribute("employeeId",employee.getId());
//        if(employee.getCommission.isEmpty()) {}
        return "employeeDelete";
//        else
//        return "employeeHasCommision";
    }

    @PostMapping("/delete/{id}")
    public String deleteEmployeeAfterConfirmation (@PathVariable long id) {

        employeeRepository.delete(getEmployee(id));

        return "redirect:/employees";
    }

    @GetMapping("/addressEdition/{id}")
    public String showEmployeeAddressForm (@PathVariable long id, Model model) {

        Employee employee = getEmployee(id);

        if(employee.getAddress()==null) {
            model.addAttribute("address",new Address());
        } else {

            model.addAttribute("address",employee.getAddress());
        }

        model.addAttribute("employeeId",id);

        return "employeeAddress";
    }

    @PostMapping("/addressEdition/{id}")
    public String createEmployeeAddressFromForm (@PathVariable long id,@ModelAttribute("address") @Valid Address address,
                                                 BindingResult result) {

        if(result.hasErrors()) {

            return "employeeAddress";
        } else {

            addressRepository.save(address);
            Employee employee = getEmployee(id);
            employee.setAddress(address);
            employeeRepository.save(employee);

            return "redirect:/employees";
        }

    }

//-----------------------------------------------------------------------------------------------------------

    @ModelAttribute("skills")
    public List<Skill> putSkillsList () {
        List <Skill> skills =new ArrayList<>();
        skills.add(new Skill(1,"Malarz")); skills.add(new Skill(2,"Tynkarz")); skills.add(new Skill(3,"Operator koparki"));
        skills.add(new Skill(4,"Betoniarz")); skills.add(new Skill(5,"Zbrojarz")); skills.add(new Skill(6,"Brukarz"));
        skills.add(new Skill(7,"Cieśla")); skills.add(new Skill(8,"Dekarz")); skills.add(new Skill(9,"Elektryk"));
        skills.add(new Skill(10,"Hydraulik")); skills.add(new Skill(11,"Murarz")); skills.add(new Skill(12,"Operator dźwigu"));
        skills.add(new Skill(13,"Pracownik robót wykończeniowych")); skills.add(new Skill(14,"Glazurnik"));

        skills.stream()
                .filter(skill -> skill.getId()<14)
                .forEach(skillRepository::save);

        return skills;
        }


    private Employee getEmployee (long id) {

        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        Employee employee =employeeOptional.stream()
                .findAny()
                .orElseThrow(NoSuchElementException::new);

        return employee;
    }

}
