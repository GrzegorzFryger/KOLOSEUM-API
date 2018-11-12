package workerboard.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workerboard.exception.SingUpNotAddException;
import workerboard.exception.UserNotFound;
import workerboard.model.ApplicationUser;
import workerboard.model.dto.RegistrationUserDto;
import workerboard.model.dto.ViewsForApplicationUser;
import workerboard.serivce.SignUpService;
import workerboard.serivce.mapper.SignUpMapper;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/signup")
public class SingUpController {

    private SignUpMapper registrationMapper;
    private SignUpService signUpService;

    public SingUpController(SignUpMapper registrationMapper, SignUpService signUpService) {
        this.registrationMapper = registrationMapper;
        this.signUpService = signUpService;
    }

    @PostMapping
    @JsonView(ViewsForApplicationUser.Basic.class)
    public ResponseEntity<ApplicationUser> registerNewUser(@RequestBody @Valid RegistrationUserDto registrationUserDto)
            throws SingUpNotAddException, UserNotFound {

        return ResponseEntity.ok(this.signUpService.registrationNewUser(Optional
                .of(registrationMapper.registrationUserToApplicationUser(registrationUserDto))
                .orElseThrow(() -> new SingUpNotAddException("Not add new user"))));

    }
}
