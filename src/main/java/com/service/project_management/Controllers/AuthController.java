package com.service.project_management.Controllers;

import com.service.project_management.PayLoad.request.LoginRequest;
import com.service.project_management.PayLoad.request.SignupRequest;
import com.service.project_management.PayLoad.response.JwtResponse;
import com.service.project_management.PayLoad.response.MessageResponse;
import com.service.project_management.models.ERole;
import com.service.project_management.models.Role;
import com.service.project_management.models.User;
import com.service.project_management.Repositories.RoleRepository;
import com.service.project_management.Repositories.UserRepository;
import com.service.project_management.security.jwt.JwtUtils;
import com.service.project_management.security.services.UserDetailsImpl;
import com.sun.xml.bind.v2.runtime.output.Encoded;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

// @CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser( @RequestBody String email) {

    User user = userRepository.getuserpasswordbyEmail(email);

    Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(user.getUsername(), "password"));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt,
                         userDetails.getId(), 
                         userDetails.getUsername(), 
                         userDetails.getEmail(), 
                         roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
  
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }
    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
               signUpRequest.getEmail(),
             encoder.encode(signUpRequest.getPassword())  );

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      throw new RuntimeException("Error: Role is not found.");
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);
            break;

          case "investor":
            Role invRole = roleRepository.findByName(ERole.ROLE_INVESTOR)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(invRole);
            break;

          case "contractor":
            Role conRole = roleRepository.findByName(ERole.ROLE_CONTRACTOR)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(conRole);
            break;
            
          default:
            throw new RuntimeException("Error: Role is not found.");
        }
      });
    }
    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}


