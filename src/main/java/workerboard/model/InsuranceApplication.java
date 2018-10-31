package workerboard.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class InsuranceApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Vehicle vehicle;
    private List<Person> persons;
    private List<Risk> risks;
    private String number;
    private List<ServiceMessage> messages;

    public InsuranceApplication() {
    }

    public InsuranceApplication(Vehicle vehicle, List<Person> persons, List<Risk> risks, String number, List<ServiceMessage> messages) {
        this.vehicle = vehicle;
        this.persons = persons;
        this.risks = risks;
        this.number = number;
        this.messages = messages;
    }

    public Long getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public List<Risk> getRisks() {
        return risks;
    }

    public String getNumber() {
        return number;
    }

    public List<ServiceMessage> getMessages() {
        return messages;
    }
}
