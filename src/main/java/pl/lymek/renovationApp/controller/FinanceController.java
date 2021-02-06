package pl.lymek.renovationApp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.lymek.renovationApp.components.CurrentUser;
import pl.lymek.renovationApp.model.Commission;
import pl.lymek.renovationApp.model.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/finance")
public class FinanceController {

    private final CurrentUser currentUser;

    public FinanceController(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @GetMapping
    public String showMainFinance (Model model) {

        model.addAttribute("employeesInMonth",getEmployeesWitMonthlyWage());
        model.addAttribute("currentMonth", LocalDate.now().getMonth());
        model.addAttribute("monthForAllEmployees",totalAmountForEmployees(getEmployeesWitMonthlyWage()));
        model.addAttribute("totalPrice",totalPriceForAllCommission());
        model.addAttribute("totalMaterialsCost",totalMaterialsCost());
        model.addAttribute("totalWorkersCost",totalWorkersCost());
        model.addAttribute("totalProfit",totalProfitFromActiveCommissions());

        return "finance";
    }


//--------------------------------------------------------------------

    public List<Employee> getEmployeesWitMonthlyWage () {

        List <Employee> employees = currentUser.getCurrentUser().getCompany().getEmployees();

        // 168 - średnia liczba godzin w miesiącu

        return employees.stream()
                .map(employee -> new Employee(employee.getFirstName(),employee.getLastName(),employee.getHourlyRate()*168))
                .collect(Collectors.toList());
    }

    public double totalAmountForEmployees (List <Employee> employees) {

       return employees.stream()
                .mapToDouble(Employee::getHourlyRate)
                .sum();
    }

    public double totalPriceForAllCommission () {
        List <Commission> commissions = currentUser.getCurrentUser().getCompany().getCommissions();

        return commissions.stream()
                .filter(commission -> commission.getEstimate()!=null)
                .mapToDouble(commission->commission.getEstimate().getTotalPrice())
                .sum();
    }

    public double totalMaterialsCost () {
        List <Commission> commissions = currentUser.getCurrentUser().getCompany().getCommissions();

        return commissions.stream()
                .filter(commission -> commission.getEstimate()!=null)
                .mapToDouble(commission->commission.getEstimate().getMaterialsCost())
                .sum();
    }

    public double totalWorkersCost () {
        List <Commission> commissions = currentUser.getCurrentUser().getCompany().getCommissions();

        return commissions.stream()
                .filter(commission -> commission.getEstimate()!=null)
                .mapToDouble(commission->commission.getEstimate().getWorkersCost())
                .sum();
    }

    public double totalProfitFromActiveCommissions () {
        List <Commission> commissions = currentUser.getCurrentUser().getCompany().getCommissions();

        return commissions.stream()
                .filter(commission -> commission.getEstimate()!=null)
                .mapToDouble(commission->commission.getEstimate().getProfit())
                .sum();

    }

}
