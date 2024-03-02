package org.psa.RealEstate.service;
import org.psa.RealEstate.payload.BookingDto;

import java.util.List;

public interface BookingService {
    BookingDto createBooking(long userId, long propertyId, BookingDto bookingDto);
    BookingDto updateBooking(long userId,long propertyId,long bookingId,BookingDto bookingDto);
    BookingDto findBookingById(long userId,long propertyId,long bookingId);
    List<BookingDto> getAllBooking();
    void deleteBooking(long userId,long propertyId,long bookingId);

}
