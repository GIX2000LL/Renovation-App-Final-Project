package pl.lymek.renovationApp.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.lymek.renovationApp.model.User;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PrincipalDetails implements UserDetails {

    private String email;
    private String password;
    private boolean isActive;
    private String securityRole;
    private List<GrantedAuthority> authorities;
    private User user;


    public PrincipalDetails(User user) {

        this.user=user;
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.isActive = user.isActive();
        this.securityRole=user.getSecurityRole();
        this.authorities = Arrays.stream(user.getSecurityRole().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;

    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
