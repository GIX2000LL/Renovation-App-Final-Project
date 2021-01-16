package pl.lymek.renovationApp.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

//@Configuration
public class SecurityConfigNauka extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService () {

        //tworzenie użytkownika prostego
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user1")
                .roles("USER")
                .build();
// użytkownik ADMIN
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin1")
                .roles("ADMIN")
                .build();

        //zapis użytkownika w pamięci
        return new InMemoryUserDetailsManager(user, admin);
    }

    //metoda będzie filtrować rządania i sprawdzac czy user ma uprawnienia odpowiednie.
    //najpierw zostaje wywowłana ta metoda przed obsługą żądania
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //antMatcher bieże kawałek url i sprawdza
                .antMatchers("/").permitAll()
                .anyRequest().hasAnyRole("ADMIN")
                .and()
                //możliwość logowania kiedy wymagany jest dostęp w tym przypadku rola ADMINA
                .formLogin().permitAll()
                //możliwość wylogowania z aplikacji
                .and()
                .logout().permitAll();
    }
}
