package pl.lymek.renovationApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lymek.renovationApp.model.Company;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
