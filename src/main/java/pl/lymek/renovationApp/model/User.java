package pl.lymek.renovationApp.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size (min = 2, message = "IMIĘ MUSI SIĘ SKŁADAC Z MINIMUM 2 ZNAKÓW")
    private String firstName;

    @Size (min = 2, message = "NAZWISKO MUSI SIĘ SKŁADAĆ Z MINIMUM 2 ZNAKÓW")
    private String lastName;

    private String password;

    private boolean isActive;

    @Pattern(regexp = "\\d{9}",message = "TELEFON MUSI SKŁADAĆ Z CYFR I MAKSYMALNIE 9 ZNAKÓW")
    private String phoneNumber;

    private String email;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name ="users_addresses",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "address_id"))
    private List <Address> addresses = new ArrayList<>();

    @OneToOne
    private Company company;

    private String securityRole;


// -----------------------------------------------------------------------------------------------------

    public User() {

    }

//------------------------------------------------------------------------------------------------------


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getSecurityRole() {
        return securityRole;
    }

    public void setSecurityRole(String securityRole) {
        this.securityRole = securityRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + isActive +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", addresses=" + addresses +
                ", company=" + company +
                ", securityRole='" + securityRole + '\'' +
                '}';
    }
}
