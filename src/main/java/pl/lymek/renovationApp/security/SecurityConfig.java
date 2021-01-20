package pl.lymek.renovationApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;
import pl.lymek.renovationApp.repository.UserRepository;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserRepository userRepository;

    @Autowired
    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {

        security
                .csrf().disable()  //wylaczenie automatu
                .formLogin() //wlaczenie formularza
                .loginPage("/login").permitAll() //ustawienie strony logowania
                .and()
                .logout().invalidateHttpSession(true) //ustawienia wylogowania
                .clearAuthentication(true) //wyczyscic autentyfikacje po wylogowaniu
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))//ustawienie adresu kontrolera wylogowania
                .logoutSuccessUrl("/") // gdzie przekierować po wylogowaniu
                .and()
                .authorizeRequests()
                .antMatchers("/", "/registration")
                .permitAll();
//                .and()
//                .formLogin().permitAll();

    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authProvider () {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }



//----------------------------------------KONFIGURACJA DLA USERNAME-------------------------------------------------
//    @Autowired
//    DataSource dataSource;
//
//
//    @Override
//    protected  void configure (AuthenticationManagerBuilder builder) throws Exception {
//        builder.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder());
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//        return encoder;
//    }







//-----------------------------------------------------NAUKA------------------------------------------------------
//    @Override
//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        List<UserDetails> users = new ArrayList<>();
//
//        List<User> usersFromDB = userRepository.findAll();
//
//        for (User u : usersFromDB) {
//            users.add(org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
//                    .username(u.getEmail()).password(u.getPassword()).roles(u.getSecurityRole()).build());
//        }
//        return new InMemoryUserDetailsManager(users);
//    }
}
