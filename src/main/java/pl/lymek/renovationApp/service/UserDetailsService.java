package pl.lymek.renovationApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.lymek.renovationApp.model.User;
import pl.lymek.renovationApp.repository.UserRepository;
import pl.lymek.renovationApp.security.CommonUser;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);
        if(user==null) {

            throw new UsernameNotFoundException("User Not Found");
        }

        return new CommonUser(user);
    }
}
