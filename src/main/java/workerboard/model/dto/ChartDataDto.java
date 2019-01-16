package workerboard.model.dto;

import java.time.LocalDate;

public class ChartDataDto {

     private LocalDate start;
     private LocalDate end;

    public ChartDataDto() {
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }
}
