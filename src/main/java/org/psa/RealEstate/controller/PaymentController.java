package org.psa.RealEstate.controller;
import org.psa.RealEstate.payload.ApiResponse;
import org.psa.RealEstate.payload.PaymentDto;
import org.psa.RealEstate.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @PostMapping("/users/{userId}/properties/{propertyId}/bookings/{bookingId}/payments")
    public ResponseEntity<PaymentDto> createPayment(@PathVariable(value = "userId") long userId,
                                                    @PathVariable(value = "propertyId") long propertyId,
                                                    @PathVariable(value = "bookingId") long bookingId,
                                                    @RequestBody PaymentDto paymentDto){
        PaymentDto dto=paymentService.createPayment(userId, propertyId, bookingId, paymentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @PutMapping("/users/{userId}/properties/{propertyId}/bookings/{bookingId}/payments/{paymentId}/payments")
    public ResponseEntity<PaymentDto> updatePayment(@PathVariable(value = "userId") long userId,
                                                    @PathVariable(value = "propertyId") long propertyId,
                                                    @PathVariable(value = "bookingId") long bookingId,
                                                    @PathVariable(value = "paymentId") long paymentId,
                                                    @RequestBody PaymentDto paymentDto){
        PaymentDto dto=paymentService.updatePayment(userId, propertyId, bookingId, paymentId, paymentDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);

    }
    @GetMapping("/users/{userId}/properties/{propertyId}/bookings/{bookingId}/payments/{paymentId}")
    public ResponseEntity<PaymentDto> findPaymentById(@PathVariable(value = "userId") long userId,
                                                      @PathVariable(value = "propertyId") long propertyId,
                                                      @PathVariable(value = "bookingId") long bookingId,
                                                      @PathVariable(value = "paymentId") long paymentId){
        PaymentDto dto=paymentService.findByPaymentById(userId, propertyId, bookingId, paymentId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @GetMapping("/payments")
    public ResponseEntity<List<PaymentDto>> getAllPayment(){
        List<PaymentDto> dto=paymentService.getAllPayment();
        return new ResponseEntity<>(dto,HttpStatus.OK);

    }
    @DeleteMapping("/users/{userId}/properties/{propertyId}/bookings/{bookingId}/payments/{paymentId}")
    public ResponseEntity<ApiResponse> deletePayment(@PathVariable(value = "userId") long userId,
                                                     @PathVariable(value = "propertyId") long propertyId,
                                                     @PathVariable(value = "bookingId") long bookingId,
                                                     @PathVariable(value = "paymentId") long paymentId){
        paymentService.deletePayment(userId, propertyId, bookingId, paymentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Payment Deleted Successfully!!!",true),HttpStatus.OK);
    }
}
