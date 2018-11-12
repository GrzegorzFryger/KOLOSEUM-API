package workerboard.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import workerboard.exception.UserNotFound;
import workerboard.exception.UserWrongPassword;
import workerboard.model.dto.LoginDto;
import workerboard.security.jwt.JwtProvider;
import workerboard.security.jwt.model.JwtAuthenticationResponse;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/signin")
public class SignInController {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    AuthenticationManager authenticationManager;


    @PostMapping
    public ResponseEntity<?> loginUser(@RequestBody @Valid LoginDto loginDto) throws UserWrongPassword, UserNotFound {

        //pull db
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));

    }
}
