package org.psa.RealEstate.service.impl;
import org.psa.RealEstate.config.AppConstant;
import org.psa.RealEstate.entities.Role;
import org.psa.RealEstate.entities.User;
import org.psa.RealEstate.exception.UserNotFoundException;
import org.psa.RealEstate.payload.UserDto;
import org.psa.RealEstate.repository.RoleRepository;
import org.psa.RealEstate.repository.UserRepository;
import org.psa.RealEstate.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private ModelMapper mapper;

    public User mapToEntity(UserDto userDto) {
        /*User user = new User();
        user.setId();
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setAddress(userDto.getAddress());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setRegistrationDate(userDto.getRegistrationDate());
        user.setLastLoginDate(userDto.getLastLoginDate());
        user.setActive(userDto.isActive());
        user.setBookings();
        user.setAppointments();
        user.setPayments();
        user.setReviews();
        user.setComments();
        return user;*/
        User user = mapper.map(userDto, User.class);
        return user;
    }
    public UserDto mapToDto(User user){
        /*UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setAddress(user.getAddress());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());
        userDto.setRegistrationDate(user.getRegistrationDate());
        userDto.setLastLoginDate(user.getLastLoginDate());
        userDto.setActive(user.isActive());
        return userDto;*/
        UserDto userDto=mapper.map(user,UserDto.class);
        return userDto;

    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user=mapToEntity(userDto);
        User userEntity=userRepository.save(user);
        UserDto dto=mapToDto(userEntity);
        return dto;
    }

    @Override
    public UserDto updateUser(UserDto userDto, long id) {
        User user=userRepository.findById(id).orElseThrow(
                ()-> new UserNotFoundException("User does not exist with this Id: ")
        );
         user.setFirstName(userDto.getFirstName());
         user.setLastName(userDto.getLastName());
         user.setAddress(userDto.getAddress());
         user.setActive(userDto.isActive());
         user.setEmail(userDto.getEmail());
         user.setLastLoginDate(userDto.getLastLoginDate());
         user.setPassword(userDto.getPassword());
         user.setPhoneNumber(userDto.getPhoneNumber());
         user.setRegistrationDate(userDto.getRegistrationDate());
         user.setUsername(userDto.getUsername());
         User newUser=userRepository.save(user);
        return mapToDto(newUser);
    }

    @Override
    public UserDto findUserById(long id) {
        User  user=userRepository.findById(id).orElseThrow(
                ()-> new UserNotFoundException("Invalid Id Plz Enter Valid Id: ")
        );
        UserDto userDto=mapToDto(user);
        return userDto;
    }

    @Override
    public List<UserDto> getAllUser() {
      List<User> users=userRepository.findAll();
      List<UserDto> userDto=users.stream().map(user -> mapToDto(user))
              .collect(Collectors.toList());
        return userDto;
    }

    @Override
    public void deleteUser(long id) {
        User user=userRepository.findById(id).orElseThrow(
                ()-> new UserNotFoundException("Invalid User Id: ")
        );
        userRepository.delete(user);

    }


    @Override
    public UserDto registerNewUser(UserDto userDto) {
        User user=mapToEntity(userDto);


        // encoded the password
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        // roles
        Role role = roleRepository.findById(Long.valueOf(AppConstant.NORMAL_USER)).get();

        user.getRoles().add(role);

        User newUser =userRepository.save(user);
        UserDto dto=mapToDto(newUser);

        return dto;
    }
}
