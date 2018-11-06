package workerboard.model.dto;

public class RiskDtoDto {
    private String name;
    private double sum;
    private PremiumDto premiumList;

    public RiskDtoDto() {
    }

    public RiskDtoDto(String name, double sum, PremiumDto premiumList) {
        this.name = name;
        this.sum = sum;
        this.premiumList = premiumList;
    }

    public String getName() {
        return name;
    }

    public double getSum() {
        return sum;
    }

    public PremiumDto getPremiumList() {
        return premiumList;
    }

    public void setPremiumList(PremiumDto premiumList) {
        this.premiumList = premiumList;
    }
}
