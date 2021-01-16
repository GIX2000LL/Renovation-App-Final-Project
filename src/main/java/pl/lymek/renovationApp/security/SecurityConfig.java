package pl.lymek.renovationApp.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure (HttpSecurity security) throws Exception {

        security.authorizeRequests()
                .antMatchers("/","/registration")
                .permitAll();
    }
}
