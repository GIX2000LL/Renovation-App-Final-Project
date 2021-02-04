package pl.lymek.renovationApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.lymek.renovationApp.components.CurrentUser;
import pl.lymek.renovationApp.model.Commission;
import pl.lymek.renovationApp.model.Company;
import pl.lymek.renovationApp.model.Employee;
import pl.lymek.renovationApp.model.Estimate;
import pl.lymek.renovationApp.repository.CommissionRepository;
import pl.lymek.renovationApp.repository.CompanyRepository;
import pl.lymek.renovationApp.repository.EmployeeRepository;
import pl.lymek.renovationApp.security.PrincipalDetails;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/commissions")
public class CommissionController {


    private final EmployeeRepository employeeRepository;
    private final CurrentUser currentUser;
    private final CommissionRepository commissionRepository;
    private final CompanyRepository companyRepository;

    public CommissionController(EmployeeRepository employeeRepository, CurrentUser currentUser, CommissionRepository commissionRepository, CompanyRepository companyRepository) {
        this.employeeRepository = employeeRepository;
        this.currentUser = currentUser;
        this.commissionRepository = commissionRepository;
        this.companyRepository = companyRepository;
    }

    @GetMapping
    public String showAllCommission (@AuthenticationPrincipal PrincipalDetails principal, Model model) {

        Company company = principal.getUser().getCompany();
        if(company.getEmployees().isEmpty()) {

            return "noEmployeesToCommission";
        } else {
            model.addAttribute("commissions", company.getCommissions());

        }
        return "commissions";

    }

    @GetMapping("/add")
    public String showCommissionForm (Model model) {

        model.addAttribute("commission",new Commission());
        model.addAttribute("companyId",currentUser.getCurrentUser().getCompany().getId());

        return "commissionForm";
    }

    @PostMapping("/add")
    public String createNewCommissionFromForm (@AuthenticationPrincipal PrincipalDetails principal,
                                               @ModelAttribute("commission") @Valid Commission commission, BindingResult result) {

        if (result.hasErrors()) {

            return "commissionForm";

        } else {

            Company company =principal.getUser().getCompany();
            commission.setCompany(company);
            Set<Employee> commissionEmployees = new HashSet<>();
            commissionEmployees.add(commission.getLeader());
            commission.setEmployees(commissionEmployees);
            commissionRepository.save(commission);

            return "redirect:/company";
        }
    }

    @GetMapping("/details/{id}")
    public String showCommissionDetails(@PathVariable long id, Model model) {

        model.addAttribute("commission",findCommissionById(id));

        return "commissionDetails";
    }

    @GetMapping("/addEmployees/{commissionId}")
    public String addEmployeesIntoCommission (@PathVariable long commissionId, @RequestParam long [] employee) {

        Commission commission = findCommissionById (commissionId);
        Set <Employee> commissionEmployees = commission.getEmployees();

        for (long e:employee) {
            Employee employee1 = findEmployeeById(e);
            commissionEmployees.add(employee1);
        }

        commission.setEmployees(commissionEmployees);
        commissionRepository.save(commission);

        return "redirect:/commissions";

    }

    @GetMapping ("/deleteEmployeeFromCommission/{employeeId}/{commissionId}")
    public String removeEmployeeFromCommission (@PathVariable long commissionId,@PathVariable long employeeId) {

        Commission commission = findCommissionById(commissionId);
        Set<Employee> employees = commission.getEmployees();
        employees.remove(findEmployeeById(employeeId));
        commission.setEmployees(employees);
        commissionRepository.save(commission);

        return "redirect:/commissions";

    }

    @GetMapping("/addEstimate/{commissionId}")
    public String showEstimateForm (@PathVariable long commissionId, Model model) {

        Estimate estimate = new Estimate();
        estimate.setCommission(findCommissionById(commissionId));

        model.addAttribute("estimate",estimate);

        return "estimate";
    }





//----------------------------------------------------------------------------------------------------------------

    @ModelAttribute("currentCompanyEmployees")
    protected List <Employee> getAllEmployeesFromCurrentCompany () {

        Long id =currentUser.getCurrentUser().getCompany().getId();

        return currentUser.getCurrentUserCompanyById(id).getEmployees();
    }

    public Commission findCommissionById (long id) {

        Optional<Commission> commissionOptional = commissionRepository.findById(id);

        Commission commission = commissionOptional.stream()
                .findAny()
                .orElseThrow(()->new NoSuchElementException("NIE ZNALEZIONO ZLECENIA"));

        return commission;
    }

    public Employee findEmployeeById (long id) {

        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        Employee employee = optionalEmployee.stream()
                .findAny()
                .orElseThrow(()->new NoSuchElementException("NIE ZNALEZIONO PRACOWNIKA"));

        return employee;
    }

}
