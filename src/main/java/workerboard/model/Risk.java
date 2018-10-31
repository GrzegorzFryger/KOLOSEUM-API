package workerboard.model;

import javax.persistence.*;

@Entity
public class Risk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double sum;
    @OneToOne
    @JoinColumn(name = "premiumList_id")
    private Premium premiumList;

    public Risk() {
    }


    public Risk(String name, double sum, Premium premiumList) {
        this.name = name;
        this.sum = sum;
        this.premiumList = premiumList;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSum() {
        return sum;
    }

    public Premium getPremiumList() {
        return premiumList;
    }
}
