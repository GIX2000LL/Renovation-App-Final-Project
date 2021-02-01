package pl.lymek.renovationApp.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
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

    private double hourlyRate;

//    @ManyToMany(mappedBy = "employees")
//    private List<Commission> commissions;

//---------------------------------------------------------------------------------------------------

    public Employee() {
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

//    public List<Commission> getCommissions() {
//        return commissions;
//    }
//
//    public void setCommissions(List<Commission> commissions) {
//        this.commissions = commissions;
//    }




}
