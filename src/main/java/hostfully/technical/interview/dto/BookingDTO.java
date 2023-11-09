package hostfully.technical.interview.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class BookingDTO {
    private LocalDateTime startDate;
    private LocalDateTime  endDate;
    private Long userId;
    private String bookingStatus;
}
