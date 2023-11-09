package hostfully.technical.interview.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@Getter
public class BookingDTO {
    private LocalDateTime startDate;
    private LocalDateTime  endDate;
    private Long userId;
    private String bookingStatus;
}
