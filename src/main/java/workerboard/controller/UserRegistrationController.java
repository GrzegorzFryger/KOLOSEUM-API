package workerboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workerboard.exception.RegistrationNotAddException;
import workerboard.model.dto.RegistrationUser;
import workerboard.serivce.RegistrationUserService;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/registration")
public class UserRegistrationController {


    private RegistrationUserService registrationUserService;

    @Autowired
    public UserRegistrationController(RegistrationUserService registrationUserService) {
        this.registrationUserService = registrationUserService;
    }

    @PostMapping
    public ResponseEntity<Void> registerNewUser(@Valid @RequestBody RegistrationUser registrationUser)
            throws RegistrationNotAddException {


        if (registrationUserService.registrationNewUser(registrationUser)) {
            return ResponseEntity.accepted().build();
        }


        throw new RegistrationNotAddException("Not added user");

    }
}
