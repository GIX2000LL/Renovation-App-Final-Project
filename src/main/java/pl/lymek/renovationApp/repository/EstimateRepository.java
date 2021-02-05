package pl.lymek.renovationApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lymek.renovationApp.model.Estimate;

public interface EstimateRepository extends JpaRepository <Estimate,Long> {
}
