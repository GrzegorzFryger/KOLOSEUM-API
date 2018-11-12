package workerboard.controller;


import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workerboard.exception.UserNotFound;
import workerboard.exception.UserWrongPassword;
import workerboard.model.ApplicationUser;
import workerboard.model.dto.LoginDto;
import workerboard.model.dto.ViewsForApplicationUser;
import workerboard.serivce.SignInService;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/login")
public class SignInController
{

    private final SignInService signInService;


    @Autowired
    public SignInController(SignInService signInService) {
        this.signInService = signInService;
    }


    @PostMapping
    @JsonView(ViewsForApplicationUser.Basic.class)
    public ResponseEntity<ApplicationUser> loginUser(@RequestBody @Valid LoginDto loginDto) throws UserWrongPassword, UserNotFound {

        return ResponseEntity.ok(signInService.checkUser(loginDto));

    }
}
