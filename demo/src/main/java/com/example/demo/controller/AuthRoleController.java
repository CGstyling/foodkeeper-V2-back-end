package com.example.demo.controller;

import com.example.demo.dto.request.UserLoginRequest;
import com.example.demo.dto.request.UserSignUpRequest;
import com.example.demo.dto.response.AuthenticationResponse;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.AuthRole;
import com.example.demo.model.EAuthRole;
import com.example.demo.model.User;
import com.example.demo.model.UserDetailImpl;
import com.example.demo.repository.AuthRoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/foodkeeper")
public class AuthRoleController {

    @Autowired
    AuthRoleRepository authRoleRepository;
    UserRepository userRepository;
    JwtUtil jwtUtil;
    PasswordEncoder passwordEncoder;
    AuthenticationManager authenticationManager;

    public AuthRoleController(AuthRoleRepository authRoleRepository, UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.authRoleRepository = authRoleRepository;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signInUser(@RequestBody UserLoginRequest userLoginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginRequest.getUsername(),
                        userLoginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateJwtToken(authentication);

        UserDetailImpl userDetails = (UserDetailImpl) authentication.getPrincipal();
        List<String> authRoles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new AuthenticationResponse(
                jwt,
                userDetails.getUserId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                authRoles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody UserSignUpRequest userSignUpRequest) {

        if(userRepository.existsByUsername(userSignUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Sorry, this username is already taken");
        }

        if(userRepository.existsByEmail(userSignUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Sorry, this email is already taken");
        }

        User user = new User(
                userSignUpRequest.getUsername(),
                userSignUpRequest.getEmail(),
                passwordEncoder.encode(userSignUpRequest.getPassword()));

        Set<String> setRoles = userSignUpRequest.getAuthRole();
        Set<AuthRole> authRoles = new HashSet<>();

        if(setRoles== null) {
            AuthRole userRole = authRoleRepository.findByRoleName(EAuthRole.ROLE_USER)
                    .orElseThrow(() -> new UserNotFoundException("User is not found"));
            authRoles.add(userRole);
        } else {
            setRoles.forEach(role -> {
                if ("admin".equals(role)) {
                    AuthRole adminRole = authRoleRepository.findByRoleName(EAuthRole.ROLE_ADMIN)
                            .orElseThrow(() -> new UserNotFoundException("User is not found"));
                    authRoles.add(adminRole);
                } else {
                    AuthRole userRole = authRoleRepository.findByRoleName(EAuthRole.ROLE_USER)
                            .orElseThrow(() -> new UserNotFoundException("User is not found"));
                    authRoles.add(userRole);
                }
            });
        }

        user.setAuthRoles(authRoles);
        userRepository.save(user);
        return ResponseEntity.ok("User made an account!");
    }
}
