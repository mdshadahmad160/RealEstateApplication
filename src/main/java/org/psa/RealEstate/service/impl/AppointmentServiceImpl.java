package org.psa.RealEstate.service.impl;
import org.psa.RealEstate.entities.Appointment;
import org.psa.RealEstate.entities.Property;
import org.psa.RealEstate.entities.User;
import org.psa.RealEstate.exception.AppointmentNotFoundException;
import org.psa.RealEstate.exception.PropertyNotFoundException;
import org.psa.RealEstate.exception.UserNotFoundException;
import org.psa.RealEstate.payload.AppointmentDto;
import org.psa.RealEstate.repository.AppointmentRepository;
import org.psa.RealEstate.repository.PropertyRepository;
import org.psa.RealEstate.repository.UserRepository;
import org.psa.RealEstate.service.AppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl  implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private ModelMapper mapper;


    public Appointment mapToEntity(AppointmentDto appointmentDto){
        /*Appointment appointment = new Appointment();
        appointment.setId(appointmentDto.getId());
        appointment.setUser();
        appointment.setProperty();
        appointment.setAppointmentDate(appointmentDto.getAppointmentDate());
        appointment.setDuration(appointmentDto.getDuration());
        appointment.setStatus(appointmentDto.getStatus());
        appointment.setAgentOrContactPerson(appointmentDto.getAgentOrContactPerson());
        appointment.setConfirmationCode(appointmentDto.getConfirmationCode());
        appointment.setUserFeedback(appointmentDto.getUserFeedback());
        return appointment;*/
        Appointment appointment=mapper.map(appointmentDto, Appointment.class);
        return appointment;
    }

    public AppointmentDto mapToDto(Appointment appointment){
       /* AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setId(appointment.getId());
        appointmentDto.setAppointmentDate(appointment.getAppointmentDate());
        appointmentDto.setDuration(appointment.getDuration());
        appointmentDto.setStatus(appointment.getStatus());
        appointmentDto.setAgentOrContactPerson(appointment.getAgentOrContactPerson());
        appointmentDto.setConfirmationCode(appointment.getConfirmationCode());
        appointmentDto.setUserFeedback(appointment.getUserFeedback());
        return appointmentDto;*/
        AppointmentDto appointmentDto=mapper.map(appointment,AppointmentDto.class);
        return appointmentDto;
    }
    @Override
    public AppointmentDto createAppointment(long userId, long propertyId, AppointmentDto appointmentDto) {
        User user=userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("Invalid User Id: ")
        );
        Property property=propertyRepository.findById(propertyId).orElseThrow(
                ()-> new PropertyNotFoundException("Property DoesNot Exists With This Id: ")
        );
        Appointment appointment=mapToEntity(appointmentDto);
        appointment.setUser(user);
        appointment.setProperty(property);
        Appointment newAppointment=appointmentRepository.save(appointment);
        AppointmentDto dto=mapToDto(newAppointment);
        return dto;
    }

    @Override
    public AppointmentDto updateAppointment(long userId, long propertyId, long appointmentId,AppointmentDto appointmentDto) {
        User user=userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("Invalid User Id  Plz Enter Valid User Id:  ")
        );
        Property property=propertyRepository.findById(propertyId).orElseThrow(
                ()-> new PropertyNotFoundException("Property DoesNot Exists With This Id: ")
        );
        Appointment appointment=appointmentRepository.findById(appointmentId).orElseThrow(
                ()-> new AppointmentNotFoundException("Invalid Appointment Id: Plz Enter Valid Appointment Id: ")
        );
        appointment.setAppointmentDate(appointmentDto.getAppointmentDate());
        appointment.setDuration(appointmentDto.getDuration());
        appointment.setStatus(appointmentDto.getStatus());
        appointment.setAgentOrContactPerson(appointmentDto.getAgentOrContactPerson());
        appointment.setConfirmationCode(appointmentDto.getConfirmationCode());
        appointment.setUserFeedback(appointmentDto.getUserFeedback());
        Appointment newAppointment=appointmentRepository.save(appointment);
        return mapToDto(newAppointment);
    }

    @Override
    public AppointmentDto findAppointmentById(long userId, long propertyId, long appointmentId) {
        User user=userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("Invalid User Id: ")
        );
        Property property=propertyRepository.findById(propertyId).orElseThrow(
                ()-> new PropertyNotFoundException("Invalid Property Id: ")
        );
        Appointment appointment=appointmentRepository.findById(appointmentId).orElseThrow(
                ()-> new AppointmentNotFoundException("Invalid Appointment Id: ")
        );
        AppointmentDto dto=mapToDto(appointment);
        return dto;
    }


    @Override
    public List<AppointmentDto> getAllAppointment() {
        List<Appointment> appointments=appointmentRepository.findAll();
        List<AppointmentDto> appointmentDto=appointments.stream().map(appointment -> mapToDto(appointment))
                .collect(Collectors.toList());
        return appointmentDto;
    }

    @Override
    public void deleteAppointment(long userId, long propertyId, long appointmentId) {
        User user=userRepository.findById(userId).orElseThrow(
                ()-> new AppointmentNotFoundException("Invalid User Id: ")
        );
        Property property=propertyRepository.findById(propertyId).orElseThrow(
                ()-> new PropertyNotFoundException("Invalid Property Id: ")
        );
        Appointment appointment=appointmentRepository.findById(appointmentId).orElseThrow(
                ()-> new PropertyNotFoundException("Invalid Appointment Id: ")
        );
        appointmentRepository.delete(appointment);

    }
}
