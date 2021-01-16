package pl.lymek.renovationApp.model;

import java.time.LocalDate;
import java.util.List;

public class Commission {

    private long id;
    private String principal;
    private LocalDate commissionStart;
    private LocalDate commissionEnd;
    private Estimate estimate;
    private Schedule schedule;
    private List<Employee> employees;

    public Commission() {
    }

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
}
