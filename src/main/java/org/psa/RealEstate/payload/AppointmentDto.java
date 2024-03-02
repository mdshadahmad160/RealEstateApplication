package org.psa.RealEstate.payload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Duration;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {

    private long id;
    private LocalDateTime appointmentDate;

    private Duration duration;

    private String status; // e.g., pending, confirmed, canceled

    private String agentOrContactPerson;
    private String confirmationCode;

    private String userFeedback;


}
