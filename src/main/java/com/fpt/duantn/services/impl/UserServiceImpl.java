package com.fpt.duantn.services.impl;

import com.fpt.duantn.exceptions.UserServiceException;
import com.fpt.duantn.io.entity.User;
import com.fpt.duantn.io.repository.UserRepository;
import com.fpt.duantn.security.CustomUserDetails;
import com.fpt.duantn.ui.model.request.RegisterRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(userOptional.get());
    }

    public UserDetails loadUserByID(Long userId) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UserServiceException("No user with id: " + userId);
        }
        return new CustomUserDetails(user.get());
    }

    public User createUser(User user){
        User savedUser = userRepository.save(user);
        return savedUser;
    }
}
