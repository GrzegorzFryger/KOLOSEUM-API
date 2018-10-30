package workerboard.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mark;
    private String model;
    private String engineCapacity;
    private String loadWeight;
    private String enginePower;
    private String productionYear;
    private String cylindersCount;
    private String seatCount;
    private String doorsCount;
    private String gearboxCount;
    private String gearboxType;
    private String name;
    private String type;
    private String engineHPower;
    private String fuelType;
    private String bodyType;
    private String vehicleValue;

    public Vehicle() {
    }


    public Long getId() {
        return id;
    }

    public String getMark() {
        return mark;
    }

    public String getModel() {
        return model;
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public String getLoadWeight() {
        return loadWeight;
    }

    public String getEnginePower() {
        return enginePower;
    }

    public String getCylindersCount() {
        return cylindersCount;
    }

    public String getSeatCount() {
        return seatCount;
    }

    public String getProductionYear() {
        return productionYear;
    }

    public String getDoorsCount() {
        return doorsCount;
    }

    public String getGearboxCount() {
        return gearboxCount;
    }

    public String getGearboxType() {
        return gearboxType;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getEngineHPower() {
        return engineHPower;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getBodyType() {
        return bodyType;
    }

    public String getVehicleValue() {
        return vehicleValue;
    }
}
