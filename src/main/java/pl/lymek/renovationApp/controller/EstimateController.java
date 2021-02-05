package pl.lymek.renovationApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.lymek.renovationApp.components.CurrentCommission;
import pl.lymek.renovationApp.model.Commission;
import pl.lymek.renovationApp.model.Employee;
import pl.lymek.renovationApp.model.Estimate;
import pl.lymek.renovationApp.repository.CommissionRepository;
import pl.lymek.renovationApp.repository.EstimateRepository;

import javax.validation.Valid;
import java.util.Set;


@RequestMapping("/estimates")
@Controller
public class EstimateController {

    private final EstimateRepository estimateRepository;
    private final CurrentCommission currentCommission;
    private final CommissionRepository commissionRepository;

    public EstimateController(EstimateRepository estimateRepository, CurrentCommission currentCommission,
                              CommissionRepository commissionRepository) {
        this.estimateRepository = estimateRepository;
        this.currentCommission = currentCommission;
        this.commissionRepository = commissionRepository;
    }


    //--------------------------------------------------------------------------------------------------

    @GetMapping("/add/{commissionId}")
    public String showEstimateForm (@PathVariable long commissionId, Model model) {

        Estimate estimate = new Estimate();

        Commission commission = currentCommission.findCommissionById(commissionId);

        Set<Employee> employees = commission.getEmployees();

        double workersCost = employees.stream()
                .mapToDouble(worker->worker.getHourlyRate()*8)
                .sum();

        estimate.setWorkersCost(workersCost);

        estimate.setCommission(commission);

        model.addAttribute("estimate",estimate);

        return "estimate";
    }

    @PostMapping("/add/{commissionId}")
    public String createEstimate (@PathVariable long commissionId,
                                   @ModelAttribute("estimate") @Valid Estimate estimate, BindingResult result) {

        if(result.hasErrors()) {

            return "estimate";
        } else {

            double materialsCost = estimate.getMaterialsCost();
            double workersCost = estimate.getWorkersCost();
            double totalPrice = estimate.getTotalPrice();
            double profit = totalPrice -(materialsCost+workersCost);
            estimate.setProfit(profit);

            estimateRepository.save(estimate);


            return "redirect:/commissions";
        }


    }

}
