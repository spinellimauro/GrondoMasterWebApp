package com.grondomaster.springjwt.controllers;

import java.util.*;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.grondomaster.springjwt.models.DT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.grondomaster.springjwt.models.ERole;
import com.grondomaster.springjwt.models.Role;
import com.grondomaster.springjwt.payload.request.LoginRequest;
import com.grondomaster.springjwt.payload.request.SignupRequest;
import com.grondomaster.springjwt.payload.response.JwtResponse;
import com.grondomaster.springjwt.payload.response.MessageResponse;
import com.grondomaster.springjwt.repository.RoleRepository;
import com.grondomaster.springjwt.repository.UserRepository;
import com.grondomaster.springjwt.security.jwt.JwtUtils;
import com.grondomaster.springjwt.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
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
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

		DT user = userRepository.findByUsername(loginRequest.getUsername()).orElse(null);

		if(user == null)
			return ResponseEntity.badRequest().body("Usuario no encontrado");

		if(!user.isEnabled())
			return ResponseEntity.badRequest().body("Usuario desactivado");

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		user.setLastLogIn(new Date());

		userRepository.save(user);

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getNombre(),
												userDetails.getApellido(),
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		// Create new user's account
		DT user = new DT(signUpRequest.getNombre(), signUpRequest.getApellido(), signUpRequest.getUsername(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<Role> roles = new HashSet<>();

			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);

		user.setRoles(roles);

		DT userDB = userRepository.save(user);

		return ResponseEntity.ok(userDB);
	}

	@RequestMapping(value = "/get-logged-user", method = RequestMethod.GET)
	public ResponseEntity<?> GetLoggedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return ResponseEntity.ok(authentication.getPrincipal());
	}
}
