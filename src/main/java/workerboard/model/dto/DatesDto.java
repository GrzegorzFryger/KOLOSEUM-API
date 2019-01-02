package workerboard.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import workerboard.config.LocalDateDeserializer;
import workerboard.config.LocalDateSerializer;

import java.time.LocalDate;

public class DatesDto {
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate today;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate days7;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate days30;

    public DatesDto() {
    }

    public DatesDto(LocalDate today, LocalDate days7, LocalDate days30) {
        this.today = today;
        this.days7 = days7;
        this.days30 = days30;
    }


    public void setToday(LocalDate today) {
        this.today = today;
    }

    public void setDays7(LocalDate days7) {
        this.days7 = days7;
    }

    public void setDays30(LocalDate days30) {
        this.days30 = days30;
    }

    public LocalDate getToday() {
        return today;
    }

    public LocalDate getDays7() {
        return days7;
    }

    public LocalDate getDays30() {
        return days30;
    }
}
