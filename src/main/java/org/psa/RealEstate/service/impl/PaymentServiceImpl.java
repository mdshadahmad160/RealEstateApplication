package org.psa.RealEstate.service.impl;
import org.psa.RealEstate.entities.Booking;
import org.psa.RealEstate.entities.Payment;
import org.psa.RealEstate.entities.Property;
import org.psa.RealEstate.entities.User;
import org.psa.RealEstate.exception.BookingNotFoundException;
import org.psa.RealEstate.exception.PaymentNotFoundException;
import org.psa.RealEstate.exception.PropertyNotFoundException;
import org.psa.RealEstate.exception.UserNotFoundException;
import org.psa.RealEstate.payload.PaymentDto;
import org.psa.RealEstate.repository.BookingRepository;
import org.psa.RealEstate.repository.PaymentRepository;
import org.psa.RealEstate.repository.PropertyRepository;
import org.psa.RealEstate.repository.UserRepository;
import org.psa.RealEstate.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ModelMapper mapper;


    public Payment mapToEntity(PaymentDto paymentDto){
        /*Payment payment = new Payment();
        payment.setId();
        payment.setUser();
        payment.setProperty();
        payment.setBooking();
        payment.setPaymentAmount(paymentDto.getPaymentAmount());
        payment.setPaymentMethod(paymentDto.getPaymentMethod());
        payment.setPaymentStatus(paymentDto.getPaymentStatus());
        payment.setPaymentDate(paymentDto.getPaymentDate());
        payment.setTransactionId(paymentDto.getTransactionId());
        payment.setPaymentDescription(paymentDto.getPaymentDescription());
        payment.setPaymentReceipt(paymentDto.getPaymentReceipt());
        payment.setCurrency(paymentDto.getCurrency());
        payment.setRefundAmount(paymentDto.getRefundAmount());
        payment.setRefundDate(paymentDto.getRefundDate());
        payment.setPaymentSource(paymentDto.getPaymentSource());
        payment.setPaymentVerification(paymentDto.getPaymentVerification());
        payment.setInvoiceNumber(paymentDto.getInvoiceNumber());
        payment.setInvoiceDueDate(paymentDto.getInvoiceDueDate());
        return payment;*/
        Payment payment=mapper.map(paymentDto,Payment.class);
        return payment;
    }
    public PaymentDto mapToDto(Payment payment){
       /* PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPaymentAmount(payment.getPaymentAmount());
        paymentDto.setPaymentMethod(payment.getPaymentMethod());
        paymentDto.setPaymentStatus(payment.getPaymentStatus());
        paymentDto.setPaymentDate(payment.getPaymentDate());
        paymentDto.setTransactionId(payment.getTransactionId());
        paymentDto.setPaymentDescription(payment.getPaymentDescription());
        paymentDto.setPaymentReceipt(payment.getPaymentReceipt());
        paymentDto.setCurrency(payment.getCurrency());
        paymentDto.setRefundAmount(payment.getRefundAmount());
        paymentDto.setRefundDate(payment.getRefundDate());
        paymentDto.setPaymentSource(payment.getPaymentSource());
        paymentDto.setPaymentVerification(payment.getPaymentVerification());
        paymentDto.setInvoiceNumber(payment.getInvoiceNumber());
        paymentDto.setInvoiceDueDate(payment.getInvoiceDueDate());
        return paymentDto;*/
        PaymentDto dto=mapper.map(payment,PaymentDto.class);
        return dto;
    }
    @Override
    public PaymentDto createPayment(long userId, long propertyId, long bookingId, PaymentDto paymentDto) {
        User user=userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("Invalid User iD: ")
        );
       Property property=propertyRepository.findById(propertyId).orElseThrow(
               ()-> new PropertyNotFoundException("Invalid Property Id: ")
       );
        Booking booking=bookingRepository.findById(bookingId).orElseThrow(
                ()-> new BookingNotFoundException("Invalid Booking id: ")
        );
        Payment payment=mapToEntity(paymentDto);
        payment.setUser(user);
        payment.setProperty(property);
        payment.setBooking(booking);
        Payment newPayment=paymentRepository.save(payment);
        PaymentDto dto=mapToDto(newPayment);
        return dto;
    }

    @Override
    public PaymentDto updatePayment(long userId, long propertyId, long bookingId, long paymentId, PaymentDto paymentDto) {
        User user=userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("Invalid User Id: ")
        );
        Property property=propertyRepository.findById(propertyId).orElseThrow(
                ()-> new PropertyNotFoundException("Property Not Found With This Id: ")
        );
        Booking booking=bookingRepository.findById(bookingId).orElseThrow(
                ()-> new BookingNotFoundException("Booking Id DoesNot Matched With Existing Id: ")
        );
        Payment payment=paymentRepository.findById(paymentId).orElseThrow(
                ()-> new PaymentNotFoundException("Invalid Payment Id Plz Enter Valid Id: ")
        );
        payment.setPaymentAmount(paymentDto.getPaymentAmount());
        payment.setPaymentMethod(paymentDto.getPaymentMethod());
        payment.setPaymentDate(paymentDto.getPaymentDate());
        payment.setPaymentStatus(paymentDto.getPaymentStatus());
        payment.setTransactionId(paymentDto.getTransactionId());
        payment.setPaymentDescription(paymentDto.getPaymentDescription());
        payment.setPaymentReceipt(paymentDto.getPaymentReceipt());
        payment.setCurrency(paymentDto.getCurrency());
        payment.setRefundAmount(paymentDto.getRefundAmount());
        payment.setRefundDate(paymentDto.getRefundDate());
        payment.setPaymentSource(paymentDto.getPaymentSource());
        payment.setPaymentVerification(paymentDto.getPaymentVerification());
        payment.setInvoiceNumber(paymentDto.getInvoiceNumber());
        payment.setInvoiceDueDate(paymentDto.getInvoiceDueDate());
        Payment newPayment=paymentRepository.save(payment);
        PaymentDto dto=mapToDto(newPayment);
        return dto;
    }

    @Override
    public PaymentDto findByPaymentById(long userId, long propertyId, long bookingId, long paymentId) {
        User user=userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("Invalid User Id: ")
        );
        Property property=propertyRepository.findById(propertyId).orElseThrow(
                ()-> new PropertyNotFoundException("Invalid Property id: ")
        );
        Booking booking=bookingRepository.findById(bookingId).orElseThrow(
                ()-> new BookingNotFoundException("Booking Is Not Valid: ")
        );
        Payment payment=paymentRepository.findById(paymentId).orElseThrow(
                ()-> new PaymentNotFoundException("Invalid Payment Id: ")
        );
        PaymentDto dto=mapToDto(payment);
        return dto;
    }

    @Override
    public List<PaymentDto> getAllPayment() {
        List<Payment> payments=paymentRepository.findAll();
        List<PaymentDto> paymentDto=payments.stream().map(payment -> mapToDto(payment))
                .collect(Collectors.toList());
        return paymentDto;
    }

    @Override
    public void deletePayment(long userId, long propertyId, long bookingId, long paymentId) {
        User user=userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("Invalid User Id: ")
        );
        Property property=propertyRepository.findById(propertyId).orElseThrow(
                ()-> new PropertyNotFoundException("Property Id Not Valid: ")
        );
        Booking booking=bookingRepository.findById(bookingId).orElseThrow(
                ()-> new BookingNotFoundException("Booking Id Is Not Valid: ")
        );
        Payment payment=paymentRepository.findById(paymentId).orElseThrow(
                ()-> new PaymentNotFoundException("Payment Id DoesNot Exists : ")
        );
        paymentRepository.delete(payment);

    }
}
