package org.psa.RealEstate.service;
import org.psa.RealEstate.payload.PaymentDto;

import java.util.List;

public interface PaymentService {
    PaymentDto createPayment(long userId, long propertyId, long bookingId, PaymentDto paymentDto);
    PaymentDto updatePayment(long userId,long propertyId,long bookingId,long paymentId,PaymentDto paymentDto);
    PaymentDto findByPaymentById(long userId,long propertyId,long bookingId,long paymentId);
    List<PaymentDto> getAllPayment();
    void  deletePayment(long userId,long propertyId,long bookingId,long paymentId);
}
