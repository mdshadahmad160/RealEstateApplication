package org.psa.RealEstate.service;
import org.psa.RealEstate.payload.AppointmentDto;

import java.util.List;

public interface AppointmentService {

    AppointmentDto createAppointment(long userId, long propertyId, AppointmentDto appointmentDto);
    AppointmentDto updateAppointment(long userId,long propertyId,long appointmentId,AppointmentDto appointmentDto);
    AppointmentDto findAppointmentById(long userId, long propertyId, long appointmentId);
    List<AppointmentDto> getAllAppointment();
    void deleteAppointment(long userId,long propertyId,long agentId);
}
