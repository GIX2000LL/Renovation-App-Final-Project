package pl.lymek.renovationApp.components;

import org.springframework.stereotype.Component;
import pl.lymek.renovationApp.model.Commission;
import pl.lymek.renovationApp.model.Employee;
import pl.lymek.renovationApp.repository.CommissionRepository;
import pl.lymek.renovationApp.repository.EmployeeRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class CurrentCommission {

    private final CommissionRepository commissionRepository;
    private  final EmployeeRepository employeeRepository;

    public CurrentCommission(CommissionRepository commissionRepository, EmployeeRepository employeeRepository) {
        this.commissionRepository = commissionRepository;
        this.employeeRepository = employeeRepository;
    }

//---------------------------------------------------------------------------------------------------------------------

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
