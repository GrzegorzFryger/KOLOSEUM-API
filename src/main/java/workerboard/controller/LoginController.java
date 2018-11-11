package workerboard.controller;


import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workerboard.exception.ApplicationNotFound;
import workerboard.exception.ApplicationWrongPassword;
import workerboard.model.ApplicationUser;
import workerboard.model.dto.LoginDto;
import workerboard.model.dto.ViewsForApplicationUser;
import workerboard.serivce.LoginService;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/login")
public class LoginController
{

    private final LoginService loginService;


    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @PostMapping
    @JsonView(ViewsForApplicationUser.Public.class)
    public ResponseEntity<ApplicationUser> loginUser(@RequestBody @Valid LoginDto loginDto) throws ApplicationWrongPassword, ApplicationNotFound {

        return ResponseEntity.ok(loginService.checkUser(loginDto));

    }
}
