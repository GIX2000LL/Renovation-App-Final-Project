package pl.lymek.renovationApp.components;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.lymek.renovationApp.model.Company;
import pl.lymek.renovationApp.model.User;
import pl.lymek.renovationApp.repository.CompanyRepository;
import pl.lymek.renovationApp.repository.UserRepository;
import pl.lymek.renovationApp.security.PrincipalDetails;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class CurrentUser {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    public CurrentUser(UserRepository userRepository, CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }


    public User getCurrentUser () {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails principalDetails = (PrincipalDetails) auth.getPrincipal();

        return principalDetails.getUser();
    }

    public User getCurrentUserById (long id) {

        Optional<User> userOptional=userRepository.findById(id);

        User user = userOptional.stream()
                .findAny()
                .orElseThrow(NoSuchElementException::new);

        return user;
    }

    public Company getCurrentUserCompanyById (long id) {

        Optional<Company> companyOptional = companyRepository.findById(id);

        Company company = companyOptional.stream()
                .findAny()
                .orElseThrow(NoSuchElementException::new);

        return company;
    }
}
