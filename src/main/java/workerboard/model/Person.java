package workerboard.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import workerboard.config.LocalDateDeserializer;
import workerboard.config.LocalDateSerializer;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "adress_id")
    private Adress address = new Adress();
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dayOfBirth;
    private String pesel;
    private String phoneNumber;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate drivingLicenseIssueDate;
    @OneToOne
    @JoinColumn(name = "insuranceHistory_id")
    private InsuranceHistory insuranceHistory;

    public Person() {
    }

    public Person(String firstName, String lastName, Adress address, LocalDate dayOfBirth, String pesel,
                  String phoneNumber, LocalDate drivingLicenseIssueDate, InsuranceHistory insuranceHistory) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.dayOfBirth = dayOfBirth;
        this.pesel = pesel;
        this.phoneNumber = phoneNumber;
        this.drivingLicenseIssueDate = drivingLicenseIssueDate;
        this.insuranceHistory = insuranceHistory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Adress getAddress() {
        return address;
    }

    public void setAddress(Adress address) {
        this.address = address;
    }

    public LocalDate getDayOfBirth() {
        return dayOfBirth;
    }


    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDrivingLicenseIssueDate() {
        return drivingLicenseIssueDate;
    }


    public InsuranceHistory getInsuranceHistory() {
        return insuranceHistory;
    }

    public void setInsuranceHistory(InsuranceHistory insuranceHistory) {
        this.insuranceHistory = insuranceHistory;
    }
}
