package projet.spring.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import projet.spring.login.model.MyAppUser;
import projet.spring.login.model.MyAppUserRepository;

@RestController
@RequestMapping("/auth")
public class RegistrationController {

    @Autowired
    private MyAppUserRepository myAppUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(value = "/signup", consumes = "application/json")
    public ResponseEntity<MyAppUser> createUser(@RequestBody MyAppUser user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        MyAppUser savedUser = myAppUserRepository.save(user);
        savedUser.setPassword(null);

        return ResponseEntity.ok(savedUser);
    }
}
