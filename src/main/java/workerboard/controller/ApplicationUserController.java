package workerboard.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sun.security.krb5.internal.APOptions;
import workerboard.exception.ApplicationNotFound;
import workerboard.exception.ApplicationWrongPassword;
import workerboard.exception.RegistrationNotAddException;
import workerboard.model.ApplicationUser;
import workerboard.model.VievsForApplicationUser;
import workerboard.model.dto.ApplicationUserDto;
import workerboard.model.dto.UserPasswordDto;
import workerboard.serivce.ApplicationUserService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class ApplicationUserController {

    private final ApplicationUserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ApplicationUserController(ApplicationUserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    //    @GetMapping("/{userEmail}")
//    public ResponseEntity<ApplicationUser> getUserByEmail(@PathVariable @NotNull String userEmail) throws ApplicationNotFound {
//
//        if (userService.findUserByEmail(userEmail).isPresent()) {
//            return ResponseEntity.ok(userService.findUserByEmail(userEmail).get());
//        }
//
//        throw new ApplicationNotFound("Not added user");
//    }


    @JsonView(VievsForApplicationUser.Public.class)
    @GetMapping("/{id}")
    public ResponseEntity<ApplicationUser> getUserById(@PathVariable @NotNull Long id) throws ApplicationNotFound {

        System.out.print("Password : "+ bCryptPasswordEncoder.encode("testowe")+ " :koniec");
        return ResponseEntity.ok(userService.findUserById(id));


    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicationUser> updateUser(@RequestBody ApplicationUser newApplicationUser, @PathVariable @NotNull Long id) throws ApplicationNotFound {

        return ResponseEntity.ok(userService.update(id, newApplicationUser));
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<ApplicationUser> updatePassword( @RequestBody UserPasswordDto password, @PathVariable Long id) throws ApplicationWrongPassword, ApplicationNotFound {

        return ResponseEntity.ok(userService.updatePassword(id, password));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable @NotNull Long id) throws ApplicationNotFound {

        return  ResponseEntity.ok(userService.deleteUserById(id));
    }


    @GetMapping("/users")
    @JsonView(VievsForApplicationUser.Public.class)
    public ResponseEntity<List<ApplicationUser>> getAllUsers() throws ApplicationNotFound {

        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/password")
    public ResponseEntity<UserPasswordDto> getPass()
    {
        return ResponseEntity.ok(new UserPasswordDto("testowe","$2a$10$Bb4xCH6nT9QrAKLSTLc7suMyPzRW9mog7eIMfEPGQIEbuAjYvFcmC"));
    }


}
