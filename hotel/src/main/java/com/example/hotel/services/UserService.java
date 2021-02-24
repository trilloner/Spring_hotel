package com.example.hotel.services;

import com.example.hotel.dto.UserRegistrationDto;
import com.example.hotel.exceptions.ResourceNotFoundException;
import com.example.hotel.mapper.UserMapper;
import com.example.hotel.models.Roles;
import com.example.hotel.models.User;
import com.example.hotel.models.UserDetailsImpl;
import com.example.hotel.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User registerNewUser(UserRegistrationDto userDTO) throws ResourceNotFoundException {
        try {
            return userRepository.save(
                    userMapper.toEntity(userDTO)
                              .setRoleAndPassword(
                                    Roles.ROLE_USER,
                                    bCryptPasswordEncoder.encode(userDTO.getPassword()))
                     );
        } catch (Exception e) {
            log.info("{} Can`t register new User", e.getMessage());
            throw new ResourceNotFoundException("User already exist");
        }
    }

    public User findUserByEmail(String email) throws ResourceNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Can`t find user with email"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException("Could not find user"));
        return new UserDetailsImpl(user);
    }
}
