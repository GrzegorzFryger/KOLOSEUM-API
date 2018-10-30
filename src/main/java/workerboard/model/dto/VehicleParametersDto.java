package workerboard.model.dto;

public class VehicleParametersDto {
    private String mark;
    private String model;
    private String enginePower;
    private String seatCount;
    private String doorsCount;
    private String gearboxCount;
    private String type;

    public VehicleParametersDto() {
    }

    public String getMark() {
        return mark;
    }

    public String getModel() {
        return model;
    }

    public String getEnginePower() {
        return enginePower;
    }

    public String getSeatCount() {
        return seatCount;
    }

    public String getDoorsCount() {
        return doorsCount;
    }

    public String getGearboxCount() {
        return gearboxCount;
    }

    public String getType() {
        return type;
    }
}
