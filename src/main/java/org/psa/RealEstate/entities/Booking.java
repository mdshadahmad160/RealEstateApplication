package org.psa.RealEstate.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;


    @OneToMany(mappedBy = "booking",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Payment> payments;

    @UpdateTimestamp
    private LocalDateTime bookingDate;
    @UpdateTimestamp
    private LocalDateTime appointmentDate;
    private Duration duration;
    private String status; // e.g., pending, confirmed, canceled
    private String specialRequests;
    private String agentOrContactPerson;
    private String confirmationCode;
    private String cancellationReason;

}
