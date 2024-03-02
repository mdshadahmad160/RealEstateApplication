package org.psa.RealEstate.payload;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotEmpty;
import java.time.Duration;
import java.time.LocalDateTime;

@Data
public class BookingDto {

    private long id;



    private LocalDateTime bookingDate;

    private LocalDateTime appointmentDate;
    private Duration duration;
    private String status; // e.g., pending, confirmed, canceled
    private String specialRequests;
    private String agentOrContactPerson;
    private String confirmationCode;

    private String cancellationReason;


}
