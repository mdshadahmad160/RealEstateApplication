package org.psa.RealEstate.exception;
import org.hibernate.service.NullServiceException;
import org.psa.RealEstate.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
     @ExceptionHandler(AgentNotFoundException.class)
    public ResponseEntity<ApiResponse> AgentNotFoundException(AgentNotFoundException agentNotFoundException){
        String message=agentNotFoundException.getMessage();
        ApiResponse response=new ApiResponse(message,false);
        return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(AppointmentNotFoundException.class)
    public ResponseEntity<ApiResponse> AppointmentNotFoundException(AppointmentNotFoundException appointmentNotFoundException){
         String message=appointmentNotFoundException.getMessage();
         ApiResponse response=new ApiResponse(message,false);
         return  new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<ApiResponse> BookingNotFoundException(BookingNotFoundException bookingNotFoundException){
         String message=bookingNotFoundException.getMessage();
         ApiResponse response=new ApiResponse(message,false);
         return  new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CommentNotFoundException.class)
    public  ResponseEntity<ApiResponse> CommentNotFoundException(CommentNotFoundException commentNotFoundException){
         String message=commentNotFoundException.getMessage();
         ApiResponse  response=new ApiResponse(message,false);
         return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<ApiResponse> PaymentNotFoundException(PaymentNotFoundException exception){
         String message= exception.getMessage();
         ApiResponse response=new ApiResponse(message,false);
        return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(PropertyNotFoundException.class)
    public ResponseEntity<ApiResponse> PropertyNotFoundException(PropertyNotFoundException exception){
         String message=exception.getMessage();
         ApiResponse response=new ApiResponse(message,false);
         return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<ApiResponse> ReviewNotFoundException(ReviewNotFoundException exception){
         String message=exception.getMessage();
         ApiResponse response=new ApiResponse(message,false);
         return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NullServiceException.class)
    public ResponseEntity<ApiResponse> UserNotFoundException(UserNotFoundException exception){
         String message= exception.getMessage();
         ApiResponse response=new ApiResponse();
         return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
    }

    /**
     *
     * @param ex
     * @return
     */


    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> handleApiException(ApiException ex) {
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, true);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
    }




}
