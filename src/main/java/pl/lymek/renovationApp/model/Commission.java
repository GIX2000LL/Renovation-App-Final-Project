package pl.lymek.renovationApp.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "commissions")
public class Commission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String principal;
    private LocalDate commissionStart;
    private LocalDate commissionEnd;

    @OneToOne
    private Estimate estimate;

    @OneToOne
    private Schedule schedule;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "commissions_employees",joinColumns = @JoinColumn(name = "commission_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> employees;

    @ManyToOne
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

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
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

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Commission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", principal='" + principal + '\'' +
                ", commissionStart=" + commissionStart +
                ", commissionEnd=" + commissionEnd +
                ", estimate=" + estimate +
                ", schedule=" + schedule +
                ", employees=" + employees +
                ", company=" + company +
                '}';
    }
}
