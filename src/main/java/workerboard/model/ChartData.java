package workerboard.model;

import java.time.LocalDate;

public class ChartData {

    private String label;
    private LocalDate date;
    private Integer value;
    private Long count;

    public ChartData(String label, LocalDate date, Integer value, Long count) {
        this.label = label;
        this.date = date;
        this.value = value;
        this.count = count;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
