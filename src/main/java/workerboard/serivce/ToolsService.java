package workerboard.serivce;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import workerboard.model.dto.DatesDto;

import java.time.LocalDate;
import java.util.Date;

@Service
public class ToolsService {


    public DatesDto getDates() {
        DatesDto datesDto = new DatesDto();
        datesDto.setToday(LocalDate.now());
        datesDto.setDays7(LocalDate.now().minusDays(7));
        datesDto.setDays30(LocalDate.now().minusDays(30));

        return datesDto;
    }
}
