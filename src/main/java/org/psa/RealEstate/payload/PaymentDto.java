package org.psa.RealEstate.payload;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PaymentDto {


    private double paymentAmount;
    private String paymentMethod; // E.g., credit card, PayPal, bank transfer
    private String paymentStatus; // E.g., pending, successful, failed

    private LocalDateTime paymentDate;
    private String transactionId; // Unique identifier from the payment gateway
    private String paymentDescription;
    private String paymentReceipt; // Reference to the payment receipt
    private String currency;
    private double refundAmount;

    private LocalDateTime refundDate;
    private String paymentSource; // E.g., online portal, mobile app, in-person
    private String paymentVerification; // Verification details (e.g., CVV, OTP)
    private String invoiceNumber; // If generating invoices
    private LocalDate invoiceDueDate;

}
