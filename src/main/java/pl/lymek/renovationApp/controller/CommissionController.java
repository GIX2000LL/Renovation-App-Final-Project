package pl.lymek.renovationApp.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.lymek.renovationApp.components.CurrentCommission;
import pl.lymek.renovationApp.components.CurrentUser;
import pl.lymek.renovationApp.model.Commission;
import pl.lymek.renovationApp.model.Company;
import pl.lymek.renovationApp.model.Employee;
import pl.lymek.renovationApp.model.Estimate;
import pl.lymek.renovationApp.repository.CommissionRepository;
import pl.lymek.renovationApp.repository.EstimateRepository;
import pl.lymek.renovationApp.security.PrincipalDetails;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/commissions")
public class CommissionController {


    private final CurrentUser currentUser;
    private final CommissionRepository commissionRepository;
    private final CurrentCommission currentCommission;
    private final EstimateRepository estimateRepository;

    public CommissionController(CurrentUser currentUser, CommissionRepository commissionRepository, CurrentCommission currentCommission, EstimateRepository estimateRepository) {
        this.currentUser = currentUser;
        this.commissionRepository = commissionRepository;
        this.currentCommission = currentCommission;
        this.estimateRepository = estimateRepository;
    }

    //--------------------------------------------------------------------------------------------------------------------------

    @GetMapping
    public String showAllCommission (@AuthenticationPrincipal PrincipalDetails principal, Model model) {

        Company company = principal.getUser().getCompany();
        if(company.getEmployees().isEmpty()) {

            return "noEmployeesToCommission";
        } else {

            List<Commission> commissions = company.getCommissions();
            Collections.sort(commissions);
            model.addAttribute("commissions", commissions);

        }
        return "commissions";

    }

    @GetMapping("/add")
    public String showCommissionForm (Model model) {

        model.addAttribute("commission",new Commission());

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

        model.addAttribute("commission",currentCommission.findCommissionById(id));

        return "commissionDetails";
    }

    @GetMapping("/addEmployees/{commissionId}")
    public String addEmployeesIntoCommission (@PathVariable long commissionId, @RequestParam long [] employee) {

        Commission commission = currentCommission.findCommissionById (commissionId);
        Set <Employee> commissionEmployees = commission.getEmployees();
        for (long e:employee) {
            Employee employee1 = currentCommission.findEmployeeById(e);
            commissionEmployees.add(employee1);
        }
        commission.setEmployees(commissionEmployees);

        changeEstimateIfEmployeesNumberWillChange(commission,commissionEmployees);

        commissionRepository.save(commission);

        return "redirect:/commissions";

    }

    @GetMapping ("/deleteEmployeeFromCommission/{employeeId}/{commissionId}")
    public String removeEmployeeFromCommission (@PathVariable long commissionId,@PathVariable long employeeId) {

        Commission commission = currentCommission.findCommissionById(commissionId);
        Set<Employee> employees = commission.getEmployees();
        employees.remove(currentCommission.findEmployeeById(employeeId));
        commission.setEmployees(employees);

        changeEstimateIfEmployeesNumberWillChange(commission,employees);

        commissionRepository.save(commission);

        return "redirect:/commissions";
    }

//----------------------------------------------------------------------------------------------------------------

    @ModelAttribute("currentCompanyEmployees")
    protected List <Employee> getAllEmployeesFromCurrentCompany () {

        Long id =currentUser.getCurrentUser().getCompany().getId();

        return currentUser.getCurrentUserCompanyById(id).getEmployees();
    }


    public void changeEstimateIfEmployeesNumberWillChange (Commission commission, Set<Employee> employees) {

        if (commission.getEstimate() != null) {

            Estimate estimate = commission.getEstimate();
            double workersCost = employees.stream()
                    .mapToDouble(worker -> worker.getHourlyRate() * 8)
                    .sum();

            estimate.setWorkersCost(workersCost);
            commission.setEstimate(estimate);
            estimateRepository.save(estimate);
        }

    }

}
