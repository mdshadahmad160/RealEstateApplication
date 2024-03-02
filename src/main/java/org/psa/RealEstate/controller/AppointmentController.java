package org.psa.RealEstate.controller;
import org.psa.RealEstate.payload.ApiResponse;
import org.psa.RealEstate.payload.AppointmentDto;
import org.psa.RealEstate.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;


    @PostMapping("/users/{userId}/properties/{propertyId}/appointments")
    public ResponseEntity<AppointmentDto> createAppointment(@PathVariable(value = "userId") long userId,
                                                            @PathVariable(value = "propertyId") long propertyId,
                                                            @RequestBody AppointmentDto appointmentDto){
        AppointmentDto dto=appointmentService.createAppointment(userId,propertyId,appointmentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @PutMapping("/users/{userId}/properties/{propertyId}/appointments/{appointmentId}/appointments")
    public ResponseEntity<AppointmentDto> updateAppointment(@PathVariable(value = "userId") long userId,
                                                            @PathVariable(value = "propertyId") long propertyId,
                                                            @PathVariable(value = "appointmentId") long appointmentId,
                                                            @RequestBody AppointmentDto appointmentDto){
        AppointmentDto dto=appointmentService.updateAppointment(userId, propertyId, appointmentId, appointmentDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @GetMapping("/users/{userId}/properties{propertyId}/appointments/{appointmentId}")
    public ResponseEntity<AppointmentDto> findAppointmentById(@PathVariable(value = "userId") long userId,
                                                              @PathVariable(value = "propertyId") long propertyId,
                                                              @PathVariable(value = "appointmentId") long appointmentId){
        AppointmentDto dto=appointmentService.findAppointmentById(userId, propertyId, appointmentId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentDto>> getAllAppointment(){
        List<AppointmentDto> appointmentDto=appointmentService.getAllAppointment();
        return new ResponseEntity<>(appointmentDto,HttpStatus.OK);
    }
    @DeleteMapping("/users/{userId}/properties/{propertyId}/appointments/{appointmentId}")
    public ResponseEntity<ApiResponse> deleteAppointment(@PathVariable(value = "userId") long userId,
                                                         @PathVariable(value = "propertyId") long propertyId,
                                                         @PathVariable(value = "appointmentId") long appointmentId){
        appointmentService.deleteAppointment(userId, propertyId, appointmentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Appointment Deleted Successfully: ",true),HttpStatus.OK);
    }
}
