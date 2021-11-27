//package com.example.demo.service;
//
//import com.example.demo.dto.request.UserLoginRequest;
//import com.example.demo.dto.request.UserSignUpRequest;
//import com.example.demo.exception.RecordNotFoundException;
//import com.example.demo.exception.UserNotFoundException;
//import com.example.demo.model.AuthRole;
//import com.example.demo.model.EAuthRole;
//import com.example.demo.model.User;
//import com.example.demo.repository.AuthRoleRepository;
//import com.example.demo.repository.UserRepository;
//import com.example.demo.security.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Service
//public class AuthRoleService {
//
//    @Autowired
//    AuthRoleRepository authRoleRepository;
//    UserRepository userRepository;
//    JwtUtil jwtUtil;
//    PasswordEncoder passwordEncoder;
//    AuthenticationManager authenticationManager;
//    public AuthRoleService(AuthRoleRepository authRoleRepository, UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
//        this.authRoleRepository = authRoleRepository;
//        this.userRepository = userRepository;
//        this.jwtUtil = jwtUtil;
//        this.passwordEncoder = passwordEncoder;
//        this.authenticationManager = authenticationManager;
//    }
//
//    public void signUpUser(UserSignUpRequest userSignUpRequest) {
//
//        //check if user exist
//        if(userRepository.existsByUsername(userSignUpRequest.getUsername())) {
//            throw new RecordNotFoundException();
//        }
//
//        if(userRepository.existsByEmail(userSignUpRequest.getEmail())) {
//            throw new RecordNotFoundException();
//        }
//
//        //Create new user and give role
//        User user = new User(userSignUpRequest.getUsername(),
//                userSignUpRequest.getEmail(),
//                passwordEncoder.encode(userSignUpRequest.getPassword()));
//
//        Set<String> setRoles = userSignUpRequest.getAuthRole();
//        Set<AuthRole> authRoles = new HashSet<>();
//
//        if(setRoles== null) {
//            AuthRole userRole = AuthRoleRepository.findByName(EAuthRole.ROLE_USER)
//                    .orElseThrow(() -> new UserNotFoundException("Error: User role is not found."));
//            authRoles.add(userRole);
//        } else setRoles.forEach(role -> {
//            switch (role) {
//                case "admin":
//                    AuthRole adminRole = AuthRoleRepository.findByName(EAuthRole.ROLE_ADMIN)
//                            .orElseThrow(() -> new UserNotFoundException("Error: User role is not found."));
//                    authRoles.add(adminRole);
//                    break;
//                default:
//                    AuthRole userRole = AuthRoleRepository.findByName(EAuthRole.ROLE_USER)
//                            .orElseThrow(() -> new UserNotFoundException("Error: User role is not found."));
//                    authRoles.add(userRole);
//            }
//        });
//
//        user.setAuthRoles(authRoles);
//        userRepository.save(user);
//    }
//
//}
