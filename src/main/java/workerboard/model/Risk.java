package workerboard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import workerboard.config.LocalDateDeserializer;
import workerboard.config.LocalDateSerializer;

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
    private String displayName;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate startDate = LocalDate.now().plusDays(1);
    @JsonDeserialize(using = workerboard.config.LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate endDate = LocalDate.now().plusDays(365);
    @JsonIgnore
    private boolean addedToCart;

    public Risk() {
    }


    public Risk(String name, double sum, Premium premiumList, LocalDate startDate, LocalDate endDate, String displayName) {
        this.name = name;
        this.sum = sum;
        this.premiumList = premiumList;
        this.startDate = startDate;
        this.endDate = endDate;
        this.displayName = displayName;
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

    public String getDisplayName() {
        return displayName;
    }

    public boolean isAddedToCart() {
        return addedToCart;
    }
}
