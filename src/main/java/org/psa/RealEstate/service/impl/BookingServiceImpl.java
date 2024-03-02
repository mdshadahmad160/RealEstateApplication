package org.psa.RealEstate.service.impl;
import org.psa.RealEstate.entities.Booking;
import org.psa.RealEstate.entities.Property;
import org.psa.RealEstate.entities.User;
import org.psa.RealEstate.exception.BookingNotFoundException;
import org.psa.RealEstate.exception.PropertyNotFoundException;
import org.psa.RealEstate.exception.UserNotFoundException;
import org.psa.RealEstate.payload.BookingDto;
import org.psa.RealEstate.repository.BookingRepository;
import org.psa.RealEstate.repository.PropertyRepository;
import org.psa.RealEstate.repository.UserRepository;
import org.psa.RealEstate.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl  implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private ModelMapper mapper;

    public Booking mapToEntity(BookingDto bookingDto){
        /*Booking booking = new Booking();
        booking.setId(bookingDto.getId());
        booking.setUser();
        booking.setProperty();
        booking.setPayments();
        booking.setBookingDate(bookingDto.getBookingDate());
        booking.setAppointmentDate(bookingDto.getAppointmentDate());
        booking.setDuration(bookingDto.getDuration());
        booking.setStatus(bookingDto.getStatus());
        booking.setSpecialRequests(bookingDto.getSpecialRequests());
        booking.setAgentOrContactPerson(bookingDto.getAgentOrContactPerson());
        booking.setConfirmationCode(bookingDto.getConfirmationCode());
        booking.setCancellationReason(bookingDto.getCancellationReason());
        return booking;*/
        Booking booking=mapper.map(bookingDto,Booking.class);
        return booking;
    }
    public BookingDto mapToDto(Booking booking){
        /*BookingDto bookingDto = new BookingDto();
        bookingDto.setId(booking.getId());
        bookingDto.setBookingDate(booking.getBookingDate());
        bookingDto.setAppointmentDate(booking.getAppointmentDate());
        bookingDto.setDuration(booking.getDuration());
        bookingDto.setStatus(booking.getStatus());
        bookingDto.setSpecialRequests(booking.getSpecialRequests());
        bookingDto.setAgentOrContactPerson(booking.getAgentOrContactPerson());
        bookingDto.setConfirmationCode(booking.getConfirmationCode());
        bookingDto.setCancellationReason(booking.getCancellationReason());
        return bookingDto;*/
        BookingDto dto=mapper.map(booking,BookingDto.class);
        return dto;
    }
    @Override
    public BookingDto createBooking(long userId,long propertyId,BookingDto bookingDto) {
        User user=userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("User Not Valid With This Id Plz Enter Valid Id:  ")
        );
        Property property=propertyRepository.findById(propertyId).orElseThrow(
                ()-> new PropertyNotFoundException("Invalid Property Id Plz Enter Valid Id: ")
        );
        Booking booking=mapToEntity(bookingDto);
        booking.setUser(user);
        booking.setProperty(property);
        Booking newBooking=bookingRepository.save(booking);
        BookingDto dto=mapToDto(newBooking);
        return dto;
    }

    @Override
    public BookingDto updateBooking(long userId, long propertyId,long bookingId, BookingDto bookingDto) {
        User user=userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("Invalid User Id: ")
        );
        Property property=propertyRepository.findById(propertyId).orElseThrow(
                ()-> new PropertyNotFoundException("Invalid Property Id: ")
        );
        Booking booking=bookingRepository.findById(bookingId).orElseThrow(
                ()-> new BookingNotFoundException("Invalid Booking Id: ")
        );
        booking.setBookingDate(bookingDto.getBookingDate());
        booking.setDuration(bookingDto.getDuration());
        booking.setStatus(bookingDto.getStatus());
        booking.setAppointmentDate(bookingDto.getAppointmentDate());
        booking.setConfirmationCode(bookingDto.getConfirmationCode());
        booking.setAgentOrContactPerson(bookingDto.getAgentOrContactPerson());
        booking.setCancellationReason(bookingDto.getCancellationReason());
        booking.setSpecialRequests(bookingDto.getSpecialRequests());
        Booking newBooking=bookingRepository.save(booking);
        return mapToDto(newBooking);
    }

    @Override
    public BookingDto findBookingById(long userId, long propertyId, long bookingId) {
        User user=userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("Invalid User Id: ")
        );
        Property property=propertyRepository.findById(propertyId).orElseThrow(
                ()-> new PropertyNotFoundException("Invalid Property Id: ")
        );
        Booking booking=bookingRepository.findById(bookingId).orElseThrow(
                ()-> new BookingNotFoundException("Invalid Booking Id Plz Provide Valid Booking Id : ")
        );
        BookingDto dto=mapToDto(booking);
        return dto;
    }

    @Override
    public List<BookingDto> getAllBooking() {
        List<Booking> bookings = bookingRepository.findAll();
        List<BookingDto> bookingDto=bookings.stream().map(booking -> mapToDto(booking))
                .collect(Collectors.toList());
        return bookingDto;
    }

    @Override
    public void deleteBooking(long userId, long propertyId, long bookingId) {
        User user=userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("Invalid User Id: ")
        );
        Property property=propertyRepository.findById(propertyId).orElseThrow(
                ()-> new PropertyNotFoundException("Invalid Property Id: ")
        );
        Booking booking=bookingRepository.findById(bookingId).orElseThrow(
                ()-> new BookingNotFoundException("Booking Id DoesNot Exists : Plz Enter Valid Booking Id: ")
        );
        bookingRepository.delete(booking);

    }
}
