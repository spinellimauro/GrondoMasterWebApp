package com.grondomaster.springjwt.controllers;

import com.grondomaster.springjwt.models.DT;
import com.grondomaster.springjwt.payload.request.ChangePasswordRequest;
import com.grondomaster.springjwt.repository.RoleRepository;
import com.grondomaster.springjwt.repository.UserRepository;
import com.grondomaster.springjwt.utils.SharedMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController extends SharedMethods {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @RequestMapping(value = {"changePassword"}, method = { RequestMethod.POST })
    public ResponseEntity<?> cambiarPassword(HttpSession session, @RequestBody ChangePasswordRequest changePassword)
    {
        try {

            DT userDB = userRepository.findByUsername(getLoggedUser().getUsername()).orElse(null);

            if (userDB == null)
                return ResponseEntity.badRequest().body("Usuario inexistente");

            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            if (!passwordEncoder.matches(changePassword.getCurrentPassword(), userDB.getPassword()))
                return ResponseEntity.badRequest().body("Contraseña actual incorrecta");

            if (!changePassword.getNewPassword().equals(changePassword.getNewPasswordConfirmed()))
                return ResponseEntity.badRequest().body("No coinciden las contraseñas");

            String hashedPassword = passwordEncoder.encode(changePassword.getNewPassword());

            userDB.setPassword(hashedPassword);
            userRepository.save(userDB);

            session.invalidate();
            return ResponseEntity.ok(changePassword);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
