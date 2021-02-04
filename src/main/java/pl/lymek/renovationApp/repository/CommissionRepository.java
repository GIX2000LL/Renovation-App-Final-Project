package pl.lymek.renovationApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lymek.renovationApp.model.Commission;

public interface CommissionRepository extends JpaRepository <Commission,Long> {

}
