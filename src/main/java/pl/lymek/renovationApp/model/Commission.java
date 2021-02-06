package pl.lymek.renovationApp.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "commissions")
public class Commission implements Comparable<Commission> {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 1,message = "NAZWA MUSI MIEĆ CONAJMNIEJ 1 ZNAK")
    private String name;

    @NotNull(message = "PROSZE WYBRAĆ KIEROWNIKA ZLECENIA")
    @OneToOne
    private Employee leader;

    @NotNull(message = "WYBIERZ DATĘ ROZPOCZĘCIA ZLECENIA")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate commissionStart;

    @NotNull(message = "WYNIERZ DATĘ ZAKOŃCZENIA ZLECENIA")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Future(message = "DATA ZAKOŃCZENIA MUSI BYĆ W PRZYSZŁOŚCI")
    private LocalDate commissionEnd;

    @OneToOne(mappedBy = "commission")
    private Estimate estimate;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "commissions_employees",joinColumns = @JoinColumn(name = "commission_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private Set<Employee> employees;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Company company;

//----------------------------------------------------------------------------------------------------------

    public Commission() {
    }

//-----------------------------------------------------------------------------------------------------------


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

    public Employee getLeader() {
        return leader;
    }

    public void setLeader(Employee leader) {
        this.leader = leader;
    }

    public LocalDate getCommissionStart() {
        return commissionStart;
    }

    public void setCommissionStart(LocalDate commissionStart) {
        this.commissionStart = commissionStart;
    }

    public LocalDate getCommissionEnd() {
        return commissionEnd;
    }

    public void setCommissionEnd(LocalDate commissionEnd) {
        this.commissionEnd = commissionEnd;
    }

    public Estimate getEstimate() {
        return estimate;
    }

    public void setEstimate(Estimate estimate) {
        this.estimate = estimate;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public int compareTo(Commission o) {
        return getCommissionEnd().compareTo(o.getCommissionEnd());
    }

    @Override
    public String toString() {
        return "Commission{" +
                "id=" + id +
                ", estimate=" + estimate +
                '}';
    }
}
