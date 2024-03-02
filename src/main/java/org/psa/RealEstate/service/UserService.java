package org.psa.RealEstate.service;

import org.psa.RealEstate.payload.UserDto;

import java.util.List;

public interface UserService {

    UserDto registerNewUser(UserDto user);
    UserDto saveUser(UserDto  userDto);
    UserDto updateUser(UserDto userDto,long id);
    UserDto findUserById(long id);
    List<UserDto> getAllUser();
    void deleteUser(long id);
}
