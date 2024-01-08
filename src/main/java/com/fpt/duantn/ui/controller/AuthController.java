package com.fpt.duantn.ui.controller;

import com.fpt.duantn.constants.UserRole;
import com.fpt.duantn.exceptions.AuthException;
import com.fpt.duantn.exceptions.UserServiceException;
import com.fpt.duantn.io.entity.Role;
import com.fpt.duantn.io.entity.User;
import com.fpt.duantn.io.repository.RoleRepository;
import com.fpt.duantn.io.repository.UserRepository;
import com.fpt.duantn.security.CustomUserDetails;
import com.fpt.duantn.security.JwtAuthenticationFilter;
import com.fpt.duantn.security.JwtTokenProvider;
import com.fpt.duantn.services.SendMailService;
import com.fpt.duantn.services.impl.UserServiceImpl;
import com.fpt.duantn.ui.model.request.LoginRequest;
import com.fpt.duantn.ui.model.request.RegisterRequest;
import com.fpt.duantn.ui.model.response.LoginResponse;
import com.fpt.duantn.ui.model.response.MailInfo;
import com.fpt.duantn.ui.model.response.RegisterResponse;
import com.fpt.duantn.ui.model.response.base.BaseResponse;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4201","http://localhost:4200"})
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
    @Autowired
    private UserRepository userRepository;

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    SendMailService sendMailService;

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<?>> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = null;
        try {
            // Xác thực từ username và password.
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    BaseResponse.ofFailed(new AuthException("Wrong username or password"), "Wrong username or password", HttpStatus.BAD_REQUEST.toString())
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(BaseResponse.ofFailed(new RuntimeException("Internal server error")));
        }

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return ResponseEntity.ok(BaseResponse.ofSucceeded(new LoginResponse(tokenProvider.getUserIdFromJWT(jwt), principal.getUsername(), jwt)));
    }

    @PostMapping("/register")
    public ResponseEntity<BaseResponse<?>> register(@Valid @RequestBody RegisterRequest loginRequest) {
        Role customerRole = roleRepository.findRoleByName(UserRole.CUSTOMER.toString())
                .orElseThrow(() -> {
                    throw new UserServiceException("There is no role " + UserRole.CUSTOMER.name() + " in database");
                });

        String userName = loginRequest.getUsername();
        String rawPassword = loginRequest.getRawPassword();
        String email = loginRequest.getEmail();

        Optional<User> userOptional = userRepository.findByUsername(userName);

        if (userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseResponse.ofFailed(new AuthException("This username has already existed"), "This username has already existed"));
        }

        Optional<User> userByEmailOptional = userRepository.findByEmail(email);

        if (userByEmailOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseResponse.ofFailed(new AuthException("This email has already registerd"), "This email has already registerd"));
        }

        User user = User.builder()
                .username(userName)
                .password(passwordEncoder.encode(rawPassword))
                .email(email)
                .roles(Set.of(customerRole))
                .build();

        User createdUser = userService.createUser(user);

        return ResponseEntity.ok(BaseResponse.ofSucceeded(
                        RegisterResponse.builder()
                                .userId(createdUser.getId())
                                .username(createdUser.getUsername())
                                .roles(createdUser.getRoles().stream()
                                        .map(Role::getName)
                                        .collect(Collectors.toSet()))
                                .build()
                )
        );
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = ResponseCookie.from("", null).path("/").build();

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(("You've been signed out!"));
    }


    public static String generateRandomPassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new SecureRandom();
        return random.ints(length, 0, characters.length())
                .mapToObj(characters::charAt)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }


    // sendmail
    public void sendMailPassword(String email, String password, String title) throws IOException, MessagingException {
        String body = "<div>\r\n" + "        <h3>Mật khẩu của bạn là: <span style=\"color:red; font-weight: bold;\">"
                + password + "</span></h3>\r\n" + "    </div>";
        sendMailService.send( new MailInfo(email,title,body));
    }
}