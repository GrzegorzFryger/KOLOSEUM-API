package workerboard.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workerboard.exception.ApplicationNotFound;
import workerboard.exception.RegistrationNotAddException;
import workerboard.model.ApplicationUser;
import workerboard.model.dto.RegistrationUser;
import workerboard.model.dto.ViewsForApplicationUser;
import workerboard.serivce.RegistrationUserService;
import workerboard.serivce.mapper.UserRegistrationMapper;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/registration")
public class UserRegistrationController {

    private UserRegistrationMapper registrationMapper;
    private RegistrationUserService registrationUserService;

    public UserRegistrationController(UserRegistrationMapper registrationMapper, RegistrationUserService registrationUserService) {
        this.registrationMapper = registrationMapper;
        this.registrationUserService = registrationUserService;
    }

    @PostMapping
    @JsonView(ViewsForApplicationUser.Basic.class)
    public ResponseEntity<ApplicationUser> registerNewUser(@RequestBody @Valid RegistrationUser registrationUser)
            throws RegistrationNotAddException, ApplicationNotFound {

        return ResponseEntity.ok(this.registrationUserService.registrationNewUser(Optional
                .of(registrationMapper.registrationUserToApplicationUser(registrationUser))
                .orElseThrow(() -> new RegistrationNotAddException("Not add new user"))));

    }
}
