package workerboard.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import workerboard.exception.NotFound;
import workerboard.exception.UserWrongPassword;
import workerboard.model.ApplicationUser;
import workerboard.model.dto.LoginDto;
import workerboard.model.dto.SignInUserDto;
import workerboard.repository.ApplicationUserRepository;
import workerboard.security.jwt.JwtProvider;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/signin")
public class SignInController {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ApplicationUserRepository applicationUserRepository;


    @PostMapping
    public ResponseEntity<SignInUserDto> loginUser(@RequestBody @Valid LoginDto loginDto) throws UserWrongPassword, NotFound {

        SignInUserDto dto = new SignInUserDto();
        //pull db
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Optional<ApplicationUser> byEmail = applicationUserRepository.findByEmail(loginDto.getEmail());

        if(byEmail.isPresent()) {

            dto.setApplicationUser(byEmail.get());
            dto.setToken(jwtProvider.generateJwtToken(authentication));
            dto.setTokenType("Bearer");
            return ResponseEntity.ok(dto);
        }
        throw new NotFound("User with " + loginDto.getEmail() + " email not found");

    }
}
