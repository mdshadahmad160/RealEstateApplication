package org.psa.RealEstate.controller;
import org.psa.RealEstate.exception.ApiException;
import org.psa.RealEstate.payload.JwtAuthRequest;
import org.psa.RealEstate.payload.JwtAuthResponse;
import org.psa.RealEstate.security.JwtTokenHelperOrProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {


    /*@Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;


    @Autowired
    private JwtTokenHelperOrProvider jwtTokenHelperOrProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
        authenticate(request.getUsername(), request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token =jwtTokenHelperOrProvider.generateToken(userDetails);

        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);
        response.setUser(mapper.map((User) userDetails, UserDto.class));
        return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
                password);

        try {

            authenticationManager.authenticate(authenticationToken);

        } catch (BadCredentialsException e) {
            System.out.println("Invalid Details !!");
            throw new ApiException("Invalid username or password !!");
        }

    }

    // register new user api

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {
        UserDto registeredUser = userService.registerNewUser(userDto);
        return new ResponseEntity<UserDto>(registeredUser, HttpStatus.CREATED);
    }

    // get loggedin user data

    @GetMapping("/current-user/")
    public ResponseEntity<UserDto> getUser(Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).get();
        return new ResponseEntity<UserDto>(mapper.map(user, UserDto.class), HttpStatus.OK);
    }
*/
    @Autowired
    private JwtTokenHelperOrProvider jwtTokenHelperOrProvider;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws  Exception{
        authenticate(request.getUsername(), request.getPassword());
         UserDetails userDetails=userDetailsService.loadUserByUsername(request.getUsername());
         String token=jwtTokenHelperOrProvider.generateToken(userDetails);
         JwtAuthResponse response=new JwtAuthResponse();
         response.setToken(token);
         return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);


    }
    private void authenticate(String username,String password){
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password);
        try {

            authenticationManager.authenticate(authenticationToken);

        } catch (BadCredentialsException e) {
            System.out.println("Invalid Details !!");
            throw new ApiException("Invalid username or password !!");
        }
    }
}
