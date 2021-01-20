package pl.lymek.renovationApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pl.lymek.renovationApp.repository.UserRepository;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    private UserRepository userRepository;
//
//    @Autowired
//    public SecurityConfig(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }



    @Override
    protected  void configure (AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser("owner")
                .password("owner")
                .roles("OWNER")
                .and()
                .withUser("manager")
                .password("manager")
                .roles("MANAGER");
    }

    @Bean
    public PasswordEncoder passwordEncoder (){

        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {

        security.authorizeRequests()
                .antMatchers("/registration").hasAnyRole("MANAGER")
                .antMatchers("/").permitAll()
                .and().formLogin();

//        security
//                .authorizeRequests()
//                .antMatchers("/", "/registration","/login")
//                .permitAll()
//                .anyRequest().authenticated()

//                .and()
//                .authorizeRequests()
//                .antMatchers("/user")
//                .hasAnyRole("owner","superadmin")

//                .and()
//                .csrf().disable()  //wylaczenie automatu
//                .formLogin() //wlaczenie formularza
//                .loginPage("/login").permitAll() //ustawienie strony logowania

//                .and()
//                .logout()
//                .invalidateHttpSession(true) //ustawienia wylogowania
//                .clearAuthentication(true) //wyczyscic autentyfikacje po wylogowaniu
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))//ustawienie adresu kontrolera wylogowania
//                .logoutSuccessUrl("/logout-success").permitAll(); // gdzie przekierować po wylogowaniu

    }

//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Bean
//    public AuthenticationProvider authProvider () {
//
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userDetailsService);
//        provider.setPasswordEncoder(new BCryptPasswordEncoder());
//        return provider;
//    }

}
