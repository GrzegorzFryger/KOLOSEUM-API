package workerboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workerboard.model.dto.RegistrationUser;
import workerboard.serivce.RegistrationUserService;

@RestController
@CrossOrigin
@RequestMapping("/api/registration")
public class ApplicationUserRegistration {


    private RegistrationUserService registrationUserService;

    @Autowired
    public ApplicationUserRegistration(RegistrationUserService registrationUserService) {
        this.registrationUserService = registrationUserService;
    }

    @PostMapping
    public ResponseEntity<Void> registerNewUser(@RequestAttribute RegistrationUser registrationUser)
    {



        return registrationUserService.registrationNewUser(registrationUser) ?
                ResponseEntity.accepted().build() : ResponseEntity.badRequest().build();



    }
}
