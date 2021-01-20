package pl.lymek.renovationApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "PODAJ NAZWĘ FIRMY")
    private String name;

    @ManyToOne
    private pl.lymek.renovationApp.model.Address address;

    private String email;
    private String phoneNumber;

    @OneToMany
    private List<Employee> employees;

    @OneToOne
    private User owner;

    @OneToMany(mappedBy = "company")
    private List <Commission> commissions;

    @ManyToOne
    private Address addresses;

//------------------------------------------------------------------------------------------------

    public Company() {
    }

    public Company(@NotNull(message = "PODAJ NAZWĘ FIRMY") String name) {
        this.name = name;
    }

    //--------------------------------------------------------------------------------------------------

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Commission> getCommissions() {
        return commissions;
    }

    public void setCommissions(List<Commission> commissions) {
        this.commissions = commissions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
