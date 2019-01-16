package workerboard.model;

import workerboard.serivce.mapper.MapGenerate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vehicle implements MapGenerate {

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
    private String licencePlateNumber;
    private String vin;

    public Vehicle() {
    }

    public Vehicle(String mark, String model, String engineCapacity, String loadWeight, String enginePower, String productionYear, String cylindersCount, String seatCount, String doorsCount, String gearboxCount, String gearboxType, String name, String type, String engineHPower, String fuelType, String bodyType, String vehicleValue, String licencePlateNumber, String vin) {
        this.mark = mark;
        this.model = model;
        this.engineCapacity = engineCapacity;
        this.loadWeight = loadWeight;
        this.enginePower = enginePower;
        this.productionYear = productionYear;
        this.cylindersCount = cylindersCount;
        this.seatCount = seatCount;
        this.doorsCount = doorsCount;
        this.gearboxCount = gearboxCount;
        this.gearboxType = gearboxType;
        this.name = name;
        this.type = type;
        this.engineHPower = engineHPower;
        this.fuelType = fuelType;
        this.bodyType = bodyType;
        this.vehicleValue = vehicleValue;
        this.licencePlateNumber = licencePlateNumber;
        this.vin = vin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getLoadWeight() {
        return loadWeight;
    }

    public void setLoadWeight(String loadWeight) {
        this.loadWeight = loadWeight;
    }

    public String getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(String enginePower) {
        this.enginePower = enginePower;
    }

    public String getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }

    public String getCylindersCount() {
        return cylindersCount;
    }

    public void setCylindersCount(String cylindersCount) {
        this.cylindersCount = cylindersCount;
    }

    public String getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(String seatCount) {
        this.seatCount = seatCount;
    }

    public String getDoorsCount() {
        return doorsCount;
    }

    public void setDoorsCount(String doorsCount) {
        this.doorsCount = doorsCount;
    }

    public String getGearboxCount() {
        return gearboxCount;
    }

    public void setGearboxCount(String gearboxCount) {
        this.gearboxCount = gearboxCount;
    }

    public String getGearboxType() {
        return gearboxType;
    }

    public void setGearboxType(String gearboxType) {
        this.gearboxType = gearboxType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEngineHPower() {
        return engineHPower;
    }

    public void setEngineHPower(String engineHPower) {
        this.engineHPower = engineHPower;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getVehicleValue() {
        return vehicleValue;
    }

    public void setVehicleValue(String vehicleValue) {
        this.vehicleValue = vehicleValue;
    }

    public String getLicencePlateNumber() {
        return licencePlateNumber;
    }

    public String getVin() {
        return vin;
    }
}
