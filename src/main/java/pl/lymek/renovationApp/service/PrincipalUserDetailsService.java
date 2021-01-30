package pl.lymek.renovationApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.lymek.renovationApp.model.User;
import pl.lymek.renovationApp.repository.UserRepository;
import pl.lymek.renovationApp.security.PrincipalDetails;

import java.util.Optional;

@Service
public class PrincipalUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> user=userRepository.findByEmail(email);

        user.orElseThrow(()-> new UsernameNotFoundException("USER"+email+ "NOT FOUND"));

        return user.map(PrincipalDetails::new).get();


    }
}
