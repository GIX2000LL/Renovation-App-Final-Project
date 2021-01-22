package pl.lymek.renovationApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lymek.renovationApp.model.Company;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
