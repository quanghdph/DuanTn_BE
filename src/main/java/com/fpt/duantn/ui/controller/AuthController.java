package com.fpt.duantn.ui.controller;

import com.fpt.duantn.constants.UserRole;
import com.fpt.duantn.exceptions.UserServiceException;
import com.fpt.duantn.io.entity.Role;
import com.fpt.duantn.io.entity.User;
import com.fpt.duantn.io.repository.RoleRepository;
import com.fpt.duantn.security.CustomUserDetails;
import com.fpt.duantn.security.JwtTokenProvider;
import com.fpt.duantn.services.impl.UserServiceImpl;
import com.fpt.duantn.ui.model.request.LoginRequest;
import com.fpt.duantn.ui.model.request.RegisterRequest;
import com.fpt.duantn.ui.model.response.LoginResponse;
import com.fpt.duantn.ui.model.response.RegisterResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/login")
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return new LoginResponse(tokenProvider.getUserIdFromJWT(jwt), principal.getUsername(), jwt);
    }

    @PostMapping("/register")
    public RegisterResponse register(@Valid @RequestBody RegisterRequest loginRequest) {
        Role customerRole = roleRepository.findRoleByName(UserRole.CUSTOMER.toString())
                .orElseThrow(() -> {
                    throw new UserServiceException("There is no role " + UserRole.CUSTOMER.name() + " in database");
                });

        User user = User.builder()
                .username(loginRequest.getUsername())
                .password(passwordEncoder.encode(loginRequest.getRawPassword()))
                .email(loginRequest.getEmail())
                .roles(Set.of(customerRole))
                .build();

        User createdUser = userService.createUser(user);

        return RegisterResponse.builder()
                .userId(createdUser.getId())
                .username(createdUser.getUsername())
                .roles(createdUser.getRoles().stream()
                        .map(Role::toString)
                        .collect(Collectors.toSet()))
                .build();
    }

}