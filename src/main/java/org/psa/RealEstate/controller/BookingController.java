package org.psa.RealEstate.controller;
import org.psa.RealEstate.payload.ApiResponse;
import org.psa.RealEstate.payload.BookingDto;
import org.psa.RealEstate.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/users/{userId}/properties/{propertyId}/bookings")
    public ResponseEntity<BookingDto> createBooking(@PathVariable(value = "userId") long userId,
                                                    @PathVariable(value = "propertyId") long propertyId,
                                                    @RequestBody BookingDto bookingDto) {
        BookingDto dto = bookingService.createBooking(userId, propertyId, bookingDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @PutMapping("/users/{userId}/properties/{propertyId}/bookings/{bookingId}/bookings")
    public ResponseEntity<BookingDto> updateBooking(@PathVariable(value = "userId") long userId,
                                                    @PathVariable(value = "propertyId") long propertyId,
                                                    @PathVariable(value = "bookingId") long bookingId,
                                                    @RequestBody BookingDto  bookingDto){
        BookingDto dto=bookingService.updateBooking(userId, propertyId, bookingId, bookingDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @GetMapping("/users/{userId}/properties/{propertyId}/bookings/{bookingId}")
    public ResponseEntity<BookingDto> findBookingById(@PathVariable(value = "userId") long userId,
                                                      @PathVariable(value = "propertyId") long propertyId,
                                                      @PathVariable(value = "bookingId") long bookingId){
        BookingDto dto=bookingService.findBookingById(userId, propertyId, bookingId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @GetMapping("/bookings")
    public ResponseEntity<List<BookingDto>> getAllBooking(){
        List<BookingDto> bookingDto=bookingService.getAllBooking();
        return new ResponseEntity<>(bookingDto,HttpStatus.OK);
    }
    @DeleteMapping("/users/{userId}/properties/{propertyId}/bookings/{bookingId}")
    public ResponseEntity<ApiResponse> deleteBooking(@PathVariable(value = "userId") long userId,
                                                     @PathVariable(value = "propertyId") long propertyId,
                                                     @PathVariable(value = "bookingId") long bookingId){
        bookingService.deleteBooking(userId, propertyId, bookingId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Booking Deleted Successfully: ",true),HttpStatus.OK);
    }
}
