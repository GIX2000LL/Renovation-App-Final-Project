package pl.lymek.renovationApp.model;

import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size (min = 2, message = "IMIĘ MUSI SIĘ SKŁADAC Z MINIMUM 2 ZNAKÓW")
    private String firstName;

    @Size (min = 2, message = "NAZWISKO MUSI SIĘ SKŁADAC Z MINIMUM 2 ZNAKÓW")
    private String lastName;

    @OneToOne
    private Address address;

    @NumberFormat
    private double hourlyRate;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "employees_skills",joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private List <Skill> skills=new ArrayList<>();

    @ManyToOne
    private Company company;

    @ManyToMany(mappedBy = "employees")
    private List<Commission> commissions;

//---------------------------------------------------------------------------------------------------

    public Employee() {
    }

    public Employee(@Size(min = 2, message = "IMIĘ MUSI SIĘ SKŁADAC Z MINIMUM 2 ZNAKÓW") String firstName,
                    @Size(min = 2, message = "NAZWISKO MUSI SIĘ SKŁADAC Z MINIMUM 2 ZNAKÓW") String lastName, double hourlyRate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hourlyRate = hourlyRate;
    }

    //----------------------------------------------------------------------------------------------------

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }


    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Commission> getCommissions() {
        return commissions;
    }

    public void setCommissions(List<Commission> commissions) {
        this.commissions = commissions;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address=" + address +
                ", hourlyRate=" + hourlyRate +
                ", skills=" + skills +
                ", company=" + company +
                ", commissions=" + commissions +
                '}';
    }
}
