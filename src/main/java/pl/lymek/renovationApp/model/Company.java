package pl.lymek.renovationApp.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min=2,message = "NAZWA MUSI MIEĆ CONAJMNIEJ 2 ZNAKI")
    @NotNull(message = "PODAJ NAZWĘ FIRMY")
    private String name;

    @ManyToOne
    private Address address;

    private String email;

    @Pattern(regexp = "\\d{9}", message = "NUMER TELEFONU SKŁĄDA SIĘ Z 9 CYFR")
    private String phoneNumber;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "company")
    private List<Employee> employees;

    @OneToOne (fetch = FetchType.EAGER)
    private User owner;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "company")
    private List <Commission> commissions;

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

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
}
