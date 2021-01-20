package pl.lymek.renovationApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lymek.renovationApp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail (String email);


}
