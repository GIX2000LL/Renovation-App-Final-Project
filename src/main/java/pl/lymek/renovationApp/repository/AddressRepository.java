package pl.lymek.renovationApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lymek.renovationApp.model.Address;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address,Long> {


}
