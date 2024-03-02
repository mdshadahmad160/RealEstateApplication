package org.psa.RealEstate.controller;
import org.psa.RealEstate.payload.ApiResponse;
import org.psa.RealEstate.payload.UserDto;
import org.psa.RealEstate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto){
        UserDto dto=userService.saveUser(userDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto  userDto, @PathVariable(value = "userId") long id){
        UserDto dto=userService.updateUser(userDto,id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser(){
        List<UserDto> dto=userService.getAllUser();
        return  new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> findUserById(@PathVariable(value = "userId") long id){
        UserDto dto=userService.findUserById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable(value = "userId") long id){
        userService.deleteUser(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully: ",true),HttpStatus.OK);
    }
}
