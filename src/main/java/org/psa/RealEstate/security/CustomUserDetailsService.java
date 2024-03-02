package org.psa.RealEstate.security;
import org.psa.RealEstate.entities.User;
import org.psa.RealEstate.exception.UserNotFoundException;
import org.psa.RealEstate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByusername(username)
                .orElseThrow(() -> new UserNotFoundException("User ", " email : " + username, 0));

        return user;
    }




    }

