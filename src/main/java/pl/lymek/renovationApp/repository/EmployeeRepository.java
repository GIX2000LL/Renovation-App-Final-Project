package pl.lymek.renovationApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lymek.renovationApp.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
