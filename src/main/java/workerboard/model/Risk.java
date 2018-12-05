package workerboard.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Risk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double sum;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "premiumList_id")
    private Premium premiumList;
    private LocalDate startDate = LocalDate.now().plusDays(1);
    private LocalDate endDate = LocalDate.now().plusDays(365);

    public Risk() {
    }


    public Risk(String name, double sum, Premium premiumList, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.sum = sum;
        this.premiumList = premiumList;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
