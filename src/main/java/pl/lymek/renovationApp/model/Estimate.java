package pl.lymek.renovationApp.model;

import javax.persistence.*;

@Entity
@Table(name = "estimates")
public class Estimate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Commission commission;

    private double costs;

    private double totalPrice;

//-------------------------------------------------------------------------

    public Estimate() {
    }
//---------------------------------------------------------------------------


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Commission getCommission() {
        return commission;
    }

    public void setCommission(Commission commission) {
        this.commission = commission;
    }

    public double getCosts() {
        return costs;
    }

    public void setCosts(double costs) {
        this.costs = costs;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
