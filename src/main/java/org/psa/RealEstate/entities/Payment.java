package org.psa.RealEstate.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;

        @ManyToOne
        @JoinColumn(name = "property_id")
        private Property property;

        @ManyToOne
        @JoinColumn(name = "booking_id")
        private Booking booking;

        private double paymentAmount;
        private String paymentMethod; // E.g., credit card, PayPal, bank transfer
        private String paymentStatus; // E.g., pending, successful, failed
        //@UpdateTimestamp
        private LocalDateTime paymentDate;
        private String transactionId; // Unique identifier from the payment gateway
        private String paymentDescription;
        private String paymentReceipt; // Reference to the payment receipt
        private String currency;
        private double refundAmount;
       // @UpdateTimestamp
        private LocalDateTime refundDate;
        private String paymentSource; // E.g., online portal, mobile app, in-person
        private String paymentVerification; // Verification details (e.g., CVV, OTP)
        private String invoiceNumber; // If generating invoices
        //@UpdateTimestamp
        private LocalDate invoiceDueDate;




}
